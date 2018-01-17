/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.Model.Beneficiario;
import edu.uniajc.proyeccionSocial.Model.Etapa;
import edu.uniajc.proyeccionSocial.Model.Oferente;
import edu.uniajc.proyeccionSocial.Model.Proyecto;
import edu.uniajc.proyeccionSocial.Model.ProyectoEtapa;
import edu.uniajc.proyeccionSocial.Model.Tercero;
import edu.uniajc.proyeccionSocial.Model.Usuario;

import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.BeneficiarioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.EtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.OferenteServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProyectoEtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProyectoServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.interfaces.IBeneficiario;
import edu.uniajc.proyeccionsocial.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.interfaces.IOferente;
import edu.uniajc.proyeccionsocial.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.interfaces.IProyecto;
import edu.uniajc.proyeccionsocial.interfaces.IProyectoEtapa;
import edu.uniajc.proyeccionsocial.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.interfaces.ITercero;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.DualListModel;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@ViewScoped
public class ProyectoBean {

    //Proyecto Create
    private Proyecto proyecto;
    private IProyecto servicioProyecto;
    //combo programas
    private ArrayList<SelectItem> itemsProgramas;
    private int idPrograma;
    private IPrograma programaservices;
    //Combo Servicios
    private ArrayList<SelectItem> itemsServicios;
    private int idServicio;

    //Servicios segun el programa
    private IServicio serviciosServ;
    //etapas segun el servicio
    private IEtapa servicioEtapa;
    List<Etapa> etapas;

    //Usuario Session
    private Usuario usuario;
    //Lista de correos para notificacion
    List<String> correos;
    //Emisor
    List<String> emisor;

    //Beneficiarios
    private ITercero terceroServices;
    private IBeneficiario beneficiarioServices;
    private List<Tercero> beneSource;
    private List<Tercero> beneTarget;
    private DualListModel<Tercero> terceros;

    //Oferentes
    private List<SelectItem> itemsOferente;
    private int idOferente;
    private IOferente oferenteServices;
    
    
    private IProyectoEtapa servicioProyectoEtapa;

    @PostConstruct
    public void init() {
        //Proyecto etapa
          
     servicioProyectoEtapa = new ProyectoEtapaServices();
        //Proyecto create
        proyecto = new Proyecto();
        servicioProyecto = new ProyectoServices();
        //ComboProgramas
        programaservices = new ProgramaServices();
        itemsProgramas = Utilidades.llenar_Combo_Programas(programaservices.getAllPrograma());
        //Servicios segun el programa
        serviciosServ = new ServicioServices();
        //Etapas segun el servicio
        servicioEtapa = new EtapaServices();
        etapas = new ArrayList<Etapa>();
        //Usuario
        usuario = Utilidades.cargarUsuario();
        //Beneficiarios
        initBeneficiarios();
        //Combo Servicios
        itemsServicios = new ArrayList<>();

        //oferentes
        itemsOferente = Utilidades.llenar_Combo_Terceros(terceroServices.getAllTercero());
        oferenteServices = new OferenteServices();
    }

    public void initBeneficiarios() {
        terceroServices = new TerceroServices();
        beneSource = terceroServices.getAllTercero();
        beneTarget = new ArrayList<Tercero>();
        terceros = new DualListModel<Tercero>(beneSource, beneTarget);
        beneficiarioServices = new BeneficiarioServices();
    }

    public void guardarBeneficiarios(int idProyecto) {
        //borro todos
        //psServices.deleteProgramaServicioByProg(idPrograma);
        //creo todos
        for (Object obj : terceros.getTarget()) {

            String tercero = (String) obj;

            Beneficiario crear = new Beneficiario();

            crear.setCreadopor(usuario.getUsuario());
            crear.setEstado(1);
            crear.setId_proyecto(idProyecto);
            crear.setId_tercero(Integer.valueOf(tercero));

            beneficiarioServices.createBeneficiario(crear);
        }

    }

    public void guardarOferente(int idProyecto) {

        Oferente crear = new Oferente();

        crear.setCreadopor(usuario.getUsuario());
        crear.setEstado(1);
        crear.setId_proyecto(idProyecto);
        crear.setId_tercero(idOferente);

        oferenteServices.createOferente(crear);

    }

    public void actionCombo() {
        if (idPrograma != 0) {

            //Llenar el combo de Servicios por programa
            idServicio = 0;
            itemsServicios = Utilidades.llenar_Combo_ServiciosByPrograma(serviciosServ.getAllServicioByProg(idPrograma));
        } else {
            itemsServicios = new ArrayList<>();
            idServicio = 0;
        }
    }

