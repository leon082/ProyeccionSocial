/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import com.edu.uniajc.proyeccionsocial.interfaces.IOferente;
import com.edu.uniajc.proyeccionsocial.logic.services.OferenteServices;
import edu.uniajc.proyeccionSocial.Model.Oferente;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class OferenteServicesTest extends TestCase {
    //falta meter la conexion

    IOferente services;
    int creado;

    public OferenteServicesTest() {
        this.services = new OferenteServices();
    }

    @Test
    public void crearOferente() {

        creado = services.createOferente(initOferente());
        if (creado == 0) {
            fail("No creo el Oferente");
        }

    }

    @Test
    public void updateOferente() {
        Oferente o = initOferente();
        o.setId_Oferente(creado);
        assertTrue(services.updateOferente(o));
    }

    @Test
    public void getAllOferentes() {

        assertNotNull(services.getAllOferentes());

    }

    @Test
    public void deleteOferente() {

        assertTrue(services.deleteOferente(creado));

    }

    public Oferente initOferente() {
        Oferente oferente = new Oferente();

        java.util.Date fecha = new java.util.Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

        oferente.setId_Proyecto(1);
        oferente.setId_Tercero(1);
        oferente.setEstadoOferente(1);
        oferente.setObservacion("Observacion");
        oferente.setCreadoPor("userDemo");

        return oferente;
    }

}
