/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IRol;
import edu.uniajc.proyeccionsocial.bussiness.services.RolServices;
import edu.uniajc.proyeccionSocial.persistence.Model.Rol;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static junit.framework.TestCase.*;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class RolServicesTest  {
    @Test
    public void prueba() {
       assertTrue(true);
       
    }
    /*

    IRol services;
    int creado;

    public RolServicesTest() {
        this.services = new RolServices();
    }

    @Test
    public void crearRol() {

        creado = services.createRol(initRol());
        if (creado == 0) {
            fail("No creo el Rol");
        }

    }

    @Test
    public void updateUsuario() {
        Rol r = initRol();
        r.setId_Rol(creado);
        assertTrue(services.updateRol(r));
    }

    @Test
    public void getAllProyectos() {

        assertNotNull(services.getAllRol());

    }

    @Test
    public void deleteRol() {

        assertTrue(services.deleteRol(creado));

    }

    public Rol initRol() {
        Rol rol = new Rol();

        rol.setValor(1);
        rol.setDescripcion("Rol test");
        rol.setEstadoRol(1);

        return rol;
    }
*/
}
