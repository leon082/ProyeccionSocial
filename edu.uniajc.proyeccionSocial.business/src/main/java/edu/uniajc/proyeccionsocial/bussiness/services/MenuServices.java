/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.persistence.DAO.Opciones_menuDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IOpciones_menuDao;
import edu.uniajc.proyeccionSocial.persistence.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOpciones_menu;
import java.sql.Connection;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class MenuServices implements IOpciones_menu {

    IOpciones_menuDao dao;
    private static final Logger LOGGER =  Logger.getLogger(MenuServices.class.getName());

    public MenuServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new Opciones_menuDAO(connection);
    }

    @Override
    public List<Opciones_menu> getMenuCuentaByUser(Usuario user) {
        try {

            List<Opciones_menu> list = dao.getMenuCuentaByUser(user);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error MenuServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public List<Opciones_menu> getMenuParametrizarByUser(Usuario user) {
          try {

            List<Opciones_menu> list = dao.getMenuParametrizarByUser(user);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error MenuServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public List<Opciones_menu> getMenuProyectosByUser(Usuario user) {
          try {

            List<Opciones_menu> list = dao.getMenuProyectosByUser(user);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error MenuServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public List<Opciones_menu> getMenuUsuariosByUser(Usuario user) {
           try {

            List<Opciones_menu> list = dao.getMenuUsuariosByUser(user);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error MenuServices"+e.getMessage() );
            return null;
        }
    }

}
