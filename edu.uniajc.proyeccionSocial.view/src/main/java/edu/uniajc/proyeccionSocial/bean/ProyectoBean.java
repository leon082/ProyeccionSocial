/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.Model.Etapa;
import edu.uniajc.proyeccionSocial.Model.Proyecto;
import edu.uniajc.proyeccionSocial.Model.Servicio;
import edu.uniajc.proyeccionSocial.Model.Usuario;

import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.EtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProyectoServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.interfaces.IProyecto;
import edu.uniajc.proyeccionsocial.interfaces.IServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@ViewScoped
public class ProyectoBean {

    //Proyecto Create
    private Proyecto proyecto;
    private IProyecto servicioProyecto;
    //combo programas
    private ArrayList<SelectItem> itemsProgramas;
    private int idPrograma;
    private IPrograma programaservices;
    //Servicios segun el programa
    private IServicio serviciosServ;
    //etapas segun el servicio
    private IEtapa servicioEtapa;
    //Lista para mostrar
    List<proyectoRequest> listRequest;
    //Usuario Session
    private Usuario usuario;
    //Lista de correos para notificacion
    List<String> correos;
    //Emisor
    List<String> emisor;

    @PostConstruct
    public void init() {
        //Proyecto create
        proyecto = new Proyecto();
        servicioProyecto = new ProyectoServices();
        //ComboProgramas
        programaservices = new ProgramaServices();
        itemsProgramas = Utilidades.llenar_Combo_Programas(programaservices.getAllPrograma());
        //Servicios segun el programa
        serviciosServ = new ServicioServices();
        //Etapas segun el servicio
        servicioEtapa = new EtapaServices();
        //Lista Para mostrrar
        listRequest = new ArrayList();
        //Usuario
        usuario = Utilidades.cargarUsuario();

    }

    public void actionCombo() {

        listRequest = new ArrayList();
        List<Servicio> listServicio = serviciosServ.getAllServicioByProg(idPrograma);
        for (Servicio objServicio : listServicio) {
            List<Etapa> listEtapa = servicioEtapa.getAllEtapaByServicio(objServicio.getId_servicio());
            for (Etapa objEtapa : listEtapa) {
                listRequest.add(new proyectoRequest(objServicio.getDescripcion(), objEtapa.getDescripcion()));
            }
        }

    }

    public void crear() {

        proyecto.setCreadopor(usuario.getUsuario());
        proyecto.setId_programa(idPrograma);
        int result = servicioProyecto.createProyecto(proyecto);

        if (result != 0) {
            envioCorreo();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForma();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realizar la operaciónn");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public String getTextOfEmail() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario "
                + "ha realizado la creacion de un proyecto para su aprobacion. ";
        text += "Titulo del proyecto: :titulo";

        return text;
    }

    public void envioCorreo() {
        //Lista de correos para notificacion
        correos = Utilidades.findSendEmail();
        //Cuenta emisora
        emisor = Utilidades.findEmailEmisor();

        //Texto
        String text = getTextOfEmail();
        text = text.replace(":usuario", usuario.getUsuario());
        text = text.replace(":titulo", proyecto.getTituloproyecto());

        for (String receptor : correos) {
            Properties properties = new Properties();
            Session session;
            String cuenta = emisor.get(0);
            String password = emisor.get(1);
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", 25);
            properties.put("mail.smtp.mail.sender", cuenta);
            properties.put("mail.smtp.user", cuenta);
            properties.put("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(properties);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
                message.setSubject("Creacion de Proyecto");
                message.setText(text);
                Transport t = session.getTransport("smtp");
                t.connect((String) properties.get("mail.smtp.user"), password);
                t.sendMessage(message, message.getAllRecipients());
                t.close();
            } catch (MessagingException me) {
                System.out.println("Error, no se pudo enviar el correo --> " + me.getMessage());
                return;
            }
        }

    }

    public void limpiarForma() {
        proyecto = new Proyecto();
        listRequest = new ArrayList();
        idPrograma = 0;

    }

    public class proyectoRequest {

        public String servicio;
        public String etapa;

        public proyectoRequest(String servicio, String etapa) {
            this.servicio = servicio;
            this.etapa = etapa;
        }

        public String getServicio() {
            return servicio;
        }

        public void setServicio(String servicio) {
            this.servicio = servicio;
        }

        public String getEtapa() {
            return etapa;
        }

        public void setEtapa(String etapa) {
            this.etapa = etapa;
        }

    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public IProyecto getServicioProyecto() {
        return servicioProyecto;
    }

    public void setServicioProyecto(IProyecto servicioProyecto) {
        this.servicioProyecto = servicioProyecto;
    }

    public ArrayList<SelectItem> getItemsProgramas() {
        return itemsProgramas;
    }

    public void setItemsProgramas(ArrayList<SelectItem> itemsProgramas) {
        this.itemsProgramas = itemsProgramas;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public IPrograma getProgramaservices() {
        return programaservices;
    }

    public void setProgramaservices(IPrograma programaservices) {
        this.programaservices = programaservices;
    }

    public IServicio getServiciosServ() {
        return serviciosServ;
    }

    public void setServiciosServ(IServicio serviciosServ) {
        this.serviciosServ = serviciosServ;
    }

    public IEtapa getServicioEtapa() {
        return servicioEtapa;
    }

    public void setServicioEtapa(IEtapa servicioEtapa) {
        this.servicioEtapa = servicioEtapa;
    }

    public List<proyectoRequest> getListRequest() {
        return listRequest;
    }

    public void setListRequest(List<proyectoRequest> listRequest) {
        this.listRequest = listRequest;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getCorreos() {
        return correos;
    }

    public void setCorreos(List<String> correos) {
        this.correos = correos;
    }

}