    public void actionComboServicio() {

        //Llenar tabla de etapas
        if (idServicio != 0) {
            etapas = servicioEtapa.getAllEtapaByServicio(idServicio);
        } else {
            etapas = new ArrayList<>();

        }

    }
public void  guardarProyectoEtapa (int idProyecto){
    List<Etapa> list = servicioEtapa.getAllEtapaByServicio(idServicio);
    for(Etapa e : list){
        ProyectoEtapa proyectoEtapa = new ProyectoEtapa();
        proyectoEtapa.setCreadopor(usuario.getUsuario());
        proyectoEtapa.setEstado(0);
        proyectoEtapa.setId_etapa(e.getId_etapa());
        proyectoEtapa.setId_proyecto(idProyecto);
        servicioProyectoEtapa.createProyectoEtapa(proyectoEtapa);
    }
}
    public void crear() {
        if (!servicioProyecto.tieneProyectoPendiente(usuario.getUsuario())) {

            proyecto.setCreadopor(usuario.getUsuario());
            proyecto.setId_programa(idPrograma);
            proyecto.setId_servicio(idServicio);
            int result = servicioProyecto.createProyecto(proyecto);

            if (result != 0) {
                guardarBeneficiarios(result);
                guardarOferente(result);
                guardarProyectoEtapa(result);
                envioCorreo();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                limpiarForma();
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "No se pudo realizar la operaciónn");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Tiene un proyecto pendiente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void envioCorreo() {
        //Lista de correos para notificacion
        correos = Utilidades.findSendEmail();
        //Cuenta emisora
        emisor = Utilidades.findEmailEmisor();
        Utilidades.envioCorreo(correos, emisor, usuario, proyecto, 0, "Creacion de Proyecto",0);

    }

    public void limpiarForma() {
        proyecto = new Proyecto();
        etapas = new ArrayList<>();
        idPrograma = 0;
        idServicio = 0;
        idOferente = 0;

    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public IProyecto getServicioProyecto() {
        return servicioProyecto;
    }

    public void setServicioProyecto(IProyecto servicioProyecto) {
        this.servicioProyecto = servicioProyecto;
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

    public IPrograma getProgramaservices() {
        return programaservices;
    }

    public void setProgramaservices(IPrograma programaservices) {
        this.programaservices = programaservices;
    }

    public IServicio getServiciosServ() {
        return serviciosServ;
    }

    public void setServiciosServ(IServicio serviciosServ) {
        this.serviciosServ = serviciosServ;
    }

    public IEtapa getServicioEtapa() {
        return servicioEtapa;
    }

    public void setServicioEtapa(IEtapa servicioEtapa) {
        this.servicioEtapa = servicioEtapa;
    }

    public ArrayList<SelectItem> getItemsServicios() {
        return itemsServicios;
    }

    public void setItemsServicios(ArrayList<SelectItem> itemsServicios) {
        this.itemsServicios = itemsServicios;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getCorreos() {
        return correos;
    }

    public void setCorreos(List<String> correos) {
        this.correos = correos;
    }

    public List<String> getEmisor() {
        return emisor;
    }

    public void setEmisor(List<String> emisor) {
        this.emisor = emisor;
    }

    public ITercero getTerceroServices() {
        return terceroServices;
    }

    public void setTerceroServices(ITercero terceroServices) {
        this.terceroServices = terceroServices;
    }

    public IBeneficiario getBeneficiarioServices() {
        return beneficiarioServices;
    }

    public void setBeneficiarioServices(IBeneficiario beneficiarioServices) {
        this.beneficiarioServices = beneficiarioServices;
    }

    public List<Tercero> getBeneSource() {
        return beneSource;
    }

    public void setBeneSource(List<Tercero> beneSource) {
        this.beneSource = beneSource;
    }

    public List<Tercero> getBeneTarget() {
        return beneTarget;
    }

    public void setBeneTarget(List<Tercero> beneTarget) {
        this.beneTarget = beneTarget;
    }

    public DualListModel<Tercero> getTerceros() {
        return terceros;
    }

    public void setTerceros(DualListModel<Tercero> terceros) {
        this.terceros = terceros;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<SelectItem> getItemsOferente() {
        return itemsOferente;
    }

    public void setItemsOferente(List<SelectItem> itemsOferente) {
        this.itemsOferente = itemsOferente;
    }

    public int getIdOferente() {
        return idOferente;
    }

    public void setIdOferente(int idOferente) {
        this.idOferente = idOferente;
    }

    public IOferente getOferenteServices() {
        return oferenteServices;
    }

    public void setOferenteServices(IOferente oferenteServices) {
        this.oferenteServices = oferenteServices;
    }

}