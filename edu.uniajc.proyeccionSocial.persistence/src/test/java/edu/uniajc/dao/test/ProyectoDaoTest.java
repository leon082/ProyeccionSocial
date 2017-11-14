/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.DAO.ProyectoDao;
import edu.uniajc.proyeccionSocial.interfaces.model.Proyecto;
import java.sql.Connection;
import java.util.ArrayList;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class ProyectoDaoTest extends TestCase{
    Connection DBConnection;
    @Test
    public void crearProyecto() {
        ProyectoDao dao = new ProyectoDao(DBConnection);
        int result=dao.createProyecto(initProyecto());
        if(result == 0){
            fail("No creo el proyecto");
        }
       
    }
    
     @Test
    public void updateProyecto() {
        ProyectoDao dao = new ProyectoDao(DBConnection);
        boolean result=dao.updateProyecto(initProyecto());
        if(!result){
            fail("No actualizo el proyecto");
        }
       
    }
    
      @Test
    public void deleteProyecto() {
        ProyectoDao dao = new ProyectoDao(DBConnection);
        boolean result=dao.deleteProyecto(initProyecto().getId_Proyecto());
        if(!result){
            fail("No borro el proyecto");
        }
       
    }
    
        @Test
    public void getAllProyectos() {
        ProyectoDao dao = new ProyectoDao(DBConnection);
        ArrayList<Proyecto> list =dao.getAllProyectos();
        if(list==null){
            fail("No trajo todos los proyectos");
        }
       
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
