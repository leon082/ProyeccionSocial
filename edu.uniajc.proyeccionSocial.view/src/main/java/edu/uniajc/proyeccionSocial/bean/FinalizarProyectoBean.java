/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Beneficiario;
import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;
import edu.uniajc.proyeccionSocial.persistence.Model.Oferente;
import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;

import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.BeneficiarioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.EtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.OferenteServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProyectoServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IBeneficiario;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOferente;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyecto;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@SessionScoped
public class FinalizarProyectoBean {

    //Proyecto Create
    private Proyecto proyecto;
    private IProyecto servicioProyecto;
    //combo programas
    private ArrayList<SelectItem> itemsProgramas;
    private int idPrograma;
    private IPrograma programaservices;
    //Combo Servicios
    private ArrayList<SelectItem> itemsServicios;
    private int idServicio;

    //Servicios segun el programa
    private IServicio serviciosServ;
    //etapas segun el servicio
    private IEtapa servicioEtapa;
    List<Etapa> etapas;

    //Usuario Session
    private Usuario usuario;
    private IUsuario usuarioServices;
    //Lista de correos para notificacion
    List<String> correos;
    //Emisor
    List<String> emisor;

    //Beneficiarios
    private ITercero terceroServices;
    private IBeneficiario beneficiarioServices;
    private List<Tercero> beneficiarios;

    //Oferentes
    private List<SelectItem> itemsOferente;
    private int idOferente;
    private IOferente oferenteServices;

    private List<Proyecto> proyectosAprobar;
    private boolean showAprobar;
    private boolean showRechazar;

    @PostConstruct
    public void init() {
        showAprobar=true;
        showRechazar=true;
         correos = new ArrayList<>();
        emisor = new ArrayList<>();
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
        etapas = new ArrayList<Etapa>();
        //Usuario
        usuarioServices = new UsuarioServices();
        usuario = Utilidades.cargarUsuario();
        //Beneficiarios
        beneficiarios = new ArrayList<>();
        //Combo Servicios
        itemsServicios = new ArrayList<>();
        terceroServices = new TerceroServices();

        //oferentes
        itemsOferente = Utilidades.llenar_Combo_Terceros(terceroServices.getAllTercero());
        oferenteServices = new OferenteServices();
        beneficiarioServices = new BeneficiarioServices();
        proyectosAprobar = new ArrayList<>();
        proyectosAprobar = servicioProyecto.getAllProyectoAprobado();
    }

    public void llenarBeneficiarios() {
        beneficiarios = new ArrayList<>();
        //Lista de beneficiarios por proyecto
        List<Beneficiario> beneficiarioByProyecto = beneficiarioServices.getAllBeneficiarioByProyect(proyecto.getId_proyecto());
        for (Beneficiario beneficiario : beneficiarioByProyecto) {
            beneficiarios.add(terceroServices.getTerceroById(beneficiario.getId_tercero()));
        }
    }

    public void setProgramaByProyecto() {
        idPrograma = proyecto.getId_programa();
    }

    public void setOferenteByProyecto() {
        Oferente oferente = oferenteServices.getOferenteByProyecto(proyecto.getId_proyecto());
        Tercero tercero = terceroServices.getTerceroById(oferente.getId_tercero());
        idOferente = tercero.getId_tercero();
    }

    public void servByProg() {
        if (idPrograma != 0) {

            //Llenar el combo de Servicios por programa
            itemsServicios = Utilidades.llenar_Combo_ServiciosByPrograma(serviciosServ.getAllServicioByProg(idPrograma));
            idServicio = proyecto.getId_servicio();
        } else {
            itemsServicios = new ArrayList<>();
            idServicio = 0;
        }
    }

    public void llenarEtapasByServicio() {

        //Llenar tabla de etapas
        if (idServicio != 0) {
            etapas = servicioEtapa.getAllEtapaByServicio(idServicio);
        } else {
            etapas = new ArrayList<>();

        }

    }

