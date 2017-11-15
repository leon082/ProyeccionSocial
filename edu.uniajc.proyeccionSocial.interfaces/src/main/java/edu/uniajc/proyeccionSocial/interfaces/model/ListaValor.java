package edu.uniajc.proyeccionSocial.interfaces.model;

import java.sql.Date;

/**
 *
 * @author Leon
 */
public class ListaValor {
    
    
   private int id_ListaValor;
   private String agrupacion;
   private String descripcion;
   private int estado;
   private String creadoPor;
   private Date creadoEn;
   private String modificadoPor;
   private Date modificadoEn;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

   
   

    public int getId_ListaValor() {
        return id_ListaValor;
    }

    public void setId_ListaValor(int id_ListaValor) {
        this.id_ListaValor = id_ListaValor;
    }

    

    public String getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(String agrupacion) {
        this.agrupacion = agrupacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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