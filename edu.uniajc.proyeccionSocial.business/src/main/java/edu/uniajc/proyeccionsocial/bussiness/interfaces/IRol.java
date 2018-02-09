/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public interface IRol {

    public int createRol(Rol rol);

    public boolean deleteRol(int id);

    public boolean updateRol(Rol rol);

    public ArrayList<Rol> getAllRol();

    public Rol getRolById(int id);
    
    public List<Rol> getRolesByUser(int idUsuario);

}
