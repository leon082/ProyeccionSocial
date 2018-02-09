/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.SessionUtils;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.MenuServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOpciones_menu;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
    private List<Opciones_menu> listaModulos;
    // private boolean logeado = false;

    /*  public boolean estaLogeado() {
        return logeado;
    }*/
    @PostConstruct
    public void init() {

        usuarioServices = new UsuarioServices();
        menuServices = new MenuServices();
        listaModulos=new ArrayList<>();

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
        try {
            user = usuarioServices.getUsuarioLogin(nombre, Utilidades.generateHash(clave));
            
            if (user != null) {
                listaModulos = menuServices.getMenuByUser(user);
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", nombre);

              
                return "Home.xhtml";
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales no validas");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "login.xhtml";
            }
        } catch (NoSuchAlgorithmException | RuntimeException e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Comuniquese con el Administrador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login.xhtml";
        }
    }

    public String logout() {

        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login.xhtml";
    }

    public void logoutTest() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/home.xhtml");
    }

    public String registrar() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saludo", "Formualrio de Registro");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "registrar.xhtml";
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

    public List<Opciones_menu> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Opciones_menu> listaModulos) {
        this.listaModulos = listaModulos;
    }
    
    

}
