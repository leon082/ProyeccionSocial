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
        ConexionBD bd = new ConexionBD();
        this.DBConnection = bd.conexion();
    }

    public int createPrograma(Programa programa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            programa.setCreadoen(fechaSQL);
            programa.setEstado(1);
            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Programa.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                programa.setId_programa(codigo);
            }

            SQL = "INSERT INTO TB_Programa "
                    + "(ID_Programa, Descripcion, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, programa.getId_programa());
            ps.setString(2, programa.getDescripcion());
            ps.setInt(3, programa.getEstado());
            ps.setString(4, programa.getCreadopor());
            ps.setDate(5, programa.getCreadoen());
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

            String SQL = "UPDATE TB_Programa SET Estado=0 WHERE ID_Programa =" + id + " ";

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

            programa.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Programa SET "
                    + "Descripcion=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Programa = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setString(1, programa.getDescripcion());
            ps.setInt(2, programa.getEstado());
            ps.setString(3, programa.getModificadopor());
            ps.setDate(4, programa.getModificadoen());
            ps.setInt(5, programa.getId_programa());
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

            final String SQL = "SELECT * from TB_Programa where estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Programa programa = new Programa();
                programa.setId_programa(rs.getInt("ID_Programa"));
                programa.setDescripcion(rs.getString("Descripcion"));
                programa.setEstado(rs.getInt("Estado"));
                programa.setCreadopor(rs.getString("CreadoPor"));
                programa.setModificadopor(rs.getString("ModificadoPor"));
                programa.setCreadoen(rs.getDate("CreadoEn"));
                programa.setModificadoen(rs.getDate("ModificadoEn"));

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

            String SQL = "select * from TB_Programa where ID_Programa =" + id + " and estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                programa.setId_programa(rs.getInt("ID_Programa"));
                programa.setDescripcion(rs.getString("Descripcion"));
                programa.setEstado(rs.getInt("Estado"));
                programa.setCreadopor(rs.getString("CreadoPor"));
                programa.setModificadopor(rs.getString("ModificadoPor"));
                programa.setCreadoen(rs.getDate("CreadoEn"));
                programa.setModificadoen(rs.getDate("ModificadoEn"));

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
