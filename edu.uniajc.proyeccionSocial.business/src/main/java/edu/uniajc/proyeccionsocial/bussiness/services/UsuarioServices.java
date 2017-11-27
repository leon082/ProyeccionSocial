/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.DAO.UsuarioDao;
import edu.uniajc.proyeccionsocial.interfaces.IUsuario;

import edu.uniajc.proyeccionSocial.Model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class UsuarioServices implements IUsuario {

    UsuarioDao dao;

    public UsuarioServices() {

        this.dao = new UsuarioDao();
    }

    @Override
    public int createUsuario(Usuario user) {
        try {

            // validacion de Data
            if (user != null) {
                user.setUsuario(user.getUsuario().toLowerCase());
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
            System.out.println(e.getMessage());
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

}
