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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class UsuarioServices implements IUsuario {

    IUsuarioDao dao;
    private static final Logger LOGGER =  Logger.getLogger(UsuarioServices.class.getName());

    public UsuarioServices(Connection connection) {
        this.dao = new UsuarioDao(connection);
        org.apache.log4j.BasicConfigurator.configure();
    }

    @Override
    public int createUsuario(Usuario user) {
        try {
            // validacion de Data
            if (user != null) {
                user.setContrasena(generateHash(user.getContrasena()));
                user.setUsuario(user.getUsuario().toLowerCase().trim());
                int flag = dao.createUsuario(user);
                return flag;
            } else {
                
                return 0;
            }
        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteUsuario(int id) {
        try {

            return dao.deleteUsuario(id);

        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        try {
            usuario.setContrasena(generateHash(usuario.getContrasena()));
            return dao.updateUsuario(usuario);

        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> getAllUsuario() {
        try {
            ArrayList<Usuario> list = dao.getAllUsuario();
            return list;
        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            return null;
        }
    }

    @Override
    public Usuario getUserById(int id) {
        try {
            Usuario usuario = dao.getUserById(id);
            return usuario;
        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            return null;
        }
    }

    @Override
    public Usuario getUsuarioLogin(String user, String password) {
        try {
            Usuario usuario = dao.getUsuarioLogin(user.toLowerCase(), generateHash(password));
            return usuario;
        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            System.out.println("---------------------------------------- USUARIO SERVICE ----------------------------------------");
           
            return null;
        }
    }

    @Override
    public Usuario getUserByUsername(String user) {
        try {
            Usuario usuario = dao.getUserByUsername(user.toLowerCase());
            return usuario;
        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            return null;
        }
    }

    @Override
    public String getEmailByUsername(String user) {
        try {
            String correo = dao.getEmailByUsername(user.toLowerCase());
            return correo;
        } catch (Exception e) {
            LOGGER.error("Error UsuarioServices  "+e.getMessage() );
            return null;
        }
    }
    
     public String generateHash(String password) throws RuntimeException, NoSuchAlgorithmException {

        if (password == null && password.length() < 0) {
            System.err.println("String to MD5 digest should be first and only parameter");
            throw new RuntimeException();
        }
        String original = password;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }
}
