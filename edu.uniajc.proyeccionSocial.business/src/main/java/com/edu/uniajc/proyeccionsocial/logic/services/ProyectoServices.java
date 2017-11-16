/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.logic.services;

import com.edu.uniajc.proyeccionsocial.interfaces.IProyecto;

import edu.uniajc.proyeccionSocial.DAO.ProyectoDao;
import edu.uniajc.proyeccionSocial.Model.Proyecto;
import java.util.ArrayList;

/**
 * @author Fabian Castro - IRIS 15/05/2017 Nombre Clase:ProyectoServices
 * Descripcion: logica de la clase proyecto
 */
public class ProyectoServices implements IProyecto {

    ProyectoDao dao;

    public ProyectoServices() {

        this.dao = new ProyectoDao();
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

}
