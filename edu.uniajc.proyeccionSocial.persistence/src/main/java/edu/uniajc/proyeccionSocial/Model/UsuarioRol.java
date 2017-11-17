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
public class UsuarioRol {
    private int id_UsuarioRol;
    private int id_Usuario;
    private int id_Rol;
    private int estadoUsuarioRol;
    private String creadoPor;
    private Date creadoEn;
    private String modificadoPor;
    private Date modificadoEn;

    public int getId_UsuarioRol() {
        return id_UsuarioRol;
    }

    public void setId_UsuarioRol(int id_UsuarioRol) {
        this.id_UsuarioRol = id_UsuarioRol;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }

    public int getEstadoUsuarioRol() {
        return estadoUsuarioRol;
    }

    public void setEstadoUsuarioRol(int estadoUsuarioRol) {
        this.estadoUsuarioRol = estadoUsuarioRol;
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
