/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.Model;

import java.sql.Date;

/**
 *
 * @author rlara
 */
public class Fase {
    private int id_Fase;
    private String descripcion;
    private int id_ProgramaServicio;
    private int estadoFase;
    private String creadoPor;
    private Date creadoEn;
    private String modificadoPor;
    private Date modificadoEn;

    public int getId_Fase() {
        return id_Fase;
    }

    public void setId_Fase(int id_Fase) {
        this.id_Fase = id_Fase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_ProgramaServicio() {
        return id_ProgramaServicio;
    }

    public void setId_ProgramaServicio(int id_ProgramaServicio) {
        this.id_ProgramaServicio = id_ProgramaServicio;
    }

    public int getEstadoFase() {
        return estadoFase;
    }

    public void setEstadoFase(int estadoFase) {
        this.estadoFase = estadoFase;
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
