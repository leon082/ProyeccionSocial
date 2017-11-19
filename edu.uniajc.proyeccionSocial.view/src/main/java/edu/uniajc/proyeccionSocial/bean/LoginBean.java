/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.view.util.SessionUtils;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
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
    // private boolean logeado = false;

    /*  public boolean estaLogeado() {
        return logeado;
    }*/
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

    public String login() {
System.out.println("Entro a Login----$$$$$$$");
        if (nombre != null && nombre.equals("admin") && clave != null
                && clave.equals("admin")) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", nombre);

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saludo", "Bienvenido");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "index.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales no validas");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login.xhtml";
        }
    }

    //logout event, invalidate session
    public String logout() {
        System.out.println("Entro a Logout----$$$$$$$");
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login.xhtml";
    }

}
