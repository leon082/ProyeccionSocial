/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.Model;

import java.sql.Date;

/**
 *
 * @author rlara
 */
public class ServicioEtapa {
    private int id_servicioetapa;
    private int id_servicio;
    private int id_etapa;
    private int estado;
    private String creadopor;
    private Date creadoen;
    private String modificadopor;
    private Date modificadoen;

    public int getId_servicioetapa() {
        return id_servicioetapa;
    }

    public void setId_servicioetapa(int id_servicioetapa) {
        this.id_servicioetapa = id_servicioetapa;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getId_etapa() {
        return id_etapa;
    }

    public void setId_etapa(int id_etapa) {
        this.id_etapa = id_etapa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCreadopor() {
        return creadopor;
    }

    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    public Date getCreadoen() {
        return creadoen;
    }

    public void setCreadoen(Date creadoen) {
        this.creadoen = creadoen;
    }

    public String getModificadopor() {
        return modificadopor;
    }

    public void setModificadopor(String modificadopor) {
        this.modificadopor = modificadopor;
    }

    public Date getModificadoen() {
        return modificadoen;
    }

    public void setModificadoen(Date modificadoen) {
        this.modificadoen = modificadoen;
    }
}