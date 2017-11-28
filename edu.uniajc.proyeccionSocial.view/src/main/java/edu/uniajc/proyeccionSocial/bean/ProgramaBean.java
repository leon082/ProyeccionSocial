/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.Model.Programa;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.SessionUtils;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.interfaces.IPrograma;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import edu.uniajc.proyeccionsocial.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;


/**
 *
 * @author luis.leon
 */
@ManagedBean
@SessionScoped
public class ProgramaBean {

    private IPrograma servicios;
    private Programa programa;
    private List<Programa> listaPrograma;
    
    //datos user
      private IUsuario usuarioServices;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        servicios = new ProgramaServices();
        programa = new Programa();
        usuarioServices = new UsuarioServices();
        usuario = cargarUsuario();
        listaPrograma = servicios.getAllPrograma();
    }

    public void limpiarForma() {
        programa = new Programa();
        listaPrograma = servicios.getAllPrograma();
    }
    
     public Usuario cargarUsuario() {
        HttpSession session = SessionUtils.getSession();
        String user = (String) session.getAttribute("username");
        Usuario us = usuarioServices.getUserByUsername(user);
        return us;
    }

    public void crear() {

        programa.setCreadopor(usuario.getUsuario());
        int result = servicios.createPrograma(programa);

        if (result != 0) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            limpiarForma();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realizar la operaciónn");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }
    
     public void modificar(RowEditEvent event) {

        Object ob = event.getObject();
        Programa pr = (Programa) ob;
         
        //ln.setCreadoEn(fechaSQL);
       // ln.setModificadoEn(fechaSQL);
       // ln.setCreadoPor("Leon");
        pr.setModificadopor(usuario.getUsuario());

        if (servicios.updatePrograma(pr)) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realziar la operación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
     
       public void eliminar(int idPrograma) {
        boolean flag = false;
        for (Programa programaEliminar : listaPrograma) {
            if (programaEliminar.getId_programa()== idPrograma) {
                
                if (servicios.deletePrograma(idPrograma)) {
                    flag = true;
                    break;

                }
            }
        }
        listaPrograma = servicios.getAllPrograma();
        if (flag) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El Programa Fue eliminado con exito.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realziar la operación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public IPrograma getServicios() {
        return servicios;
    }

    public void setServicios(IPrograma servicios) {
        this.servicios = servicios;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public List<Programa> getListaPrograma() {
        return listaPrograma;
    }

    public void setListaPrograma(List<Programa> listaPrograma) {
        this.listaPrograma = listaPrograma;
    }

}
