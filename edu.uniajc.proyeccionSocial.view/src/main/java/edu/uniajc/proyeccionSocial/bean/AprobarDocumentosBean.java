/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Beneficiario;
import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;
import edu.uniajc.proyeccionSocial.persistence.Model.Oferente;
import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import edu.uniajc.proyeccionSocial.persistence.Model.ProyectoEtapa;
import edu.uniajc.proyeccionSocial.persistence.Model.SoporteProyectoEtapa;
import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;

import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.BeneficiarioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.EtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.OferenteServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProyectoEtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ProyectoServices;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.services.SoporteProyectoEtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IBeneficiario;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOferente;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyecto;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyectoEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ISoporteProyectoEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@SessionScoped
public class AprobarDocumentosBean {

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
    List<EtapasEntregas> etapas;

    //Usuario Session
    private Usuario usuario;
    private IUsuario usuarioServices;
    //Lista de correos para notificacion
    List<String> correos;
    //Emisor
    List<String> emisor;

    //Beneficiarios
    private ITercero terceroServices;
    private IBeneficiario beneficiarioServices;
    private List<Tercero> beneficiarios;

    //Oferentes
    private List<SelectItem> itemsOferente;
    private int idOferente;
    private IOferente oferenteServices;

    //Soporte
    private ISoporteProyectoEtapa servicioSoporte;
    private String rutaArchivo;
    private DefaultStreamedContent download;
    private IProyectoEtapa servicioProyectoEtapa;

    private String idSoporte;
    SoporteProyectoEtapa soporte;
    ProyectoEtapa proyectoEtapa;

    @PostConstruct
    public void init() {
        proyectoEtapa = new ProyectoEtapa();
        servicioProyectoEtapa = new ProyectoEtapaServices(Utilidades.getConnection());
        //Soporte

        servicioSoporte = new SoporteProyectoEtapaServices(Utilidades.getConnection());
        //Proyecto create

        servicioProyecto = new ProyectoServices(Utilidades.getConnection());
        //ComboProgramas
        programaservices = new ProgramaServices(Utilidades.getConnection());
        itemsProgramas = Utilidades.llenar_Combo_Programas(programaservices.getAllPrograma());
        //Servicios segun el programa
        serviciosServ = new ServicioServices(Utilidades.getConnection());
        //Etapas segun el servicio
        servicioEtapa = new EtapaServices(Utilidades.getConnection());
        etapas = new ArrayList<EtapasEntregas>();
        //Usuario
        usuarioServices = new UsuarioServices(Utilidades.getConnection());
        usuario = Utilidades.cargarUsuario();

        //Beneficiarios
        beneficiarios = new ArrayList<>();
        //Combo Servicios
        itemsServicios = new ArrayList<>();
        terceroServices = new TerceroServices(Utilidades.getConnection());

        //oferentes
        itemsOferente = Utilidades.llenar_Combo_Terceros(terceroServices.getAllTercero());
        oferenteServices = new OferenteServices(Utilidades.getConnection());
        beneficiarioServices = new BeneficiarioServices(Utilidades.getConnection());

        proyecto = new Proyecto();

        rutaArchivo = "";
    }

    public void llenarBeneficiarios() {
        beneficiarios = new ArrayList<>();
        //Lista de beneficiarios por proyecto
        List<Beneficiario> beneficiarioByProyecto = beneficiarioServices.getAllBeneficiarioByProyect(proyecto.getId_proyecto());
        for (Beneficiario beneficiario : beneficiarioByProyecto) {
            beneficiarios.add(terceroServices.getTerceroById(beneficiario.getId_tercero()));
        }
    }

