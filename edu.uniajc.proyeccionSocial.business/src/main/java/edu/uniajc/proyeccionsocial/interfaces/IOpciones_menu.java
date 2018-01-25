/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public interface IOpciones_menu {
 public List<Opciones_menu> getMenuByUser(Usuario user);   
}
