/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IListaValor;
import edu.uniajc.proyeccionSocial.persistence.DAO.ListaValorDao;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IListaValorDao;
import edu.uniajc.proyeccionSocial.persistence.Model.ListaValor;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class ListaValorServices implements IListaValor {

    IListaValorDao dao;

    public ListaValorServices(Connection connection) {

        this.dao = new ListaValorDao(connection);
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

            return dao.deleteListaValor(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateListaValor(ListaValor listaValor) {
        try {

            return dao.updateListaValor(listaValor);

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

}
