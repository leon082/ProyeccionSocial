/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IOferente;
import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.DAO.OferenteDao;
import edu.uniajc.proyeccionSocial.Model.Oferente;
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
public class OferenteServices implements IOferente{
    
      ConexionBD cn;
     OferenteDao dao ;

    public OferenteServices() {
        this.cn = new ConexionBD();
        this.dao= new OferenteDao(cn.conexion());
    }

    @Override
    public int createOferente(Oferente oferente) {
        try {

            // validacion de Data
            if (oferente != null) {              

                int flag = dao.createOferente(oferente);
                

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
    public boolean deleteOferente(int id) {
        try {
            
            dao.deleteOferente(id);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateOferente(Oferente oferente) {
         try {
           
            dao.updateOferente(oferente);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Oferente> getAllOferentes() {
         try {

            ArrayList<Oferente> list = dao.getAllOferentes();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Oferente getOferenteById(int id) {
       try {

            Oferente oferente = dao.getOferenteById(id);

            return oferente;

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
            System.out.println("Error en OferenteServices finish -->" + sqle.getMessage());
            Logger.getLogger(OferenteServices.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
    
}
