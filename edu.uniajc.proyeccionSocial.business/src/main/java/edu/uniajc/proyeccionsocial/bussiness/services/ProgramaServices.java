/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IPrograma;
import edu.uniajc.proyeccionSocial.DAO.ProgramaDAO;

import edu.uniajc.proyeccionSocial.Model.Programa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class ProgramaServices implements IPrograma {

    ProgramaDAO dao;

    public ProgramaServices() {

        this.dao = new ProgramaDAO();
    }

    @Override
    public int createPrograma(Programa programa) {
        try {

            // validacion de Data
            if (programa != null) {

                int flag = dao.createPrograma(programa);

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
    public boolean deletePrograma(int id) {
        try {

            dao.deletePrograma(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updatePrograma(Programa programa) {
        try {
            dao.updatePrograma(programa);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Programa> getAllPrograma() {
        try {

            ArrayList<Programa> list = dao.getAllPrograma();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Programa getProgramaById(int id) {
        try {

            Programa programa = dao.getProgramaById(id);

            return programa;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
