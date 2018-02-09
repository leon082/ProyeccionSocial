/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOferente;
import edu.uniajc.proyeccionSocial.persistence.DAO.OferenteDao;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IOferenteDao;
import edu.uniajc.proyeccionSocial.persistence.Model.Oferente;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public class OferenteServices implements IOferente {

    IOferenteDao dao;

    public OferenteServices(Connection connection) {

        this.dao = new OferenteDao(connection);
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
