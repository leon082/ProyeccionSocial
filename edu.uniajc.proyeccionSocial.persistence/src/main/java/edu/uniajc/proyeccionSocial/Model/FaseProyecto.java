/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.Model;

import java.sql.Date;

/**
 *
 * @author luis.leon
 */
public class FaseProyecto {

    private int id_FaseProyecto;
    private int id_Proyecto;
    private int id_Fase;
    private int estadoFaseProyecto;
    private String observacion;
    private Date fechaInicio;
    private Date fechaFin;
    private String creadoPor;
    private Date creadoEn;
    private String modificadoPor;
    private Date modificadoEn;

    public int getId_FaseProyecto() {
        return id_FaseProyecto;
    }

    public void setId_FaseProyecto(int id_FaseProyecto) {
        this.id_FaseProyecto = id_FaseProyecto;
    }

    public int getId_Proyecto() {
        return id_Proyecto;
    }

    public void setId_Proyecto(int id_Proyecto) {
        this.id_Proyecto = id_Proyecto;
    }

    public int getId_Fase() {
        return id_Fase;
    }

    public void setId_Fase(int id_Fase) {
        this.id_Fase = id_Fase;
    }

    public int getEstadoFaseProyecto() {
        return estadoFaseProyecto;
    }

    public void setEstadoFaseProyecto(int estadoFaseProyecto) {
        this.estadoFaseProyecto = estadoFaseProyecto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getModificadoEn() {
        return modificadoEn;
    }

    public void setModificadoEn(Date modificadoEn) {
        this.modificadoEn = modificadoEn;
    }
    
    
    

}
