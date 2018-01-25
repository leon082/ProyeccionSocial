/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.DAO.Opciones_menuDAO;
import edu.uniajc.proyeccionSocial.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import edu.uniajc.proyeccionsocial.interfaces.IOpciones_menu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public class MenuServices implements IOpciones_menu{
    Opciones_menuDAO dao;

    public MenuServices() {

        this.dao = new Opciones_menuDAO();
    }
    @Override
    public List<Opciones_menu> getMenuByUser(Usuario user) {
        try {

            List<Opciones_menu> list = dao.getMenuByUser(user);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
