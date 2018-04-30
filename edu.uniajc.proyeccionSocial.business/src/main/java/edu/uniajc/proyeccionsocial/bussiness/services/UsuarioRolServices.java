/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuarioRol;
import edu.uniajc.proyeccionSocial.persistence.DAO.UsuarioRolDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IUsuarioRolDao;

import edu.uniajc.proyeccionSocial.persistence.Model.UsuarioRol;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class UsuarioRolServices implements IUsuarioRol {

    IUsuarioRolDao dao;
    private static final Logger LOGGER =  Logger.getLogger(UsuarioRolServices.class.getName());

    public UsuarioRolServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new UsuarioRolDAO(connection);
    }

    @Override
    public int createUsuarioRol(UsuarioRol usuarioRol) {
        try {

            // validacion de Data
            if (usuarioRol != null) {

                int flag = dao.createUsuarioRol(usuarioRol);

                return flag;
            } else {
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error UsuarioRolServices  "+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteUsuarioRol(int id) {
        try {

            return dao.deleteUsuarioRol(id);

        } catch (Exception e) {
            LOGGER.error("Error UsuarioRolServices  "+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateUsuarioRol(UsuarioRol usuarioRol) {
        try {

            return dao.updateUsuarioRol(usuarioRol);
        } catch (Exception e) {
            LOGGER.error("Error UsuarioRolServices  "+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<UsuarioRol> getAllUsuarioRol() {
        try {

            ArrayList<UsuarioRol> list = dao.getAllUsuarioRol();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error UsuarioRolServices  "+e.getMessage() );
            return null;
        }
    }

    @Override
    public UsuarioRol getUsuarioRolById(int id) {
        try {

            UsuarioRol usuarioRol = dao.getUsuarioRolById(id);

            return usuarioRol;

        } catch (Exception e) {
            LOGGER.error("Error UsuarioRolServices  "+e.getMessage() );
            return null;
        }
    }

    @Override
    public boolean deleteRolesByUser(int idUser) {
        try {

            return dao.deleteRolesByUser(idUser);

        } catch (Exception e) {
            LOGGER.error("Error UsuarioRolServices  "+e.getMessage() );
            return false;
        }
    }

}
