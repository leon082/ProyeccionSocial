/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.utils;

import edu.uniajc.proyeccionSocial.DAO.EtapaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Leon
 */
public class ConexionBD {

    private static ConexionBD instance;
    private Connection connection;

    private ConexionBD(){
        try {
            //conectar= ((DataSource) new InitialContext().lookup("jdbc/uniajc")).getConnection(); 
            System.out.println("---------------------------------------- CONECTAR ----------------------------------------");
            connection = ((DataSource) new InitialContext().lookup("jdbc/sample")).getConnection();
            System.out.println(connection);
        } catch (SQLException | NamingException e) {
            System.out.println("Error en ConexionBD init -> " + e.getMessage());
            System.out.println(e);
        }
    }

    public static synchronized ConexionBD getInstance() {
        if(instance == null){
            instance =  new ConexionBD();
        }
        return instance ;
    }
    
    public Connection getConnection(){
        return connection;
    }
/*
    @PreDestroy
    public void finish() {
        try {
            connection.commit();
            connection.close();
            System.out.println("FINISH DB EXECUTED");

        } catch (SQLException sqle) {
            System.out.println("Error en ConexionBD finish " + sqle.getMessage());
            Logger.getLogger(EtapaDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }*/
}
