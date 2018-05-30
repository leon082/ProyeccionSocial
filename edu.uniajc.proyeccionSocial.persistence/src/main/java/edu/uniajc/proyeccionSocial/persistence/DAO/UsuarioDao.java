/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IUsuarioDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class UsuarioDao implements IUsuarioDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(UsuarioDao.class.getName());

    public UsuarioDao(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public int createUsuario(Usuario user) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            user.setEstado(1);
            

            String SQL = "select SQ_TB_Usuario.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
             rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                user.setId_usuario(codigo);
            }

            SQL = "INSERT INTO TB_Usuario"
                    + " (ID_Usuario, ID_Tercero, Usuario, Contrasena, Estado) "
                    + " values(?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, user.getId_usuario());
            ps.setInt(2, user.getId_tercero());
            ps.setString(3, user.getUsuario());
            ps.setString(4, user.getContrasena());
            ps.setInt(5, user.getEstado());

            ps.execute();

           

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en UsuarioDao Insert -->" + e.getMessage());
            
            return 0;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUsuario(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "UPDATE TB_Usuario SET Estado=0 WHERE ID_Usuario = ? ";

             ps = connection.prepareStatement(SQL);
             ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en Usuario DAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             
            ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param usuario
     * @return
     */
    @Override
    public boolean updateUsuario(Usuario usuario) {
        PreparedStatement ps =null;
         
        try {

            
            String SQL = "UPDATE TB_Usuario SET "
                    + " ID_Tercero=?, Usuario=?, Estado=? , contrasena=? "
                    + " where ID_Usuario = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, usuario.getId_tercero());
            ps.setString(2, usuario.getUsuario());            
            ps.setInt(3, usuario.getEstado());
            ps.setString(4, usuario.getContrasena());
            ps.setInt(5, usuario.getId_usuario());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en Usuario DAO UPDATE " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
             ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Usuario> getAllUsuario() {
        PreparedStatement ps =null;
         ResultSet rs = null;
        ArrayList<Usuario> list = new ArrayList<>(0);
        try {

            

            final String SQL = "SELECT * from TB_Usuario where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setEstado(rs.getInt("Estado"));

                list.add(usuario);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en Usuario DAO getAllUsuarios" + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
              ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Usuario getUserById(int id) {

        Usuario usuario = null;
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Usuario where ID_Usuario = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setEstado(rs.getInt("Estado"));

            }
            

            return usuario;
        } catch (SQLException e) {
            LOGGER.error("Error en usuario DAO getUserById " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param user
     * @param contrasena
     * @return
     */
    @Override
    public Usuario getUsuarioLogin(String user, String contrasena) {

        Usuario usuario = null;
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Usuario where Usuario = ? and Contrasena = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, user);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            if (rs.next()) {

                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setEstado(rs.getInt("Estado"));

            }
            

            return usuario;
        } catch (SQLException e) {
            LOGGER.error("Error en usuario DAO getUsuarioLogin " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public Usuario getUserByUsername(String user) {

        Usuario usuario = null;
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Usuario where Usuario = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {

                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("ID_Usuario"));
                usuario.setId_tercero(rs.getInt("ID_Tercero"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setEstado(rs.getInt("Estado"));
                usuario.setContrasena(rs.getString("contrasena"));

            }
            

            return usuario;
        } catch (SQLException e) {
            LOGGER.error("Error en usuario DAO getUserByUsername " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
              ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public String getEmailByUsername(String user) {

        String correo = null;
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select tercero.correo from tb_usuario usuario inner join tb_tercero tercero on usuario.ID_TERCERO = tercero.ID_TERCERO "
                    + "where usuario.USUARIO = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, user);
             rs = ps.executeQuery();
            if (rs.next()) {

                correo = rs.getString("correo");

            }
            

            return correo;
        } catch (SQLException e) {
            LOGGER.error("Error en usuario DAO getCorreoByUsername " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

}
