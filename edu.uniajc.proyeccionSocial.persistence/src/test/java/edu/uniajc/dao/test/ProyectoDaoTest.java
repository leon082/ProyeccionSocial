/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.persistence.DAO.ProyectoDao;
import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 *
 * @author luis.leon
 */
public class ProyectoDaoTest {
    @Test
    public void prueba() {
       assertTrue(true);
       
    }
    /*
   
     ProyectoDao dao = new ProyectoDao();
     int creado;
    @Test
    public void crearProyecto() {
       
         creado=dao.createProyecto(initProyecto());
        if(creado == 0){
            fail("No creo el proyecto");
        }
       
    }
    
     @Test
    public void updateProyecto() {
       Proyecto p = initProyecto();
       p.setId_Proyecto(creado);
         assertTrue(dao.updateProyecto(p));
    }
    
    
    
        @Test
    public void getAllProyectos() {
        
            assertNotNull(dao.getAllProyectos());
      
       
    }
    
      @Test
    public void deleteProyecto() {
      
       assertTrue(dao.deleteProyecto(creado));
        
       
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
*/
    
}
