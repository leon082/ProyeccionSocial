/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.Model.Tercero;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.SessionUtils;

import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@ViewScoped
public class EditarCuentaBean {

    private TerceroServices terceroServices;
    private Tercero tercero;
    private UsuarioServices usuarioServices;
    private Usuario usuario;
    private String contra;
    private Date fecha;

    //Combos
    private ArrayList<SelectItem> itemsDocumentos;
    private int docuSelected;

    @PostConstruct
    public void init() {
        terceroServices = new TerceroServices();
        usuarioServices = new UsuarioServices();
        usuario = cargarUsuario();
        tercero = cargarTercero();
        itemsDocumentos = Utilidades.Consultar_Documentos_combo();
        docuSelected=tercero.getId_lv_tipoidentificacion();
        fecha=tercero.getFechanacimiento();
    }

    public Usuario cargarUsuario() {
        HttpSession session = SessionUtils.getSession();
        String user = (String) session.getAttribute("username");
        Usuario us = usuarioServices.getUserByUsername(user);
        return us;
    }

    public Tercero cargarTercero() {
        Tercero ter = terceroServices.getTerceroById(this.usuario.getId_tercero());
        return ter;
    }

    public boolean guardar() {
        boolean result = false;

        try {
            tercero.setFechanacimiento(Utilidades.dateToSql(fecha));
            tercero.setCreadopor("system");
            tercero.setModificadopor("system");
            usuario.setContrasena(Utilidades.generateHash(contra));
        } catch (NoSuchAlgorithmException | RuntimeException e) {
            result = false;
        }
        if (Utilidades.validarCorreo(tercero.getCorreo())
                && terceroServices.updateTercero(tercero)
                && usuarioServices.updateUsuario(usuario)) {

            result = true;

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Correo No Valido");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            result = false;
        }

        return result;
    }

    public String actionButon() {
        if (valdiaciones()) {

            if (guardar()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cuenta actualizada");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "Home.xhtml";
            } else {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error, intentelo de nuevo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "editar_cuenta.xhtml";
            }

        }
        return "editar_cuenta.xhtml";
    }

    public boolean valdiaciones() {
        boolean result = true;

        if (!Utilidades.validarFechaNacimiento(fecha)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error, Fecha de Nacimiento debe ser anterior a la fecha actual");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return false;
        }

        return result;

    }

    public TerceroServices getTerceroServices() {
        return terceroServices;
    }

    public void setTerceroServices(TerceroServices terceroServices) {
        this.terceroServices = terceroServices;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public UsuarioServices getUsuarioServices() {
        return usuarioServices;
    }

    public void setUsuarioServices(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
