/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Servicio;
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
public class ServicioDAO {
    
    private Connection DBConnection = null;

    public ServicioDAO() {
          ConexionBD bd= new ConexionBD();
        this.DBConnection = bd.conexion();
    }
    
    public int createServicio(Servicio servicio) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            servicio.setCreadoen(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Servicio.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                servicio.setId_servicio(codigo);
            }

            SQL = "INSERT INTO TB_Servicio "
                    + "(ID_Servicio, Descripcion, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, servicio.getId_servicio());
            ps.setString(2, servicio.getDescripcion());
            ps.setInt(3, servicio.getEstado());
            ps.setString(4, servicio.getCreadopor());
            ps.setDate(5, servicio.getCreadoen());
            ps.execute();

            ps.close();

            System.out.println("Codigo de Servicio" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en ServicioDAO insert -->" + e.getMessage());
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteServicio(int id) {
        try {

            String SQL = "UPDATE TB_Servicio SET Estado=0 WHERE ID_Servicio =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ServicioDAO Delete " + e.getMessage());
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateServicio(Servicio servicio) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            servicio.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Servicio SET "
                    + "Descripcion=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Servicio = ?";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setString(1, servicio.getDescripcion());
            ps.setInt(2, servicio.getEstado());
            ps.setString(3, servicio.getModificadopor());
            ps.setDate(4, servicio.getModificadoen());
            ps.setInt(5, servicio.getId_servicio());
            ps.execute();
            ps.close();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ServicioDAO UPDATE " + e.getMessage());
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Servicio> getAllServicio() {
        ArrayList<Servicio> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Servicio";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId_servicio(rs.getInt("ID_Servicio"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setEstado(rs.getInt("Estado"));
                servicio.setCreadopor(rs.getString("CreadoPor"));
                servicio.setModificadopor(rs.getString("ModificadoPor"));
                servicio.setCreadoen(rs.getDate("CreadoEn"));
                servicio.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(servicio);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en ServicioDAO getAllServicio " + e.getMessage());
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Servicio getServicioById(int id) {

        Servicio servicio = new Servicio();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Servicio where ID_Servicio =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                servicio.setId_servicio(rs.getInt("ID_Servicio"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setEstado(rs.getInt("Estado"));
                servicio.setCreadopor(rs.getString("CreadoPor"));
                servicio.setModificadopor(rs.getString("ModificadoPor"));
                servicio.setCreadoen(rs.getDate("CreadoEn"));
                servicio.setModificadoen(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return servicio;
        } catch (SQLException e) {
            System.out.println("Error enServicioDAO getServiciosById " + e.getMessage());
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en ServicioDAO finish" + sqle.getMessage());
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
