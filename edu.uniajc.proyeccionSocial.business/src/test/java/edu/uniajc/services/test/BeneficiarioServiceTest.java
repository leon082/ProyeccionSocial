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

/**
 *
 * @author luis.leon
 */
public class BeneficiarioServiceTest {
    
    IBeneficiario services;
    int creado;
    Connection conection;

    public BeneficiarioServiceTest() {
          conection = new ConeccionTest().getConnection();
          services = new  BeneficiarioServices(conection);
    }
    
     @Test
    public void crearBeneficiario() {

        creado = services.createBeneficiario(initBeneficiario());
        if (creado == 0) {
            fail("No creo el Beneficiario");
        }

    }

    @Test
    public void updateBeneficiario() {
        Beneficiario o = initBeneficiario();
        o.setId_beneficiario(creado);
        assertTrue(services.updateBeneficiario(o));
    }

    @Test
    public void getAllBeneficiarios() {

        assertNotNull(services.getAllBeneficiario());

    }

    @Test
    public void deleteBeneficiario() {

        assertTrue(services.deleteBeneficiario(creado));

    }
    
    
    
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
    public void cerrarConexion()   {
        try{
               conection.close();
        }catch(SQLException e){
         
        }

    }
    
    
    
}
