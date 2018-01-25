/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Usuario;
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
public class UsuarioDao {

    private Connection DBConnection = null;

    public UsuarioDao() {
        ConexionBD bd = new ConexionBD();
        this.DBConnection = bd.conexion();
    }

    public int createUsuario(Usuario user) {
        try {
            user.setEstado(1);
            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Usuario.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                user.setId_usuario(codigo);
            }

            SQL = "INSERT INTO TB_Usuario"
                    + " (ID_Usuario, ID_Tercero, Usuario, Contrasena, Estado) "
                    + " values(?,?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, user.getId_usuario());
            ps.setInt(2, user.getId_tercero());
            ps.setString(3, user.getUsuario());
            ps.setString(4, user.getContrasena());
            ps.setInt(5, user.getEstado());

            ps.execute();

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en UsuarioDao Insert -->" + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteUsuario(int id) {
        try {

            String SQL = "UPDATE TB_Usuario SET Estado=0 WHERE ID_Usuario =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Usuario DAO Delete " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateUsuario(Usuario usuario) {
        try {

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Usuario SET "
                    + " ID_Tercero=?, Usuario=?, Estado=? , contrasena=? "
                    + " where ID_Usuario = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, usuario.getId_tercero());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getContrasena());
            ps.setInt(4, usuario.getEstado());
            ps.setInt(5, usuario.getId_usuario());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Usuario DAO UPDATE " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Usuario> getAllUsuario() {
        ArrayList<Usuario> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Usuario where estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setEstado(rs.getInt("Estado"));

                list.add(usuario);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en Usuario DAO getAllUsuarios" + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Usuario getUserById(int id) {

        Usuario usuario = null;
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Usuario where ID_Usuario =" + id + " and estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setEstado(rs.getInt("Estado"));

            }
            ps.close();

            return usuario;
        } catch (SQLException e) {
            System.out.println("Error en usuario DAO getUserById " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Usuario getUsuarioLogin(String user, String contrasena) {

        Usuario usuario = null;
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Usuario where Usuario = '" + user + "' and Contrasena ='" + contrasena + "' and estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setEstado(rs.getInt("Estado"));

            }
            ps.close();

            return usuario;
        } catch (SQLException e) {
            System.out.println("Error en usuario DAO getUsuarioLogin " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }
    
    

    public Usuario getUserByUsername(String user) {

        Usuario usuario = null;
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Usuario where Usuario ='" + user + "' ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setEstado(rs.getInt("Estado"));
                usuario.setContrasena(rs.getString("contrasena"));

            }
            ps.close();

            return usuario;
        } catch (SQLException e) {
            System.out.println("Error en usuario DAO getUserByUsername " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public String getEmailByUsername(String user) {

        String correo = null;
        try {

            PreparedStatement ps = null;

            String SQL = "select tercero.correo from tb_usuario usuario inner join tb_tercero tercero on usuario.ID_TERCERO = tercero.ID_TERCERO "
                    + "where usuario.USUARIO = '" + user + "' ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                correo = rs.getString("correo");

            }
            ps.close();

            return correo;
        } catch (SQLException e) {
            System.out.println("Error en usuario DAO getCorreoByUsername " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en Usuario DAO finish " + sqle.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
