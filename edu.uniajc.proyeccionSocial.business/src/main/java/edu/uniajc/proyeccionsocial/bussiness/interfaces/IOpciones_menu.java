/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public interface IOpciones_menu {
 public List<Opciones_menu> getMenuCuentaByUser(Usuario user);   
 public List<Opciones_menu> getMenuParametrizarByUser(Usuario user);   
 public List<Opciones_menu> getMenuProyectosByUser(Usuario user);   
 public List<Opciones_menu> getMenuUsuariosByUser(Usuario user);   
}
