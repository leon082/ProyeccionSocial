/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IProyecto;

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
    public ArrayList<Proyecto> getAllProyectoPendiente() {
        try {

            ArrayList<Proyecto> list = dao.getAllProyectoPendiente();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean tieneProyectoPendiente(String usuario) {
        try {
            return dao.tieneProyectoPendiente(usuario);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Proyecto getProyectoByUser(String user) {
        try {
            Proyecto proyecto = dao.getProyectoByUser(user);

            return proyecto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Proyecto getProyectoById(int id) {
        try {
            Proyecto proyecto = dao.getProyectoById(id);

            return proyecto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
