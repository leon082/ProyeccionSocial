/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public interface IUsuarioDao {

    public int createUsuario(Usuario user);

    public boolean deleteUsuario(int id);

    public boolean updateUsuario(Usuario usuario);

    public ArrayList<Usuario> getAllUsuario();

    public Usuario getUserById(int id);

    public Usuario getUsuarioLogin(String user, String password);

    public Usuario getUserByUsername(String user);

    public String getEmailByUsername(String user);

}
