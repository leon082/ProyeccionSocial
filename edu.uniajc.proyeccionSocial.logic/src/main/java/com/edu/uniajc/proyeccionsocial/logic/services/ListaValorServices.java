/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IListaValor;
import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.DAO.ListaValorDao;
import edu.uniajc.proyeccionSocial.Model.ListaValor;
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
public class ListaValorServices implements IListaValor{
    
       ConexionBD cn;
     ListaValorDao dao ;

    public ListaValorServices() {
        this.cn = new ConexionBD();
        this.dao= new ListaValorDao(cn.conexion());
    }
    
    @Override
    public int createListaValor(ListaValor listaValor) {
         try {

            // validacion de Data
            if (listaValor != null) {              

                int flag = dao.createListaValor(listaValor);
                

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
    public boolean deleteListaValor(int id) {
        try {
            
            dao.deleteListaValor(id);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateListaValor(ListaValor listaValor) {
        try {
           
            dao.updateListaValor(listaValor);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<ListaValor> getAllListaValor() {
         try {

            ArrayList<ListaValor> list = dao.getAllListaValor();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ListaValor getListaValorById(int id) {
      

             try {

            ListaValor listaValor = dao.getListaValorById(id);

            return listaValor;

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
            System.out.println("Error en ListaValorServices finish -->" + sqle.getMessage());
            Logger.getLogger(ListaValorServices.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
