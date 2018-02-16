/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionSocial.persistence.DAO.TerceroDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.ITerceroDao;

import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class TerceroServices implements ITercero {

    ITerceroDao dao;

    public TerceroServices(Connection connection) {

        this.dao = new TerceroDAO(connection);
    }

    @Override
    public int createTercero(Tercero tercero) {
        try {

            // validacion de Data
            if (tercero != null) {
                tercero.setCorreo(tercero.getCorreo().toLowerCase());
                int flag = dao.createTercero(tercero);

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
    public boolean deleteTercero(int id) {
        try {

            return dao.deleteTercero(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateTercero(Tercero tercero) {
        try {
            tercero.setCorreo(tercero.getCorreo().toLowerCase());

            return dao.updateTercero(tercero);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Tercero> getAllTercero() {
        try {

            ArrayList<Tercero> list = dao.getAllTercero();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tercero getTerceroById(int id) {
        try {

            Tercero tercero = dao.getTerceroById(id);

            return tercero;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tercero getTerceroByIdentificacion(int tipoDoc, String doc) {
        try {

            Tercero tercero = dao.getTerceroByIdentificacion(tipoDoc, doc);

            return tercero;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Tercero> getAllTerceroUsuario() {
        try {

            ArrayList<Tercero> list = dao.getAllTerceroUsuario();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
