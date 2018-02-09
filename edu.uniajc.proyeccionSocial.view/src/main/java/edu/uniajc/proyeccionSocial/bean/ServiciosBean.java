/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Servicio;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

import edu.uniajc.proyeccionsocial.bussiness.services.ServicioEtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicioEtapa;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@SessionScoped
public class ServiciosBean {

    private List<Servicio> listServicio;
    private IServicio servicioServices;

    private Usuario usuario;
    private Servicio servicio;
    private IServicioEtapa seservices;

    @PostConstruct
    public void init() {

        listServicio = new ArrayList<>();
        usuario = Utilidades.cargarUsuario();
        servicioServices = new ServicioServices(Utilidades.getConnection());
        servicio = new Servicio();
        listServicio=new ArrayList<>();
        listServicio = servicioServices.getAllServicio();
        seservices = new ServicioEtapaServices(Utilidades.getConnection());

    }

    public void limpiarForm() {
        servicio = new Servicio();
        listServicio = servicioServices.getAllServicio();

    }

    public void eliminar(int idServicio) {

        if (!servicioServices.isInProg(idServicio)) {

            boolean flag = false;
            seservices.deleteEtapaServicioByServicio(idServicio);
            flag = servicioServices.deleteServicio(idServicio);
            listServicio = servicioServices.getAllServicio();
            if (flag) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El Servicio Fue eliminado con exito.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realizar la operación");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El servicio esta asociado a un programa");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void guardar() {

        servicio.setCreadopor(usuario.getUsuario());
        int result = servicioServices.createServicio(servicio);

        if (result != 0) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForm();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realizar la operaciónn");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public void update(RowEditEvent event) {

        Object ob = event.getObject();
        Servicio sr = (Servicio) ob;
        sr.setModificadopor(usuario.getUsuario());

        if (servicioServices.updateServicio(sr)) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realziar la operación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Servicio> getListServicio() {
        return listServicio;
    }

    public void setListServicio(List<Servicio> listServicio) {
        this.listServicio = listServicio;
    }

    public IServicio getServicioServices() {
        return servicioServices;
    }

    public void setServicioServices(IServicio servicioServices) {
        this.servicioServices = servicioServices;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
