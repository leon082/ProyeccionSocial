/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionsocial.interfaces.IBeneficiario;
import edu.uniajc.proyeccionSocial.DAO.BeneficiarioDAO;

import edu.uniajc.proyeccionSocial.Model.Beneficiario;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public class BeneficiarioServices implements IBeneficiario {

    BeneficiarioDAO dao;

    public BeneficiarioServices() {

        this.dao = new BeneficiarioDAO();
    }

    @Override
    public int createBeneficiario(Beneficiario beneficiario) {
        try {

            // validacion de Data
            if (beneficiario != null) {

                int flag = dao.createBeneficiario(beneficiario);

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
    public boolean deleteBeneficiario(int id) {
        try {

            dao.deleteBeneficiario(id);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBeneficiario(Beneficiario beneficiario) {
        try {
            dao.updateBeneficiario(beneficiario);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Beneficiario> getAllBeneficiario() {
        try {

            ArrayList<Beneficiario> list = dao.getAllBeneficiario();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Beneficiario getBeneficiarioById(int id) {
        try {

            Beneficiario beneficiario = dao.getBeneficiarioById(id);

            return beneficiario;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
