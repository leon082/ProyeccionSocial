/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.persistence.DAO.BeneficiarioDAO;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IBeneficiario;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IBeneficiarioDao;

import edu.uniajc.proyeccionSocial.persistence.Model.Beneficiario;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class BeneficiarioServices implements IBeneficiario {

    IBeneficiarioDao dao;
    
private static final Logger LOGGER =  Logger.getLogger(BeneficiarioServices.class.getName());

    public BeneficiarioServices(Connection connection) {
        org.apache.log4j.BasicConfigurator.configure();
        this.dao = new BeneficiarioDAO(connection);
    }

    @Override
    public int createBeneficiario(Beneficiario beneficiario) {
        try {

            // validacion de Data
            if (beneficiario != null) {

                int flag = dao.createBeneficiario(beneficiario);

                return flag;
            } else {
                LOGGER.error("Faltan Datos en pantalla" );
                
                return 0;

            }
        } catch (Exception e) {
            LOGGER.error("Error Beneficiario Services "+e.getMessage() );
            return 0;
        }
    }

    @Override
    public boolean deleteBeneficiario(int id) {
        try {

            return dao.deleteBeneficiario(id);

        } catch (Exception e) {
            LOGGER.error("Error BeneficiarioServices "+e.getMessage() );
            
            return false;
        }
    }

    @Override
    public boolean updateBeneficiario(Beneficiario beneficiario) {
        try {

            return dao.updateBeneficiario(beneficiario);
        } catch (Exception e) {
            LOGGER.error("Error BeneficiarioServices "+e.getMessage() );
            
            return false;
        }
    }

    @Override
    public ArrayList<Beneficiario> getAllBeneficiario() {
        try {

            ArrayList<Beneficiario> list = dao.getAllBeneficiario();

            return list;

        } catch (Exception e) {
            LOGGER.error("Error BeneficiarioServices "+e.getMessage() );
            return null;
        }
    }

    @Override
    public Beneficiario getBeneficiarioById(int id) {
        try {

            Beneficiario beneficiario = dao.getBeneficiarioById(id);

            return beneficiario;

        } catch (Exception e) {
            LOGGER.error("Error BeneficiarioServices "+e.getMessage() );
            return null;
        }
    }

    @Override
    public ArrayList<Beneficiario> getAllBeneficiarioByProyect(int idProyect) {
        try {

            ArrayList<Beneficiario> list = dao.getAllBeneficiarioByProyect(idProyect);

            return list;

        } catch (Exception e) {
            LOGGER.error("Error BeneficiarioServices "+e.getMessage() );
            return null;
        }
    }

}
