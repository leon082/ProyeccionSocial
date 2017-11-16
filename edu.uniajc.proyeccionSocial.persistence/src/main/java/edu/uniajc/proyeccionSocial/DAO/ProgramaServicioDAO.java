/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.ProgramaServicio;
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
 * @author luis.leon
 */
public class ProgramaServicioDAO {

    private Connection DBConnection = null;
    

    public ProgramaServicioDAO() {
        
        this.DBConnection = new ConexionBD().conexion();
    }

    public int createProgramaServicio(ProgramaServicio progServi) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            progServi.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_ProgramaServicio.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                progServi.setId_ProgramaServicio(codigo);
            }

            SQL = "INSERT INTO TB_ProgramaServicio"
                    + " (ID_ProgramaServicio,Descripcion,ID_Programa, EstadoProgramaServicio,CreadoPor, CreadoEn) "
                    + " values(?,?,?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, progServi.getId_ProgramaServicio());
            ps.setString(2, progServi.getDescripcion());
            ps.setInt(3, progServi.getId_Programa());
            ps.setInt(4, progServi.getEstadoProgramaServicio());
            ps.setString(5, progServi.getCreadoPor());
            ps.setDate(6, progServi.getCreadoEn());
            ps.execute();

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en ProgramaServicioDAO Insert -->" + e.getMessage());
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteProgramaServicio(int id) {
        try {

            String SQL = "DELETE FROM TB_ProgramaServicio WHERE ID_ProgramaServicio =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ProgramaServicio DAO Delete " + e.getMessage());
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateProgramaServicio(ProgramaServicio progServi) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            progServi.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_ProgramaServicio SET "
                    + " Descripcion=?,ID_Programa=?, EstadoProgramaServicio=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_ProgramaServicio = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setString(1, progServi.getDescripcion());
            ps.setInt(2, progServi.getId_Programa());
            ps.setInt(3, progServi.getEstadoProgramaServicio());
            ps.setString(4, progServi.getModificadoPor());
            ps.setDate(5, progServi.getModificadoEn());
            ps.setInt(6, progServi.getId_ProgramaServicio());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ProgramaServicio DAO UPDATE " + e.getMessage());
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<ProgramaServicio> getAllProgramaServicioByPrograma(int idPrograma) {
        ArrayList<ProgramaServicio> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_ProgramaServicio where ID_Programa = " + idPrograma + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProgramaServicio progServi = new ProgramaServicio();
                progServi.setId_ProgramaServicio(rs.getInt("ID_ProgramaServicio"));
                progServi.setDescripcion(rs.getString("Descripcion"));
                progServi.setId_Programa(rs.getInt("ID_Programa"));
                progServi.setEstadoProgramaServicio(rs.getInt("EstadoProgramaServicio"));
                progServi.setCreadoPor(rs.getString("CREADOPOR"));
                progServi.setModificadoPor(rs.getString("MODIFICADOPOR"));
                progServi.setCreadoEn(rs.getDate("CREADOEN"));
                progServi.setModificadoEn(rs.getDate("MODIFICADOEN"));

                list.add(progServi);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en ProgramaServicio DAO getAllProgramaServicioByPrograma " + e.getMessage());
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public ProgramaServicio getProgramaServicioById(int id) {

        ProgramaServicio progServi = new ProgramaServicio();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_ProgramaServicio where ID_ProgramaServicio =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                progServi.setId_ProgramaServicio(rs.getInt("ID_ProgramaServicio"));
                progServi.setDescripcion(rs.getString("Descripcion"));
                progServi.setId_Programa(rs.getInt("ID_Programa"));
                progServi.setEstadoProgramaServicio(rs.getInt("EstadoProgramaServicio"));
                progServi.setCreadoPor(rs.getString("CREADOPOR"));
                progServi.setModificadoPor(rs.getString("MODIFICADOPOR"));
                progServi.setCreadoEn(rs.getDate("CREADOEN"));
                progServi.setModificadoEn(rs.getDate("MODIFICADOEN"));

            }
            ps.close();

            return progServi;
        } catch (SQLException e) {
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
    
    @PreDestroy
    public void finish() {
        try {
          
            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en PrgramaServicio DAO finish " + sqle.getMessage());
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
