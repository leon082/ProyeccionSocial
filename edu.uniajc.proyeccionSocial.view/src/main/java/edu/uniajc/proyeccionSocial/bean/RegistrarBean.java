/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;

import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuarioRol;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@ViewScoped
public class RegistrarBean {

    private ITercero terceroServices;
    private Tercero tercero;
    private IUsuario usuarioServices;
    private Usuario usuario;
    private int idTercero;
    private int idUsuario;
    private String username;
    private String contra;
    private Date fecha;
    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(AprobarDocumentosBean.class.getName());

    //Combos
    private ArrayList<SelectItem> itemsDocumentos;
    private int docuSelected;
    private IUsuarioRol usuarioRolServices;

    @PostConstruct
    public void init() {

        tercero = new Tercero();

        usuario = new Usuario();
        itemsDocumentos = Utilidades.Consultar_Documentos_combo();
    }

    public boolean registrar() {
        connection = new ConexionBD().getConnection();
        terceroServices = new TerceroServices(connection);
        usuarioServices = new UsuarioServices(connection);
        boolean result = false;
        if (!Utilidades.validarTercero(docuSelected, tercero.getNumidentificacion())
                && !Utilidades.validarUsuario(username)) {
            tercero.setId_lv_tipoidentificacion(docuSelected);
            tercero.setFechanacimiento(Utilidades.dateToSql(fecha));
            tercero.setCreadopor("system");

            if (Utilidades.validarCorreo(tercero.getCorreo())) {
                idTercero = terceroServices.createTercero(tercero);
                if (idTercero != 0) {

                    usuario.setId_tercero(idTercero);

                    usuario.setUsuario(username);
                    usuario.setContrasena(contra);

                    idUsuario = usuarioServices.createUsuario(usuario);
                    if (idUsuario != 0) {
                        usuario.setId_usuario(idUsuario);
                        Utilidades.asignarRolCreador(usuario);
                        result = true;
                    }

                }
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Correo No Valido");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                result = false;
            }

        } else if (Utilidades.validarTercero(docuSelected, tercero.getNumidentificacion())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Persona ya Existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            result = false;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Ya existe una persona con ese Usuario, Favor intente otro Usuario");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            result = false;
        }

        return result;
    }

    public void cerrarConeccion() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error en RegistrarBean cerrarConeccion "+e.getMessage());
        }
    }

    public String actionButon() {
        if (valdiaciones()) {

            if (registrar()) {

                cerrarConeccion();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario Creado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "login.xhtml";
            } else {
                terceroServices.deleteTercero(idTercero);
                usuarioServices.deleteUsuario(idUsuario);
                cerrarConeccion();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error, intentelo de nuevo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "registrar.xhtml";
            }

        }
        return "registrar.xhtml";
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

    public void setTerceroServices(TerceroServices terceroServices) {
        this.terceroServices = terceroServices;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
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

    public int getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(int idTercero) {
        this.idTercero = idTercero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public ITercero getTerceroServices() {
        return terceroServices;
    }

    public void setTerceroServices(ITercero terceroServices) {
        this.terceroServices = terceroServices;
    }

    public IUsuario getUsuarioServices() {
        return usuarioServices;
    }

    public void setUsuarioServices(IUsuario usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

}
