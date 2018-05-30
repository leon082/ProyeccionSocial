/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.UsuarioRol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IUsuarioRolDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class UsuarioRolDAO implements IUsuarioRolDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(UsuarioRolDAO.class.getName());

    public UsuarioRolDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param usuarioRol
     * @return
     */
    @Override
    public int createUsuarioRol(UsuarioRol usuarioRol) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            usuarioRol.setCreadoen(fechaSQL);
            usuarioRol.setEstado(1);

            

            String SQL = "select SQ_TB_UsuarioRol.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                usuarioRol.setId_usuariorol(codigo);
            }

            SQL = "INSERT INTO TB_UsuarioRol "
                    + "(ID_UsuarioRol, ID_Usuario, ID_Rol, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?) ";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, usuarioRol.getId_usuariorol());
            ps.setInt(2, usuarioRol.getId_usuario());
            ps.setInt(3, usuarioRol.getId_rol());
            ps.setInt(4, usuarioRol.getEstado());
            ps.setString(5, usuarioRol.getCreadopor());
            ps.setDate(6, usuarioRol.getCreadoen());
            ps.execute();

            

            System.out.println("Codigo de UsuarioRol" + codigo);

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en UsuarioRolDAO insert -->" + e.getMessage());
            
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
    public boolean deleteUsuarioRol(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "UPDATE TB_UsuarioRol SET Estado=0 WHERE ID_UsuarioRol = ? ";

             ps = connection.prepareStatement(SQL);
             ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en UsuarioRolDAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
             ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param idUser
     * @return
     */
    @Override
    public boolean deleteRolesByUser(int idUser) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "DELETE FROM TB_UsuarioRol WHERE "
                    + "ID_Usuario = ? ";

             ps = connection.prepareStatement(SQL);
             ps.setInt(1, idUser);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en deleteRolesByUser " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             
             ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param usuarioRol
     * @return
     */
    @Override
    public boolean updateUsuarioRol(UsuarioRol usuarioRol) {
        PreparedStatement ps =null;
         
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            usuarioRol.setModificadoen(fechaSQL);

            
            String SQL = "UPDATE TB_UsuarioRol SET "
                    + "ID_Usuario=?, ID_Rol=?, Estado=?, "
                    + "ModificadoPor=?, ModificadoEn=? where ID_UsuarioRol = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, usuarioRol.getId_usuario());
            ps.setInt(2, usuarioRol.getId_rol());
            ps.setInt(3, usuarioRol.getEstado());
            ps.setString(4, usuarioRol.getModificadopor());
            ps.setDate(5, usuarioRol.getModificadoen());
            ps.setInt(6, usuarioRol.getId_usuariorol());
            ps.execute();
            

            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en UsuarioRolDAO UPDATE " + e.getMessage());
            
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
    public ArrayList<UsuarioRol> getAllUsuarioRol() {
        ArrayList<UsuarioRol> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_UsuarioRol where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setId_usuariorol(rs.getInt("ID_UsuarioRol"));
                usuarioRol.setId_usuario(rs.getInt("ID_Usuario"));
                usuarioRol.setId_rol(rs.getInt("ID_Rol"));
                usuarioRol.setEstado(rs.getInt("Estado"));
                usuarioRol.setCreadopor(rs.getString("CreadoPor"));
                usuarioRol.setModificadopor(rs.getString("ModificadoPor"));
                usuarioRol.setCreadoen(rs.getDate("CreadoEn"));
                usuarioRol.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(usuarioRol);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en UsuarioRolDAO getAllUsuarioRol " + e.getMessage());
            
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
    public UsuarioRol getUsuarioRolById(int id) {

        UsuarioRol usuarioRol = new UsuarioRol();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_UsuarioRol where ID_UsuarioRol = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
             rs = ps.executeQuery();
            if (rs.next()) {

                usuarioRol.setId_usuariorol(rs.getInt("ID_UsuarioRol"));
                usuarioRol.setId_usuario(rs.getInt("ID_Usuario"));
                usuarioRol.setId_rol(rs.getInt("ID_Rol"));
                usuarioRol.setEstado(rs.getInt("Estado"));
                usuarioRol.setCreadopor(rs.getString("CreadoPor"));
                usuarioRol.setModificadopor(rs.getString("ModificadoPor"));
                usuarioRol.setCreadoen(rs.getDate("CreadoEn"));
                usuarioRol.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return usuarioRol;
        } catch (SQLException e) {
            LOGGER.error("Error en UsuarioRolDAO getUsuarioRolsById " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
              ConexionBD.cerrarConexiones(rs, ps);
        }

    }

}
