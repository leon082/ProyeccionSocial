/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.interfaces.model;

/**
 *
 * @author luis.leon
 */
public class Rol {
    
    private int id_Rol;
    private int valor;
    private String descripcion;
    private int estadoRol;

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(int estadoRol) {
        this.estadoRol = estadoRol;
    }
    
    
    
}
