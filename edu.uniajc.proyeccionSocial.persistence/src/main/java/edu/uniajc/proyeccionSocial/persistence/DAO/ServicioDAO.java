/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Servicio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IServicioDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class ServicioDAO implements IServicioDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(ServicioDAO.class.getName());

    public ServicioDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param servicio
     * @return
     */
    @Override
    public int createServicio(Servicio servicio) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            servicio.setCreadoen(fechaSQL);
            servicio.setEstado(1);

            String SQL = "select SQ_TB_Servicio.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                servicio.setId_servicio(codigo);
            }

            SQL = "INSERT INTO TB_Servicio "
                    + "(ID_Servicio, Descripcion, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?) ";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, servicio.getId_servicio());
            ps.setString(2, servicio.getDescripcion());
            ps.setInt(3, servicio.getEstado());
            ps.setString(4, servicio.getCreadopor());
            ps.setDate(5, servicio.getCreadoen());
            ps.execute();

            System.out.println("Codigo de Servicio" + codigo);

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en ServicioDAO insert -->" + e.getMessage());

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
    public boolean deleteServicio(int id) {
        PreparedStatement ps = null;

        try {

            String SQL = "UPDATE TB_Servicio SET Estado=0 WHERE ID_Servicio = ? ";

            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ServicioDAO Delete " + e.getMessage());

            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
          
              ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param servicio
     * @return
     */
    @Override
    public boolean updateServicio(Servicio servicio) {
        PreparedStatement ps = null;

        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            servicio.setModificadoen(fechaSQL);

            String SQL = "UPDATE TB_Servicio SET "
                    + "Descripcion=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Servicio = ?";
            ps = connection.prepareStatement(SQL);

            ps.setString(1, servicio.getDescripcion());
            ps.setInt(2, servicio.getEstado());
            ps.setString(3, servicio.getModificadopor());
            ps.setDate(4, servicio.getModificadoen());
            ps.setInt(5, servicio.getId_servicio());
            ps.execute();

            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ServicioDAO UPDATE " + e.getMessage());

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
    public ArrayList<Servicio> getAllServicio() {
        ArrayList<Servicio> list = new ArrayList<>(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "SELECT * from TB_Servicio where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId_servicio(rs.getInt("ID_Servicio"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setEstado(rs.getInt("Estado"));
                servicio.setCreadopor(rs.getString("CreadoPor"));
                servicio.setModificadopor(rs.getString("ModificadoPor"));
                servicio.setCreadoen(rs.getDate("CreadoEn"));
                servicio.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(servicio);
            }

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en ServicioDAO getAllServicio " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param idProg
     * @return
     */
    @Override
    public ArrayList<Servicio> getAllServicioByProg(int idProg) {
        // getAllEtapaByServicio
        ArrayList<Servicio> list = new ArrayList<>(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "select s.* from tb_servicio s \n"
                    + "inner join TB_PROGRAMASERVICIO ps on s.ID_SERVICIO = ps.ID_SERVICIO\n"
                    + "where ps.ESTADO=1 and  ps.ID_PROGRAMA= ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idProg);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId_servicio(rs.getInt("ID_Servicio"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setEstado(rs.getInt("Estado"));
                servicio.setCreadopor(rs.getString("CreadoPor"));
                servicio.setModificadopor(rs.getString("ModificadoPor"));
                servicio.setCreadoen(rs.getDate("CreadoEn"));
                servicio.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(servicio);
            }

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en ServicioDAO getAllServiciobyprog " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
              ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param idServicio
     * @return
     */
    @Override
    public boolean isInProg(int idServicio) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {

            

            final String SQL = "select s.* from tb_servicio s \n"
                    + "inner join TB_PROGRAMASERVICIO ps on s.ID_SERVICIO = ps.ID_SERVICIO\n"
                    + "where ps.ESTADO = 1 and s.ID_SERVICIO = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idServicio);
            rs = ps.executeQuery();

            result = rs.next();

            

            return result;
        } catch (SQLException e) {
            LOGGER.error("Error en ServicioDAO isInProg " + e.getMessage());

            return result;
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
    public Servicio getServicioById(int id) {

        Servicio servicio = new Servicio();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Servicio where ID_Servicio = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                servicio.setId_servicio(rs.getInt("ID_Servicio"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setEstado(rs.getInt("Estado"));
                servicio.setCreadopor(rs.getString("CreadoPor"));
                servicio.setModificadopor(rs.getString("ModificadoPor"));
                servicio.setCreadoen(rs.getDate("CreadoEn"));
                servicio.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return servicio;
        } catch (SQLException e) {
            LOGGER.error("Error enServicioDAO getServiciosById " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

}
