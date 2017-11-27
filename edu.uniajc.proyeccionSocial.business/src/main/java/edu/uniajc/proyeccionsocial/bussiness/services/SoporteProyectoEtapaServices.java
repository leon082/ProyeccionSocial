/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.ISoporteProyectoEtapa;
import edu.uniajc.proyeccionSocial.DAO.SoporteProyectoEtapaDAO;

import edu.uniajc.proyeccionSocial.Model.SoporteProyectoEtapa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class SoporteProyectoEtapaServices implements ISoporteProyectoEtapa {

    SoporteProyectoEtapaDAO dao;

    public SoporteProyectoEtapaServices() {

        this.dao = new SoporteProyectoEtapaDAO();
    }

    @Override
    public int createSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        try {

            // validacion de Data
            if (soporteProyectoEtapa != null) {

                int flag = dao.createSoporteProyectoEtapa(soporteProyectoEtapa);

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
    public boolean updateSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        try {
            dao.updateSoporteProyectoEtapa(soporteProyectoEtapa);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<SoporteProyectoEtapa> getAllSoporteProyectoEtapa() {
        try {

            ArrayList<SoporteProyectoEtapa> list = dao.getAllSoporteProyectoEtapa();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public SoporteProyectoEtapa getSoporteProyectoEtapaById(int id) {
        try {

            SoporteProyectoEtapa soporteProyectoEtapa = dao.getSoporteProyectoEtapaById(id);

            return soporteProyectoEtapa;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
