/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.EtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioEtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicioEtapa;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@ViewScoped
public class EtapaBean {
    //etapaBean

    private IEtapa servicios;
    private IServicioEtapa seservices;

    private Etapa etapa;
    private List<Etapa> listaEtapa;

    //datos user
    private Usuario usuario;

    @PostConstruct
    public void init() {
        servicios = new EtapaServices(Utilidades.getConnection());
        etapa = new Etapa();
        seservices = new ServicioEtapaServices(Utilidades.getConnection());

        usuario = Utilidades.cargarUsuario();
        listaEtapa = servicios.getAllEtapa();
    }

    public void limpiarForma() {
        etapa = new Etapa();
        listaEtapa = servicios.getAllEtapa();
    }

    public void crear() {

        etapa.setCreadopor(usuario.getUsuario());
        int result = servicios.createEtapa(etapa);

        if (result != 0) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForma();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo realizar la operaciónn");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public void modificar(RowEditEvent event) {

        Object ob = event.getObject();
        Etapa et = (Etapa) ob;

        //ln.setCreadoEn(fechaSQL);
        // ln.setModificadoEn(fechaSQL);
        // ln.setCreadoPor("Leon");
        et.setModificadopor(usuario.getUsuario());

        if (servicios.updateEtapa(et)) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "informacion", "No se pudo realziar la operación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void eliminar(int idEtapa) {
        boolean flag = false;
        if (!servicios.isInServ(idEtapa)) {
            for (Etapa etapaEliminar : listaEtapa) {
                if (etapaEliminar.getId_etapa() == idEtapa) {

                    if (servicios.deleteEtapa(idEtapa)) {
                        flag = true;
                        break;

                    }
                }
            }
            listaEtapa = servicios.getAllEtapa();
            if (flag) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "La etapa Fue eliminada con exito.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "informacion", "No se pudo realziar la operación");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "La etapa esta asociada a un Servicio");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public IEtapa getServicios() {
        return servicios;
    }

    public void setServicios(IEtapa servicios) {
        this.servicios = servicios;
    }

    public IServicioEtapa getSeservices() {
        return seservices;
    }

    public void setSeservices(IServicioEtapa seservices) {
        this.seservices = seservices;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public List<Etapa> getListaEtapa() {
        return listaEtapa;
    }

    public void setListaEtapa(List<Etapa> listaEtapa) {
        this.listaEtapa = listaEtapa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
