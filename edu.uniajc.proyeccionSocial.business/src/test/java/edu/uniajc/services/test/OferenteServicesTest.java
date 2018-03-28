/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IOferente;
import edu.uniajc.proyeccionsocial.bussiness.services.OferenteServices;
import edu.uniajc.proyeccionSocial.persistence.Model.Oferente;
import java.sql.Connection;
import java.sql.SQLException;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.*;
import static junit.framework.TestCase.fail;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author luis.leon
 */
@RunWith(DescriptionSorterRunner.class)
public class OferenteServicesTest  {
    /*@Test
    public void prueba() {
       assertTrue(true);
       
    }
    */

    IOferente services;
    int creado;
    Connection conection;
    public OferenteServicesTest() {
         conection = new ConeccionTest().getConnection();
        this.services = new OferenteServices(conection);
    }

    @Test
    public void AcrearOferente() {

        creado = services.createOferente(initOferente());
        if (creado == 0) {
            fail("No creo el Oferente");
        }

    }

    @Test
    public void BupdateOferente() {
        Oferente o = initOferente();
        o.setId_oferente(creado);
        assertTrue(services.updateOferente(o));
    }

    @Test
    public void CgetAllOferentes() {

        assertNotNull(services.getAllOferente());

    }

    @Test
    public void DdeleteOferente() {

        assertTrue(services.deleteOferente(creado));

    }

    public Oferente initOferente() {
        Oferente oferente = new Oferente();

        java.util.Date fecha = new java.util.Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

        oferente.setId_proyecto(1);
        oferente.setId_tercero(1);
        oferente.setEstado(1);
        oferente.setObservacion("ObservacionDEMO");
        oferente.setCreadopor("UserDemo");

        return oferente;
    }
    
       @Test
    public void EcerrarConexion()   {
        try{
               conection.close();
        }catch(SQLException e){
         
        }

    }

}
