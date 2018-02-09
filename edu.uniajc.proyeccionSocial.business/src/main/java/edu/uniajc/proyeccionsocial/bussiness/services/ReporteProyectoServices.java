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
import java.sql.Date;
import java.util.ArrayList;


/**
 *
 * @author LuisLeon
 */
public class ReporteProyectoServices implements IReporteProyecto{
    
    IReporteProyectoDao dao;

    public ReporteProyectoServices() {

        this.dao = new ReporteProyectoDAO();
    }

    @Override
    public ArrayList<ReporteProyecto> getAllProyect(int idPrograma, int idServicio, int idTerceroOferente, int idTerceroCreadoPor, Date fechaDesde, Date fechaHasta, int estado) {
         try {

            ArrayList<ReporteProyecto> list = dao.getAllProyect(idPrograma, idServicio, idTerceroOferente, idTerceroCreadoPor, fechaDesde, fechaHasta, estado);
            

            return list;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

   
    
}
