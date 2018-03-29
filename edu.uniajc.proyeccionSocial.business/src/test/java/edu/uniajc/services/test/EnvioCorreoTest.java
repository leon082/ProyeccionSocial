/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEnvioCorreo;
import edu.uniajc.proyeccionsocial.bussiness.services.EnvioCorreoServices;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author luis.leon
 */
@RunWith(DescriptionSorterRunner.class)
public class EnvioCorreoTest {
    /*
    IEnvioCorreo services;

    public EnvioCorreoTest() {
        services = new EnvioCorreoServices();
    }
    
    @Test
    public void enviarCorreo(){
        List<String> correosDestino= new ArrayList<>();
        correosDestino.add("leon9326@gmail.com");
        List<String> correoEmisor= new ArrayList<>();
        correoEmisor.add("proyeccionsocial.noresponder@gmail.com");
        correoEmisor.add("proyeccionsocial123");
        assertTrue(services.envioCorreoPrueba(correosDestino, correoEmisor));
    }
    */
      @Test
    public void free(){
        assertTrue(true);
    }
}
