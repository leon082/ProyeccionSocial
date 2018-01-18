/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.Beneficiario;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IBeneficiario {

    public int createBeneficiario(Beneficiario beneficiario);

    public boolean deleteBeneficiario(int id);

    public boolean updateBeneficiario(Beneficiario beneficiario);

    public ArrayList<Beneficiario> getAllBeneficiario();

    public ArrayList<Beneficiario> getAllBeneficiarioByProyect(int idProyect);

    public Beneficiario getBeneficiarioById(int id);

}
