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
public class ProgramaServicio {

    private int id_ProgramaServicio;
    private String descripcion;
    private int id_Programa;
    private int estadoProgramaServicio;
    private String creadoPor;
    private Date creadoEn;
    private String modificadoPor;
    private Date modificadoEn;

    public int getId_ProgramaServicio() {
        return id_ProgramaServicio;
    }

    public void setId_ProgramaServicio(int id_ProgramaServicio) {
        this.id_ProgramaServicio = id_ProgramaServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_Programa() {
        return id_Programa;
    }

    public void setId_Programa(int id_Programa) {
        this.id_Programa = id_Programa;
    }

    public int getEstadoProgramaServicio() {
        return estadoProgramaServicio;
    }

    public void setEstadoProgramaServicio(int estadoProgramaServicio) {
        this.estadoProgramaServicio = estadoProgramaServicio;
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
