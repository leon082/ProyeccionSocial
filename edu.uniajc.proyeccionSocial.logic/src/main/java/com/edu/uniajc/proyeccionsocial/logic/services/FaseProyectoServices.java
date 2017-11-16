/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IFaseProyecto;
import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.DAO.FaseProyectoDAO;

import edu.uniajc.proyeccionSocial.Model.FaseProyecto;
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
public class FaseProyectoServices implements IFaseProyecto {

     ConexionBD cn;
     FaseProyectoDAO dao ;

    public FaseProyectoServices() {
        this.cn = new ConexionBD();
        this.dao= new FaseProyectoDAO(cn.conexion());
    }

    @Override
    public int createFaseProyecto(FaseProyecto faseProyecto) {
        try {

            // validacion de Data
            if (faseProyecto != null) {              

                int flag = dao.createFaseProyecto(faseProyecto);
                

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
    public boolean deleteFaseProyecto(int id) {
        try {
            
            dao.deleteFaseProyecto(id);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateFaseProyecto(FaseProyecto faseProyecto) {
        try {
           
            dao.updateFaseProyecto(faseProyecto);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<FaseProyecto> getAllFaseProyectos() {
        try {

            ArrayList<FaseProyecto> list = dao.getAllFaseProyectos();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public FaseProyecto getFaseProyectoById(int id) {
         try {

            FaseProyecto faseProyecto = dao.getFaseProyectoById(id);

            return faseProyecto;

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
            System.out.println("Error en FaseProyectoServices finish -->" + sqle.getMessage());
            Logger.getLogger(ProyectoServices.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
