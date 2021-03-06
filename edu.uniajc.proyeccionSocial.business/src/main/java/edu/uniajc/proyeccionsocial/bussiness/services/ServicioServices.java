/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IServicio;
import edu.uniajc.proyeccionSocial.persistence.DAO.ServicioDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IServicioDao;

import edu.uniajc.proyeccionSocial.persistence.Model.Servicio;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class ServicioServices implements IServicio {

    IServicioDao dao;
    private static final Logger LOGGER =  Logger.getLogger(ServicioServices.class.getName());

    public ServicioServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new ServicioDAO(connection);
    }

    @Override
    public int createServicio(Servicio servicio) {
        try {

            // validacion de Data
            if (servicio != null) {

                int flag = dao.createServicio(servicio);

                return flag;
            } else {
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error ServicioServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteServicio(int id) {
        try {

            return dao.deleteServicio(id);

        } catch (Exception e) {
            LOGGER.error("Error ServicioServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateServicio(Servicio servicio) {
        try {

            return dao.updateServicio(servicio);
        } catch (Exception e) {
            LOGGER.error("Error ServicioServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<Servicio> getAllServicio() {
        try {

            ArrayList<Servicio> list = dao.getAllServicio();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ServicioServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public Servicio getServicioById(int id) {
        try {

            Servicio servicio = dao.getServicioById(id);

            return servicio;

        } catch (Exception e) {
            LOGGER.error("Error ServicioServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ArrayList<Servicio> getAllServicioByProg(int idProg) {
        try {

            ArrayList<Servicio> list = dao.getAllServicioByProg(idProg);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ServicioServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public boolean isInProg(int idServicio) {
        try {
            return dao.isInProg(idServicio);

        } catch (Exception e) {
            LOGGER.error("Error ServicioServices"+e.getMessage() );
            return false;
        }
    }

}
