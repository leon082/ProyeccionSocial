/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IPrograma;
import edu.uniajc.proyeccionSocial.persistence.DAO.ProgramaDAO;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IProgramaDao;

import edu.uniajc.proyeccionSocial.persistence.Model.Programa;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class ProgramaServices implements IPrograma {

    IProgramaDao dao;

    public ProgramaServices(Connection connection) {

        this.dao = new ProgramaDAO(connection);
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

            return dao.deletePrograma(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updatePrograma(Programa programa) {
        try {

            return dao.updatePrograma(programa);
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
    
     @Override
    public boolean isInProy(int idPrograma) {
        try {
            return dao.isInProy(idPrograma);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
