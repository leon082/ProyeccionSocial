/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;


import com.edu.uniajc.proyeccionsocial.interfaces.IProyecto;
import com.edu.uniajc.proyeccionsocial.logic.services.ProyectoServices;
import edu.uniajc.proyeccionSocial.Model.Proyecto;

import junit.framework.TestCase;

import org.junit.Test;
import static junit.framework.TestCase.fail;

/**
 *
 * @author luis.leon
 */
public class ProyectoServicesTest extends TestCase{
        
     IProyecto services ;
     int creado;
     
     public void ProyectoServicesTest(){
         this.services= new ProyectoServices();
     }
     
    @Test
    public void crearProyecto() {
       
         creado=services.createProyecto(initProyecto());
        if(creado == 0){
            fail("No creo el proyecto");
        }
       
    }
    
     @Test
    public void updateProyecto() {
       Proyecto p = initProyecto();
       p.setId_Proyecto(creado);
         assertTrue(services.updateProyecto(p));
    }
    
    
    
        @Test
    public void getAllProyectos() {
        
            assertNotNull(services.getAllProyectos());
      
       
    }
    
      @Test
    public void deleteProyecto() {
      
       assertTrue(services.deleteProyecto(creado));
        
       
    }
    
    
    
    public Proyecto initProyecto(){
        Proyecto proyecto=new Proyecto();
        
        
                proyecto.setTituloProyecto("ProyectoPrueba");
                proyecto.setResumenProyecto("Proyecto de prueba para Casos TEST");
                proyecto.setiD_Programa(1);
                proyecto.setiD_ProgramaServicio(1);
                proyecto.setEstadoProyecto(1);
                proyecto.setCreadoPor("usuarioDemo");
            
        
        return proyecto;
    }
    
}
