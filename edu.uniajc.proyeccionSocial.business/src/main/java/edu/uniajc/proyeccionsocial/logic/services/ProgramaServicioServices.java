/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.logic.services;

import edu.uniajc.proyeccionsocial.interfaces.IProgramaServicio;
import edu.uniajc.proyeccionSocial.DAO.ProgramaServicioDAO;
import edu.uniajc.proyeccionSocial.Model.ProgramaServicio;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class ProgramaServicioServices implements IProgramaServicio {

    ProgramaServicioDAO dao;

    public ProgramaServicioServices() {

        this.dao = new ProgramaServicioDAO();
    }

    @Override
    public int createProgramaServicio(ProgramaServicio progServi) {
        try {

            // validacion de Data
            if (progServi != null) {

                int flag = dao.createProgramaServicio(progServi);

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
    public boolean deleteProgramaServicio(int id) {
        try {

            dao.deleteProgramaServicio(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProgramaServicio(ProgramaServicio progServi) {
        try {

            dao.updateProgramaServicio(progServi);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<ProgramaServicio> getAllProgramaServicioByPrograma(int idPrograma) {
        try {

            ArrayList<ProgramaServicio> list = dao.getAllProgramaServicioByPrograma(idPrograma);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ProgramaServicio getProgramaServicioById(int id) {
        try {

            ProgramaServicio programaServicio = dao.getProgramaServicioById(id);

            return programaServicio;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}