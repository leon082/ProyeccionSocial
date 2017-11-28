/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Leon
 */

public class ConexionBD {
    
    public Connection conexion() {
        try{
        //Connection dbConnection = ((DataSource) new InitialContext().lookup("jdbc/uniajc")).getConnection(); 
        Connection dbConnection = ((DataSource) new InitialContext().lookup("jdbc/sample")).getConnection(); 
         return dbConnection;
         } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
            return null;
        }
  
    }
    
    
}
