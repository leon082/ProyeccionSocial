/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IFaseProyecto;
import edu.uniajc.proyeccionSocial.DAO.FaseProyectoDAO;

import edu.uniajc.proyeccionSocial.Model.FaseProyecto;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class FaseProyectoServices implements IFaseProyecto {

    FaseProyectoDAO dao;

    public FaseProyectoServices() {

        this.dao = new FaseProyectoDAO();
    }

    @Override
    public int createFaseProyecto(FaseProyecto faseProyecto) {
        try {

            // validacion de Data
            if (faseProyecto != null) {

                int flag = dao.createFaseProyecto(faseProyecto);

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
    public boolean deleteFaseProyecto(int id) {
        try {

            dao.deleteFaseProyecto(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateFaseProyecto(FaseProyecto faseProyecto) {
        try {
            dao.updateFaseProyecto(faseProyecto);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<FaseProyecto> getAllFaseProyectos() {
        try {

            ArrayList<FaseProyecto> list = dao.getAllFaseProyectos();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public FaseProyecto getFaseProyectoById(int id) {
        try {

            FaseProyecto faseProyecto = dao.getFaseProyectoById(id);

            return faseProyecto;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
