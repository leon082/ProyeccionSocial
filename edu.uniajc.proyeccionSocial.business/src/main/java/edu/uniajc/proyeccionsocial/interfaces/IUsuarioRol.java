/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.UsuarioRol;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IUsuarioRol {

    public int createUsuarioRol(UsuarioRol usuarioRol);

    public boolean deleteUsuarioRol(int id);

    public boolean updateUsuarioRol(UsuarioRol usuarioRol);

    public ArrayList<UsuarioRol> getAllUsuarioRol();

    public UsuarioRol getUsuarioRolById(int id);
    
    public boolean deleteRolesByUser(int idUser);

}
