/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.Model;

/**
 *
 * @author luis.leon
 */
public class Usuario {
    
    private int id_Usuario;
    private int id_Tercero;
    private String usuario;
    private int estado;

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getId_Tercero() {
        return id_Tercero;
    }

    public void setId_Tercero(int id_Tercero) {
        this.id_Tercero = id_Tercero;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

   
    
    
    
}
