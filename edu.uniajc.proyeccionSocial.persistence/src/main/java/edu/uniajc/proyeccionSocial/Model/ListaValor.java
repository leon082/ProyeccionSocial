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
public class ListaValor {
    private int id_listavalor;
    private String agrupacion;
    private String descripcion;
    private int estado;
    private String creadopor;
    private Date creadoen;
    private String modificadopor;
    private Date modificadoen;

    public int getId_listavalor() {
        return id_listavalor;
    }

    public void setId_listavalor(int id_listavalor) {
        this.id_listavalor = id_listavalor;
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
}