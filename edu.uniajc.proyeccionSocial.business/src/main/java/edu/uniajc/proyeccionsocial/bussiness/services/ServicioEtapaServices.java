/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicioEtapa;
import edu.uniajc.proyeccionSocial.persistence.DAO.ServicioEtapaDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IServicioEtapaDao;

import edu.uniajc.proyeccionSocial.persistence.Model.ServicioEtapa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class ServicioEtapaServices implements IServicioEtapa {

    IServicioEtapaDao dao;

    public ServicioEtapaServices() {

        this.dao = new ServicioEtapaDAO();
    }

    @Override
    public int createServicioEtapa(ServicioEtapa servicioEtapa) {
        try {

            // validacion de Data
            if (servicioEtapa != null) {

                int flag = dao.createServicioEtapa(servicioEtapa);

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
    public boolean deleteServicioEtapa(int id) {
        try {

            dao.deleteServicioEtapa(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateServicioEtapa(ServicioEtapa servicioEtapa) {
        try {
            dao.updateServicioEtapa(servicioEtapa);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<ServicioEtapa> getAllServicioEtapa() {
        try {

            ArrayList<ServicioEtapa> list = dao.getAllServicioEtapa();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ServicioEtapa getServicioEtapaById(int id) {
        try {

            ServicioEtapa servicioEtapa = dao.getServicioEtapaById(id);

            return servicioEtapa;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteEtapaServicioByServicio(int id) {
        try {

            dao.deleteEtapaServicioByServicio(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
