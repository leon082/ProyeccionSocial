/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.Model.Programa;
import edu.uniajc.proyeccionSocial.Model.ProgramaServicio;
import edu.uniajc.proyeccionSocial.Model.Servicio;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.interfaces.IProgramaServicio;
import edu.uniajc.proyeccionsocial.interfaces.IServicio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@SessionScoped
public class ProgramaServiciosBean {

    private IProgramaServicio programaServicioService;
    private IPrograma programaService;
    private ProgramaServicio programaServicio;
    private List<Programa> listPrograma;
    private List<ProgramaServicio> listProgramaServicio;
    private List<Servicio> listServicio;
    private IServicio servicioServices;

    private Usuario usuario;
    private Servicio servicio;

    //combos
    private ArrayList<SelectItem> itemsProgramas;
    private int programSelected;

    @PostConstruct
    public void init() {

        programaServicioService = new ProgramaServicioServices();
        programaService = new ProgramaServices();
        programaServicio = new ProgramaServicio();
        listPrograma = cargarListProgramas();
        listServicio = new ArrayList<>();
        itemsProgramas = Utilidades.llenar_Combo_Programas(listPrograma);
        usuario = Utilidades.cargarUsuario();
        servicioServices = new ServicioServices();
        
        servicio = new Servicio();

    }
    
    public void limpiarForm(){
        servicio=new Servicio();
        
    }
    
    public void eliminar( int idServicio){
        
        //elimino el servicio
        boolean flag = false;
                if (servicioServices.deleteServicio(idServicio)) {
                    flag = true;   
                }
       //elimino el registro de programa Servicio hay q hacer servicios DAO
       if (programaServicioService.deleteProgramaServicio(programaServicio.getId_programaservicio())) {
                    flag = true;   
                }
            
        if (flag) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El Programa Fue eliminado con exito.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realziar la operación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       

        
    }

    public List<Programa> cargarListProgramas() {
        List<Programa> listProgramas;
        listProgramas = programaService.getAllPrograma();
        return listProgramas;
    }

    public void llenarTablaServicios() {
        listProgramaServicio = programaServicioService.getAllProgramaServicioByPrograma(programSelected);
        listServicio = new ArrayList<>();
        if (listProgramaServicio != null) {
            for (ProgramaServicio obj : listProgramaServicio) {
                listServicio.add(servicioServices.getServicioById(obj.getId_servicio()));
            }
        }

    }

    public void guardar() {
        Servicio ser = servicioServices.getServicioById(servicio.getId_servicio());
        if (ser != null) {
            update();
            
            
        } else {
            crear();
           
        }
        llenarTablaServicios();
        limpiarForm();
    }

    public void crear() {
        //guardar en servicio y en programaServicio
        //guardo el servicio

        servicio.setCreadopor(usuario.getUsuario());
        int result = servicioServices.createServicio(servicio);

        //guardo en programaServicio la relacion de servicio creado con programa seleccionado
        programaServicio.setCreadopor(usuario.getUsuario());
        programaServicio.setId_programa(programSelected);
        programaServicio.setId_servicio(result);
        
        int result2 = programaServicioService.createProgramaServicio(programaServicio);
        if (result != 0 && result2 != 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realziar la operación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void update() {
        boolean result = false;
        boolean result2=false;
        servicio.setModificadopor(usuario.getUsuario());
        result = servicioServices.updateServicio(servicio);
        
        
        programaServicio.setModificadopor(usuario.getUsuario());
        programaServicio.setId_programa(programSelected);
        programaServicio.setId_servicio(servicio.getId_servicio());
        
        
        result2 = programaServicioService.updateProgramaServicio(programaServicio);
        
        if (result && result2) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realziar la operación");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void action(int idServicio) {
        for (Servicio obj : listServicio) {
            if (obj.getId_servicio() == idServicio) {
                servicio = obj;                
            }
        }
    }

    public IProgramaServicio getProgramaServicioService() {
        return programaServicioService;
    }

    public void setProgramaServicioService(IProgramaServicio programaServicioService) {
        this.programaServicioService = programaServicioService;
    }

    public IPrograma getProgramaService() {
        return programaService;
    }

    public void setProgramaService(IPrograma programaService) {
        this.programaService = programaService;
    }

    public ProgramaServicio getProgramaServicio() {
        return programaServicio;
    }

    public void setProgramaServicio(ProgramaServicio programaServicio) {
        this.programaServicio = programaServicio;
    }

    public List<Programa> getListPrograma() {
        return listPrograma;
    }

    public void setListPrograma(List<Programa> listPrograma) {
        this.listPrograma = listPrograma;
    }

    public List<ProgramaServicio> getListProgramaServicio() {
        return listProgramaServicio;
    }

    public void setListProgramaServicio(List<ProgramaServicio> listProgramaServicio) {
        this.listProgramaServicio = listProgramaServicio;
    }

    public List<Servicio> getListServicio() {
        return listServicio;
    }

    public void setListServicio(List<Servicio> listServicio) {
        this.listServicio = listServicio;
    }

    public IServicio getServicioServices() {
        return servicioServices;
    }

    public void setServicioServices(IServicio servicioServices) {
        this.servicioServices = servicioServices;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public ArrayList<SelectItem> getItemsProgramas() {
        return itemsProgramas;
    }

    public void setItemsProgramas(ArrayList<SelectItem> itemsProgramas) {
        this.itemsProgramas = itemsProgramas;
    }

    public int getProgramSelected() {
        return programSelected;
    }

    public void setProgramSelected(int programSelected) {
        this.programSelected = programSelected;
    }

}
