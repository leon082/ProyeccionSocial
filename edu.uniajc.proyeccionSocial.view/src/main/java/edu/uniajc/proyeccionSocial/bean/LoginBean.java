/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.view.util.SessionUtils;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEnvioCorreo;
import edu.uniajc.proyeccionsocial.bussiness.services.MenuServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOpciones_menu;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.services.EnvioCorreoServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String nombre;
    private String clave;
    private IUsuario usuarioServices;
    private IOpciones_menu menuServices;
    private Usuario user;
    private List<Opciones_menu> listaModuloCuenta;
    private List<Opciones_menu> listaModuloParametrizar;
    private List<Opciones_menu> listaModuloUsuarios;
    private List<Opciones_menu> listaModuloProyectos;
    private ITercero terceroService;
    Connection connection;
    HttpSession session;
    private IEnvioCorreo envioCorreoServices;
    // private boolean logeado = false;

    /*  public boolean estaLogeado() {
        return logeado;
    }*/
    @PostConstruct
    public void init() {

        connection = new ConexionBD().getConnection();
        session = SessionUtils.getSession();
        session.setAttribute("connection", connection);
        usuarioServices = new UsuarioServices(connection);
        menuServices = new MenuServices(connection);
        terceroService = new TerceroServices(connection);
        envioCorreoServices = new EnvioCorreoServices();
        listaModuloCuenta = new ArrayList<>();
        listaModuloParametrizar= new ArrayList<>();
        listaModuloProyectos= new ArrayList<>();
        listaModuloUsuarios= new ArrayList<>();

    }

    /*public String login() {

        if (nombre != null && nombre.equals("admin") && clave != null
                && clave.equals("admin")) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", nombre);

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saludo", "Bienvenido");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "Home.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales no validas");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login.xhtml";
        }
    }*/
    public String login() {
        if (nombre != null && !nombre.equalsIgnoreCase("") && clave != null && !clave.equalsIgnoreCase("")) {
            try {

                user = usuarioServices.getUsuarioLogin(nombre, clave);

                if (user != null) {
                    listaModuloCuenta = menuServices.getMenuCuentaByUser(user);
                    listaModuloParametrizar = menuServices.getMenuParametrizarByUser(user);
                    listaModuloProyectos = menuServices.getMenuProyectosByUser(user);
                    listaModuloUsuarios = menuServices.getMenuUsuariosByUser(user);
                    
                            
                            
                    session.setAttribute("username", nombre);

                    return "inicio.xhtml";
                } else {
                    try {
                        Utilidades.getConnection().close();
                    } catch (SQLException e) {

                    }
                    session.invalidate();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales no validas");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return "login.xhtml";
                }
            } catch (Exception e) {
                e.printStackTrace();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Comuniquese con el Administrador");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "login.xhtml";
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Diligencie los campos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login.xhtml";
        }

    }

    public String logout() {

        try {
            Utilidades.getConnection().close();
        } catch (SQLException e) {

        }

        session.invalidate();
        return "login.xhtml";
    }

    public void logoutTest() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/home.xhtml");
    }

    public String registrar() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Formualrio de Registro");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "registrar.xhtml";
    }

    public void recuperar() {

        if (nombre != null && !nombre.equalsIgnoreCase("")) {
            envioCorreo(1);
            envioCorreo(0);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Revise su bandeja de correó");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe digitar un usuario");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void envioCorreo(int tipoCorreo) {
        if (tipoCorreo != 0) {
            Usuario usuario = usuarioServices.getUserByUsername(nombre);
            envioCorreoServices.envioCorreo(Utilidades.findSendEmail(), Utilidades.findEmailEmisor(), usuario, null, 7, "Recuperar Contraseña", 0);

        } else {
            Usuario usuario = usuarioServices.getUserByUsername(nombre);
            Tercero tercero = terceroService.getTerceroById(usuario.getId_tercero());
            List<String> destino = new ArrayList<>();
            destino.add(tercero.getCorreo());
            envioCorreoServices.envioCorreo(destino, Utilidades.findEmailEmisor(), usuario, null, 8, "Recuperar Contraseña", 0);
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public IUsuario getUsuarioServices() {
        return usuarioServices;
    }

    public void setUsuarioServices(IUsuario usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Opciones_menu> getListaModuloCuenta() {
        return listaModuloCuenta;
    }

    public void setListaModuloCuenta(List<Opciones_menu> listaModuloCuenta) {
        this.listaModuloCuenta = listaModuloCuenta;
    }

    public List<Opciones_menu> getListaModuloParametrizar() {
        return listaModuloParametrizar;
    }

    public void setListaModuloParametrizar(List<Opciones_menu> listaModuloParametrizar) {
        this.listaModuloParametrizar = listaModuloParametrizar;
    }

    public List<Opciones_menu> getListaModuloUsuarios() {
        return listaModuloUsuarios;
    }

    public void setListaModuloUsuarios(List<Opciones_menu> listaModuloUsuarios) {
        this.listaModuloUsuarios = listaModuloUsuarios;
    }

    public List<Opciones_menu> getListaModuloProyectos() {
        return listaModuloProyectos;
    }

    public void setListaModuloProyectos(List<Opciones_menu> listaModuloProyectos) {
        this.listaModuloProyectos = listaModuloProyectos;
    }

  

}