    public void finalizar() {
        //Se Cancela el proyecto
        proyecto.setEstado(4);
        servicioProyecto.updateProyecto(proyecto);
        correos = new ArrayList<>();
        correos.add(usuarioServices.getEmailByUsername(proyecto.getCreadopor()));
        emisor = Utilidades.findEmailEmisor();
        if (Utilidades.envioCorreo(correos, emisor, usuario, proyecto, 6, "Proyecto Cancelado", 0)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        limpiarForma();
        ocultarBotones();

    }
/*
    public void rechazar() {
        proyecto.setEstado(2);
        servicioProyecto.updateProyecto(proyecto);
        correos = new ArrayList<>();
        correos.add(usuarioServices.getEmailByUsername(proyecto.getCreadopor()));
        emisor = Utilidades.findEmailEmisor();
        if (Utilidades.envioCorreo(correos, emisor, usuario, proyecto, 2, "Proyecto Rechazado", 0)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        limpiarForma();
        ocultarBotones();

    }*/

    public void limpiarForma() {
        proyecto = new Proyecto();
        etapas = new ArrayList<>();
        idPrograma = 0;
        idServicio = 0;
        idOferente = 0;
        proyectosAprobar = servicioProyecto.getAllProyectoAprobado();
        beneficiarios = new ArrayList<>();

    }

    public void actionBoton(Proyecto p) {
        System.out.println("proyecto seleccionado -->" + p);
        proyecto = p;
        /*for(Proyecto p : proyectosAprobar){
        if(p.getId_proyecto()== id){
            proyecto=p;
            break;
        }
    }*/
        setProgramaByProyecto();
        servByProg();
        setOferenteByProyecto();
        llenarEtapasByServicio();
        llenarBeneficiarios();
        mostrarBotones();

    }
    
    public void mostrarBotones(){
        showAprobar=false;
        showRechazar=false;
    }
     public void ocultarBotones(){
        showAprobar=true;
        showRechazar=true;
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

    public ArrayList<SelectItem> getItemsServicios() {
        return itemsServicios;
    }

    public void setItemsServicios(ArrayList<SelectItem> itemsServicios) {
        this.itemsServicios = itemsServicios;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
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

    public List<String> getEmisor() {
        return emisor;
    }

    public void setEmisor(List<String> emisor) {
        this.emisor = emisor;
    }

    public ITercero getTerceroServices() {
        return terceroServices;
    }

    public void setTerceroServices(ITercero terceroServices) {
        this.terceroServices = terceroServices;
    }

    public IBeneficiario getBeneficiarioServices() {
        return beneficiarioServices;
    }

    public void setBeneficiarioServices(IBeneficiario beneficiarioServices) {
        this.beneficiarioServices = beneficiarioServices;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<SelectItem> getItemsOferente() {
        return itemsOferente;
    }

    public void setItemsOferente(List<SelectItem> itemsOferente) {
        this.itemsOferente = itemsOferente;
    }

    public int getIdOferente() {
        return idOferente;
    }

    public void setIdOferente(int idOferente) {
        this.idOferente = idOferente;
    }

    public IOferente getOferenteServices() {
        return oferenteServices;
    }

    public void setOferenteServices(IOferente oferenteServices) {
        this.oferenteServices = oferenteServices;
    }

    public IUsuario getUsuarioServices() {
        return usuarioServices;
    }

    public void setUsuarioServices(IUsuario usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    public List<Tercero> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Tercero> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public List<Proyecto> getProyectosAprobar() {
        return proyectosAprobar;
    }

    public void setProyectosAprobar(List<Proyecto> proyectosAprobar) {
        this.proyectosAprobar = proyectosAprobar;
    }

    public boolean isShowAprobar() {
        return showAprobar;
    }

    public void setShowAprobar(boolean showAprobar) {
        this.showAprobar = showAprobar;
    }

    public boolean isShowRechazar() {
        return showRechazar;
    }

    public void setShowRechazar(boolean showRechazar) {
        this.showRechazar = showRechazar;
    }
    
    
}
