/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IUsuario;
import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.DAO.UsuarioDao;
import edu.uniajc.proyeccionSocial.Model.Usuario;
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
public class UsuarioServices implements IUsuario{
    
     ConexionBD cn;
     UsuarioDao dao ;

    public UsuarioServices() {
        this.cn = new ConexionBD();
        this.dao= new UsuarioDao(cn.conexion());
    }

    @Override
    public int createUsuario(Usuario user) {
        try {

            // validacion de Data
            if (user != null) {              

                int flag = dao.createUsuario(user);
                

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
    public boolean deleteUsuario(int id) {
        try {
            
            dao.deleteUsuario(id);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        try {
           
            dao.updateUsuario(usuario);
           

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> getAllUsuarios() {
        try {

            ArrayList<Usuario> list = dao.getAllUsuarios();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Usuario getUserById(int id) {
       try {

            Usuario usuario = dao.getUserById(id);

            return usuario;

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
            System.out.println("Error en UsuarioServices finish -->" + sqle.getMessage());
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
