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
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class ServicioEtapaServices implements IServicioEtapa {

    IServicioEtapaDao dao;
    private static final Logger LOGGER =  Logger.getLogger(ServicioEtapaServices.class.getName());

    public ServicioEtapaServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new ServicioEtapaDAO(connection);
    }

    @Override
    public int createServicioEtapa(ServicioEtapa servicioEtapa) {
        try {

            // validacion de Data
            if (servicioEtapa != null) {

                int flag = dao.createServicioEtapa(servicioEtapa);

                return flag;
            } else {
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error ServicioEtapaServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteServicioEtapa(int id) {
        try {

            return dao.deleteServicioEtapa(id);

        } catch (Exception e) {
            LOGGER.error("Error ServicioEtapaServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateServicioEtapa(ServicioEtapa servicioEtapa) {
        try {

            return dao.updateServicioEtapa(servicioEtapa);
        } catch (Exception e) {
            LOGGER.error("Error ServicioEtapaServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<ServicioEtapa> getAllServicioEtapa() {
        try {

            ArrayList<ServicioEtapa> list = dao.getAllServicioEtapa();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ServicioEtapaServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ServicioEtapa getServicioEtapaById(int id) {
        try {

            ServicioEtapa servicioEtapa = dao.getServicioEtapaById(id);

            return servicioEtapa;

        } catch (Exception e) {
            LOGGER.error("Error ServicioEtapaServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public boolean deleteEtapaServicioByServicio(int id) {
        try {

            return dao.deleteEtapaServicioByServicio(id);

        } catch (Exception e) {
            LOGGER.error("Error ServicioEtapaServices"+e.getMessage() );
            return false;
        }
    }

}
