/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyecto;

import edu.uniajc.proyeccionSocial.persistence.DAO.ProyectoDao;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IProyectoDao;
import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author Fabian Castro - IRIS 15/05/2017 Nombre Clase:ProyectoServices
 * Descripcion: logica de la clase proyecto
 */
public class ProyectoServices implements IProyecto {

    IProyectoDao dao;
    private static final Logger LOGGER =  Logger.getLogger(ProyectoServices.class.getName());

    public ProyectoServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new ProyectoDao(connection);
    }

    @Override
    public int createProyecto(Proyecto proyecto) {
        try {

            // validacion de Data
            if (proyecto != null) {

                int flag = dao.createProyecto(proyecto);

                return flag;
            } else {
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteProyecto(int ID) {
        try {

            return dao.deleteProyecto(ID);

        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean updateProyecto(Proyecto proyecto) {
        try {

            return dao.updateProyecto(proyecto);

        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public ArrayList<Proyecto> getAllProyectoPendiente() {
        try {

            ArrayList<Proyecto> list = dao.getAllProyectoPendiente();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public boolean tieneProyectoPendiente(String usuario) {
        try {
            return dao.tieneProyectoPendiente(usuario);

        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return false;
        }
    }

    @Override
    public List<Proyecto> getProyectoByUser(String user) {
        try {
            List<Proyecto> proyectos = dao.getProyectoByUser(user);

            return proyectos;
        } catch (Exception e) {
            
            LOGGER.error("Error en grProyectoByUser Services"+e.getMessage() );
            return null;
        }
    }

    @Override
    public Proyecto getProyectoById(int id) {
        try {
            Proyecto proyecto = dao.getProyectoById(id);

            return proyecto;
        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ArrayList<Proyecto> getAllProyectoAprobado() {
        try {

            ArrayList<Proyecto> list = dao.getAllProyectoAprobado();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ArrayList<Proyecto> getAllProyectoFinalizado() {
        try {

            ArrayList<Proyecto> list = dao.getAllProyectoFinalizado();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return null;
        }
    }

    @Override
    public ArrayList<Proyecto> getAllProyectoCancelado() {
        try {

            ArrayList<Proyecto> list = dao.getAllProyectoCancelado();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ProyectoServices"+e.getMessage() );
            return null;
        }
    }

}
