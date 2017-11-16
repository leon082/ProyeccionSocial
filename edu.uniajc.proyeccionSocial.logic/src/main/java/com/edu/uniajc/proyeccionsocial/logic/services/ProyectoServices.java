/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IProyecto;
import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;

import edu.uniajc.proyeccionSocial.DAO.ProyectoDao;
import edu.uniajc.proyeccionSocial.Model.Proyecto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

/**
 * @author Fabian Castro - IRIS 15/05/2017 Nombre Clase:ProyectoServices
 * Descripcion: logica de la clase proyecto
 */
@Stateless
public class ProyectoServices implements IProyecto {

    ConexionBD cn;
    ProyectoDao dao;

    public ProyectoServices() {
        this.cn = new ConexionBD();
        this.dao = new ProyectoDao(cn.conexion());
    }

    @Override
    public int createProyecto(Proyecto proyecto) {
        try {

            // validacion de Data
            if (proyecto != null) {

                int flag = dao.createProyecto(proyecto);

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
    public boolean deleteProyecto(int ID) {
        try {

            dao.deleteProyecto(ID);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProyecto(Proyecto proyecto) {
        try {

            dao.updateProyecto(proyecto);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Proyecto> getAllProyectos() {
        try {

            ArrayList<Proyecto> list = dao.getAllProyectos();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PreDestroy
    public void finish() {
        try {
            cn.conexion().close();

        } catch (SQLException sqle) {
            System.out.println("Error en ProyectoServices finish -->" + sqle.getMessage());
            Logger.getLogger(ProyectoServices.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
