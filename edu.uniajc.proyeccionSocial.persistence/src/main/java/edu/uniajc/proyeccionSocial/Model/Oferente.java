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
public class Oferente {

    private int id_Oferente;
    private int id_Proyecto;
    private int id_Tercero;
    private int estadoOferente;
    private String observacion;
    private String creadoPor;
    private Date creadoEn;
    private String modificadoPor;
    private Date modificadoEn;

    public int getId_Oferente() {
        return id_Oferente;
    }

    public void setId_Oferente(int id_Oferente) {
        this.id_Oferente = id_Oferente;
    }

    public int getId_Proyecto() {
        return id_Proyecto;
    }

    public void setId_Proyecto(int id_Proyecto) {
        this.id_Proyecto = id_Proyecto;
    }

    public int getId_Tercero() {
        return id_Tercero;
    }

    public void setId_Tercero(int id_Tercero) {
        this.id_Tercero = id_Tercero;
    }

    public int getEstadoOferente() {
        return estadoOferente;
    }

    public void setEstadoOferente(int estadoOferente) {
        this.estadoOferente = estadoOferente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
