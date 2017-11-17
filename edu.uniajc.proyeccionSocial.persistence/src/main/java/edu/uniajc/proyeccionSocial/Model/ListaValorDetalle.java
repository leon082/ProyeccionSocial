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
public class ListaValorDetalle {
    private int id_ListaValorDetalle;
    private int id_ListaValor;
    private String valor;
    private int estado;
    private String creadoPor;
    private Date creadoEn;
    private String modificadoPor;
    private Date modificadoEn;

    public int getId_ListaValorDetalle() {
        return id_ListaValorDetalle;
    }

    public void setId_ListaValorDetalle(int id_ListaValorDetalle) {
        this.id_ListaValorDetalle = id_ListaValorDetalle;
    }

    public int getId_ListaValor() {
        return id_ListaValor;
    }

    public void setId_ListaValor(int id_ListaValor) {
        this.id_ListaValor = id_ListaValor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
