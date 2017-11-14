/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.DAO.ProgramaServicioDAO;
import edu.uniajc.proyeccionSocial.interfaces.model.ProgramaServicio;
import java.sql.Connection;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class ProgramaServicioDaoTest extends TestCase{
    //falta meter la conexion
     Connection DBConnection;
     ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
    @Test
    public void crearProgramaServicio() {
        // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
        int result=dao.createProgramaServicio(initProgramaServicio());
        if(result == 0){
            fail("No creo el ProgramaServicio");
        }
       
    }
    
     @Test
    public void updateProgramaServicio() {
       // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
         assertTrue(dao.updateProgramaServicio(initProgramaServicio()));
    }
    
      @Test
    public void deleteProyecto() {
       // ProgramaServicioDAO dao = new ProgramaServicioDAO(DBConnection);
       assertTrue(dao.deleteProgramaServicio(initProgramaServicio().getId_ProgramaServicio()));
        
       
    }
    
        @Test
    public void getAllProyectos() {
        
            assertNotNull(dao.getAllProgramaServicioByPrograma(1));
      
       
    }
    
    
    
    public ProgramaServicio initProgramaServicio(){
        ProgramaServicio progServi=new ProgramaServicio();
        
       
                progServi.setDescripcion("prueba TEST");
                progServi.setId_Programa(1);
                progServi.setEstadoProgramaServicio(1);                
                progServi.setCreadoPor("userDEMo");                
                
                
            
        
        return progServi;
    }
}
