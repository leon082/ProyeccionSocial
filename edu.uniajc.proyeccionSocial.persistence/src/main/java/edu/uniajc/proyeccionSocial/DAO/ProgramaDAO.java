/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Programa;
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
public class ProgramaDAO {
    
    private Connection DBConnection = null;

    public ProgramaDAO() {
        
        this.DBConnection = new ConexionBD().conexion();
    }
    
    public int createPrograma(Programa programa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            programa.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Programa.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                programa.setId_Programa(codigo);
            }

            SQL = "INSERT INTO TB_Programa "
                    + "(ID_Programa, Descripcion, EstadoPrograma, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, programa.getId_Programa());
            ps.setString(2, programa.getDescripcion());
            ps.setInt(3, programa.getEstadoPrograma());
            ps.setString(4, programa.getCreadoPor());
            ps.setDate(5, programa.getCreadoEn());
            ps.execute();

            ps.close();

            System.out.println("Codigo de Programa" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO insert -->" + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deletePrograma(int id) {
        try {

            String SQL = "DELETE FROM TB_Programa WHERE ID_Programa =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO Delete " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updatePrograma(Programa programa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            programa.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Programa SET "
                    + "Descripcion=?, EstadoPrograma=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Programa = ?";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setString(1, programa.getDescripcion());
            ps.setInt(2, programa.getEstadoPrograma());
            ps.setString(3, programa.getModificadoPor());
            ps.setDate(4, programa.getModificadoEn());
            ps.setInt(5, programa.getId_Programa());
            ps.execute();
            ps.close();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO UPDATE " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Programa> getAllPrograma() {
        ArrayList<Programa> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Programa";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Programa programa = new Programa();
                programa.setId_Programa(rs.getInt("ID_Programa"));
                programa.setDescripcion(rs.getString("Descripcion"));
                programa.setEstadoPrograma(rs.getInt("EstadoPrograma"));
                programa.setCreadoPor(rs.getString("CreadoPor"));
                programa.setModificadoPor(rs.getString("ModificadoPor"));
                programa.setCreadoEn(rs.getDate("CreadoEn"));
                programa.setModificadoEn(rs.getDate("ModificadoEn"));

                list.add(programa);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO getAllPrograma " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Programa getProgramaById(int id) {

        Programa programa = new Programa();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Programa where ID_Programa =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                programa.setId_Programa(rs.getInt("ID_Programa"));
                programa.setDescripcion(rs.getString("Descripcion"));
                programa.setEstadoPrograma(rs.getInt("EstadoPrograma"));
                programa.setCreadoPor(rs.getString("CreadoPor"));
                programa.setModificadoPor(rs.getString("ModificadoPor"));
                programa.setCreadoEn(rs.getDate("CreadoEn"));
                programa.setModificadoEn(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return programa;
        } catch (SQLException e) {
            System.out.println("Error enProgramaDAO getProgramasById " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en ProgramaDAO finish" + sqle.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
