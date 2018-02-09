/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.Model;

import java.sql.Date;

/**
 *
 * @author luis.leon
 */
public class Proyecto {
    private int id_proyecto;
    private String tituloproyecto;
    private String resumenproyecto;
    private int id_programa;
    private int id_servicio;
    private int estado;
    private String creadopor;
    private Date creadoen;
    private String modificadopor;
    private Date modificadoen;
    private int facultad;

    public int getFacultad() {
        return facultad;
    }

    public void setFacultad(int facultad) {
        this.facultad = facultad;
    }
    
    

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getTituloproyecto() {
        return tituloproyecto;
    }

    public void setTituloproyecto(String tituloproyecto) {
        this.tituloproyecto = tituloproyecto;
    }

    public String getResumenproyecto() {
        return resumenproyecto;
    }

    public void setResumenproyecto(String resumenproyecto) {
        this.resumenproyecto = resumenproyecto;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
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

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }
    
}