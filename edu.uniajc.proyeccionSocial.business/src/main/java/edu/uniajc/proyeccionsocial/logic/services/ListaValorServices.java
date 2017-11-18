/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.logic.services;

import edu.uniajc.proyeccionsocial.interfaces.IListaValor;
import edu.uniajc.proyeccionSocial.DAO.ListaValorDao;
import edu.uniajc.proyeccionSocial.Model.ListaValor;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class ListaValorServices implements IListaValor {

    ListaValorDao dao;

    public ListaValorServices() {

        this.dao = new ListaValorDao();
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

}
