/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IEtapaDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class EtapaDAO implements IEtapaDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(EtapaDAO.class.getName());

    public EtapaDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param etapa
     * @return
     */
    @Override
    public int createEtapa(Etapa etapa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            etapa.setCreadoen(fechaSQL);
            etapa.setEstado(1);

            String SQL = "select SQ_TB_Etapa.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                etapa.setId_etapa(codigo);
            }

            SQL = "INSERT INTO TB_Etapa"
                    + " (ID_Etapa,Descripcion, Estado , creadopor , creadoen) "
                    + " values(?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, etapa.getId_etapa());
            ps.setString(2, etapa.getDescripcion());
            ps.setInt(3, etapa.getEstado());
            ps.setString(4, etapa.getCreadopor());
            ps.setDate(5, etapa.getCreadoen());

            ps.execute();

            

            return codigo;
        } catch (SQLException e) {

            LOGGER.error("Error en  EtapaDao Insert -->" + e.getMessage());

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
    public boolean deleteEtapa(int id) {
        PreparedStatement ps = null;

        try {

            String SQL = "UPDATE TB_Etapa SET Estado=0 WHERE ID_Etapa = ? ";

            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  EtapaDao Delete -->" + e.getMessage());
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
              ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param etapa
     * @return
     */
    @Override
    public boolean updateEtapa(Etapa etapa) {
        PreparedStatement ps = null;

        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            etapa.setModificadoen(fechaSQL);

            String SQL = "UPDATE TB_Etapa SET Descripcion=?, Estado=? ,modificadopor =? , modificadoen =? "
                    + " where ID_Etapa = ?";
            ps = connection.prepareStatement(SQL);

            ps.setString(1, etapa.getDescripcion());
            ps.setInt(2, etapa.getEstado());
            ps.setString(3, etapa.getModificadopor());
            ps.setDate(4, etapa.getModificadoen());
            ps.setInt(5, etapa.getId_etapa());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  EtapaDao Update -->" + e.getMessage());
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
    public ArrayList<Etapa> getAllEtapa() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Etapa> list = new ArrayList<>(0);
        try {

            final String SQL = "SELECT * from TB_Etapa where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Etapa etapa = new Etapa();
                etapa.setId_etapa(rs.getInt("ID_Etapa"));
                etapa.setDescripcion(rs.getString("Descripcion"));
                etapa.setEstado(rs.getInt("Estado"));
                etapa.setCreadopor(rs.getString("creadopor"));
                etapa.setModificadopor(rs.getString("modificadopor"));
                etapa.setCreadoen(rs.getDate("creadoen"));
                etapa.setModificadoen(rs.getDate("modificadoen"));

                list.add(etapa);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en  EtapaDao getAllUsuarios -->" + e.getMessage());
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
    public Etapa getEtapaById(int id) {

        Etapa etapa = new Etapa();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String SQL = "select * from TB_Etapa where ID_Etapa = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                etapa.setId_etapa(rs.getInt("ID_Etapa"));
                etapa.setDescripcion(rs.getString("Descripcion"));
                etapa.setEstado(rs.getInt("Estado"));

            }
            

            return etapa;
        } catch (SQLException e) {
            LOGGER.error("Error en  EtapaDao getEtapaById -->" + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
               ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param idEtapa
     * @return
     */
    @Override
    public boolean isInServ(int idEtapa) {

        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "select s.* from tb_etapa s \n"
                    + "inner join tb_servicioetapa ps on s.id_etapa = ps.id_etapa \n"
                    + "where ps.ESTADO = 1 and s.id_etapa = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idEtapa);
            rs = ps.executeQuery();

            result = rs.next();

            

            return result;
        } catch (SQLException e) {
            LOGGER.error("Error en  EtapaDao isInServ -->" + e.getMessage());

            return result;
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
    public ArrayList<Etapa> getAllEtapaByServicio(int idServicio) {

        ArrayList<Etapa> list = new ArrayList<>(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "select s.* from tb_etapa s \n"
                    + "inner join TB_SERVICIOetapa ps on s.id_etapa = ps.id_etapa \n"
                    + "where ps.ESTADO=1 and  ps.id_servicio= ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idServicio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Etapa etapa = new Etapa();
                etapa.setId_etapa(rs.getInt("id_etapa"));
                etapa.setDescripcion(rs.getString("Descripcion"));
                etapa.setEstado(rs.getInt("Estado"));
                etapa.setCreadopor(rs.getString("CreadoPor"));
                etapa.setModificadopor(rs.getString("ModificadoPor"));
                etapa.setCreadoen(rs.getDate("CreadoEn"));
                etapa.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(etapa);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en  EtapaDao getAllEtapaByServicio -->" + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
              ConexionBD.cerrarConexiones(rs, ps);
        }

    }
    
   

}
