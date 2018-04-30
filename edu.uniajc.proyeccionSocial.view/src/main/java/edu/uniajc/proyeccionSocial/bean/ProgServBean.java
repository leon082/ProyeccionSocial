/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.ProgramaServicio;
import edu.uniajc.proyeccionSocial.persistence.Model.Servicio;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DualListModel;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProgramaServicio;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IPrograma;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@ViewScoped
public class ProgServBean {

    private DualListModel<Servicio> servicios;
    private IServicio servicioServices;
    private IPrograma programaservices;
    private IProgramaServicio psServices;
    private List<Servicio> servSource;
    private List<Servicio> servTarget;
    //combo
    private ArrayList<SelectItem> itemsProgramas;
    private int idPrograma;

    private Usuario usuario;

    @PostConstruct
    public void init() {
        servicioServices = new ServicioServices(Utilidades.getConnection());
        psServices = new ProgramaServicioServices(Utilidades.getConnection());
        programaservices = new ProgramaServices(Utilidades.getConnection());
        servSource = new ArrayList<Servicio>();
        servSource = servicioServices.getAllServicio();
        usuario = Utilidades.cargarUsuario();
        servTarget = new ArrayList<Servicio>();
        servicios = new DualListModel<Servicio>(servSource, servTarget);
        itemsProgramas = Utilidades.llenar_Combo_Programas(programaservices.getAllPrograma());

    }

    public void guardar() {

        if (idPrograma != 0) {

            //borro todos
            psServices.deleteProgramaServicioByProg(idPrograma);
            //creo todos
            for (Object obj : servicios.getTarget()) {

                String servicio = (String) obj;
               

                ProgramaServicio crear = new ProgramaServicio();

                crear.setCreadopor(usuario.getUsuario());
                crear.setEstado(1);
                crear.setId_programa(idPrograma);
                crear.setId_servicio(Integer.valueOf(servicio));

                psServices.createProgramaServicio(crear);
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Servicios Asignados");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            actionCombo();
        }
    }

    public void actionCombo() {
        if (idPrograma != 0) {
            servTarget = new ArrayList<Servicio>();
            servSource = new ArrayList<Servicio>();
            servSource = servicioServices.getAllServicio();
            servTarget = servicioServices.getAllServicioByProg(idPrograma);

            for (int i = 0; i < servSource.size(); i++) {

                Servicio source = servSource.get(i);

                for (int j = 0; j < servTarget.size(); j++) {

                    Servicio targed = servTarget.get(j);

                    if (source.getId_servicio() == targed.getId_servicio()) {

                        servSource.remove(source);
                    }
                }

            }
            servicios = new DualListModel<Servicio>(servSource, servTarget);
        } else {
            init();
        }

    }

    public DualListModel<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(DualListModel<Servicio> servicios) {
        this.servicios = servicios;
    }

    public IServicio getServicioServices() {
        return servicioServices;
    }

    public void setServicioServices(IServicio servicioServices) {
        this.servicioServices = servicioServices;
    }

    public IPrograma getProgramaservices() {
        return programaservices;
    }

    public void setProgramaservices(IPrograma programaservices) {
        this.programaservices = programaservices;
    }

    public IProgramaServicio getPsServices() {
        return psServices;
    }

    public void setPsServices(IProgramaServicio psServices) {
        this.psServices = psServices;
    }

    public List<Servicio> getServSource() {
        return servSource;
    }

    public void setServSource(List<Servicio> servSource) {
        this.servSource = servSource;
    }

    public List<Servicio> getServTarget() {
        return servTarget;
    }

    public void setServTarget(List<Servicio> servTarget) {
        this.servTarget = servTarget;
    }

    public ArrayList<SelectItem> getItemsProgramas() {
        return itemsProgramas;
    }

    public void setItemsProgramas(ArrayList<SelectItem> itemsProgramas) {
        this.itemsProgramas = itemsProgramas;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
