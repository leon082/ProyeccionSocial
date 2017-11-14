/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.DAO.FaseProyectoDAO;
import edu.uniajc.proyeccionSocial.interfaces.model.FaseProyecto;
import java.sql.Connection;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class FaseProyectoDaoTest extends TestCase{
    
       Connection DBConnection;
     FaseProyectoDAO dao = new FaseProyectoDAO(DBConnection);
    @Test
    public void crearProyecto() {
       
        int result=dao.createFaseProyecto(initFaseProyecto());
        if(result == 0){
            fail("No creo el proyecto");
        }
       
    }
    
     @Test
    public void updateProyecto() {
       
         assertTrue(dao.updateFaseProyecto(initFaseProyecto()));
    }
    
      @Test
    public void deleteProyecto() {
      
       assertTrue(dao.deleteFaseProyecto(initFaseProyecto().getId_FaseProyecto()));
        
       
    }
    
        @Test
    public void getAllProyectos() {
        
            assertNotNull(dao.getAllFaseProyectos());
      
       
    }
    
    
    
    public FaseProyecto initFaseProyecto(){
        FaseProyecto faseProyecto=new FaseProyecto();
        
          java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                
                faseProyecto.setId_Proyecto(1);
                faseProyecto.setId_Fase(1);
                faseProyecto.setEstadoFaseProyecto(1);
                faseProyecto.setObservacion("Observacion");
                faseProyecto.setFechaInicio(fechaSQL);
                faseProyecto.setFechaFin(fechaSQL);
                faseProyecto.setCreadoPor("ususarioDEMo");
              
        
        return faseProyecto;
    }
    
}