    public void buscar() {
        soporte = servicioSoporte.getSoporteProyectoEtapaById(Integer.valueOf(idSoporte));
        if (soporte != null) {
            proyectoEtapa = servicioProyectoEtapa.getProyectoEtapaById(soporte.getId_proyectoetapa());
            proyecto = servicioProyecto.getProyectoById(proyectoEtapa.getId_proyecto());
            setProgramaByProyecto();
            servByProg();
            setOferenteByProyecto();
            llenarEtapas(proyectoEtapa);
            llenarBeneficiarios();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Entrega no existente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void setProgramaByProyecto() {
        idPrograma = proyecto.getId_programa();
    }

    public void setOferenteByProyecto() {
        Oferente oferente = oferenteServices.getOferenteByProyecto(proyecto.getId_proyecto());
        Tercero tercero = terceroServices.getTerceroById(oferente.getId_tercero());
        idOferente = tercero.getId_tercero();
    }

    public void servByProg() {
        if (idPrograma != 0) {

            //Llenar el combo de Servicios por programa
            itemsServicios = Utilidades.llenar_Combo_ServiciosByPrograma(serviciosServ.getAllServicioByProg(idPrograma));
            idServicio = proyecto.getId_servicio();
        } else {
            itemsServicios = new ArrayList<>();
            idServicio = 0;
        }
    }

    public void aprobarEntrega() {
        proyectoEtapa.setEstado(1);
        servicioProyectoEtapa.updateProyectoEtapa(proyectoEtapa);
        correos = new ArrayList<>();
        correos.add(usuarioServices.getEmailByUsername(proyecto.getCreadopor()));
        emisor = new ArrayList<>();
        emisor = Utilidades.findEmailEmisor();
        if (Utilidades.envioCorreo(correos, emisor, usuario, proyecto, 4, "Entrega Aprobada", 0)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if (finalizarProyecto()) {
            proyecto.setEstado(3);
            servicioProyecto.updateProyecto(proyecto);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Con esta entrega el proyecto queda finalizado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        buscar();

    }

    public boolean finalizarProyecto() {
        List<ProyectoEtapa> proyectoEtapas = servicioProyectoEtapa.getAllProyectoEtapaByProyecto(proyecto.getId_proyecto());
        boolean flag = true;
        for (ProyectoEtapa etapa : proyectoEtapas) {
            if (etapa.getEstado() == 0
                    || etapa.getEstado() == 3
                    || etapa.getEstado() == 2) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void rechazarEntrega() {
        //3 rechazado
        proyectoEtapa.setEstado(3);
        servicioProyectoEtapa.updateProyectoEtapa(proyectoEtapa);
        correos = new ArrayList<>();
        correos.add(usuarioServices.getEmailByUsername(proyecto.getCreadopor()));
        emisor = Utilidades.findEmailEmisor();
        if (Utilidades.envioCorreo(correos, emisor, usuario, proyecto, 5, "Entrega Rechazada", 0)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        buscar();

    }

    public void llenarEtapas(ProyectoEtapa obj) {
        etapas = new ArrayList<>();
        //Llenar tabla de etapas con la etapa q se debe aprobar
        EtapasEntregas e = new EtapasEntregas();
        Etapa etapa = servicioEtapa.getEtapaById(obj.getId_etapa());
        e.setNombreEtapa(etapa.getDescripcion());
        e.setIdProyectoEtapa(obj.getId_proyectoetapa());
        if (obj.getEstado() == 1) {
            e.setEstado("Aprobado");
            e.setFlag(true);

        }
        if (obj.getEstado() == 0) {
            e.setEstado("Faltante");
            e.setFlag(true);
        }
        if (obj.getEstado() == 3) {
            e.setEstado("Rechazado");
            e.setFlag(true);
        }
        if (obj.getEstado() == 2) {
            e.setEstado("Pendiente Aprobacion");
            e.setFlag(false);
        }

        etapas.add(e);
    }

    public class EtapasEntregas {

        public String nombreEtapa;
        public String estado;
        public int idProyectoEtapa;
        public boolean flag;

        public String getNombreEtapa() {
            return nombreEtapa;
        }

        public void setNombreEtapa(String nombreEtapa) {
            this.nombreEtapa = nombreEtapa;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public int getIdProyectoEtapa() {
            return idProyectoEtapa;
        }

        public void setIdProyectoEtapa(int idProyectoEtapa) {
            this.idProyectoEtapa = idProyectoEtapa;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public DefaultStreamedContent getDownload() throws Exception {
        return download;
    }

    public void prepDownload() throws FileNotFoundException {
        //String ruta = Utilidades.leerArchivo("ruta");
        String ruta = Utilidades.getRuta();
      
        String retorno = ruta + soporte.getArchivo();
        
        File file = new File(retorno);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
    }

    public void envioCorreo() {
        //Lista de correos para notificacion
        correos = Utilidades.findSendEmail();
        //Cuenta emisora
        emisor = Utilidades.findEmailEmisor();
        Utilidades.envioCorreo(correos, emisor, usuario, proyecto, 3, "Entrega de Etapa Proyecto", 0);

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

    public IUsuario getUsuarioServices() {
        return usuarioServices;
    }

    public void setUsuarioServices(IUsuario usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    public List<Tercero> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Tercero> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public List<EtapasEntregas> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<EtapasEntregas> etapas) {
        this.etapas = etapas;
    }

    public ISoporteProyectoEtapa getServicioSoporte() {
        return servicioSoporte;
    }

    public void setServicioSoporte(ISoporteProyectoEtapa servicioSoporte) {
        this.servicioSoporte = servicioSoporte;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public IProyectoEtapa getServicioProyectoEtapa() {
        return servicioProyectoEtapa;
    }

    public void setServicioProyectoEtapa(IProyectoEtapa servicioProyectoEtapa) {
        this.servicioProyectoEtapa = servicioProyectoEtapa;
    }

    public String getIdSoporte() {
        return idSoporte;
    }

    public void setIdSoporte(String idSoporte) {
        this.idSoporte = idSoporte;
    }

    public SoporteProyectoEtapa getSoporte() {
        return soporte;
    }

    public void setSoporte(SoporteProyectoEtapa soporte) {
        this.soporte = soporte;
    }

    public ProyectoEtapa getProyectoEtapa() {
        return proyectoEtapa;
    }

    public void setProyectoEtapa(ProyectoEtapa proyectoEtapa) {
        this.proyectoEtapa = proyectoEtapa;
    }

}
