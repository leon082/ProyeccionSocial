/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.Model;

import java.sql.Date;

/**
 *
 * @author rlara
 */
public class Tercero {
    private int id_tercero;
    private int id_lv_tipoidentificacion;
    private String numidentificacion;
    private String primernombre;
    private String segundonombre;
    private String primerapellido;
    private String segundoapellido;
    private Date fechanacimiento;
    private String telefonofijo;
    private String telefonocelular;
    private String correo;
    private int estado;
    private String creadopor;
    private Date creadoen;
    private String modificadopor;
    private Date modificadoen;
    private String nombreCompleto;

    public int getId_tercero() {
        return id_tercero;
    }

    public String getNombreCompleto() {
        return this.primernombre+" "+this.primerapellido;
    }
    
    

    public void setId_tercero(int id_tercero) {
        this.id_tercero = id_tercero;
    }

    public int getId_lv_tipoidentificacion() {
        return id_lv_tipoidentificacion;
    }

    public void setId_lv_tipoidentificacion(int id_lv_tipoidentificacion) {
        this.id_lv_tipoidentificacion = id_lv_tipoidentificacion;
    }

    public String getNumidentificacion() {
        return numidentificacion;
    }

    public void setNumidentificacion(String numidentificacion) {
        this.numidentificacion = numidentificacion;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getTelefonocelular() {
        return telefonocelular;
    }

    public void setTelefonocelular(String telefonocelular) {
        this.telefonocelular = telefonocelular;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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