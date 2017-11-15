/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import edu.uniajc.proyeccionSocial.interfaces.model.ListaValor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author luis.leon
 */
public class ListaValorDao {
    
    private Connection DBConnection = null;

    public ListaValorDao(Connection openConnection) {
        this.DBConnection = openConnection;
    }

    public int createListaValor(ListaValor listaValor) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            listaValor.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_ListaValor.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                listaValor.setId_ListaValor(codigo);
            }

            SQL = "INSERT INTO TB_ListaValor"
                    + " (ID_ListaValor,Agrupacion,Descripcion, Estado,CreadoPor, CreadoEn) "
                    + " values(?,?,?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, listaValor.getId_ListaValor());
            ps.setString(2, listaValor.getAgrupacion());
            ps.setString(3, listaValor.getDescripcion());
            ps.setInt(4, listaValor.getEstado());            
            ps.setString(5, listaValor.getCreadoPor());
            ps.setDate(6, listaValor.getCreadoEn());
            
            ps.execute();
           

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en ListaValorDao Insert -->" + e.getMessage());
            Logger.getLogger(ListaValorDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteListaValor(int id) {
        try {

            String SQL = "DELETE FROM TB_ListaValor WHERE ID_ListaValor =" + id + " ";

           
            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

                       
        } catch (SQLException e) {
            System.out.println("Error en ListaValor DAO Delete " + e.getMessage());
            Logger.getLogger(ListaValor.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateListaValor(ListaValor listaValor) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            
            listaValor.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_ListaValor SET "
                    + " Agrupacion=?, Descripcion=?, Estado=?,ModificadoPor=?, ModificadoEn=? "
                    + " where ID_ListaValor = ?";
            ps = this.DBConnection.prepareStatement(SQL);

           
            ps.setString(1, listaValor.getAgrupacion());
            ps.setString(2, listaValor.getDescripcion());
            ps.setInt(3, listaValor.getEstado());
            ps.setString(4, listaValor.getModificadoPor());
            ps.setDate(5, listaValor.getModificadoEn());
            ps.setInt(5, listaValor.getId_ListaValor());
            

            ps.execute();
            ps.close();
            return true;

                      
        } catch (SQLException e) {
            System.out.println("Error en ListaValor DAO UPDATE " + e.getMessage());
            Logger.getLogger(ListaValor.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<ListaValor> getAllListaValor() {
        ArrayList<ListaValor> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_ListaValor";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ListaValor listaValor = new ListaValor();
                listaValor.setId_ListaValor(rs.getInt("ID_ListaValor"));
                listaValor.setAgrupacion(rs.getString("Agrupacion"));
                listaValor.setDescripcion(rs.getString("Descripcion"));
                listaValor.setEstado(rs.getInt("Estado"));                               
                listaValor.setCreadoPor(rs.getString("CREADOPOR"));
                listaValor.setModificadoPor(rs.getString("MODIFICADOPOR"));
                listaValor.setCreadoEn(rs.getDate("CREADOEN"));
                listaValor.setModificadoEn(rs.getDate("MODIFICADOEN"));

                list.add(listaValor);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en ListaValor DAO getAllListaValor " + e.getMessage());
            Logger.getLogger(ListaValor.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
    
    public ListaValor getListaValorById(int id) {
        
        ListaValor listaValor = new ListaValor();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_ListaValor where ID_ListaValor =" +id+" ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
            rs.next();
               
                listaValor.setId_ListaValor(rs.getInt("ID_ListaValor"));
                listaValor.setAgrupacion(rs.getString("Agrupacion"));
                listaValor.setDescripcion(rs.getString("Descripcion"));
                listaValor.setEstado(rs.getInt("Estado"));                               
                listaValor.setCreadoPor(rs.getString("CREADOPOR"));
                listaValor.setModificadoPor(rs.getString("MODIFICADOPOR"));
                listaValor.setCreadoEn(rs.getDate("CREADOEN"));
                listaValor.setModificadoEn(rs.getDate("MODIFICADOEN"));
                
            }
            ps.close();
                       
            return listaValor;
        } catch (SQLException e) {
            System.out.println("Error en ListaValor DAO getListaValorById " + e.getMessage());
            Logger.getLogger(ListaValor.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
    
}
