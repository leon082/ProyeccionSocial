/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.DAO.OferenteDao;
import edu.uniajc.proyeccionSocial.Model.Oferente;
import java.sql.Connection;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class OferenteDaoTest extends TestCase{
    //falta meter la conexion
     Connection DBConnection;
     OferenteDao dao = new OferenteDao(DBConnection);
     int creado;
    @Test
    public void crearOferente() {
       
        creado=dao.createOferente(initOferente());
        if(creado == 0){
            fail("No creo el Oferente");
        }
       
    }
    
     @Test
    public void updateOferente() {
        Oferente o = initOferente();
        o.setId_Oferente(creado);
         assertTrue(dao.updateOferente(o));
    }
  
    
        @Test
    public void getAllOferentes() {
        
            assertNotNull(dao.getAllOferentes());
      
       
    }
    
      
      @Test
    public void deleteOferente() {
      
       assertTrue(dao.deleteOferente(creado));
        
       
    }
    
    
    
    public Oferente initOferente(){
        Oferente oferente=new Oferente();
        
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
