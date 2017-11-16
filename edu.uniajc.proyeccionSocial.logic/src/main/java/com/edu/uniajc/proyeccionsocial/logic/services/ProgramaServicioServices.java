/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IProgramaServicio;
import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.DAO.ProgramaServicioDAO;
import edu.uniajc.proyeccionSocial.Model.ProgramaServicio;
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
public class ProgramaServicioServices implements IProgramaServicio{

      ConexionBD cn;
     ProgramaServicioDAO dao ;

    public ProgramaServicioServices() {
        this.cn = new ConexionBD();
        this.dao= new ProgramaServicioDAO(cn.conexion());
    }
    @Override
    public int createProgramaServicio(ProgramaServicio progServi) {
        try {

            // validacion de Data
            if (progServi != null) {              

                int flag = dao.createProgramaServicio(progServi);
                

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
    public boolean deleteProgramaServicio(int id) {
        try {
            
            dao.deleteProgramaServicio(id);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProgramaServicio(ProgramaServicio progServi) {
         try {
           
            dao.updateProgramaServicio(progServi);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<ProgramaServicio> getAllProgramaServicioByPrograma(int idPrograma) {
         try {

            ArrayList<ProgramaServicio> list = dao.getAllProgramaServicioByPrograma(idPrograma);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ProgramaServicio getProgramaServicioById(int id) {
         try {

            ProgramaServicio programaServicio = dao.getProgramaServicioById(id);

            return programaServicio;

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
            System.out.println("Error en ProgramaServicioServices finish -->" + sqle.getMessage());
            Logger.getLogger(ProgramaServicioServices.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
