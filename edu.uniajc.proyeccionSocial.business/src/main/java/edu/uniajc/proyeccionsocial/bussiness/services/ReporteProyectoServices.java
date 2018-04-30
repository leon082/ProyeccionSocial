/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.persistence.DAO.ReporteProyectoDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IReporteProyectoDao;
import edu.uniajc.proyeccionSocial.persistence.Model.ReporteProyecto;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IReporteProyecto;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author LuisLeon
 */
public class ReporteProyectoServices implements IReporteProyecto {

    IReporteProyectoDao dao;
    private static final Logger LOGGER =  Logger.getLogger(ReporteProyectoServices.class.getName());

    public ReporteProyectoServices(Connection connection) {
org.apache.log4j.BasicConfigurator.configure();
        this.dao = new ReporteProyectoDAO(connection);
    }

    @Override
    public ArrayList<ReporteProyecto> getAllProyect(int idPrograma, int idServicio, int idTerceroOferente, int idTerceroCreadoPor, Date fechaDesde, Date fechaHasta, int estado, int facultad) {
        try {

            ArrayList<ReporteProyecto> list = dao.getAllProyect(idPrograma, idServicio, idTerceroOferente, idTerceroCreadoPor, fechaDesde, fechaHasta, estado,facultad);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error ReporteProyectoServices"+e.getMessage() );
            return null;
        }
    }

}
