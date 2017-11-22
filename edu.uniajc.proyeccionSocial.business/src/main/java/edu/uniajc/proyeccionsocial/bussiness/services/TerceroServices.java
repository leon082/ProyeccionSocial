/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.ITercero;
import edu.uniajc.proyeccionSocial.DAO.TerceroDAO;

import edu.uniajc.proyeccionSocial.Model.Tercero;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class TerceroServices implements ITercero {

    TerceroDAO dao;

    public TerceroServices() {

        this.dao = new TerceroDAO();
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

            dao.deleteTercero(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateTercero(Tercero tercero) {
        try {
            tercero.setCorreo(tercero.getCorreo().toLowerCase());
            dao.updateTercero(tercero);
            return true;
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

}
