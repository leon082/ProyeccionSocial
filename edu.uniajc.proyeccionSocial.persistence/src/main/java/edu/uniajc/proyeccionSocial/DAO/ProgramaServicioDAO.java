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
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author luis.leon
 */
public class ProgramaServicioDAO {

    public int createProgramaServicio(ProgramaServicio progServi) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            progServi.setCreadoen(fechaSQL);
            progServi.setEstado(1);
            PreparedStatement ps = null;

            String SQL = "select SQ_TB_ProgramaServicio.nextval ID from dual";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                progServi.setId_programaservicio(codigo);
            }

            SQL = "INSERT INTO TB_ProgramaServicio"
                    + " (ID_ProgramaServicio, ID_Programa, ID_Servicio, "
                    + "Estado,CreadoPor, CreadoEn) values(?,?,?,?,?,?)";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);

            ps.setInt(1, progServi.getId_programaservicio());
            ps.setInt(2, progServi.getId_programa());
            ps.setInt(3, progServi.getId_servicio());
            ps.setInt(4, progServi.getEstado());
            ps.setString(5, progServi.getCreadopor());
            ps.setDate(6, progServi.getCreadoen());
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

            String SQL = "UPDATE TB_ProgramaServicio SET Estado=0 WHERE "
                    + "ID_ProgramaServicio =" + id + " ";

            PreparedStatement ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ProgramaServicio DAO Delete " + e.getMessage());
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean deleteProgramaServicioByProg(int id) {
        try {

            String SQL = "DELETE FROM tb_programaservicio WHERE "
                    + "ID_Programa =" + id + " ";

            PreparedStatement ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
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

            progServi.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_ProgramaServicio SET "
                    + "ID_Programa=?, ID_Servicio=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_ProgramaServicio = ?";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);

            ps.setInt(1, progServi.getId_programa());
            ps.setInt(2, progServi.getId_servicio());
            ps.setInt(3, progServi.getEstado());
            ps.setString(4, progServi.getModificadopor());
            ps.setDate(5, progServi.getModificadoen());
            ps.setInt(6, progServi.getId_programaservicio());

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

            final String SQL = "SELECT * from TB_ProgramaServicio where ID_Programa = " + idPrograma + " and estado = 1";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProgramaServicio progServi = new ProgramaServicio();
                progServi.setId_programaservicio(rs.getInt("ID_ProgramaServicio"));
                progServi.setId_programa(rs.getInt("ID_Programa"));
                progServi.setId_servicio(rs.getInt("ID_Servicio"));
                progServi.setEstado(rs.getInt("Estado"));
                progServi.setCreadopor(rs.getString("CREADOPOR"));
                progServi.setModificadopor(rs.getString("MODIFICADOPOR"));
                progServi.setCreadoen(rs.getDate("CREADOEN"));
                progServi.setModificadoen(rs.getDate("MODIFICADOEN"));

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

            String SQL = "select * from TB_ProgramaServicio where ID_ProgramaServicio =" + id + " and estado = 1";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                progServi.setId_programaservicio(rs.getInt("ID_ProgramaServicio"));
                progServi.setId_programa(rs.getInt("ID_Programa"));
                progServi.setId_servicio(rs.getInt("ID_Servicio"));
                progServi.setEstado(rs.getInt("Estado"));
                progServi.setCreadopor(rs.getString("CREADOPOR"));
                progServi.setModificadopor(rs.getString("MODIFICADOPOR"));
                progServi.setCreadoen(rs.getDate("CREADOEN"));
                progServi.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            ps.close();

            return progServi;
        } catch (SQLException e) {
            Logger.getLogger(ProgramaServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

   

}
