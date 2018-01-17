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
import edu.uniajc.proyeccionSocial.Model.SoporteProyectoEtapa;
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
import edu.uniajc.proyeccionsocial.bussiness.services.SoporteProyectoEtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.interfaces.IBeneficiario;
import edu.uniajc.proyeccionsocial.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.interfaces.IOferente;
import edu.uniajc.proyeccionsocial.interfaces.IPrograma;
import edu.uniajc.proyeccionsocial.interfaces.IProyecto;
import edu.uniajc.proyeccionsocial.interfaces.IProyectoEtapa;
import edu.uniajc.proyeccionsocial.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.interfaces.ISoporteProyectoEtapa;
import edu.uniajc.proyeccionsocial.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.interfaces.IUsuario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author LuisLeon
 */
@ManagedBean
@SessionScoped
public class ProyectoGestionBean {

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
    private SoporteProyectoEtapa soporte;
    private ISoporteProyectoEtapa servicioSoporte;
    private String rutaArchivo;

    private IProyectoEtapa servicioProyectoEtapa;

    @PostConstruct
    public void init() {
        servicioProyectoEtapa = new ProyectoEtapaServices();
        //Soporte
        soporte = new SoporteProyectoEtapa();
        servicioSoporte = new SoporteProyectoEtapaServices();
        //Proyecto create

        servicioProyecto = new ProyectoServices();
        //ComboProgramas
        programaservices = new ProgramaServices();
        itemsProgramas = Utilidades.llenar_Combo_Programas(programaservices.getAllPrograma());
        //Servicios segun el programa
        serviciosServ = new ServicioServices();
        //Etapas segun el servicio
        servicioEtapa = new EtapaServices();
        etapas = new ArrayList<EtapasEntregas>();
        //Usuario
        usuarioServices = new UsuarioServices();
        usuario = Utilidades.cargarUsuario();

        //Beneficiarios
        beneficiarios = new ArrayList<>();
        //Combo Servicios
        itemsServicios = new ArrayList<>();
        terceroServices = new TerceroServices();

        //oferentes
        itemsOferente = Utilidades.llenar_Combo_Terceros(terceroServices.getAllTercero());
        oferenteServices = new OferenteServices();
        beneficiarioServices = new BeneficiarioServices();

        proyecto = new Proyecto();
        cargarProyectoByUsuario();
        rutaArchivo="";
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
        etapas = new ArrayList<EtapasEntregas>();
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
            if(obj.getEstado() == 0) {
                e.setEstado("Faltante");
                e.setFlag(false);
            }
            if(obj.getEstado() == 3){
                e.setEstado("Rechazado");
                e.setFlag(false);
            }
            if(obj.getEstado() == 2){
                e.setEstado("Pendiente Aprobacion");
                e.setFlag(true);
            }
            
            
            etapas.add(e);
        }

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

  

    public void cargarProyectoByUsuario() {

        proyecto = servicioProyecto.getProyectoByUser(usuario.getUsuario());

        setProgramaByProyecto();
        servByProg();
        setOferenteByProyecto();
        llenarEtapasByProyecto();
        llenarBeneficiarios();

    }

    public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {

        System.out.println("si entra 4444:" + event.getFile().getFileName());

        String name = "_" + 4 + event.getFile().getFileName();
        String ruta = Utilidades.leerArchivo("ruta");

        String retorno = ruta + name;
        System.out.println("si entra 222:" + retorno);
        File filePrueba = new File(ruta);
        //pregunto si el directorio existe sino lo creo
        if (!filePrueba.exists()) {
            filePrueba.mkdirs();
        }
        filePrueba = new File(retorno);
        //luego adiciono el archivo y lo mando a crear
        InputStream is = event.getFile().getInputstream();
        OutputStream out = new FileOutputStream(filePrueba);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        is.close();
        out.close();
        rutaArchivo = name;
        if(!"".equals(rutaArchivo)){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Archivo subido, no olvide guardar.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            }

    }
    
     public void envioCorreo(int idEtapa) {
        //Lista de correos para notificacion
        correos = Utilidades.findSendEmail();
        //Cuenta emisora
        emisor = Utilidades.findEmailEmisor();
        Utilidades.envioCorreo(correos, emisor, usuario, proyecto, 3, "Entrega de Etapa Proyecto",idEtapa);

    }
     
    public void actionBoton(EtapasEntregas etapaEntrega){
            if(!"".equals(rutaArchivo)){
               int result= guardarSoporte(etapaEntrega.getIdProyectoEtapa());
               envioCorreo(result);
               System.out.println("ProyectoEtapa "+etapaEntrega.getIdProyectoEtapa());
                ProyectoEtapa proyectoE = servicioProyectoEtapa.getProyectoEtapaById(etapaEntrega.getIdProyectoEtapa());
                proyectoE.setEstado(2);
                servicioProyectoEtapa.updateProyectoEtapa(proyectoE);
                
                llenarEtapasByProyecto();
                rutaArchivo="";
            }
    }
        
        
    
    public int guardarSoporte(int idProyectoEtapa) {
        soporte.setArchivo(rutaArchivo);
        soporte.setCreadopor(usuario.getUsuario());
        soporte.setId_proyectoetapa(idProyectoEtapa);
        int result=servicioSoporte.createSoporteProyectoEtapa(soporte);
        if(result!=0){
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Entrega Realizada.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return result;
    }

    /*public byte[] read(File file) throws IOException {

        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException e) {
            }

            try {
                if (ios != null) {
                    ios.close();
                }
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }*/

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
