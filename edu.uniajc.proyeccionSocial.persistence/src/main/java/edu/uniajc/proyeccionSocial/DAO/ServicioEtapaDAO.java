/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.ServicioEtapa;
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
public class ServicioEtapaDAO {

    private Connection DBConnection = null;

    public ServicioEtapaDAO() {
  ConexionBD bd= new ConexionBD();
        this.DBConnection = bd.conexion();
    }

    public int createServicioEtapa(ServicioEtapa servicioEtapa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            servicioEtapa.setCreadoen(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_ServicioEtapa.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                servicioEtapa.setId_servicioetapa(codigo);
            }

            SQL = "INSERT INTO TB_ServicioEtapa"
                    + " (ID_ServicioEtapa, ID_Programa, ID_Servicio, "
                    + "Estado,CreadoPor, CreadoEn) values(?,?,?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, servicioEtapa.getId_servicioetapa());
            ps.setInt(2, servicioEtapa.getId_servicio());
            ps.setInt(3, servicioEtapa.getId_etapa());
            ps.setInt(4, servicioEtapa.getEstado());
            ps.setString(5, servicioEtapa.getCreadopor());
            ps.setDate(6, servicioEtapa.getCreadoen());
            ps.execute();

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en ServicioEtapaDAO Insert -->" + e.getMessage());
            Logger.getLogger(ServicioEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteServicioEtapa(int id) {
        try {

            String SQL = "UPDATE TB_ServicioEtapa SET Estado=0 WHERE "
                    + "ID_ServicioEtapa =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ServicioEtapa DAO Delete " + e.getMessage());
            Logger.getLogger(ServicioEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateServicioEtapa(ServicioEtapa servicioEtapa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            servicioEtapa.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_ServicioEtapa SET "
                    + "ID_Servicio=?, ID_Etapa=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_ServicioEtapa = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, servicioEtapa.getId_servicio());
            ps.setInt(2, servicioEtapa.getId_servicio());
            ps.setInt(3, servicioEtapa.getEstado());
            ps.setString(4, servicioEtapa.getModificadopor());
            ps.setDate(5, servicioEtapa.getModificadoen());
            ps.setInt(6, servicioEtapa.getId_servicioetapa());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ServicioEtapa DAO UPDATE " + e.getMessage());
            Logger.getLogger(ServicioEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<ServicioEtapa> getAllServicioEtapa() {
        ArrayList<ServicioEtapa> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_ServicioEtapa";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ServicioEtapa servicioEtapa = new ServicioEtapa();
                servicioEtapa.setId_servicioetapa(rs.getInt("ID_ServicioEtapa"));
                servicioEtapa.setId_servicio(rs.getInt("ID_Servicio"));
                servicioEtapa.setId_etapa(rs.getInt("ID_Etapa"));
                servicioEtapa.setEstado(rs.getInt("Estado"));
                servicioEtapa.setCreadopor(rs.getString("CREADOPOR"));
                servicioEtapa.setModificadopor(rs.getString("MODIFICADOPOR"));
                servicioEtapa.setCreadoen(rs.getDate("CREADOEN"));
                servicioEtapa.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(servicioEtapa);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en ServicioEtapa DAO getAllServicioEtapaByPrograma " + e.getMessage());
            Logger.getLogger(ServicioEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public ServicioEtapa getServicioEtapaById(int id) {

        ServicioEtapa servicioEtapa = new ServicioEtapa();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_ServicioEtapa where ID_ServicioEtapa =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                servicioEtapa.setId_servicioetapa(rs.getInt("ID_ServicioEtapa"));
                servicioEtapa.setId_servicio(rs.getInt("ID_Servicio"));
                servicioEtapa.setId_etapa(rs.getInt("ID_Etapa"));
                servicioEtapa.setEstado(rs.getInt("Estado"));
                servicioEtapa.setCreadopor(rs.getString("CREADOPOR"));
                servicioEtapa.setModificadopor(rs.getString("MODIFICADOPOR"));
                servicioEtapa.setCreadoen(rs.getDate("CREADOEN"));
                servicioEtapa.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            ps.close();

            return servicioEtapa;
        } catch (SQLException e) {
            Logger.getLogger(ServicioEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en ServicioEtapa DAO finish " + sqle.getMessage());
            Logger.getLogger(ServicioEtapaDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
