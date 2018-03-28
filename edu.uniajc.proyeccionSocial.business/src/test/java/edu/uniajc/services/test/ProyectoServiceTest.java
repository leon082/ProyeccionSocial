/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProyecto;
import edu.uniajc.proyeccionsocial.bussiness.services.ProyectoServices;
import java.sql.Connection;
import java.sql.SQLException;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class ProyectoServiceTest {
    
    IProyecto services;
    int creado;
    Connection conection;

    public ProyectoServiceTest() {
        conection = new ConeccionTest().getConnection();
        services = new ProyectoServices(conection);
    }
   

    @Test
    public void crearProyecto() {

        creado = services.createProyecto(initProyecto());
        if (creado == 0) {
            fail("No creo el proyecto");
        }

    }

    @Test
    public void updateProyecto() {
        Proyecto p = initProyecto();
        p.setId_proyecto(creado);
        assertTrue(services.updateProyecto(p));
    }
/*
    @Test
    public void getAllProyectoAprobado() {

        assertNotNull(services.getAllProyectoAprobado());

    }
      @Test
    public void getAllProyectoCancelado() {

        assertNotNull(services.getAllProyectoCancelado());

    }
      @Test
    public void getAllProyectoFinalizado() {

        assertNotNull(services.getAllProyectoFinalizado());

    }
      @Test
    public void getAllProyectoPendiente() {

        assertNotNull(services.getAllProyectoPendiente());

    }
*/
    @Test
    public void deleteProyecto() {

        assertTrue(services.deleteProyecto(creado));

    }

    public Proyecto initProyecto() {
        Proyecto proyecto = new Proyecto();

        proyecto.setTituloproyecto("ProyectoPrueba");
        proyecto.setResumenproyecto("Proyecto de prueba para Casos TEST");
        proyecto.setId_programa(1);
        proyecto.setId_servicio(1);
        proyecto.setEstado(1);
        proyecto.setCreadopor("usuarioDemo");

        return proyecto;
    }
       @Test
    public void cerrarConexion()   {
        try{
               conection.close();
        }catch(SQLException e){
         
        }

    }
    
}
