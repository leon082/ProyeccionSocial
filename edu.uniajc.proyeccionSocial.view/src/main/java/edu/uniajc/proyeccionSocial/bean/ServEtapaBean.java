/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.bean;

import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;
import edu.uniajc.proyeccionSocial.persistence.Model.ServicioEtapa;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.view.util.Utilidades;
import edu.uniajc.proyeccionsocial.bussiness.services.EtapaServices;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import edu.uniajc.proyeccionsocial.bussiness.services.ServicioEtapaServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEtapa;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicioEtapa;
import javax.faces.model.SelectItem;

/**
 *
 * @author luis.leon
 */
@ManagedBean
@SessionScoped
public class ServEtapaBean {

    private DualListModel<Etapa> etapas;
    private IEtapa etapaServices;
    private IServicio servicioservices;
    private IServicioEtapa seServices;
    private List<Etapa> etapaSource;
    private List<Etapa> etapaTarget;
    //combo
    private ArrayList<SelectItem> itemsServicios;
    private int idServicio;

    private Usuario usuario;

    @PostConstruct
    public void init() {
        etapaServices = new EtapaServices();
        seServices = new ServicioEtapaServices();
        servicioservices = new ServicioServices();
         etapaSource = new ArrayList<Etapa>();
        etapaSource = etapaServices.getAllEtapa();
        usuario = Utilidades.cargarUsuario();
        etapaTarget = new ArrayList<Etapa>();
        etapas = new DualListModel<Etapa>(etapaSource, etapaTarget);
        itemsServicios = Utilidades.llenar_Combo_Servicios(servicioservices.getAllServicio());

    }

    public void guardar() {

        if (idServicio != 0) {

            //borro todos
            seServices.deleteEtapaServicioByServicio(idServicio);
            //creo todos
            for (Object obj : etapas.getTarget()) {

                String etapa = (String) obj;
                System.out.println("etapa" + etapa);

                ServicioEtapa crear = new ServicioEtapa();

                crear.setCreadopor(usuario.getUsuario());
                crear.setEstado(1);
                crear.setId_servicio(idServicio);
                crear.setId_etapa(Integer.valueOf(etapa));

                seServices.createServicioEtapa(crear);
            }
            actionCombo();
        }
    }

    public void actionCombo() {
        if (idServicio != 0) {
            etapaTarget = new ArrayList<Etapa>();
            etapaSource = new ArrayList<Etapa>();
            etapaSource = etapaServices.getAllEtapa();
            etapaTarget = etapaServices.getAllEtapaByServicio(idServicio);

            for (int i = 0; i < etapaSource.size(); i++) {

                Etapa source = etapaSource.get(i);

                for (int j = 0; j < etapaTarget.size(); j++) {

                    Etapa targed = etapaTarget.get(j);

                    if (source.getId_etapa() == targed.getId_etapa()) {

                        etapaSource.remove(source);
                    }
                }

            }
            etapas = new DualListModel<Etapa>(etapaSource, etapaTarget);
        } else {
            init();
        }

    }

    public DualListModel<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(DualListModel<Etapa> etapas) {
        this.etapas = etapas;
    }

    public IEtapa getEtapaServices() {
        return etapaServices;
    }

    public void setEtapaServices(IEtapa etapaServices) {
        this.etapaServices = etapaServices;
    }

    public IServicio getServicioservices() {
        return servicioservices;
    }

    public void setServicioservices(IServicio servicioservices) {
        this.servicioservices = servicioservices;
    }

    public IServicioEtapa getSeServices() {
        return seServices;
    }

    public void setSeServices(IServicioEtapa seServices) {
        this.seServices = seServices;
    }

    public List<Etapa> getEtapaSource() {
        return etapaSource;
    }

    public void setEtapaSource(List<Etapa> etapaSource) {
        this.etapaSource = etapaSource;
    }

    public List<Etapa> getEtapaTarget() {
        return etapaTarget;
    }

    public void setEtapaTarget(List<Etapa> etapaTarget) {
        this.etapaTarget = etapaTarget;
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

}
