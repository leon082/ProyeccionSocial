/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Beneficiario;
import java.util.List;

/**
 *
 * @author rlara
 */
public interface IBeneficiario {

    public int createBeneficiario(Beneficiario beneficiario);

    public boolean deleteBeneficiario(int id);

    public boolean updateBeneficiario(Beneficiario beneficiario);

    public List<Beneficiario> getAllBeneficiario();

    public List<Beneficiario> getAllBeneficiarioByProyect(int idProyect);

    public Beneficiario getBeneficiarioById(int id);

}
