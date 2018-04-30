/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProgramaServicio;
import edu.uniajc.proyeccionSocial.persistence.DAO.ProgramaServicioDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IProgramaServicioDao;
import edu.uniajc.proyeccionSocial.persistence.Model.ProgramaServicio;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class ProgramaServicioServices implements IProgramaServicio {

    IProgramaServicioDao dao;
    private static final Logger LOGGER =  Logger.getLogger(ProgramaServicioServices.class.getName());

    public ProgramaServicioServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new ProgramaServicioDAO(connection);
    }

    @Override
    public int createProgramaServicio(ProgramaServicio progServi) {
        try {

            // validacion de Data
            if (progServi != null) {

                int flag = dao.createProgramaServicio(progServi);

                return flag;
            } else {
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error ProgramaServicioServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteProgramaServicio(int id) {
        try {

            return dao.deleteProgramaServicio(id);

        } catch (Exception e) {
            LOGGER.error("Error ProgramaServicioServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateProgramaServicio(ProgramaServicio progServi) {
        try {

            return dao.updateProgramaServicio(progServi);

        } catch (Exception e) {
            LOGGER.error("Error ProgramaServicioServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<ProgramaServicio> getAllProgramaServicioByPrograma(int idPrograma) {
        try {

            ArrayList<ProgramaServicio> list = dao.getAllProgramaServicioByPrograma(idPrograma);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ProgramaServicioServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ProgramaServicio getProgramaServicioById(int id) {
        try {

            ProgramaServicio programaServicio = dao.getProgramaServicioById(id);

            return programaServicio;

        } catch (Exception e) {
            LOGGER.error("Error ProgramaServicioServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public boolean deleteProgramaServicioByProg(int id) {
        try {

            return dao.deleteProgramaServicioByProg(id);

        } catch (Exception e) {
            LOGGER.error("Error ProgramaServicioServices"+e.getMessage() );
            return false;
        }
    }

}
