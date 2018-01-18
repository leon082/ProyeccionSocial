/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IServicio;
import edu.uniajc.proyeccionSocial.DAO.ServicioDAO;

import edu.uniajc.proyeccionSocial.Model.Servicio;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class ServicioServices implements IServicio {

    ServicioDAO dao;

    public ServicioServices() {

        this.dao = new ServicioDAO();
    }

    @Override
    public int createServicio(Servicio servicio) {
        try {

            // validacion de Data
            if (servicio != null) {

                int flag = dao.createServicio(servicio);

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
    public boolean deleteServicio(int id) {
        try {

            dao.deleteServicio(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateServicio(Servicio servicio) {
        try {
            dao.updateServicio(servicio);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Servicio> getAllServicio() {
        try {

            ArrayList<Servicio> list = dao.getAllServicio();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Servicio getServicioById(int id) {
        try {

            Servicio servicio = dao.getServicioById(id);

            return servicio;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Servicio> getAllServicioByProg(int idProg) {
        try {

            ArrayList<Servicio> list = dao.getAllServicioByProg(idProg);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isInProg(int idServicio) {
        try {
            return dao.isInProg(idServicio);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
