/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IRol;
import edu.uniajc.proyeccionSocial.persistence.DAO.RolDao;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IRolDao;
import edu.uniajc.proyeccionSocial.persistence.Model.Rol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public class RolServices implements IRol {

    IRolDao dao;

    public RolServices() {

        this.dao = new RolDao();
    }

    @Override
    public int createRol(Rol rol) {
        try {

            // validacion de Data
            if (rol != null) {

                int flag = dao.createRol(rol);

                return flag;
            } else {
                System.out.println("Faltan Datos en pantalla");
                return 0;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean deleteRol(int id) {
        try {

            dao.deleteRol(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateRol(Rol rol) {
        try {

            dao.updateRol(rol);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Rol> getAllRol() {
        try {

            ArrayList<Rol> list = dao.getAllRol();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Rol getRolById(int id) {
        try {

            Rol rol = dao.getRolById(id);

            return rol;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Rol> getRolesByUser(int idUsuario) {
         try {

            List<Rol> list = dao.getRolesByUser(idUsuario);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
