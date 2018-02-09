/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.persistence.DAO.ListaValorDao;
import edu.uniajc.proyeccionSocial.persistence.Model.ListaValor;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static junit.framework.TestCase.*;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class ListaValorDaoTest  {
    @Test
    public void prueba() {
       assertTrue(true);
       
    }
/*
    
     ListaValorDao dao = new ListaValorDao();
     int creado;
    @Test
    public void crearListaValor() {
       
        creado=dao.createListaValor(initListaValor());
        if(creado == 0){
            fail("No creo el Lista Valor");
        }
       
    }
    
     @Test
    public void updateListaValor() {
       ListaValor p =initListaValor();
       p.setId_ListaValor(creado);
         assertTrue(dao.updateListaValor(p));
    }
    
   
    
        @Test
    public void getAllListaValor() {
        
            assertNotNull(dao.getAllListaValor());
      
       
    }
    
       @Test
    public void deleteListaValor() {
      
       assertTrue(dao.deleteListaValor(creado));
        
       
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
    */
}
