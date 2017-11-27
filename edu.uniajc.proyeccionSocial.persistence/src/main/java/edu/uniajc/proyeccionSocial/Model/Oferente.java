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
    private int id_oferente;
    private int id_proyecto;
    private int id_tercero;
    private int estado;
    private String observacion;
    private String creadopor;
    private Date creadoen;
    private String modificadopor;
    private Date modificadoen;

    public int getId_oferente() {
        return id_oferente;
    }

    public void setId_oferente(int id_oferente) {
        this.id_oferente = id_oferente;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public int getId_tercero() {
        return id_tercero;
    }

    public void setId_tercero(int id_tercero) {
        this.id_tercero = id_tercero;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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