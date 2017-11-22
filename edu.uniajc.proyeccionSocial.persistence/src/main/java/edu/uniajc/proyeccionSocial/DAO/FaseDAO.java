/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Fase;
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
public class FaseDAO {
    
    private Connection DBConnection = null;

    public FaseDAO() {
          ConexionBD bd= new ConexionBD();
        this.DBConnection = bd.conexion();
    }
    
    public int createFase(Fase fase) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            fase.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Fase.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                fase.setId_Fase(codigo);
            }

            SQL = "INSERT INTO TB_Fase "
                    + "(ID_Fase, Descripcion, ID_ProgramaServicio, EstadoFase, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, fase.getId_Fase());
            ps.setString(2, fase.getDescripcion());
            ps.setInt(3, fase.getId_ProgramaServicio());
            ps.setInt(4, fase.getEstadoFase());
            ps.setString(5, fase.getCreadoPor());
            ps.setDate(6, fase.getCreadoEn());
            ps.execute();

            ps.close();

            System.out.println("Codigo de Fase" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en FaseDAO insert -->" + e.getMessage());
            Logger.getLogger(FaseDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteFase(int id) {
        try {

            String SQL = "DELETE FROM TB_Fase WHERE ID_Fase =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en FaseDAO Delete " + e.getMessage());
            Logger.getLogger(FaseDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateFase(Fase fase) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            fase.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Fase SET "
                    + "Descripcion=?, ID_ProgramaServicio=?, EstadoFase=?, "
                    + "ModificadoPor=?, ModificadoEn=? where ID_Fase = ?";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setString(1, fase.getDescripcion());
            ps.setInt(2, fase.getId_ProgramaServicio());
            ps.setInt(3, fase.getEstadoFase());
            ps.setString(4, fase.getModificadoPor());
            ps.setDate(5, fase.getModificadoEn());
            ps.setInt(6, fase.getId_Fase());
            ps.execute();
            ps.close();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Error en FaseDAO UPDATE " + e.getMessage());
            Logger.getLogger(FaseDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Fase> getAllFase() {
        ArrayList<Fase> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Fase";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fase fase = new Fase();
                fase.setId_Fase(rs.getInt("ID_Fase"));
                fase.setDescripcion(rs.getString("Descripcion"));
                fase.setId_ProgramaServicio(rs.getInt("ID_ProgramaServicio"));
                fase.setEstadoFase(rs.getInt("EstadoFase"));
                fase.setCreadoPor(rs.getString("CreadoPor"));
                fase.setModificadoPor(rs.getString("ModificadoPor"));
                fase.setCreadoEn(rs.getDate("CreadoEn"));
                fase.setModificadoEn(rs.getDate("ModificadoEn"));

                list.add(fase);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en FaseDAO getAllFase " + e.getMessage());
            Logger.getLogger(FaseDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Fase getFaseById(int id) {

        Fase fase = new Fase();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Fase where ID_Fase =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                fase.setId_Fase(rs.getInt("ID_Fase"));
                fase.setDescripcion(rs.getString("Descripcion"));
                fase.setId_ProgramaServicio(rs.getInt("ID_ProgramaServicio"));
                fase.setEstadoFase(rs.getInt("EstadoFase"));
                fase.setCreadoPor(rs.getString("CreadoPor"));
                fase.setModificadoPor(rs.getString("ModificadoPor"));
                fase.setCreadoEn(rs.getDate("CreadoEn"));
                fase.setModificadoEn(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return fase;
        } catch (SQLException e) {
            System.out.println("Error en FaseDAO getFasesById " + e.getMessage());
            Logger.getLogger(FaseDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en FaseDAO finish" + sqle.getMessage());
            Logger.getLogger(FaseDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
