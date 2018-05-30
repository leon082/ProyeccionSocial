/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.SoporteProyectoEtapa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.ISoporteProyectoEtapaDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class SoporteProyectoEtapaDAO implements ISoporteProyectoEtapaDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(SoporteProyectoEtapaDAO.class.getName());

    public SoporteProyectoEtapaDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param soporteProyectoEtapa
     * @return
     */
    @Override
    public int createSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            soporteProyectoEtapa.setCreadoen(fechaSQL);

            

            String SQL = "select SQ_TB_SoporteProyectoEtapa.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                soporteProyectoEtapa.setId_soporteproyectoetapa(codigo);
            }

            SQL = "INSERT INTO TB_SoporteProyectoEtapa "
                    + "(ID_SoporteProyectoEtapa, id_proyectoetapa, archivo, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?) ";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, soporteProyectoEtapa.getId_soporteproyectoetapa());
            ps.setInt(2, soporteProyectoEtapa.getId_proyectoetapa());

            ps.setString(3, soporteProyectoEtapa.getArchivo());
            ps.setString(4, soporteProyectoEtapa.getCreadopor());
            ps.setDate(5, soporteProyectoEtapa.getCreadoen());
            ps.execute();



            LOGGER.debug("Codigo de SoporteProyectoEtapa" + codigo);

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en SoporteProyectoEtapaDAO insert -->" + e.getMessage());
            
            return 0;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param soporteProyectoEtapa
     * @return
     */
    @Override
    public boolean updateSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        PreparedStatement ps =null;
         
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            soporteProyectoEtapa.setModificadoen(fechaSQL);

            
            String SQL = "UPDATE TB_SoporteProyectoEtapa SET "
                    + "id_proyectoetapa=?,archivo=?, "
                    + "ModificadoPor=?, ModificadoEn=? where ID_SoporteProyectoEtapa = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, soporteProyectoEtapa.getId_proyectoetapa());

            ps.setString(2, soporteProyectoEtapa.getArchivo());
            ps.setString(3, soporteProyectoEtapa.getModificadopor());
            ps.setDate(4, soporteProyectoEtapa.getModificadoen());
            ps.setInt(5, soporteProyectoEtapa.getId_soporteproyectoetapa());
            ps.execute();
            

            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en SoporteProyectoEtapaDAO UPDATE " + e.getMessage());
            
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
    public ArrayList<SoporteProyectoEtapa> getAllSoporteProyectoEtapa() {
        ArrayList<SoporteProyectoEtapa> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_SoporteProyectoEtapa";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                SoporteProyectoEtapa soporteProyectoEtapa = new SoporteProyectoEtapa();
                soporteProyectoEtapa.setId_soporteproyectoetapa(rs.getInt("ID_SoporteProyectoEtapa"));
                soporteProyectoEtapa.setId_proyectoetapa(rs.getInt("ID_ProyectoEtapa"));

                soporteProyectoEtapa.setArchivo(rs.getString("Archivo"));
                soporteProyectoEtapa.setCreadopor(rs.getString("CreadoPor"));
                soporteProyectoEtapa.setModificadopor(rs.getString("ModificadoPor"));
                soporteProyectoEtapa.setCreadoen(rs.getDate("CreadoEn"));
                soporteProyectoEtapa.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(soporteProyectoEtapa);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en SoporteProyectoEtapaDAO getAllSoporteProyectoEtapa " + e.getMessage());
            
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
    public List<SoporteProyectoEtapa> getSoporteProyectoEtapaByIdProyectoEtapa(int id) {

        List<SoporteProyectoEtapa> list = new ArrayList<>();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_SoporteProyectoEtapa where ID_ProyectoEtapa = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
             rs = ps.executeQuery();
            while (rs.next()) {
                SoporteProyectoEtapa soporteProyectoEtapa = new SoporteProyectoEtapa();
                soporteProyectoEtapa.setId_soporteproyectoetapa(rs.getInt("ID_SoporteProyectoEtapa"));
                soporteProyectoEtapa.setId_proyectoetapa(rs.getInt("ID_ProyectoEtapa"));
                soporteProyectoEtapa.setArchivo(rs.getString("Archivo"));
                soporteProyectoEtapa.setCreadopor(rs.getString("CreadoPor"));
                soporteProyectoEtapa.setModificadopor(rs.getString("ModificadoPor"));
                soporteProyectoEtapa.setCreadoen(rs.getDate("CreadoEn"));
                soporteProyectoEtapa.setModificadoen(rs.getDate("ModificadoEn"));
                list.add(soporteProyectoEtapa);
                
            }

                

            
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en SoporteProyectoEtapaDAO getSoporteProyectoEtapasById " + e.getMessage());
            
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
    public SoporteProyectoEtapa getSoporteProyectoEtapaById(int id) {

        SoporteProyectoEtapa soporteProyectoEtapa = new SoporteProyectoEtapa();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_SoporteProyectoEtapa where ID_SoporteProyectoEtapa = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
             rs = ps.executeQuery();
            if (rs.next()) {

                soporteProyectoEtapa.setId_soporteproyectoetapa(rs.getInt("ID_SoporteProyectoEtapa"));
                soporteProyectoEtapa.setId_proyectoetapa(rs.getInt("ID_ProyectoEtapa"));
                soporteProyectoEtapa.setArchivo(rs.getString("Archivo"));
                soporteProyectoEtapa.setCreadopor(rs.getString("CreadoPor"));
                soporteProyectoEtapa.setModificadopor(rs.getString("ModificadoPor"));
                soporteProyectoEtapa.setCreadoen(rs.getDate("CreadoEn"));
                soporteProyectoEtapa.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return soporteProyectoEtapa;
        } catch (SQLException e) {
            LOGGER.error("Error en SoporteProyectoEtapaDAO getSoporteProyectoEtapasById " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

}
