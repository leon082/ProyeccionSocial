/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public interface IOpciones_menuDao {
 public List<Opciones_menu> getMenuByUser(Usuario user);   
}
