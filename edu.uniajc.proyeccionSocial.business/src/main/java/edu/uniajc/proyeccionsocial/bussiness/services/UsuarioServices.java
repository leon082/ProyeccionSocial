/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.persistence.DAO.UsuarioDao;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IUsuarioDao;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;

import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class UsuarioServices implements IUsuario {

    IUsuarioDao dao;

    public UsuarioServices(Connection connection) {
        this.dao = new UsuarioDao(connection);
    }

    @Override
    public int createUsuario(Usuario user) {
        try {
            // validacion de Data
            if (user != null) {
                user.setUsuario(user.getUsuario().toLowerCase().trim());
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
            return dao.updateUsuario(usuario);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> getAllUsuario() {
        try {
            ArrayList<Usuario> list = dao.getAllUsuario();
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

    @Override
    public Usuario getUsuarioLogin(String user, String password) {
        try {
            Usuario usuario = dao.getUsuarioLogin(user.toLowerCase(), password);
            return usuario;
        } catch (Exception e) {
            System.out.println("---------------------------------------- USUARIO SERVICE ----------------------------------------");
            System.out.print(e);
            return null;
        }
    }

    @Override
    public Usuario getUserByUsername(String user) {
        try {
            Usuario usuario = dao.getUserByUsername(user.toLowerCase());
            return usuario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String getEmailByUsername(String user) {
        try {
            String correo = dao.getEmailByUsername(user.toLowerCase());
            return correo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
