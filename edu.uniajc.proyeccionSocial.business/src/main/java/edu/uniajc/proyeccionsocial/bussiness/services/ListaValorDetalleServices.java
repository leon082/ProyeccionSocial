/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IListaValorDetalle;
import edu.uniajc.proyeccionSocial.persistence.DAO.ListaValorDetalleDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IListaValorDetalleDao;

import edu.uniajc.proyeccionSocial.persistence.Model.ListaValorDetalle;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class ListaValorDetalleServices implements IListaValorDetalle {

    IListaValorDetalleDao dao;
    private static final Logger LOGGER =  Logger.getLogger(ListaValorDetalleServices.class.getName());

    public ListaValorDetalleServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new ListaValorDetalleDAO(connection);
    }

    @Override
    public int createListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        try {

            // validacion de Data
            if (listaValorDetalle != null) {

                int flag = dao.createListaValorDetalle(listaValorDetalle);

                return flag;
            } else {
                LOGGER.error("Faltan datos en pantalla");
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error ListaValorDetalleServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteListaValorDetalle(int id) {
        try {

            return dao.deleteListaValorDetalle(id);

        } catch (Exception e) {
            LOGGER.error("Error ListaValorDetalleServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        try {

            return dao.updateListaValorDetalle(listaValorDetalle);
        } catch (Exception e) {
            LOGGER.error("Error ListaValorDetalleServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<ListaValorDetalle> getAllListaValorDetalle(String agrupa) {
        try {

            ArrayList<ListaValorDetalle> list = dao.getAllListaValorDetalle(agrupa);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ListaValorDetalleServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ListaValorDetalle getListaValorDetalleById(int idValor) {
        try {

            ListaValorDetalle listaValorDetalle = dao.getListaValorDetalleById(idValor);

            return listaValorDetalle;

        } catch (Exception e) {
            LOGGER.error("Error ListaValorDetalleServices"+e.getMessage() );
            return null;
        }
    }

}
