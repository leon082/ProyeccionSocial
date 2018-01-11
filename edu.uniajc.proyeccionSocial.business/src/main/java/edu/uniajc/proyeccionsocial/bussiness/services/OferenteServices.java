/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IOferente;
import edu.uniajc.proyeccionSocial.DAO.OferenteDao;
import edu.uniajc.proyeccionSocial.Model.Oferente;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class OferenteServices implements IOferente {

    OferenteDao dao;

    public OferenteServices() {

        this.dao = new OferenteDao();
    }

    @Override
    public int createOferente(Oferente oferente) {
        try {

            // validacion de Data
            if (oferente != null) {

                int flag = dao.createOferente(oferente);

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
    public boolean deleteOferente(int id) {
        try {

            dao.deleteOferente(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateOferente(Oferente oferente) {
        try {

            dao.updateOferente(oferente);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Oferente> getAllOferente() {
        try {

            ArrayList<Oferente> list = dao.getAllOferente();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Oferente getOferenteById(int id) {
        try {

            Oferente oferente = dao.getOferenteById(id);

            return oferente;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Oferente getOferenteByProyecto(int id) {
         try {

            Oferente oferente = dao.getOferenteByProyecto(id);

            return oferente;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
