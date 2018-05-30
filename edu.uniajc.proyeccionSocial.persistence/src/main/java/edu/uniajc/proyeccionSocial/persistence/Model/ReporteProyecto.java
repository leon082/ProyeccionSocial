/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.Model;

/**
 *
 * @author LuisLeon
 */
import java.sql.Date;
public class ReporteProyecto {
    
   private String tituloProyecto;
    private String oferente;
    private String creadoPor;
    private Date creadoEn;
    private String fechaCreate;
    private String programa;
    private String servicio;
    private String estado;
    private String facultad;

    public String getTituloProyecto() {
        return tituloProyecto;
    }

    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    public String getOferente() {
        return oferente;
    }

    public void setOferente(String oferente) {
        this.oferente = oferente;
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

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreate() {
        return creadoEn.toString();
    }

    public void setFechaCreate(String fechaCreate) {
        this.fechaCreate = fechaCreate;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    
    
    
    
    
    
}
