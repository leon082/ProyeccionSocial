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

        this.DBConnection = new ConexionBD().conexion();
    }

    public int createUsuario(Usuario user) {
        try {

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Usuario.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                user.setId_Usuario(codigo);
            }

            SQL = "INSERT INTO TB_Usuario"
                    + " (ID_Usuario,ID_Tercero,Usuario, EstadoUsuario) "
                    + " values(?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, user.getId_Usuario());
            ps.setInt(2, user.getId_Tercero());
            ps.setString(3, user.getUsuario());
            ps.setInt(4, user.getEstado());

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

            String SQL = "DELETE FROM TB_Usuario WHERE ID_Usuario =" + id + " ";

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
                    + " ID_Tercero=?, Usuario=?, EstadoUsuario=? "
                    + " where ID_Usuario = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, usuario.getId_Tercero());
            ps.setString(2, usuario.getUsuario());
            ps.setInt(3, usuario.getEstado());
            ps.setInt(4, usuario.getId_Usuario());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Usuario DAO UPDATE " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Usuario> getAllUsuarios() {
        ArrayList<Usuario> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Usuario";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_Usuario(rs.getInt("ID_Usuario"));
                usuario.setId_Tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setEstado(rs.getInt("EstadoUsuario"));

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

        Usuario usuario = new Usuario();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Usuario where ID_Usuario =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                usuario.setId_Usuario(rs.getInt("ID_Usuario"));
                usuario.setId_Tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setEstado(rs.getInt("EstadoUsuario"));

            }
            ps.close();

            return usuario;
        } catch (SQLException e) {
            System.out.println("Error en usuario DAO getUserById " + e.getMessage());
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
