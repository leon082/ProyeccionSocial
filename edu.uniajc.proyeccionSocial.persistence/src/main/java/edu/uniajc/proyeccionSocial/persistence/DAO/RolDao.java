/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IRolDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class RolDao implements IRolDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(RolDao.class.getName());

    public RolDao(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param rol
     * @return
     */
    @Override
    public int createRol(Rol rol) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            rol.setCreadoen(fechaSQL);
            rol.setEstado(1);

            

            String SQL = "select SQ_TB_Rol.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                rol.setId_rol(codigo);
            }

            SQL = "INSERT INTO TB_Rol"
                    + " (ID_Rol,Valor,Descripcion, Estado,creadopor,creadoen) "
                    + " values(?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, rol.getId_rol());
            ps.setString(2, rol.getValor());
            ps.setString(3, rol.getDescripcion());
            ps.setInt(4, rol.getEstado());
            ps.setString(5, rol.getCreadopor());
            ps.setDate(6, rol.getCreadoen());

            ps.execute();

            

            return codigo;
        } catch (SQLException e) {
           LOGGER.error("Error en RolDao Insert -->" + e.getMessage());
            
            return 0;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteRol(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "UPDATE TB_Rol SET Estado=0 WHERE ID_Rol = ? ";

             ps = connection.prepareStatement(SQL);
             ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en Rol DAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param rol
     * @return
     */
    @Override
    public boolean updateRol(Rol rol) {
        PreparedStatement ps =null;
         
        try {

            
            String SQL = "UPDATE TB_Rol SET "
                    + " Valor=?, Descripcion=?, Estado=? "
                    + " where ID_Rol = ?";
            ps = connection.prepareStatement(SQL);

            ps.setString(1, rol.getValor());
            ps.setString(2, rol.getDescripcion());
            ps.setInt(3, rol.getEstado());
            ps.setInt(4, rol.getId_rol());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en Rol DAO UPDATE " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Rol> getAllRol() {
        ArrayList<Rol> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_Rol where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getString("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstado(rs.getInt("Estado"));

                list.add(rol);
            }


            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en Rol DAO getAllUsuarios" + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Rol getRolById(int id) {

        Rol rol = new Rol();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Rol where ID_Rol = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                rol.setId_rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getString("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstado(rs.getInt("Estado"));

            }
            

            return rol;
        } catch (SQLException e) {
            LOGGER.error("Error en Rol DAO getRolById " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param idUsuario
     * @return
     */
    @Override
    public List<Rol> getRolesByUser(int idUsuario) {
        List<Rol> listaRoles = new ArrayList<>();
        PreparedStatement ps =null;
         ResultSet rs = null;

        try {
            

            String SQL = "select TB_Rol.* from TB_ROL inner join TB_USUARIOROL on TB_ROL.ID_ROL = TB_USUARIOROL.ID_ROL and TB_USUARIOROL.ID_USUARIO = ? and TB_ROL.estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getString("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstado(rs.getInt("Estado"));
                listaRoles.add(rol);
            }
            

            return listaRoles;
        } catch (SQLException e) {
            LOGGER.error("Error en Rol DAO getRolesByUser " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }
    }

}
