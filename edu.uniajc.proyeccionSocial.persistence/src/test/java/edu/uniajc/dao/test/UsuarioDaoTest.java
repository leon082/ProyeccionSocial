/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.persistence.DAO.UsuarioDao;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 *
 * @author luis.leon
 */
public class UsuarioDaoTest {
    @Test
    public void prueba() {
       assertTrue(true);
       
    }
    /*
   
     UsuarioDao dao = new UsuarioDao();
     int creado;
    @Test
    public void crearUsuario() {
       
         creado=dao.createUsuario(initUsuario());
        if(creado == 0){
            fail("No creo el usuario");
        }
       
    }
    
     @Test
    public void updateUsuario() {
       Usuario u = initUsuario();
       u.setId_Usuario(creado);
         assertTrue(dao.updateUsuario(u));
    }
    
       
        @Test
    public void getAllProyectos() {
        
            assertNotNull(dao.getAllUsuarios());
      
       
    }
    
       @Test
    public void deleteProyecto() {
      
       assertTrue(dao.deleteUsuario(creado));
        
       
    }
    
    
    
    public Usuario initUsuario(){
        Usuario usuario=new Usuario();
        
                usuario.setId_Tercero(1);
                usuario.setUsuario("user");
                usuario.setEstado(1);
                
            
        
        return usuario;
    }
    
    */
}
