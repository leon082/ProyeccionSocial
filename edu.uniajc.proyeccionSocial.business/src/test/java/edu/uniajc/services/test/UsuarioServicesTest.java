/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import com.edu.uniajc.proyeccionsocial.interfaces.IUsuario;
import com.edu.uniajc.proyeccionsocial.logic.services.UsuarioServices;
import edu.uniajc.proyeccionSocial.DAO.UsuarioDao;
import edu.uniajc.proyeccionSocial.Model.Usuario;
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
public class UsuarioServicesTest extends TestCase{
    
     IUsuario services ;
     int creado;

    public UsuarioServicesTest() {
        this.services= new UsuarioServices();
    }
     
     
    @Test
    public void crearUsuario() {
       
         creado=services.createUsuario(initUsuario());
        if(creado == 0){
            fail("No creo el usuario");
        }
       
    }
    
     @Test
    public void updateUsuario() {
       Usuario u = initUsuario();
       u.setId_Usuario(creado);
         assertTrue(services.updateUsuario(u));
    }
    
       
        @Test
    public void getAllProyectos() {
        
            assertNotNull(services.getAllUsuarios());
      
       
    }
    
       @Test
    public void deleteProyecto() {
      
       assertTrue(services.deleteUsuario(creado));
        
       
    }
    
    
    
    public Usuario initUsuario(){
        Usuario usuario=new Usuario();
        
                usuario.setId_Tercero(1);
                usuario.setUsuario("user");
                usuario.setEstado(1);
                
            
        
        return usuario;
    }
    
    
}
