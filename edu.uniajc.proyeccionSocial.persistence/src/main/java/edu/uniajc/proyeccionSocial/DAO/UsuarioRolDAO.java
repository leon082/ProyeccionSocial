/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.UsuarioRol;
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
public class UsuarioRolDAO {
    
    private Connection DBConnection = null;

    public UsuarioRolDAO() {
        
        this.DBConnection = new ConexionBD().conexion();
    }
    
    public int createUsuarioRol(UsuarioRol usuarioRol) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            usuarioRol.setCreadoEn(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_UsuarioRol.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                usuarioRol.setId_UsuarioRol(codigo);
            }

            SQL = "INSERT INTO TB_UsuarioRol "
                    + "(ID_UsuarioRol, ID_Usuario, ID_Rol, EstadoUsuarioRol, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?) ";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, usuarioRol.getId_UsuarioRol());
            ps.setInt(2, usuarioRol.getId_Usuario());
            ps.setInt(3, usuarioRol.getId_Rol());
            ps.setInt(4, usuarioRol.getEstadoUsuarioRol());
            ps.setString(5, usuarioRol.getCreadoPor());
            ps.setDate(6, usuarioRol.getCreadoEn());
            ps.execute();

            ps.close();

            System.out.println("Codigo de UsuarioRol" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en UsuarioRolDAO insert -->" + e.getMessage());
            Logger.getLogger(UsuarioRolDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteUsuarioRol(int id) {
        try {

            String SQL = "DELETE FROM TB_UsuarioRol WHERE ID_UsuarioRol =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en UsuarioRolDAO Delete " + e.getMessage());
            Logger.getLogger(UsuarioRolDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateUsuarioRol(UsuarioRol usuarioRol) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            usuarioRol.setModificadoEn(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_UsuarioRol SET "
                    + "ID_Usuario=?, ID_Rol=?, EstadoUsuarioRol=?, "
                    + "ModificadoPor=?, ModificadoEn=? where ID_UsuarioRol = ?";
            ps = this.DBConnection.prepareStatement(SQL);
            
            ps.setInt(1, usuarioRol.getId_Usuario());
            ps.setInt(2, usuarioRol.getId_Rol());
            ps.setInt(3, usuarioRol.getEstadoUsuarioRol());
            ps.setString(4, usuarioRol.getModificadoPor());
            ps.setDate(5, usuarioRol.getModificadoEn());
            ps.setInt(6, usuarioRol.getId_UsuarioRol());
            ps.execute();
            ps.close();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Error en UsuarioRolDAO UPDATE " + e.getMessage());
            Logger.getLogger(UsuarioRolDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<UsuarioRol> getAllUsuarioRol() {
        ArrayList<UsuarioRol> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_UsuarioRol";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setId_UsuarioRol(rs.getInt("ID_UsuarioRol"));
                usuarioRol.setId_Usuario(rs.getInt("ID_Usuario"));
                usuarioRol.setId_Rol(rs.getInt("ID_Rol"));
                usuarioRol.setEstadoUsuarioRol(rs.getInt("EstadoUsuarioRol"));
                usuarioRol.setCreadoPor(rs.getString("CreadoPor"));
                usuarioRol.setModificadoPor(rs.getString("ModificadoPor"));
                usuarioRol.setCreadoEn(rs.getDate("CreadoEn"));
                usuarioRol.setModificadoEn(rs.getDate("ModificadoEn"));

                list.add(usuarioRol);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en UsuarioRolDAO getAllUsuarioRol " + e.getMessage());
            Logger.getLogger(UsuarioRolDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public UsuarioRol getUsuarioRolById(int id) {

        UsuarioRol usuarioRol = new UsuarioRol();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_UsuarioRol where ID_UsuarioRol =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                usuarioRol.setId_UsuarioRol(rs.getInt("ID_UsuarioRol"));
                usuarioRol.setId_Usuario(rs.getInt("ID_Usuario"));
                usuarioRol.setId_Rol(rs.getInt("ID_Rol"));
                usuarioRol.setEstadoUsuarioRol(rs.getInt("EstadoUsuarioRol"));
                usuarioRol.setCreadoPor(rs.getString("CreadoPor"));
                usuarioRol.setModificadoPor(rs.getString("ModificadoPor"));
                usuarioRol.setCreadoEn(rs.getDate("CreadoEn"));
                usuarioRol.setModificadoEn(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return usuarioRol;
        } catch (SQLException e) {
            System.out.println("Error en UsuarioRolDAO getUsuarioRolsById " + e.getMessage());
            Logger.getLogger(UsuarioRolDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en UsuarioRolDAO finish" + sqle.getMessage());
            Logger.getLogger(UsuarioRolDAO.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }
}
