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
public class SoporteProyectoEtapa {
    private int id_soporteproyectoetapa;
    private int id_proyectoetapa;
    private String archivo;
    private String creadopor;
    private Date creadoen;
    private String modificadopor;
    private Date modificadoen;

    public int getId_soporteproyectoetapa() {
        return id_soporteproyectoetapa;
    }

    public void setId_soporteproyectoetapa(int id_soporteproyectoetapa) {
        this.id_soporteproyectoetapa = id_soporteproyectoetapa;
    }

    public int getId_proyectoetapa() {
        return id_proyectoetapa;
    }

    public void setId_proyectoetapa(int id_proyectoetapa) {
        this.id_proyectoetapa = id_proyectoetapa;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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