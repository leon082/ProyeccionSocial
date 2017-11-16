/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;


import com.edu.uniajc.proyeccionsocial.interfaces.IListaValor;
import com.edu.uniajc.proyeccionsocial.logic.services.ListaValorServices;
import edu.uniajc.proyeccionSocial.Model.ListaValor;
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
public class ListaValorServicesTest extends TestCase {
    //falta meter la conexion
   
     IListaValor services ;
     int creado;

    public ListaValorServicesTest() {
        this.services= new ListaValorServices();;
    }
     
     
    @Test
    public void crearListaValor() {
       
        creado=services.createListaValor(initListaValor());
        if(creado == 0){
            fail("No creo el Lista Valor");
        }
       
    }
    
     @Test
    public void updateListaValor() {
       ListaValor p =initListaValor();
       p.setId_ListaValor(creado);
         assertTrue(services.updateListaValor(p));
    }
    
   
    
        @Test
    public void getAllListaValor() {
        
            assertNotNull(services.getAllListaValor());
      
       
    }
    
       @Test
    public void deleteListaValor() {
      
       assertTrue(services.deleteListaValor(creado));
        
       
    }
    
    
    public ListaValor initListaValor(){
        ListaValor listaValor=new ListaValor();
        
          java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                
                listaValor.setAgrupacion("Agrupacion");
                listaValor.setDescripcion("Descripcion");
                listaValor.setEstado(1);                               
                listaValor.setCreadoPor("UserDemo");
                
              
        
        return listaValor;
    }
    
}
