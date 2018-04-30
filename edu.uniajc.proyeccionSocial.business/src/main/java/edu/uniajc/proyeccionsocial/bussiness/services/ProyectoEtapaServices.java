/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyectoEtapa;
import edu.uniajc.proyeccionSocial.persistence.DAO.ProyectoEtapaDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IProyectoEtapaDao;

import edu.uniajc.proyeccionSocial.persistence.Model.ProyectoEtapa;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class ProyectoEtapaServices implements IProyectoEtapa {

    IProyectoEtapaDao dao;
    private static final Logger LOGGER =  Logger.getLogger(ProyectoEtapaServices.class.getName());

    public ProyectoEtapaServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new ProyectoEtapaDAO(connection);
    }

    @Override
    public int createProyectoEtapa(ProyectoEtapa proyectoEtapa) {
        try {

            // validacion de Data
            if (proyectoEtapa != null) {

                int flag = dao.createProyectoEtapa(proyectoEtapa);

                return flag;
            } else {
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error ProyectoEtapaServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteProyectoEtapa(int id) {
        try {

            return dao.deleteProyectoEtapa(id);

        } catch (Exception e) {
            LOGGER.error("Error ProyectoEtapaServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateProyectoEtapa(ProyectoEtapa proyectoEtapa) {
        try {

            return dao.updateProyectoEtapa(proyectoEtapa);
        } catch (Exception e) {
            LOGGER.error("Error ProyectoEtapaServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<ProyectoEtapa> getAllProyectoEtapaByProyecto(int idProyecto) {
        try {

            ArrayList<ProyectoEtapa> list = dao.getAllProyectoEtapaByProyecto(idProyecto);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ProyectoEtapaServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ProyectoEtapa getProyectoEtapaById(int id) {
        try {

            ProyectoEtapa proyectoEtapa = dao.getProyectoEtapaById(id);

            return proyectoEtapa;

        } catch (Exception e) {
            LOGGER.error("Error ProyectoEtapaServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ArrayList<ProyectoEtapa> getAllProyectoEtapasAprobadasByProyecto(int idProyecto) {
        try {

            ArrayList<ProyectoEtapa> list = dao.getAllProyectoEtapasAprobadasByProyecto(idProyecto);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ProyectoEtapaServices"+e.getMessage() );
            return null;
        }
    }
    
    
    }
    


