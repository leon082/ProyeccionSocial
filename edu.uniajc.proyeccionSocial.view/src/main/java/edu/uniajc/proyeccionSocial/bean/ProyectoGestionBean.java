/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Beneficiario;
import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;
import edu.uniajc.proyeccionSocial.persistence.Model.EtapasEntregas;
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
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEnvioCorreo;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOferente;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyecto;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyectoEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ISoporteProyectoEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.services.EnvioCorreoServices;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@ViewScoped
public class ProyectoGestionBean {

    private IEnvioCorreo envioCorreoServices;

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
    private ArrayList<SelectItem> itemsFacultad;
    private int facultad;

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
    private SoporteProyectoEtapa soporte;
    private ISoporteProyectoEtapa servicioSoporte;
    private String rutaArchivo;

    private IProyectoEtapa servicioProyectoEtapa;

    private List<Proyecto> proyectos;

    private static final Logger LOGGER = Logger.getLogger(ProyectoGestionBean.class.getName());

    @PostConstruct
    public void init() {
        org.apache.log4j.BasicConfigurator.configure();
        correos = new ArrayList<>();
        emisor = new ArrayList<>();
        servicioProyectoEtapa = new ProyectoEtapaServices(Utilidades.getConnection());
        //Soporte
        soporte = new SoporteProyectoEtapa();
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
        itemsFacultad = Utilidades.Consultar_Facultades_combo();
        facultad = 0;
        proyecto = new Proyecto();
        proyectos = servicioProyecto.getProyectoByUser(usuario.getUsuario());
        rutaArchivo = "";
        envioCorreoServices = new EnvioCorreoServices();
        envioCorreoServices.init();

    }

    public void buscar(Proyecto p) {

        proyecto = p;

        facultad = proyecto.getFacultad();
        setProgramaByProyecto();
        servByProg();
        setOferenteByProyecto();
        llenarEtapasByProyecto();
        llenarBeneficiarios();

    }

    public void llenarBeneficiarios() {
        beneficiarios = new ArrayList<>();
        //Lista de beneficiarios por proyecto
        List<Beneficiario> beneficiarioByProyecto = beneficiarioServices.getAllBeneficiarioByProyect(proyecto.getId_proyecto());
        for (Beneficiario beneficiario : beneficiarioByProyecto) {
            beneficiarios.add(terceroServices.getTerceroById(beneficiario.getId_tercero()));
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

    public void llenarEtapasByProyecto() {
        etapas = new ArrayList<>();
        //Llenar tabla de etapas
        //Lista de etapas segun Proyecto
        List<ProyectoEtapa> proyectoEtapas = servicioProyectoEtapa.getAllProyectoEtapaByProyecto(proyecto.getId_proyecto());
        for (ProyectoEtapa obj : proyectoEtapas) {
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
                e.setFlag(false);
            }
            if (obj.getEstado() == 3) {
                e.setEstado("Rechazado");
                e.setFlag(false);
            }
            if (obj.getEstado() == 2) {
                e.setEstado("Pendiente Aprobacion");
                e.setFlag(true);
            }

            etapas.add(e);
        }

    }

    public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
        OutputStream out = null;
        InputStream is =null;
        try {
            String name = "_" + 4 + event.getFile().getFileName();
            // String ruta = Utilidades.leerArchivo("ruta");
            String ruta = Utilidades.getRuta();

            String retorno = ruta + name;

            File filePrueba = new File(ruta);
            //pregunto si el directorio existe sino lo creo
            if (!filePrueba.exists()) {
                filePrueba.mkdirs();
            }
            filePrueba = new File(retorno);
            //luego adiciono el archivo y lo mando a crear
            is = event.getFile().getInputstream();
            out = new FileOutputStream(filePrueba);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            is.close();
            out.close();
            rutaArchivo = name;
            if (!"".equals(rutaArchivo)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Archivo subido, no olvide guardar.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } catch (Exception e) {
            LOGGER.error("Error Subiendo el archivo" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (is != null) {
                is.close();
            } // Multiple streams were opened. Only the last is closed.
        }

    }

    public void envioCorreo(int idEtapa) {
        //Lista de correos para notificacion
        correos = Utilidades.findSendEmail();
        //Cuenta emisora
        emisor = Utilidades.findEmailEmisor();
        envioCorreoServices.envioCorreo(correos, emisor, usuario, proyecto, 3, "Entrega de Etapa", idEtapa);

    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public void actionBoton(EtapasEntregas etapaEntrega) {
        if (!"".equals(rutaArchivo)) {
            int result = guardarSoporte(etapaEntrega.getIdProyectoEtapa());
            envioCorreo(result);

            ProyectoEtapa proyectoE = servicioProyectoEtapa.getProyectoEtapaById(etapaEntrega.getIdProyectoEtapa());
            proyectoE.setEstado(2);
            servicioProyectoEtapa.updateProyectoEtapa(proyectoE);
            llenarEtapasByProyecto();
            rutaArchivo = "";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Debe subir un archivo.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void clear() {
        rutaArchivo = "";
        idPrograma = 0;
        idServicio = 0;

        facultad = 0;
        idOferente = 0;
        init();
    }

    public int guardarSoporte(int idProyectoEtapa) {
        soporte.setArchivo(rutaArchivo);
        soporte.setCreadopor(usuario.getUsuario());
        soporte.setId_proyectoetapa(idProyectoEtapa);
        int result = servicioSoporte.createSoporteProyectoEtapa(soporte);
        if (result != 0) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Entrega Realizada.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return result;
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

    public ArrayList<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }

    public void setItemsFacultad(ArrayList<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }

    public int getFacultad() {
        return facultad;
    }

    public void setFacultad(int facultad) {
        this.facultad = facultad;
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

    public SoporteProyectoEtapa getSoporte() {
        return soporte;
    }

    public void setSoporte(SoporteProyectoEtapa soporte) {
        this.soporte = soporte;
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

}
