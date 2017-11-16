/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Oferente;
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
public class OferenteDao {

    private Connection DBConnection = null;
    

    public OferenteDao() {
        
        this.DBConnection = new ConexionBD().conexion();
    }

    public int createOferente(Oferente oferente) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            oferente.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Oferente.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                oferente.setId_Oferente(codigo);
            }

            SQL = "INSERT INTO TB_Oferente"
                    + " (ID_Oferente,ID_Proyecto,ID_Tercero, EstadoOferente,Observacion,CreadoPor, CreadoEn) "
                    + "values(?,?,?,?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, oferente.getId_Oferente());
            ps.setInt(2, oferente.getId_Proyecto());
            ps.setInt(3, oferente.getId_Tercero());
            ps.setInt(4, oferente.getEstadoOferente());
            ps.setString(5, oferente.getObservacion());
            ps.setString(6, oferente.getCreadoPor());
            ps.setDate(7, oferente.getCreadoEn());
            ps.execute();

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en OferenteDAO Insert -->" + e.getMessage());
            Logger.getLogger(OferenteDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteOferente(int id) {
        try {

            String SQL = "DELETE FROM TB_Oferente WHERE ID_Oferente =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Oferente DAO Delete " + e.getMessage());
            Logger.getLogger(OferenteDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateOferente(Oferente oferente) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            oferente.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Oferente SET "
                    + "ID_Proyecto=?,ID_Tercero=?, EstadoOferente=?, Observacion=?,ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Oferente = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, oferente.getId_Proyecto());
            ps.setInt(2, oferente.getId_Tercero());
            ps.setInt(3, oferente.getEstadoOferente());
            ps.setString(4, oferente.getObservacion());
            ps.setString(5, oferente.getModificadoPor());
            ps.setDate(6, oferente.getModificadoEn());
            ps.setInt(7, oferente.getId_Oferente());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Oferente DAO UPDATE " + e.getMessage());
            Logger.getLogger(OferenteDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Oferente> getAllOferentes() {
        ArrayList<Oferente> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Oferente";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Oferente oferente = new Oferente();
                oferente.setId_Oferente(rs.getInt("ID_Oferente"));
                oferente.setId_Proyecto(rs.getInt("ID_Proyecto"));
                oferente.setId_Tercero(rs.getInt("ID_Tercero"));
                oferente.setEstadoOferente(rs.getInt("EstadoOferente"));
                oferente.setObservacion(rs.getString("Observacion"));
                oferente.setCreadoPor(rs.getString("CREADOPOR"));
                oferente.setModificadoPor(rs.getString("MODIFICADOPOR"));
                oferente.setCreadoEn(rs.getDate("CREADOEN"));
                oferente.setModificadoEn(rs.getDate("MODIFICADOEN"));

                list.add(oferente);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en Oferentes DAO getAllOferentes " + e.getMessage());
            Logger.getLogger(OferenteDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Oferente getOferenteById(int id) {

        Oferente oferente = new Oferente();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Oferente where ID_Oferente =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                oferente.setId_Oferente(rs.getInt("ID_Oferente"));
                oferente.setId_Proyecto(rs.getInt("ID_Proyecto"));
                oferente.setId_Tercero(rs.getInt("ID_Tercero"));
                oferente.setEstadoOferente(rs.getInt("EstadoOferente"));
                oferente.setObservacion(rs.getString("Observacion"));
                oferente.setCreadoPor(rs.getString("CREADOPOR"));
                oferente.setModificadoPor(rs.getString("MODIFICADOPOR"));
                oferente.setCreadoEn(rs.getDate("CREADOEN"));
                oferente.setModificadoEn(rs.getDate("MODIFICADOEN"));

            }
            ps.close();

            return oferente;
        } catch (SQLException e) {
            Logger.getLogger(OferenteDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
    
     @PreDestroy
    public void finish() {
        try {
          
            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en Oferente DAO finish " + sqle.getMessage());
            Logger.getLogger(OferenteDao.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
