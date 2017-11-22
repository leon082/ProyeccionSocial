/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IFase;
import edu.uniajc.proyeccionSocial.DAO.FaseDAO;

import edu.uniajc.proyeccionSocial.Model.Fase;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class FaseServices implements IFase {

    FaseDAO dao;

    public FaseServices() {

        this.dao = new FaseDAO();
    }

    @Override
    public int createFase(Fase fase) {
        try {

            // validacion de Data
            if (fase != null) {

                int flag = dao.createFase(fase);

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
    public boolean deleteFase(int id) {
        try {

            dao.deleteFase(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateFase(Fase fase) {
        try {
            dao.updateFase(fase);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Fase> getAllFase() {
        try {

            ArrayList<Fase> list = dao.getAllFase();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Fase getFaseById(int id) {
        try {

            Fase fase = dao.getFaseById(id);

            return fase;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
