/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import edu.uniajc.proyeccionsocial.bussiness.interfaces.IListaValor;
import edu.uniajc.proyeccionsocial.bussiness.services.ListaValorServices;
import edu.uniajc.proyeccionSocial.persistence.Model.ListaValor;
import java.sql.Connection;
import java.sql.SQLException;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class ListaValorServicesTest  {
   /* @Test
    public void prueba() {
       assertTrue(true);
       
    }*/
    

    IListaValor services;
    int creado;
    
    Connection conection;

    public ListaValorServicesTest() {
        conection = new ConeccionTest().getConnection();
        this.services = new ListaValorServices(conection);
    }

    @Test
    public void crearListaValor() {

        creado = services.createListaValor(initListaValor());
        if (creado == 0) {
            fail("No creo el Lista Valor");
        }

    }

    @Test
    public void updateListaValor() {
        ListaValor p = initListaValor();
        p.setId_listavalor(creado);
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

    public ListaValor initListaValor() {
        ListaValor listaValor = new ListaValor();

        java.util.Date fecha = new java.util.Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

        listaValor.setAgrupacion("AgrupacionDEMO");
        listaValor.setDescripcion("DescripcionDEMO");
        listaValor.setEstado(1);
        listaValor.setCreadopor("UserDemo");

        return listaValor;
    }
    
       @Test
    public void cerrarConexion()   {
        try{
               conection.close();
        }catch(SQLException e){
           assertTrue("No cerro la conexion",false);
        }

    }

}
