/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.persistence.DAO.EtapaDAO;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEtapa;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IEtapaDao;
import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class EtapaServices implements IEtapa {

    IEtapaDao dao;
    private static final Logger LOGGER =  Logger.getLogger(EtapaServices.class.getName());

    public EtapaServices(Connection connection) {
        org.apache.log4j.BasicConfigurator.configure();
        this.dao = new EtapaDAO(connection);
    }

    @Override
    public int createEtapa(Etapa etapa) {
        try {

            // validacion de Data
            if (etapa != null) {

                int flag = dao.createEtapa(etapa);

                return flag;
            } else {
                LOGGER.error("EtapaServices Faltan datos en pantallas ");
                
                return 0;

            }
        } catch (Exception e) {
           LOGGER.error("Error EtapaServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteEtapa(int id) {
        try {

            return dao.deleteEtapa(id);

        } catch (Exception e) {
            LOGGER.error("Error EtapaServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateEtapa(Etapa etapa) {
        try {

            return dao.updateEtapa(etapa);

        } catch (Exception e) {
            LOGGER.error("Error EtapaServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<Etapa> getAllEtapa() {
        try {

            ArrayList<Etapa> list = dao.getAllEtapa();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error EtapaServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public Etapa getEtapaById(int id) {
        try {

            Etapa etapa = dao.getEtapaById(id);

            return etapa;

        } catch (Exception e) {
            LOGGER.error("Error EtapaServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public boolean isInServ(int idEtapa) {
        try {
            return dao.isInServ(idEtapa);

        } catch (Exception e) {
            LOGGER.error("Error EtapaServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<Etapa> getAllEtapaByServicio(int idServicio) {
        try {

            ArrayList<Etapa> list = dao.getAllEtapaByServicio(idServicio);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error EtapaServices"+e.getMessage() );
            return null;
        }
    }

}
