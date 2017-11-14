/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import edu.uniajc.proyeccionSocial.interfaces.model.FaseProyecto;
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
public class FaseProyectoDAO {

    private Connection DBConnection = null;

    public FaseProyectoDAO(Connection openConnection) {
        this.DBConnection = openConnection;
    }

    public int createFaseProyecto(FaseProyecto faseProyecto) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            faseProyecto.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_FaseProyecto.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                faseProyecto.setId_FaseProyecto(codigo);
            }

            SQL = "INSERT INTO TB_FaseProyecto"
                    + "(ID_FaseProyecto,ID_Proyecto,ID_Fase, EstadoFaseProyecto, Observacion,FechaInicio,FechaFin,"
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);
            ps.setInt(1, faseProyecto.getId_FaseProyecto());
            ps.setInt(2, faseProyecto.getId_Proyecto());
            ps.setInt(3, faseProyecto.getId_Fase());
            ps.setInt(4, faseProyecto.getEstadoFaseProyecto());
            ps.setString(5, faseProyecto.getObservacion());
            ps.setDate(6, faseProyecto.getFechaInicio());
            ps.setDate(7, faseProyecto.getFechaFin());
            ps.setString(8, faseProyecto.getCreadoPor());
            ps.setDate(9, faseProyecto.getCreadoEn());
            ps.execute();

            ps.close();

            System.out.println("Codigo de FaseProyecto" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en FaseProyecto DAO insert -->" + e.getMessage());
            Logger.getLogger(FaseProyectoDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteFaseProyecto(int id) {
        try {

            String SQL = "DELETE FROM TB_FaseProyecto WHERE ID_FaseProyecto =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en FaseProyecto DAO Delete " + e.getMessage());
            Logger.getLogger(FaseProyectoDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateFaseProyecto(FaseProyecto faseProyecto) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            faseProyecto.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_FaseProyecto SET "
                    + "ID_Proyecto=?,ID_Fase=?, EstadoFaseProyecto=?, Observacion=?,"
                    + " FechaInicio=?,FechaFin=?,ModificadoPor=?, ModificadoEn=? "
                    + "where ID_FaseProyecto = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, faseProyecto.getId_Proyecto());
            ps.setInt(2, faseProyecto.getId_Fase());
            ps.setInt(3, faseProyecto.getEstadoFaseProyecto());
            ps.setString(4, faseProyecto.getObservacion());
            ps.setDate(5, faseProyecto.getFechaInicio());
            ps.setDate(6, faseProyecto.getFechaFin());
            ps.setString(6, faseProyecto.getModificadoPor());
            ps.setDate(7, faseProyecto.getModificadoEn());
            ps.setInt(8, faseProyecto.getId_FaseProyecto());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Proyecto DAO UPDATE " + e.getMessage());
            Logger.getLogger(FaseProyectoDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<FaseProyecto> getAllFaseProyectos() {
        ArrayList<FaseProyecto> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_FaseProyecto";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FaseProyecto faseProyecto = new FaseProyecto();
                faseProyecto.setId_FaseProyecto(rs.getInt("ID_FaseProyecto"));
                faseProyecto.setId_Proyecto(rs.getInt("ID_Proyecto"));
                faseProyecto.setId_Fase(rs.getInt("ID_Fase"));
                faseProyecto.setEstadoFaseProyecto(rs.getInt("EstadoFaseProyecto"));
                faseProyecto.setObservacion(rs.getString("Observacion"));
                faseProyecto.setFechaInicio(rs.getDate("FechaInicio"));
                faseProyecto.setFechaFin(rs.getDate("FechaFin"));
                faseProyecto.setCreadoPor(rs.getString("CREADOPOR"));
                faseProyecto.setModificadoPor(rs.getString("MODIFICADOPOR"));
                faseProyecto.setCreadoEn(rs.getDate("CREADOEN"));
                faseProyecto.setModificadoEn(rs.getDate("MODIFICADOEN"));

                list.add(faseProyecto);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            Logger.getLogger(FaseProyectoDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public FaseProyecto getFaseProyectoById(int id) {

        FaseProyecto faseProyecto = new FaseProyecto();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_FaseProyecto where ID_FaseProyecto =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                faseProyecto.setId_FaseProyecto(rs.getInt("ID_FaseProyecto"));
                faseProyecto.setId_Proyecto(rs.getInt("ID_Proyecto"));
                faseProyecto.setId_Fase(rs.getInt("ID_Fase"));
                faseProyecto.setEstadoFaseProyecto(rs.getInt("EstadoFaseProyecto"));
                faseProyecto.setObservacion(rs.getString("Observacion"));
                faseProyecto.setFechaInicio(rs.getDate("FechaInicio"));
                faseProyecto.setFechaFin(rs.getDate("FechaFin"));
                faseProyecto.setCreadoPor(rs.getString("CREADOPOR"));
                faseProyecto.setModificadoPor(rs.getString("MODIFICADOPOR"));
                faseProyecto.setCreadoEn(rs.getDate("CREADOEN"));
                faseProyecto.setModificadoEn(rs.getDate("MODIFICADOEN"));

            }
            ps.close();

            return faseProyecto;
        } catch (SQLException e) {
            Logger.getLogger(FaseProyectoDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
}
