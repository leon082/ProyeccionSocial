/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IEtapa;
import edu.uniajc.proyeccionSocial.DAO.EtapaDAO;
import edu.uniajc.proyeccionSocial.Model.Etapa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class EtapaServices implements IEtapa {

    EtapaDAO dao;

    public EtapaServices() {

        this.dao = new EtapaDAO();
    }

    @Override
    public int createEtapa(Etapa etapa) {
        try {

            // validacion de Data
            if (etapa != null) {

                int flag = dao.createEtapa(etapa);

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
    public boolean deleteEtapa(int id) {
        try {

            dao.deleteEtapa(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateEtapa(Etapa etapa) {
        try {

            dao.updateEtapa(etapa);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Etapa> getAllEtapa() {
        try {

            ArrayList<Etapa> list = dao.getAllEtapa();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Etapa getEtapaById(int id) {
        try {

            Etapa etapa = dao.getEtapaById(id);

            return etapa;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isInServ(int idEtapa) {
       try {
           return dao.isInServ(idEtapa);
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Etapa> getAllEtapaByServicio(int idServicio) {
          try {

            ArrayList<Etapa> list = dao.getAllEtapaByServicio(idServicio);

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
