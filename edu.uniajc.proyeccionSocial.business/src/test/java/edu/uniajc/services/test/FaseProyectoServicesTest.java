/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import com.edu.uniajc.proyeccionsocial.interfaces.IFaseProyecto;
import com.edu.uniajc.proyeccionsocial.logic.services.FaseProyectoServices;

import edu.uniajc.proyeccionSocial.Model.FaseProyecto;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class FaseProyectoServicesTest extends TestCase{
   
     IFaseProyecto services;
     int creado;

    public FaseProyectoServicesTest() {
        this.services=  new FaseProyectoServices();
    }
     
     
    @Test
    public void crearProyecto() {
       
        creado=services.createFaseProyecto(initFaseProyecto());
        if(creado == 0){
            fail("No creo Faseproyecto");
        }
       
    }
    
     @Test
    public void updateProyecto() {
       FaseProyecto p =initFaseProyecto();
       p.setId_FaseProyecto(creado);
         assertTrue(services.updateFaseProyecto(p));
    }
    
   
    
        @Test
    public void getAllProyectos() {
        
            assertNotNull(services.getAllFaseProyectos());
      
       
    }
    
       @Test
    public void deleteProyecto() {
      
       assertTrue(services.deleteFaseProyecto(creado));
        
       
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
