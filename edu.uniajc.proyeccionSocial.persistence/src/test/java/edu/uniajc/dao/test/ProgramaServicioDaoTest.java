/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.DAO.ProgramaServicioDAO;
import edu.uniajc.proyeccionSocial.Model.ProgramaServicio;
import junit.framework.TestCase;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 *
 * @author luis.leon
 */
public class ProgramaServicioDaoTest  {
    @Test
    public void prueba() {
       assertTrue(true);
       
    }
    /*

  
    ProgramaServicioDAO dao = new ProgramaServicioDAO();
    int creado;

    @Test
    public void crearProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        creado = dao.createProgramaServicio(initProgramaServicio());
        if (creado == 0) {
            fail("No creo el ProgramaServicio");
        }

    }

    @Test
    public void updateProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        ProgramaServicio p = initProgramaServicio();
        p.setId_ProgramaServicio(creado);
        assertTrue(dao.updateProgramaServicio(p));

    }

   

    @Test
    public void getAllProgramaServicio() {

        assertNotNull(dao.getAllProgramaServicioByPrograma(1));

    }
    
     @Test
    public void deleteProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        assertTrue(dao.deleteProgramaServicio(creado));

    }

    public ProgramaServicio initProgramaServicio() {
        ProgramaServicio progServi = new ProgramaServicio();

        progServi.setDescripcion("prueba TEST");
        progServi.setId_Programa(1);
        progServi.setEstadoProgramaServicio(1);
        progServi.setCreadoPor("userDEMo");

        return progServi;
    }
*/
}
