/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luis.leon
 */
public class ConeccionTest {
    
    public Connection getConnection() {
        try{
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
             Connection conn = DriverManager.getConnection
          ("jdbc:oracle:thin:@localhost:1521:XE", "system", "luisleon9");             
             return conn;
        }catch(SQLException e){
            return null;
        }
            
        
    }
    
}
