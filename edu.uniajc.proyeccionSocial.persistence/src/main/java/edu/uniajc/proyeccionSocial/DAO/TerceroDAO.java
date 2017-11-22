/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Tercero;
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
public class TerceroDAO {
    
    private Connection DBConnection = null;

    public TerceroDAO() {
          ConexionBD bd= new ConexionBD();
        this.DBConnection = bd.conexion();
    }
    
    public int createTercero(Tercero tercero) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            tercero.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Tercero.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                tercero.setId_Tercero(codigo);
            }

            SQL = "INSERT INTO TB_Tercero "
                    + "(ID_Tercero, ID_LV_TipoIdentificacion, NumIdentificacion, "
                    + "PrimerNombre, SegundoNombre, PrimerApellido, SegundoApellido, "
                    + "FechaNacimiento, TelefonoFijo, TelefonoCelular, EstadoTercero, "
                    + "CreadoPor, CreadoEn,Correo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, tercero.getId_Tercero());
            ps.setInt(2, tercero.getId_LV_TipoIdentificacion());
            ps.setString(3, tercero.getNumIdentificacion());
            ps.setString(4, tercero.getPrimerNombre());
            ps.setString(5, tercero.getSegundoNombre());
            ps.setString(6, tercero.getPrimerApellido());
            ps.setString(7, tercero.getSegundoApellido());
            ps.setDate(8, tercero.getFechaNacimiento());
            ps.setString(9, tercero.getTelefonoFijo());
            ps.setString(10, tercero.getTelefonoCelular());
            ps.setInt(11, tercero.getEstadoTercero());
            ps.setString(12, tercero.getCreadoPor());
            ps.setDate(13, tercero.getCreadoEn());
            ps.setString(14, tercero.getCorreo());
            ps.execute();

            ps.close();

            System.out.println("Codigo de Tercero" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en TerceroDAO insert -->" + e.getMessage());
            Logger.getLogger(TerceroDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteTercero(int id) {
        try {

            String SQL = "DELETE FROM TB_Tercero WHERE ID_Tercero =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en TerceroDAO Delete " + e.getMessage());
            Logger.getLogger(TerceroDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateTercero(Tercero tercero) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            tercero.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Tercero SET "
                    + "ID_LV_TipoIdentificacion=?, NumIdentificacion=?, "
                    + "PrimerNombre=?, SegundoNombre=?, PrimerApellido=?, SegundoApellido=?, "
                    + "FechaNacimiento=?, TelefonoFijo=?, TelefonoCelular=?, EstadoTercero=?, "
                    + "ModificadoPor=?, ModificadoEn=? , correo =? where ID_Tercero = ?";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, tercero.getId_LV_TipoIdentificacion());
            ps.setString(2, tercero.getNumIdentificacion());
            ps.setString(3, tercero.getPrimerNombre());
            ps.setString(4, tercero.getSegundoNombre());
            ps.setString(5, tercero.getPrimerApellido());
            ps.setString(6, tercero.getSegundoApellido());
            ps.setDate(7, tercero.getFechaNacimiento());
            ps.setString(8, tercero.getTelefonoFijo());
            ps.setString(9, tercero.getTelefonoCelular());
            ps.setInt(10, tercero.getEstadoTercero());
            ps.setString(11, tercero.getModificadoPor());
            ps.setDate(12, tercero.getModificadoEn());
            ps.setString(13, tercero.getCorreo());
            ps.setInt(14, tercero.getId_Tercero());
            ps.execute();
            ps.close();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Error en TerceroDAO UPDATE " + e.getMessage());
            Logger.getLogger(TerceroDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Tercero> getAllTercero() {
        ArrayList<Tercero> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Tercero";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tercero tercero = new Tercero();
                tercero.setId_Tercero(rs.getInt("ID_Tercero"));
                tercero.setId_LV_TipoIdentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumIdentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimerNombre(rs.getString("PrimerNombre"));
                tercero.setSegundoNombre(rs.getString("SegundoNombre"));
                tercero.setPrimerApellido(rs.getString("PrimerApellido"));
                tercero.setSegundoApellido(rs.getString("SegundoApellido"));
                tercero.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonoFijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonoCelular(rs.getString("TelefonoCelular"));
                tercero.setEstadoTercero(rs.getInt("EstadoTercero"));
                tercero.setCreadoPor(rs.getString("CreadoPor"));
                tercero.setModificadoPor(rs.getString("ModificadoPor"));
                tercero.setCreadoEn(rs.getDate("CreadoEn"));
                tercero.setModificadoEn(rs.getDate("ModificadoEn"));
                tercero.setCorreo(rs.getString("Correo"));

                list.add(tercero);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en TerceroDAO getAllTercero " + e.getMessage());
            Logger.getLogger(TerceroDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Tercero getTerceroById(int id) {

        Tercero tercero = new Tercero();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Tercero where ID_Tercero =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                
                tercero.setId_Tercero(rs.getInt("ID_Tercero"));
                tercero.setId_LV_TipoIdentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumIdentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimerNombre(rs.getString("PrimerNombre"));
                tercero.setSegundoNombre(rs.getString("SegundoNombre"));
                tercero.setPrimerApellido(rs.getString("PrimerApellido"));
                tercero.setSegundoApellido(rs.getString("SegundoApellido"));
                tercero.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonoFijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonoCelular(rs.getString("TelefonoCelular"));
                tercero.setEstadoTercero(rs.getInt("EstadoTercero"));
                tercero.setCreadoPor(rs.getString("CreadoPor"));
                tercero.setModificadoPor(rs.getString("ModificadoPor"));
                tercero.setCreadoEn(rs.getDate("CreadoEn"));
                tercero.setModificadoEn(rs.getDate("ModificadoEn"));
                tercero.setCorreo(rs.getString("Correo"));

            }
            ps.close();

            return tercero;
        } catch (SQLException e) {
            System.out.println("Error en TerceroDAO getTercerosById " + e.getMessage());
            Logger.getLogger(TerceroDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en TerceroDAO finish" + sqle.getMessage());
            Logger.getLogger(TerceroDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
