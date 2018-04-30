/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IProgramaServicio;
import edu.uniajc.proyeccionsocial.bussiness.services.ProgramaServicioServices;
import edu.uniajc.proyeccionSocial.persistence.Model.ProgramaServicio;
import java.sql.Connection;
import java.sql.SQLException;
import static junit.framework.TestCase.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author luis.leon
 */
@RunWith(DescriptionSorterRunner.class)
public class ProgramaServicioServicesTest {
/*
    @Test
    public void prueba() {
        assertTrue(true);

    }

    @Test
    public void AcrearProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        creado = services.createProgramaServicio(initProgramaServicio());
        if (creado == 0) {
            fail("No creo el ProgramaServicio");
        }

    }

    @Test
    public void BupdateProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        ProgramaServicio p = initProgramaServicio();
        p.setId_programaservicio(creado);
        assertTrue(services.updateProgramaServicio(p));

    }

    @Test
    public void DdeleteProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        assertTrue(services.deleteProgramaServicio(creado));

    }

    public ProgramaServicio initProgramaServicio() {
        ProgramaServicio progServi = new ProgramaServicio();

        progServi.setId_programa(1);
        progServi.setEstado(1);
        progServi.setCreadopor("userDEMo");
        progServi.setId_servicio(1);

        return progServi;
    }*/

    IProgramaServicio services;
    int creado;
    Connection conection;

    public ProgramaServicioServicesTest() {
        conection = new ConeccionTest().getConnection();
        this.services = new ProgramaServicioServices(conection);
    }

    @Test
    public void CgetAllProgramaServicio() {

        assertNotNull(services.getAllProgramaServicioByPrograma(1));

    }

    @Test
    public void EcerrarConexion() {
        try {
            conection.close();
        } catch (SQLException e) {

        }

    }

}
