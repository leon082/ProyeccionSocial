/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IRol;
import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.DAO.RolDao;
import edu.uniajc.proyeccionSocial.Model.Rol;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

/**
 *
 * @author luis.leon
 */
@Stateless
public class RolServices implements IRol {
    
     ConexionBD cn;
     RolDao dao ;

    public RolServices() {
        this.cn = new ConexionBD();
        this.dao= new RolDao(cn.conexion());
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
    
     @PreDestroy
    public void finish() {
        try {
            cn.conexion().close();

        } catch (SQLException sqle) {
            System.out.println("Error en RolServices finish -->" + sqle.getMessage());
            Logger.getLogger(RolServices.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
