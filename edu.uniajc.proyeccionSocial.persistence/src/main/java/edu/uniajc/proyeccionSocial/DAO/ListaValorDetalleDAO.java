/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.ListaValorDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;

/**
 *
 * @author rlara
 */
public class ListaValorDetalleDAO {
    
    private Connection DBConnection = null;

    public ListaValorDetalleDAO() {
        
        this.DBConnection = new ConexionBD().conexion();
    }
    
    public int createListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            listaValorDetalle.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_ListaValorDetalle.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                listaValorDetalle.setId_ListaValorDetalle(codigo);
            }

            SQL = "INSERT INTO TB_ListaValorDetalle "
                    + "(ID_ListaValorDetalle, ID_ListaValor, Valor, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, listaValorDetalle.getId_ListaValorDetalle());
            ps.setInt(2, listaValorDetalle.getId_ListaValor());
            ps.setString(3, listaValorDetalle.getValor());
            ps.setInt(4, listaValorDetalle.getEstado());
            ps.setString(5, listaValorDetalle.getCreadoPor());
            ps.setDate(6, listaValorDetalle.getCreadoEn());
            ps.execute();

            ps.close();

            System.out.println("Codigo de ListaValorDetalle" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en ListaValorDetalleDAO insert -->" + e.getMessage());
            Logger.getLogger(ListaValorDetalleDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteListaValorDetalle(int id) {
        try {

            String SQL = "DELETE FROM TB_ListaValorDetalle WHERE ID_ListaValorDetalle =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ListaValorDetalleDAO Delete " + e.getMessage());
            Logger.getLogger(ListaValorDetalleDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            listaValorDetalle.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_ListaValorDetalle SET "
                    + "ID_ListaValor=?, Valor=?, Estado=?, "
                    + "ModificadoPor=?, ModificadoEn=? where ID_ListaValorDetalle = ?";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, listaValorDetalle.getId_ListaValor());
            ps.setString(2, listaValorDetalle.getValor());
            ps.setInt(3, listaValorDetalle.getEstado());
            ps.setString(4, listaValorDetalle.getModificadoPor());
            ps.setDate(5, listaValorDetalle.getModificadoEn());
            ps.setInt(6, listaValorDetalle.getId_ListaValorDetalle());
            ps.execute();
            ps.close();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ListaValorDetalleDAO UPDATE " + e.getMessage());
            Logger.getLogger(ListaValorDetalleDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<ListaValorDetalle> getAllListaValorDetalle() {
        ArrayList<ListaValorDetalle> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_ListaValorDetalle";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ListaValorDetalle listaValorDetalle = new ListaValorDetalle();
                listaValorDetalle.setId_ListaValorDetalle(rs.getInt("ID_ListaValorDetalle"));
                listaValorDetalle.setId_ListaValor(rs.getInt("ID_ListaValor"));
                listaValorDetalle.setValor(rs.getString("Valor"));
                listaValorDetalle.setEstado(rs.getInt("Estado"));
                listaValorDetalle.setCreadoPor(rs.getString("CreadoPor"));
                listaValorDetalle.setModificadoPor(rs.getString("ModificadoPor"));
                listaValorDetalle.setCreadoEn(rs.getDate("CreadoEn"));
                listaValorDetalle.setModificadoEn(rs.getDate("ModificadoEn"));

                list.add(listaValorDetalle);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en ListaValorDetalleDAO getAllListaValorDetalle " + e.getMessage());
            Logger.getLogger(ListaValorDetalleDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public ListaValorDetalle getListaValorDetalleById(int id) {

        ListaValorDetalle listaValorDetalle = new ListaValorDetalle();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_ListaValorDetalle where ID_ListaValorDetalle =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                listaValorDetalle.setId_ListaValorDetalle(rs.getInt("ID_ListaValorDetalle"));
                listaValorDetalle.setId_ListaValor(rs.getInt("ID_ListaValor"));
                listaValorDetalle.setValor(rs.getString("Valor"));
                listaValorDetalle.setEstado(rs.getInt("Estado"));
                listaValorDetalle.setCreadoPor(rs.getString("CreadoPor"));
                listaValorDetalle.setModificadoPor(rs.getString("ModificadoPor"));
                listaValorDetalle.setCreadoEn(rs.getDate("CreadoEn"));
                listaValorDetalle.setModificadoEn(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return listaValorDetalle;
        } catch (SQLException e) {
            System.out.println("Error en ListaValorDetalleDAO getListaValorDetallesById " + e.getMessage());
            Logger.getLogger(ListaValorDetalleDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en ListaValorDetalleDAO finish" + sqle.getMessage());
            Logger.getLogger(ListaValorDetalleDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
