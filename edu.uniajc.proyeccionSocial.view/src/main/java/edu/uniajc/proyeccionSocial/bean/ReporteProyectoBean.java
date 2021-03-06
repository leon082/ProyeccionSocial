/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.ReporteProyecto;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ReporteProyectoServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IReporteProyecto;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@ViewScoped
public class ReporteProyectoBean {
//Parametros

    int idPrograma;
    int idServicio;
    int idTerceroOferente;
    int idTerceroCreadoPor;
    Date fechaDesde;
    Date fechaHasta;
    int estado;
    int facultad;

    //tabla
    List<ReporteProyecto> listaReporte;

    //Combos
    private ArrayList<SelectItem> programas;
    private ArrayList<SelectItem> servicios;
    private ArrayList<SelectItem> tercerosOferentes;
    private ArrayList<SelectItem> tercerosUsuarios;
    private ArrayList<SelectItem> itemsFacultad;

    //Services
    IPrograma progServices;
    IServicio servServices;
    ITercero terceroServices;
    IReporteProyecto reporteServices;

    @PostConstruct
    public void init() {

        listaReporte = new ArrayList<>();
        reporteServices = new ReporteProyectoServices(Utilidades.getConnection());
        progServices = new ProgramaServices(Utilidades.getConnection());
        servServices = new ServicioServices(Utilidades.getConnection());
        terceroServices = new TerceroServices(Utilidades.getConnection());
        servicios = new ArrayList<SelectItem>();
        tercerosOferentes = Utilidades.llenar_Combo_Terceros(terceroServices.getAllTercero());
        programas = Utilidades.llenar_Combo_Programas(progServices.getAllPrograma());
        tercerosUsuarios = Utilidades.llenar_Combo_TerceroUsuarios(terceroServices.getAllTerceroUsuario());
        itemsFacultad = Utilidades.Consultar_Facultades_combo();
        fechaDesde = null;
        fechaHasta = null;
        estado = -1;

    }

    public void actionComboProgramas() {
        if (idPrograma != 0) {

            //Llenar el combo de Servicios por programa
            idServicio = -1;
            servicios = Utilidades.llenar_Combo_ServiciosByPrograma(servServices.getAllServicioByProg(idPrograma));
        } else {
            servicios = new ArrayList<>();
            idServicio = 0;
        }
    }

    public void clear() {
        facultad = 0;
        idPrograma = 0;
        idServicio = 0;
        idTerceroOferente = 0;
        idTerceroCreadoPor = 0;
        estado = -1;
        listaReporte = new ArrayList<>();
        fechaDesde = null;
        fechaHasta = null;

    }

    public void findReport() {
        listaReporte = new ArrayList<>();

        listaReporte = reporteServices.getAllProyect(idPrograma, idServicio, idTerceroOferente, idTerceroCreadoPor, Utilidades.dateToSql(fechaDesde), Utilidades.dateToSql(fechaHasta), estado, facultad);
        if (listaReporte.isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se encontraron Proyectos para esta consulta.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdTerceroOferente() {
        return idTerceroOferente;
    }

    public void setIdTerceroOferente(int idTerceroOferente) {
        this.idTerceroOferente = idTerceroOferente;
    }

    public int getIdTerceroCreadoPor() {
        return idTerceroCreadoPor;
    }

    public void setIdTerceroCreadoPor(int idTerceroCreadoPor) {
        this.idTerceroCreadoPor = idTerceroCreadoPor;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<ReporteProyecto> getListaReporte() {
        return listaReporte;
    }

    public void setListaReporte(List<ReporteProyecto> listaReporte) {
        this.listaReporte = listaReporte;
    }

    public ArrayList<SelectItem> getProgramas() {
        return programas;
    }

    public void setProgramas(ArrayList<SelectItem> programas) {
        this.programas = programas;
    }

    public ArrayList<SelectItem> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<SelectItem> servicios) {
        this.servicios = servicios;
    }

    public ArrayList<SelectItem> getTercerosOferentes() {
        return tercerosOferentes;
    }

    public void setTercerosOferentes(ArrayList<SelectItem> tercerosOferentes) {
        this.tercerosOferentes = tercerosOferentes;
    }

    public ArrayList<SelectItem> getTercerosUsuarios() {
        return tercerosUsuarios;
    }

    public void setTercerosUsuarios(ArrayList<SelectItem> tercerosUsuarios) {
        this.tercerosUsuarios = tercerosUsuarios;
    }

    public IPrograma getProgServices() {
        return progServices;
    }

    public void setProgServices(IPrograma progServices) {
        this.progServices = progServices;
    }

    public IServicio getServServices() {
        return servServices;
    }

    public void setServServices(IServicio servServices) {
        this.servServices = servServices;
    }

    public ITercero getTerceroServices() {
        return terceroServices;
    }

    public void setTerceroServices(ITercero terceroServices) {
        this.terceroServices = terceroServices;
    }

    public IReporteProyecto getReporteServices() {
        return reporteServices;
    }

    public void setReporteServices(IReporteProyecto reporteServices) {
        this.reporteServices = reporteServices;
    }

    public int getFacultad() {
        return facultad;
    }

    public void setFacultad(int facultad) {
        this.facultad = facultad;
    }

    public ArrayList<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }

    public void setItemsFacultad(ArrayList<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }

}
