/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Beneficiario;
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
public class BeneficiarioDAO {

    private Connection DBConnection = null;

    public BeneficiarioDAO() {
  ConexionBD bd= new ConexionBD();
        this.DBConnection = bd.conexion();
    }

    public int createBeneficiario(Beneficiario beneficiario) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            beneficiario.setCreadoen(fechaSQL);
            beneficiario.setEstado(1);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Beneficiario.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                beneficiario.setId_beneficiario(codigo);
            }

            SQL = "INSERT INTO TB_Beneficiario"
                    + " (ID_Beneficiario, ID_Proyecto, ID_Tercero, Estado, "
                    + "Observacion, CreadoPor, CreadoEn) values(?,?,?,?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, beneficiario.getId_beneficiario());
            ps.setInt(2, beneficiario.getId_proyecto());
            ps.setInt(3, beneficiario.getId_tercero());
            ps.setInt(4, beneficiario.getEstado());
            ps.setString(5, beneficiario.getObservacion());
            ps.setString(6, beneficiario.getCreadopor());
            ps.setDate(7, beneficiario.getCreadoen());
            ps.execute();

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en BeneficiarioDAO Insert -->" + e.getMessage());
            Logger.getLogger(BeneficiarioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteBeneficiario(int id) {
        try {

            String SQL = "UPDATE TB_Beneficiario SET Estado=0 WHERE ID_Beneficiario =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en BeneficiarioDAO Delete " + e.getMessage());
            Logger.getLogger(BeneficiarioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateBeneficiario(Beneficiario beneficiario) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            beneficiario.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Beneficiario SET "
                    + "ID_Proyecto=?, ID_Tercero=?, Estado=?, "
                    + "Observacion=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Beneficiario = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, beneficiario.getId_proyecto());
            ps.setInt(2, beneficiario.getId_tercero());
            ps.setInt(3, beneficiario.getEstado());
            ps.setString(4, beneficiario.getObservacion());
            ps.setString(5, beneficiario.getModificadopor());
            ps.setDate(6, beneficiario.getModificadoen());
            ps.setInt(7, beneficiario.getId_beneficiario());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en BeneficiarioDAO UPDATE " + e.getMessage());
            Logger.getLogger(BeneficiarioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Beneficiario> getAllBeneficiario() {
        ArrayList<Beneficiario> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Beneficiario where estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Beneficiario beneficiario = new Beneficiario();
                beneficiario.setId_beneficiario(rs.getInt("ID_Beneficiario"));
                beneficiario.setId_proyecto(rs.getInt("ID_Proyecto"));
                beneficiario.setId_tercero(rs.getInt("ID_Tercero"));
                beneficiario.setEstado(rs.getInt("Estado"));
                beneficiario.setObservacion(rs.getString("Observacion"));
                beneficiario.setCreadopor(rs.getString("CreadoPor"));
                beneficiario.setModificadopor(rs.getString("ModificadoPor"));
                beneficiario.setCreadoen(rs.getDate("CreadoEn"));
                beneficiario.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(beneficiario);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en BeneficiariosDAO getAllBeneficiario " + e.getMessage());
            Logger.getLogger(BeneficiarioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
    
    public ArrayList<Beneficiario> getAllBeneficiarioByProyect(int idProyect ) {
        ArrayList<Beneficiario> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Beneficiario where estado = 1 and id_proyecto = "+idProyect+" ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Beneficiario beneficiario = new Beneficiario();
                beneficiario.setId_beneficiario(rs.getInt("ID_Beneficiario"));
                beneficiario.setId_proyecto(rs.getInt("ID_Proyecto"));
                beneficiario.setId_tercero(rs.getInt("ID_Tercero"));
                beneficiario.setEstado(rs.getInt("Estado"));
                beneficiario.setObservacion(rs.getString("Observacion"));
                beneficiario.setCreadopor(rs.getString("CreadoPor"));
                beneficiario.setModificadopor(rs.getString("ModificadoPor"));
                beneficiario.setCreadoen(rs.getDate("CreadoEn"));
                beneficiario.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(beneficiario);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en BeneficiariosDAO getAllBeneficiario " + e.getMessage());
            Logger.getLogger(BeneficiarioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Beneficiario getBeneficiarioById(int id) {

        Beneficiario beneficiario = new Beneficiario();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Beneficiario where ID_Beneficiario =" + id + " and estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                beneficiario.setId_beneficiario(rs.getInt("ID_Beneficiario"));
                beneficiario.setId_proyecto(rs.getInt("ID_Proyecto"));
                beneficiario.setId_tercero(rs.getInt("ID_Tercero"));
                beneficiario.setEstado(rs.getInt("Estado"));
                beneficiario.setObservacion(rs.getString("Observacion"));
                beneficiario.setCreadopor(rs.getString("CreadoPor"));
                beneficiario.setModificadopor(rs.getString("ModificadoPor"));
                beneficiario.setCreadoen(rs.getDate("CreadoEn"));
                beneficiario.setModificadoen(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return beneficiario;
        } catch (SQLException e) {
            Logger.getLogger(BeneficiarioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en BeneficiarioDAO finish " + sqle.getMessage());
            Logger.getLogger(BeneficiarioDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
