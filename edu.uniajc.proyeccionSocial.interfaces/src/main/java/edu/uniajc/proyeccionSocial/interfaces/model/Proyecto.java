/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.interfaces.model;

import java.sql.Date;

/**
 *@author Emmanuel Barrera - IRIS
 *12/05/2017
 * Nombre Clase:Proyecto.java
 * Descripcion: Tabla que contiene los SET y GET de la tabla Proyecto
 */

public class Proyecto {
   private int id_Proyecto;
   private String tituloProyecto;
   private String resumenProyecto;
   private int iD_Programa;
   private int iD_ProgramaServicio;
   private int estadoProyecto;
   private String creadoPor;
   private Date creadoEn;
   private String modificadoPor;
   private Date modificadoEn;

    public int getId_Proyecto() {
        return id_Proyecto;
    }

    public void setId_Proyecto(int id_Proyecto) {
        this.id_Proyecto = id_Proyecto;
    }

    public String getTituloProyecto() {
        return tituloProyecto;
    }

    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    public String getResumenProyecto() {
        return resumenProyecto;
    }

    public void setResumenProyecto(String resumenProyecto) {
        this.resumenProyecto = resumenProyecto;
    }

    public int getiD_Programa() {
        return iD_Programa;
    }

    public void setiD_Programa(int iD_Programa) {
        this.iD_Programa = iD_Programa;
    }

    public int getiD_ProgramaServicio() {
        return iD_ProgramaServicio;
    }

    public void setiD_ProgramaServicio(int iD_ProgramaServicio) {
        this.iD_ProgramaServicio = iD_ProgramaServicio;
    }

    public int getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(int estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
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
