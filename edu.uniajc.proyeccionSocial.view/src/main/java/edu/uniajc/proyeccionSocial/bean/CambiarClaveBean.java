/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEnvioCorreo;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.services.EnvioCorreoServices;
import java.util.ArrayList;
import java.util.List;
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
public class CambiarClaveBean {

    private ITercero terceroServices;
    private Tercero tercero;
    private IUsuario usuarioServices;
    private Usuario usuario;
    private String name;
    private String clave;
    boolean estadoBoton;
    private IEnvioCorreo envioCorreoServices;

    //Combos
    private ArrayList<SelectItem> itemsDocumentos;
    private int docuSelected;

    @PostConstruct
    public void init() {
        terceroServices = new TerceroServices(Utilidades.getConnection());
        usuarioServices = new UsuarioServices(Utilidades.getConnection());
        usuario = new Usuario();
        tercero = new Tercero();
        itemsDocumentos = Utilidades.Consultar_Documentos_combo();
        docuSelected = 0;
        estadoBoton = true;
        envioCorreoServices = new EnvioCorreoServices();
        envioCorreoServices.init();
        name="";
        clave="";
        

    }

    public void buscar() {
        //

        usuario = usuarioServices.getUserByUsername(name);

        if (usuario == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Usuario no encontrado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            tercero = terceroServices.getTerceroById(usuario.getId_tercero());
            System.out.println("Correo--->" + tercero.getCorreo());
            docuSelected = tercero.getId_lv_tipoidentificacion();
            estadoBoton = false;
        }

    }

    public boolean guardar() {
        boolean result = false;
        System.out.println("Clave--->" + clave);
        if (!clave.equals("") && usuario != null) {

            usuario.setContrasena(clave);

            if (usuarioServices.updateUsuario(usuario)) {

                result = true;

            }
        }

        return result;
    }

    public void enviarCorreo() {

        List<String> destino = new ArrayList<>();
        destino.add(tercero.getCorreo());
        envioCorreoServices.envioCorreo(destino, Utilidades.findEmailEmisor(), usuario, null, 9, "Cambio de Contraseña," + clave, 0);
    }

    public void actionButon() {

        if (guardar()) {
            enviarCorreo();
            init();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Clave Cambiada");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error, intentelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

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

    public boolean isEstadoBoton() {
        return estadoBoton;
    }

    public void setEstadoBoton(boolean estadoBoton) {
        this.estadoBoton = estadoBoton;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
