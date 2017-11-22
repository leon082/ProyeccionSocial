/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IListaValorDetalle;
import edu.uniajc.proyeccionSocial.DAO.ListaValorDetalleDAO;

import edu.uniajc.proyeccionSocial.Model.ListaValorDetalle;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class ListaValorDetalleServices implements IListaValorDetalle {

    ListaValorDetalleDAO dao;

    public ListaValorDetalleServices() {

        this.dao = new ListaValorDetalleDAO();
    }

    @Override
    public int createListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        try {

            // validacion de Data
            if (listaValorDetalle != null) {

                int flag = dao.createListaValorDetalle(listaValorDetalle);

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
    public boolean deleteListaValorDetalle(int id) {
        try {

            dao.deleteListaValorDetalle(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        try {
            dao.updateListaValorDetalle(listaValorDetalle);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<ListaValorDetalle> getAllListaValorDetalle( int idValor) {
        try {

            ArrayList<ListaValorDetalle> list = dao.getAllListaValorDetalle(idValor);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ListaValorDetalle getListaValorDetalleById(int idValor) {
        try {

            ListaValorDetalle listaValorDetalle = dao.getListaValorDetalleById(idValor);

            return listaValorDetalle;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
