/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.interfaces;

import edu.uniajc.proyeccionSocial.interfaces.model.Rol;
import java.util.ArrayList;

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

}
