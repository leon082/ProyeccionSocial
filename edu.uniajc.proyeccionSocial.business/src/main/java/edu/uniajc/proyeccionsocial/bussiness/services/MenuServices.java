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

/**
 *
 * @author luis.leon
 */
public class MenuServices implements IOpciones_menu {

    IOpciones_menuDao dao;

    public MenuServices(Connection connection) {

        this.dao = new Opciones_menuDAO(connection);
    }

    @Override
    public List<Opciones_menu> getMenuCuentaByUser(Usuario user) {
        try {

            List<Opciones_menu> list = dao.getMenuCuentaByUser(user);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Opciones_menu> getMenuParametrizarByUser(Usuario user) {
          try {

            List<Opciones_menu> list = dao.getMenuParametrizarByUser(user);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Opciones_menu> getMenuProyectosByUser(Usuario user) {
          try {

            List<Opciones_menu> list = dao.getMenuProyectosByUser(user);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Opciones_menu> getMenuUsuariosByUser(Usuario user) {
           try {

            List<Opciones_menu> list = dao.getMenuUsuariosByUser(user);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
