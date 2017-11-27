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
            tercero.setCreadoen(fechaSQL);
            tercero.setEstado(1);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Tercero.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                tercero.setId_tercero(codigo);
            }

            SQL = "INSERT INTO TB_Tercero "
                    + "(ID_Tercero, ID_LV_TipoIdentificacion, NumIdentificacion, "
                    + "PrimerNombre, SegundoNombre, PrimerApellido, SegundoApellido, "
                    + "FechaNacimiento, TelefonoFijo, TelefonoCelular, Correo, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, tercero.getId_tercero());
            ps.setInt(2, tercero.getId_lv_tipoidentificacion());
            ps.setString(3, tercero.getNumidentificacion());
            ps.setString(4, tercero.getPrimernombre());
            ps.setString(5, tercero.getSegundonombre());
            ps.setString(6, tercero.getPrimerapellido());
            ps.setString(7, tercero.getSegundoapellido());
            ps.setDate(8, tercero.getFechanacimiento());
            ps.setString(9, tercero.getTelefonofijo());
            ps.setString(10, tercero.getTelefonocelular());
            ps.setString(11, tercero.getCorreo());
            ps.setInt(12, tercero.getEstado());
            ps.setString(13, tercero.getCreadopor());
            ps.setDate(14, tercero.getCreadoen());
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

            String SQL = "UPDATE TB_Tercero SET Estado=0 WHERE ID_Tercero =" + id + " ";

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

            tercero.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Tercero SET "
                    + "ID_LV_TipoIdentificacion=?, NumIdentificacion=?, "
                    + "PrimerNombre=?, SegundoNombre=?, PrimerApellido=?, SegundoApellido=?, "
                    + "FechaNacimiento=?, TelefonoFijo=?, TelefonoCelular=?, Correo =?, "
                    + "Estado=?, ModificadoPor=?, ModificadoEn=? where ID_Tercero = ?";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, tercero.getId_lv_tipoidentificacion());
            ps.setString(2, tercero.getNumidentificacion());
            ps.setString(3, tercero.getPrimernombre());
            ps.setString(4, tercero.getSegundonombre());
            ps.setString(5, tercero.getPrimerapellido());
            ps.setString(6, tercero.getSegundoapellido());
            ps.setDate(7, tercero.getFechanacimiento());
            ps.setString(8, tercero.getTelefonofijo());
            ps.setString(9, tercero.getTelefonocelular());
            ps.setString(10, tercero.getCorreo());
            ps.setInt(11, tercero.getEstado());
            ps.setString(12, tercero.getModificadopor());
            ps.setDate(13, tercero.getModificadoen());
            ps.setInt(14, tercero.getId_tercero());
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

            final String SQL = "SELECT * from TB_Tercero where estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tercero tercero = new Tercero();
                tercero.setId_tercero(rs.getInt("ID_Tercero"));
                tercero.setId_lv_tipoidentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumidentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimernombre(rs.getString("PrimerNombre"));
                tercero.setSegundonombre(rs.getString("SegundoNombre"));
                tercero.setPrimerapellido(rs.getString("PrimerApellido"));
                tercero.setSegundoapellido(rs.getString("SegundoApellido"));
                tercero.setFechanacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonofijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonocelular(rs.getString("TelefonoCelular"));
                tercero.setCorreo(rs.getString("Correo"));
                tercero.setEstado(rs.getInt("Estado"));
                tercero.setCreadopor(rs.getString("CreadoPor"));
                tercero.setModificadopor(rs.getString("ModificadoPor"));
                tercero.setCreadoen(rs.getDate("CreadoEn"));
                tercero.setModificadoen(rs.getDate("ModificadoEn"));

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

            String SQL = "select * from TB_Tercero where ID_Tercero =" + id + " and estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                
                tercero.setId_tercero(rs.getInt("ID_Tercero"));
                tercero.setId_lv_tipoidentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumidentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimernombre(rs.getString("PrimerNombre"));
                tercero.setSegundonombre(rs.getString("SegundoNombre"));
                tercero.setPrimerapellido(rs.getString("PrimerApellido"));
                tercero.setSegundoapellido(rs.getString("SegundoApellido"));
                tercero.setFechanacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonofijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonocelular(rs.getString("TelefonoCelular"));
                tercero.setCorreo(rs.getString("Correo"));
                tercero.setEstado(rs.getInt("Estado"));
                tercero.setCreadopor(rs.getString("CreadoPor"));
                tercero.setModificadopor(rs.getString("ModificadoPor"));
                tercero.setCreadoen(rs.getDate("CreadoEn"));
                tercero.setModificadoen(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return tercero;
        } catch (SQLException e) {
            System.out.println("Error en TerceroDAO getTercerosById " + e.getMessage());
            Logger.getLogger(TerceroDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }
        
         

    }
    
    public Tercero getTerceroByIdentificacion(int tipoDoc, String doc) {

        Tercero tercero =null;
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Tercero where ID_LV_TipoIdentificacion =" + tipoDoc + " and NumIdentificacion='" + doc + "' ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();  
                tercero= new Tercero();
                tercero.setId_tercero(rs.getInt("ID_Tercero"));
                tercero.setId_lv_tipoidentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumidentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimernombre(rs.getString("PrimerNombre"));
                tercero.setSegundonombre(rs.getString("SegundoNombre"));
                tercero.setPrimerapellido(rs.getString("PrimerApellido"));
                tercero.setSegundoapellido(rs.getString("SegundoApellido"));
                tercero.setFechanacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonofijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonocelular(rs.getString("TelefonoCelular"));
                tercero.setCorreo(rs.getString("Correo"));
                tercero.setEstado(rs.getInt("Estado"));
                tercero.setCreadopor(rs.getString("CreadoPor"));
                tercero.setModificadopor(rs.getString("ModificadoPor"));
                tercero.setCreadoen(rs.getDate("CreadoEn"));
                tercero.setModificadoen(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return tercero;
        } catch (SQLException e) {
            System.out.println("Error en TerceroDAO getTerceroByIdentificacion " + e.getMessage());
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
