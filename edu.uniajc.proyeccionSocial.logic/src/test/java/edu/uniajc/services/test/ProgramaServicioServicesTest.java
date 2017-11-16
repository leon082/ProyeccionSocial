/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import com.edu.uniajc.proyeccionsocial.interfaces.IProgramaServicio;
import com.edu.uniajc.proyeccionsocial.logic.services.ProgramaServicioServices;
import edu.uniajc.proyeccionSocial.Model.ProgramaServicio;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class ProgramaServicioServicesTest extends TestCase {

   
    IProgramaServicio services ;
    int creado;

    public ProgramaServicioServicesTest() {
        this.services=new ProgramaServicioServices();
    }
    
    

    @Test
    public void crearProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        creado = services.createProgramaServicio(initProgramaServicio());
        if (creado == 0) {
            fail("No creo el ProgramaServicio");
        }

    }

    @Test
    public void updateProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        ProgramaServicio p = initProgramaServicio();
        p.setId_ProgramaServicio(creado);
        assertTrue(services.updateProgramaServicio(p));

    }

   

    @Test
    public void getAllProgramaServicio() {

        assertNotNull(services.getAllProgramaServicioByPrograma(1));

    }
    
     @Test
    public void deleteProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        assertTrue(services.deleteProgramaServicio(creado));

    }

    public ProgramaServicio initProgramaServicio() {
        ProgramaServicio progServi = new ProgramaServicio();

        progServi.setDescripcion("prueba TEST");
        progServi.setId_Programa(1);
        progServi.setEstadoProgramaServicio(1);
        progServi.setCreadoPor("userDEMo");

        return progServi;
    }
}
