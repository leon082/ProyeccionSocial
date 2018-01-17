/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.Model.Tercero;
import edu.uniajc.proyeccionSocial.Model.Usuario;

import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.interfaces.ITercero;

import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@ViewScoped
public class TerceroBean {

    private ITercero terceroServices;
    private Tercero tercero;

    private Usuario usuario;
    private Date fecha;

    //Combos
    private ArrayList<SelectItem> itemsDocumentos;
    private int docuSelected;

    @PostConstruct
    public void init() {
        terceroServices = new TerceroServices();
        tercero = new Tercero();
        usuario = Utilidades.cargarUsuario();
        itemsDocumentos = Utilidades.Consultar_Documentos_combo();
    }

    public void limpiarForma() {
        tercero = new Tercero();

    }

    public void actionButon() {
        if (validaciones()) {

            if (!Utilidades.validarTercero(docuSelected, tercero.getNumidentificacion())) {
                tercero.setId_lv_tipoidentificacion(docuSelected);
                tercero.setFechanacimiento(Utilidades.dateToSql(fecha));
                tercero.setCreadopor(usuario.getUsuario());

                if (Utilidades.validarCorreo(tercero.getCorreo())) {
                    terceroServices.createTercero(tercero);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Persona Creada");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    limpiarForma();

                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Correo No Valido");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                }

            } else if (Utilidades.validarTercero(docuSelected, tercero.getNumidentificacion())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Persona ya Existe");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
        }

    }

    public boolean validaciones() {
        boolean result = true;

        if (!Utilidades.validarFechaNacimiento(fecha)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error, Fecha de Nacimiento debe ser anterior a la fecha actual");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return false;
        }

        return result;

    }

    public ITercero getTerceroServices() {
        return terceroServices;
    }

    public void setTerceroServices(ITercero terceroServices) {
        this.terceroServices = terceroServices;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<SelectItem> getItemsDocumentos() {
        return itemsDocumentos;
    }

    public void setItemsDocumentos(ArrayList<SelectItem> itemsDocumentos) {
        this.itemsDocumentos = itemsDocumentos;
    }

    public int getDocuSelected() {
        return docuSelected;
    }

    public void setDocuSelected(int docuSelected) {
        this.docuSelected = docuSelected;
    }

}
