/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.utils;

import edu.uniajc.proyeccionSocial.persistence.DAO.EtapaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Leon
 */
public class ConexionBD {

   // private static ConexionBD instance;
    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(ConexionBD.class.getName());

     public ConexionBD(){
        try {
            BasicConfigurator.configure();
            //conectar= ((DataSource) new InitialContext().lookup("jdbc/uniajc")).getConnection(); 
            System.out.println("---------------------------------------- CONECTAR ----------------------------------------");
            connection = ((DataSource) new InitialContext().lookup("jdbc/sample")).getConnection();
            System.out.println(connection);
        } catch (SQLException | NamingException e) {
            System.out.println("Error en ConexionBD init -> " + e.getMessage() + " Error -> "+e);
            System.out.println(e);
            LOGGER.error("Error en ConexionBD init -> " + e.getMessage() + " Error -> "+e);
        }
    }
     
        public static void cerrarConexiones(ResultSet rs, PreparedStatement ps) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception errorRS) {
            errorRS.getMessage();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception errorST) {
            errorST.getMessage();
        }

    }

   /* public static synchronized ConexionBD getInstance() {
        if(instance == null){
            instance =  new ConexionBD();
        }
        return instance ;
    }*/
    
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
