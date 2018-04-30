/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.ISoporteProyectoEtapa;
import edu.uniajc.proyeccionSocial.persistence.DAO.SoporteProyectoEtapaDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.ISoporteProyectoEtapaDao;

import edu.uniajc.proyeccionSocial.persistence.Model.SoporteProyectoEtapa;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class SoporteProyectoEtapaServices implements ISoporteProyectoEtapa {

    ISoporteProyectoEtapaDao dao;
    private static final Logger LOGGER =  Logger.getLogger(SoporteProyectoEtapaServices.class.getName());

    public SoporteProyectoEtapaServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new SoporteProyectoEtapaDAO(connection);
    }

    @Override
    public int createSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        try {

            // validacion de Data
            if (soporteProyectoEtapa != null) {

                int flag = dao.createSoporteProyectoEtapa(soporteProyectoEtapa);

                return flag;
            } else {
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error SoporteProyectoServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean updateSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        try {

            return dao.updateSoporteProyectoEtapa(soporteProyectoEtapa);
        } catch (Exception e) {
            LOGGER.error("Error SoporteProyectoServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<SoporteProyectoEtapa> getAllSoporteProyectoEtapa() {
        try {

            ArrayList<SoporteProyectoEtapa> list = dao.getAllSoporteProyectoEtapa();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error SoporteProyectoServices"+e.getMessage() );
            return null;
        }
    }
    
            @Override
    public List<SoporteProyectoEtapa> getSoporteProyectoEtapaByIdProyectoEtapa(int id) {
        try {

            List<SoporteProyectoEtapa> list = dao.getSoporteProyectoEtapaByIdProyectoEtapa(id);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error SoporteProyectoServices"+e.getMessage() );
            return null;
        }
    }


    @Override
    public SoporteProyectoEtapa getSoporteProyectoEtapaById(int id) {
        try {

            SoporteProyectoEtapa soporteProyectoEtapa = dao.getSoporteProyectoEtapaById(id);

            return soporteProyectoEtapa;

        } catch (Exception e) {
            LOGGER.error("Error SoporteProyectoServices"+e.getMessage() );
            return null;
        }
    }

}
