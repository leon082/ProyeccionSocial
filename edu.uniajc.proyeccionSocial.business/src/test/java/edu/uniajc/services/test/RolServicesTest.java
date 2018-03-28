/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IRol;
import edu.uniajc.proyeccionsocial.bussiness.services.RolServices;
import edu.uniajc.proyeccionSocial.persistence.Model.Rol;
import java.sql.Connection;
import java.sql.SQLException;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static junit.framework.TestCase.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author luis.leon
 */
@RunWith(DescriptionSorterRunner.class)
public class RolServicesTest  {
   /* @Test
    public void prueba() {
       assertTrue(true);
       
    }
    */

    IRol services;
    int creado;
     Connection conection;

    public RolServicesTest() {
         conection = new ConeccionTest().getConnection();
        this.services = new RolServices(conection);
    }

    @Test
    public void AcrearRol() {

        creado = services.createRol(initRol());
        if (creado == 0) {
            fail("No creo el Rol");
        }

    }

    @Test
    public void BupdateUsuario() {
        Rol r = initRol();
        r.setId_rol(creado);
        assertTrue(services.updateRol(r));
    }

    @Test
    public void CgetAllProyectos() {

        assertNotNull(services.getAllRol());

    }

    @Test
    public void DdeleteRol() {

        assertTrue(services.deleteRol(creado));

    }

    public Rol initRol() {
        Rol rol = new Rol();

        rol.setValor("prueba");
        rol.setDescripcion("Rol test");
        rol.setEstado(1);
        rol.setCreadopor("prueba");

        return rol;
    }
       @Test
    public void EcerrarConexion()   {
        try{
               conection.close();
        }catch(SQLException e){
            assertTrue("No cerro la conexion",false);
        }

    }

}
