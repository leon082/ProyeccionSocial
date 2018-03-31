/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@ViewScoped
public class ResetClaveBean {

    private String claveActual;
    private String claveNew1;
    private String claveNew2;
    private String claveDefinitiva;
    private IUsuario usuarioServices;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuarioServices = new UsuarioServices(Utilidades.getConnection());

    }

    public void guardar() {
        if (claveNew1.equals(claveNew2)) {
            claveDefinitiva = claveNew1;
            Usuario user = Utilidades.cargarUsuario();
            usuario = usuarioServices.getUsuarioLogin(user.getUsuario(), claveActual);
            
            if (usuario != null) {
                usuario.setContrasena(claveDefinitiva);
                if (usuarioServices.updateUsuario(usuario)) {
                    clear();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Contraseña cambiada con exito.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    clear();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "informacion", "No se pudo cambiar la contraseña.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }

            } else {
                clear();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "informacion", "Contraseña actual incorrecta");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }else{
            clear();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "informacion", "Contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    
    public void clear(){
        claveActual="";
        claveDefinitiva="";
        claveNew1="";
        claveNew2="";
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getClaveNew1() {
        return claveNew1;
    }

    public void setClaveNew1(String claveNew1) {
        this.claveNew1 = claveNew1;
    }

    public String getClaveNew2() {
        return claveNew2;
    }

    public void setClaveNew2(String claveNew2) {
        this.claveNew2 = claveNew2;
    }

    public String getClaveDefinitiva() {
        return claveDefinitiva;
    }

    public void setClaveDefinitiva(String claveDefinitiva) {
        this.claveDefinitiva = claveDefinitiva;
    }

    public IUsuario getUsuarioServices() {
        return usuarioServices;
    }

    public void setUsuarioServices(IUsuario usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    

}
