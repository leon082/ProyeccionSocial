/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Rol;
import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.persistence.Model.UsuarioRol;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.RolServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioRolServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IRol;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuarioRol;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@SessionScoped
public class AsignarRolBean {

    Usuario usuario;
    Usuario userSession;
    Tercero tercero;
    IUsuario servicioUsuario;
    ITercero servicioTercero;
    IUsuarioRol servicioUserRol;
    IRol servicioRoles;
    String userName;
    List<Rol> rolesSource;
    List<Rol> rolesTarget;
    boolean bloquearRoles;
    private DualListModel<Rol> roles;
    boolean bloquearGuardar;

    @PostConstruct
    public void init() {
        userName="";
        servicioUserRol = new UsuarioRolServices(Utilidades.getConnection());
        usuario = new Usuario();
        tercero = new Tercero();
        userSession = Utilidades.cargarUsuario();
        servicioUsuario = new UsuarioServices(Utilidades.getConnection());
        servicioTercero = new TerceroServices(Utilidades.getConnection());
        servicioRoles = new RolServices(Utilidades.getConnection());
        rolesTarget = new ArrayList<Rol>();
         rolesSource= new ArrayList<Rol>();
        iniciarRoles();
        bloquearRoles = true;
        bloquearGuardar = true;
    }

    public void iniciarRoles() {
        rolesSource = servicioRoles.getAllRol();
        rolesTarget = new ArrayList<Rol>();
        
        roles = new DualListModel<Rol>(rolesSource, rolesTarget);
    }

    public void cargarRoles() {
        rolesSource = servicioRoles.getAllRol();
        rolesTarget = servicioRoles.getRolesByUser(usuario.getId_usuario());
         for (int i = 0; i < rolesSource.size(); i++) {

                Rol source = rolesSource.get(i);

                for (int j = 0; j < rolesTarget.size(); j++) {

                    Rol targed = rolesTarget.get(j);

                    if (source.getId_rol()== targed.getId_rol()) {

                        rolesSource.remove(source);
                    }
                }

            }
        roles = new DualListModel<Rol>(rolesSource, rolesTarget);
    }

    public void buscar() {
        usuario = servicioUsuario.getUserByUsername(userName.toLowerCase().trim());
        if (usuario != null) {
            tercero = servicioTercero.getTerceroById(usuario.getId_tercero());
            cargarRoles();
            bloquearRoles = false;
            bloquearGuardar = false;

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            init();
        }
    }

    public void guardar() {

        //borro todos
        servicioUserRol.deleteRolesByUser(usuario.getId_usuario());
        //creo todos
        for (Object obj : roles.getTarget()) {

            String rol = (String) obj;
            System.out.println("Rol" + rol);

           
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setId_usuario(usuario.getId_usuario());
            usuarioRol.setId_rol(Integer.valueOf(rol));
            usuarioRol.setCreadopor(userSession.getUsuario());
            usuarioRol.setEstado(1);

            servicioUserRol.createUsuarioRol(usuarioRol);

        }
        init();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public IUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public ITercero getServicioTercero() {
        return servicioTercero;
    }

    public void setServicioTercero(ITercero servicioTercero) {
        this.servicioTercero = servicioTercero;
    }

    public IRol getServicioRoles() {
        return servicioRoles;
    }

    public void setServicioRoles(IRol servicioRoles) {
        this.servicioRoles = servicioRoles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Rol> getRolesSource() {
        return rolesSource;
    }

    public void setRolesSource(List<Rol> rolesSource) {
        this.rolesSource = rolesSource;
    }

    public List<Rol> getRolesTarget() {
        return rolesTarget;
    }

    public void setRolesTarget(List<Rol> rolesTarget) {
        this.rolesTarget = rolesTarget;
    }

    public boolean isBloquearRoles() {
        return bloquearRoles;
    }

    public void setBloquearRoles(boolean bloquearRoles) {
        this.bloquearRoles = bloquearRoles;
    }

    public DualListModel<Rol> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<Rol> roles) {
        this.roles = roles;
    }

    public IUsuarioRol getServicioUserRol() {
        return servicioUserRol;
    }

    public void setServicioUserRol(IUsuarioRol servicioUserRol) {
        this.servicioUserRol = servicioUserRol;
    }

    public boolean isBloquearGuardar() {
        return bloquearGuardar;
    }

    public void setBloquearGuardar(boolean bloquearGuardar) {
        this.bloquearGuardar = bloquearGuardar;
    }

}
