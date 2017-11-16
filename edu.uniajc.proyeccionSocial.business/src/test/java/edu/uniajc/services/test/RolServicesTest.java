/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import com.edu.uniajc.proyeccionsocial.interfaces.IRol;
import com.edu.uniajc.proyeccionsocial.logic.services.RolServices;
import edu.uniajc.proyeccionSocial.DAO.RolDao;
import edu.uniajc.proyeccionSocial.Model.Rol;
import java.sql.Connection;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class RolServicesTest extends TestCase {

    
    IRol services ;
    int creado;

    public RolServicesTest() {
        this.services=new RolServices();
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

}
