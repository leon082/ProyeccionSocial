/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Etapa;
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
public class EtapaDAO {

    private Connection DBConnection = null;

    public EtapaDAO() {
  ConexionBD bd= new ConexionBD();
  
        this.DBConnection = bd.conexion();
    }

    public int createEtapa(Etapa etapa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            etapa.setCreadoen(fechaSQL);
            etapa.setEstado(1);
            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Etapa.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                etapa.setId_etapa(codigo);
            }

            SQL = "INSERT INTO TB_Etapa"
                    + " (ID_Etapa,Descripcion, Estado) "
                    + " values(?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, etapa.getId_etapa());
            ps.setString(2, etapa.getDescripcion());
            ps.setInt(3, etapa.getEstado());

            ps.execute();

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en EtapaDao Insert -->" + e.getMessage());
            Logger.getLogger(EtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteEtapa(int id) {
        try {

            String SQL = "UPDATE TB_Etapa SET Estado=0 WHERE ID_Etapa =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Etapa DAO Delete " + e.getMessage());
            Logger.getLogger(EtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateEtapa(Etapa etapa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            etapa.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Etapa SET Descripcion=?, Estado=? "
                    + " where ID_Etapa = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setString(1, etapa.getDescripcion());
            ps.setInt(2, etapa.getEstado());
            ps.setInt(3, etapa.getId_etapa());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Etapa DAO UPDATE " + e.getMessage());
            Logger.getLogger(EtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Etapa> getAllEtapa() {
        ArrayList<Etapa> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Etapa where estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etapa etapa = new Etapa();
                etapa.setId_etapa(rs.getInt("ID_Etapa"));
                etapa.setDescripcion(rs.getString("Descripcion"));
                etapa.setEstado(rs.getInt("Estado"));

                list.add(etapa);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en Etapa DAO getAllUsuarios" + e.getMessage());
            Logger.getLogger(EtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Etapa getEtapaById(int id) {

        Etapa etapa = new Etapa();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Etapa where ID_Etapa =" + id + " and estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                etapa.setId_etapa(rs.getInt("ID_Etapa"));
                etapa.setDescripcion(rs.getString("Descripcion"));
                etapa.setEstado(rs.getInt("Estado"));

            }
            ps.close();

            return etapa;
        } catch (SQLException e) {
            System.out.println("Error en Etapa DAO getEtapaById " + e.getMessage());
            Logger.getLogger(EtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en Etapa DAO finish " + sqle.getMessage());
            Logger.getLogger(EtapaDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
