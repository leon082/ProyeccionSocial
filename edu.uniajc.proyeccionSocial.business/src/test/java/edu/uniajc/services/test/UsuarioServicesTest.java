/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.*;
import static junit.framework.TestCase.fail;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author luis.leon
 */
@RunWith(DescriptionSorterRunner.class)
public class UsuarioServicesTest {

   
     
    IUsuario services;
    int creado;
    Connection conection;

    public UsuarioServicesTest() {
        conection = new ConeccionTest().getConnection();
        this.services = new UsuarioServices(conection);
    }
    /*
      @Test
    public void prueba() {
       assertTrue(true);
       
    }

    @Test
    public void AcrearUsuario() {

        creado = services.createUsuario(initUsuario());
        if (creado == 0) {
            fail("No creo el usuario");
        }

    }

    @Test
    public void BupdateUsuario() {
        Usuario u = initUsuario();
        u.setId_usuario(creado);
        assertTrue(services.updateUsuario(u));
    }

    

    @Test
    public void EdeleteUsuario() {

        assertTrue(services.deleteUsuario(creado));

    }
    
     public Usuario initUsuario() {
        Usuario usuario = new Usuario();

        usuario.setId_tercero(1);
        usuario.setUsuario("user");
        usuario.setEstado(1);
        usuario.setContrasena("123");

        return usuario;
    }
    
    */
    
    @Test
    public void CgetAllUsuarios() {

        assertNotNull(services.getAllUsuario());

    }

    @Test
    public void DLogin() {
        
        
        assertNotNull(services.getUsuarioLogin("lleon", "lleon123"));

    }

   

    @Test
    public void FcerrarConexion() {
        try {
            conection.close();
        } catch (SQLException e) {

        }

    }

}
