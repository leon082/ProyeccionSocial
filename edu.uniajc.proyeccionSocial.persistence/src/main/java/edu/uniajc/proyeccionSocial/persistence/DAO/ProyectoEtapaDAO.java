/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.ProyectoEtapa;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IProyectoEtapaDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class ProyectoEtapaDAO implements IProyectoEtapaDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(ProyectoEtapaDAO.class.getName());

    public ProyectoEtapaDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param proyectoEtapa
     * @return
     */
    @Override
    public int createProyectoEtapa(ProyectoEtapa proyectoEtapa) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            proyectoEtapa.setCreadoen(fechaSQL);
            proyectoEtapa.setEstado(0);
            

            String SQL = "select SQ_TB_ProyectoEtapa.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                proyectoEtapa.setId_proyectoetapa(codigo);
            }

            SQL = "INSERT INTO TB_ProyectoEtapa"
                    + " (id_proyectoetapa, id_proyecto, id_etapa, estado, "
                    + "observacion, fechainicio, fechafin, CreadoPor, CreadoEn) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, proyectoEtapa.getId_proyectoetapa());
            ps.setInt(2, proyectoEtapa.getId_proyecto());
            ps.setInt(3, proyectoEtapa.getId_etapa());
            ps.setInt(4, proyectoEtapa.getEstado());
            ps.setString(5, proyectoEtapa.getObservacion());
            ps.setDate(6, proyectoEtapa.getFechainicio());
            ps.setDate(7, proyectoEtapa.getFechafin());
            ps.setString(8, proyectoEtapa.getCreadopor());
            ps.setDate(9, proyectoEtapa.getCreadoen());
            ps.execute();

            

            LOGGER.debug("Codigo de ProyectoEtapa" + codigo);

            return codigo;
        } catch (SQLException e) {
           LOGGER.error("Error en ProyectoEtapa DAO" + e.getMessage());
            
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
    public boolean deleteProyectoEtapa(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "UPDATE TB_ProyectoEtapa SET Estado=0 WHERE ID_ProyectoEtapa = ? ";

             ps = connection.prepareStatement(SQL);
             ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ProyectoEtapa DAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param proyectoEtapa
     * @return
     */
    @Override
    public boolean updateProyectoEtapa(ProyectoEtapa proyectoEtapa) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            proyectoEtapa.setModificadoen(fechaSQL);

            
            
            String SQL = "UPDATE TB_ProyectoEtapa SET "
                    + "id_proyecto=?, id_etapa=?, estado=?, observacion=?, "
                    + "fechainicio=?, fechafin=?, ModificadoPor=?, ModificadoEn=? "
                    + " where ID_ProyectoEtapa = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, proyectoEtapa.getId_proyecto());
            ps.setInt(2, proyectoEtapa.getId_etapa());
            ps.setInt(3, proyectoEtapa.getEstado());
            ps.setString(4, proyectoEtapa.getObservacion());
            ps.setDate(5, proyectoEtapa.getFechainicio());
            ps.setDate(6, proyectoEtapa.getFechafin());
            ps.setString(7, proyectoEtapa.getModificadopor());
            ps.setDate(8, proyectoEtapa.getModificadoen());
            ps.setInt(9, proyectoEtapa.getId_proyectoetapa());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ProyectoEtapa DAO UPDATE " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

         @Override
    public ArrayList<ProyectoEtapa> getAllProyectoEtapasAprobadasByProyecto(int idProyecto) {
        ArrayList<ProyectoEtapa> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_PROYECTOETAPA where ID_Proyecto = ? and estado = 1 ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idProyecto);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProyectoEtapa proyectoEtapa = new ProyectoEtapa();
                proyectoEtapa.setId_proyectoetapa(rs.getInt("ID_ProyectoEtapa"));
                proyectoEtapa.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyectoEtapa.setId_etapa(rs.getInt("ID_Etapa"));
                proyectoEtapa.setEstado(rs.getInt("Estado"));
                proyectoEtapa.setObservacion(rs.getString("Observacion"));
                proyectoEtapa.setFechainicio(rs.getDate("FechaInicio"));
                proyectoEtapa.setFechafin(rs.getDate("FechaFin"));
                proyectoEtapa.setCreadopor(rs.getString("Creadopor"));
                proyectoEtapa.setModificadopor(rs.getString("Modificadopor"));
                proyectoEtapa.setCreadoen(rs.getDate("Creadoen"));
                proyectoEtapa.setModificadoen(rs.getDate("Modificadoen"));

                list.add(proyectoEtapa);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en ProyectoEtapa DAO getAllProyectoEtapasAprobadasByProyecto " + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }
    /**
     *
     * @param idProyecto
     * @return
     */
    @Override
    public ArrayList<ProyectoEtapa> getAllProyectoEtapaByProyecto(int idProyecto) {
        ArrayList<ProyectoEtapa> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_PROYECTOETAPA where ID_Proyecto = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idProyecto);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProyectoEtapa proyectoEtapa = new ProyectoEtapa();
                proyectoEtapa.setId_proyectoetapa(rs.getInt("ID_ProyectoEtapa"));
                proyectoEtapa.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyectoEtapa.setId_etapa(rs.getInt("ID_Etapa"));
                proyectoEtapa.setEstado(rs.getInt("Estado"));
                proyectoEtapa.setObservacion(rs.getString("Observacion"));
                proyectoEtapa.setFechainicio(rs.getDate("FechaInicio"));
                proyectoEtapa.setFechafin(rs.getDate("FechaFin"));
                proyectoEtapa.setCreadopor(rs.getString("Creadopor"));
                proyectoEtapa.setModificadopor(rs.getString("Modificadopor"));
                proyectoEtapa.setCreadoen(rs.getDate("Creadoen"));
                proyectoEtapa.setModificadoen(rs.getDate("Modificadoen"));

                list.add(proyectoEtapa);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en ProyectoEtapa DAO getAllProyectoEtapaByProyecto " + e.getMessage());
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
    public ProyectoEtapa getProyectoEtapaById(int id) {

        ProyectoEtapa proyectoEtapa = new ProyectoEtapa();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_ProyectoEtapa where ID_ProyectoEtapa = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                proyectoEtapa.setId_proyectoetapa(rs.getInt("ID_ProyectoEtapa"));
                proyectoEtapa.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyectoEtapa.setId_etapa(rs.getInt("ID_Etapa"));
                proyectoEtapa.setEstado(rs.getInt("Estado"));
                proyectoEtapa.setObservacion(rs.getString("Observacion"));
                proyectoEtapa.setFechainicio(rs.getDate("FechaInicio"));
                proyectoEtapa.setFechafin(rs.getDate("FechaFin"));
                proyectoEtapa.setCreadopor(rs.getString("Creadopor"));
                proyectoEtapa.setModificadopor(rs.getString("Modificadopor"));
                proyectoEtapa.setCreadoen(rs.getDate("Creadoen"));
                proyectoEtapa.setModificadoen(rs.getDate("Modificadoen"));

            }
            

            return proyectoEtapa;
        } catch (SQLException e) {
            LOGGER.error("Error en ProyectoEtapa DAO getProyectoEtapaById " + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
