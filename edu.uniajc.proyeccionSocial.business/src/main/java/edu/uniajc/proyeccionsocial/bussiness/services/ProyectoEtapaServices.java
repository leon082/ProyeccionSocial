/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IProyectoEtapa;
import edu.uniajc.proyeccionSocial.DAO.ProyectoEtapaDAO;

import edu.uniajc.proyeccionSocial.Model.ProyectoEtapa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class ProyectoEtapaServices implements IProyectoEtapa {

    ProyectoEtapaDAO dao;

    public ProyectoEtapaServices() {

        this.dao = new ProyectoEtapaDAO();
    }

    @Override
    public int createProyectoEtapa(ProyectoEtapa proyectoEtapa) {
        try {

            // validacion de Data
            if (proyectoEtapa != null) {

                int flag = dao.createProyectoEtapa(proyectoEtapa);

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
    public boolean deleteProyectoEtapa(int id) {
        try {

            dao.deleteProyectoEtapa(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProyectoEtapa(ProyectoEtapa proyectoEtapa) {
        try {
            dao.updateProyectoEtapa(proyectoEtapa);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<ProyectoEtapa> getAllProyectoEtapa() {
        try {

            ArrayList<ProyectoEtapa> list = dao.getAllProyectoEtapa();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ProyectoEtapa getProyectoEtapaById(int id) {
        try {

            ProyectoEtapa proyectoEtapa = dao.getProyectoEtapaById(id);

            return proyectoEtapa;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
