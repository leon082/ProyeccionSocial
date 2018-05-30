/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.ServicioEtapa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IServicioEtapaDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class ServicioEtapaDAO implements IServicioEtapaDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(BeneficiarioDAO.class.getName());

    public ServicioEtapaDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param servicioEtapa
     * @return
     */
    @Override
    public int createServicioEtapa(ServicioEtapa servicioEtapa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            servicioEtapa.setCreadoen(fechaSQL);
            servicioEtapa.setEstado(1);

            String SQL = "select SQ_TB_ServicioEtapa.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                servicioEtapa.setId_servicioetapa(codigo);
            }

            SQL = "INSERT INTO TB_ServicioEtapa"
                    + " (ID_ServicioEtapa, ID_Servicio, id_ETAPA , "
                    + "Estado,CreadoPor, CreadoEn) values(?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, servicioEtapa.getId_servicioetapa());
            ps.setInt(2, servicioEtapa.getId_servicio());
            ps.setInt(3, servicioEtapa.getId_etapa());
            ps.setInt(4, servicioEtapa.getEstado());
            ps.setString(5, servicioEtapa.getCreadopor());
            ps.setDate(6, servicioEtapa.getCreadoen());
            ps.execute();

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en ServicioEtapaDAO Insert -->" + e.getMessage());

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
    public boolean deleteServicioEtapa(int id) {
        PreparedStatement ps = null;
        
        try {

            String SQL = "UPDATE TB_ServicioEtapa SET Estado=0 WHERE "
                    + "ID_ServicioEtapa = ? ";

            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ServicioEtapa DAO Delete " + e.getMessage());

            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
           
              ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteEtapaServicioByServicio(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "DELETE FROM tb_servicioetapa WHERE "
                    + "id_servicio = ? ";

             ps = connection.prepareStatement(SQL);
             ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en deleteEtapaServicioByEtapa DAO Delete " + e.getMessage());

            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
              ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param servicioEtapa
     * @return
     */
    @Override
    public boolean updateServicioEtapa(ServicioEtapa servicioEtapa) {
        PreparedStatement ps =null;
         
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            servicioEtapa.setModificadoen(fechaSQL);

            
            String SQL = "UPDATE TB_ServicioEtapa SET "
                    + "ID_Servicio=?, ID_Etapa=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_ServicioEtapa = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, servicioEtapa.getId_servicio());
            ps.setInt(2, servicioEtapa.getId_servicio());
            ps.setInt(3, servicioEtapa.getEstado());
            ps.setString(4, servicioEtapa.getModificadopor());
            ps.setDate(5, servicioEtapa.getModificadoen());
            ps.setInt(6, servicioEtapa.getId_servicioetapa());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ServicioEtapa DAO UPDATE " + e.getMessage());

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
    public ArrayList<ServicioEtapa> getAllServicioEtapa() {
        ArrayList<ServicioEtapa> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_ServicioEtapa where estado = 1";
            ps = connection.prepareStatement(SQL);
             rs = ps.executeQuery();
            while (rs.next()) {
                ServicioEtapa servicioEtapa = new ServicioEtapa();
                servicioEtapa.setId_servicioetapa(rs.getInt("ID_ServicioEtapa"));
                servicioEtapa.setId_servicio(rs.getInt("ID_Servicio"));
                servicioEtapa.setId_etapa(rs.getInt("ID_Etapa"));
                servicioEtapa.setEstado(rs.getInt("Estado"));
                servicioEtapa.setCreadopor(rs.getString("CREADOPOR"));
                servicioEtapa.setModificadopor(rs.getString("MODIFICADOPOR"));
                servicioEtapa.setCreadoen(rs.getDate("CREADOEN"));
                servicioEtapa.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(servicioEtapa);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en ServicioEtapa DAO getAllServicioEtapaByPrograma " + e.getMessage());

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
    public ServicioEtapa getServicioEtapaById(int id) {

        ServicioEtapa servicioEtapa = new ServicioEtapa();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_ServicioEtapa where ID_ServicioEtapa = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                servicioEtapa.setId_servicioetapa(rs.getInt("ID_ServicioEtapa"));
                servicioEtapa.setId_servicio(rs.getInt("ID_Servicio"));
                servicioEtapa.setId_etapa(rs.getInt("ID_Etapa"));
                servicioEtapa.setEstado(rs.getInt("Estado"));
                servicioEtapa.setCreadopor(rs.getString("CREADOPOR"));
                servicioEtapa.setModificadopor(rs.getString("MODIFICADOPOR"));
                servicioEtapa.setCreadoen(rs.getDate("CREADOEN"));
                servicioEtapa.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            

            return servicioEtapa;
        } catch (SQLException e) {
            LOGGER.error("Error en ServicioEtapa DAO getServicioEtapaById " + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
              ConexionBD.cerrarConexiones(rs, ps);
        }

    }

}
