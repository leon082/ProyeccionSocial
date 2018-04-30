/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionSocial.persistence.Model.Beneficiario;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IBeneficiario;
import edu.uniajc.proyeccionsocial.bussiness.services.BeneficiarioServices;
import java.sql.Connection;
import java.sql.SQLException;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author luis.leon
 */
@RunWith(DescriptionSorterRunner.class)
public class BeneficiarioServiceTest {

    IBeneficiario services;
    int creado;
    Connection conection;

    public BeneficiarioServiceTest() {
        conection = new ConeccionTest().getConnection();
        services = new BeneficiarioServices(conection);
    }
/*
    @Test
    public void AoperacionCrearBeneficiario() {

        creado = services.createBeneficiario(initBeneficiario());

        if (creado != 0) {

        } else {
            fail("No creo el Beneficiario");
        }
    }

    @Test
    public void BoperacionUpdateBeneficiario() {
       
        Beneficiario o = initBeneficiario();
        o.setId_beneficiario(creado);
        o.setModificadopor("userDemo");
        boolean result = services.updateBeneficiario(o);
         System.out.println("actualizo el beneficiario" + creado);
        
        assertTrue(result);
    }*/

    @Test
    public void CoperacionGetAllBeneficiarios() {

        assertNotNull(services.getAllBeneficiario());

    }
/*
    @Test
    public void DoperacionDeleteBeneficiario() {

        assertTrue(services.deleteBeneficiario(creado));

    }
*/
    public Beneficiario initBeneficiario() {
        Beneficiario beneficiario = new Beneficiario();
        java.util.Date fecha = new java.util.Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        beneficiario.setId_proyecto(1);
        beneficiario.setId_tercero(1);
        beneficiario.setEstado(1);
        beneficiario.setObservacion("ObservacionDEMO");
        beneficiario.setCreadopor("UserDemo");

        return beneficiario;
    }

    @Test
    public void EoperacionCerrarConexion() {
        try {
            conection.close();
        } catch (SQLException e) {

        }

    }
/*
    @Test
    public void free(){
        assertTrue(true);
    }*/
}
