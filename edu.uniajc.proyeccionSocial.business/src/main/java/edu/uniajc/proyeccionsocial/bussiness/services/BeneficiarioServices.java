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
import edu.uniajc.proyeccionsocial.bussiness.util.Constantes;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class BeneficiarioServices implements IBeneficiario {

    IBeneficiarioDao dao;

    private static final Logger LOGGER = Logger.getLogger(BeneficiarioServices.class.getName());

    public BeneficiarioServices(Connection connection) {
        org.apache.log4j.BasicConfigurator.configure();
        this.dao = new BeneficiarioDAO(connection);
    }

    @Override
    public int createBeneficiario(Beneficiario beneficiario) {
        try {
            if (beneficiario != null) {
                return dao.createBeneficiario(beneficiario);

            } else {
                LOGGER.error(Constantes.MENSAJEDATOSFALTANTES);
                return 0;
            }
        } catch (Exception e) {
            LOGGER.error(Constantes.ERRORBENEFICIARIO + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean deleteBeneficiario(int id) {
        try {
            return dao.deleteBeneficiario(id);

        } catch (Exception e) {
            LOGGER.error(Constantes.ERRORBENEFICIARIO + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBeneficiario(Beneficiario beneficiario) {
        try {
            return dao.updateBeneficiario(beneficiario);
        } catch (Exception e) {
            LOGGER.error(Constantes.ERRORBENEFICIARIO + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Beneficiario> getAllBeneficiario() {
        try {
            return dao.getAllBeneficiario();
        } catch (Exception e) {
            LOGGER.error(Constantes.ERRORBENEFICIARIO + e.getMessage());
            return null;
        }
    }

    @Override
    public Beneficiario getBeneficiarioById(int id) {
        try {
            return dao.getBeneficiarioById(id);
        } catch (Exception e) {
            LOGGER.error(Constantes.ERRORBENEFICIARIO + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Beneficiario> getAllBeneficiarioByProyect(int idProyect) {
        try {
            return dao.getAllBeneficiarioByProyect(idProyect);
        } catch (Exception e) {
            LOGGER.error(Constantes.ERRORBENEFICIARIO + e.getMessage());
            return null;
        }
    }
}