/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Oferente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IOferenteDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class OferenteDao implements IOferenteDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(OferenteDao.class.getName());

    public OferenteDao(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param oferente
     * @return
     */
    @Override
    public int createOferente(Oferente oferente) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            oferente.setCreadoen(fechaSQL);
            oferente.setEstado(1);

            String SQL = "select SQ_TB_Oferente.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                oferente.setId_oferente(codigo);
            }

            SQL = "INSERT INTO TB_Oferente"
                    + " (ID_Oferente,ID_Proyecto,ID_Tercero, Estado,Observacion,CreadoPor, CreadoEn) "
                    + "values(?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, oferente.getId_oferente());
            ps.setInt(2, oferente.getId_proyecto());
            ps.setInt(3, oferente.getId_tercero());
            ps.setInt(4, oferente.getEstado());
            ps.setString(5, oferente.getObservacion());
            ps.setString(6, oferente.getCreadopor());
            ps.setDate(7, oferente.getCreadoen());
            ps.execute();

            

            return codigo;
        } catch (SQLException e) {

            LOGGER.error("Error en OferenteDAO Insert -->" + e.getMessage());

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
    public boolean deleteOferente(int id) {
        PreparedStatement ps = null;

        try {

            String SQL = "UPDATE TB_Oferente SET Estado=0 WHERE ID_Oferente = ? ";

            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en Oferente DAO Delete " + e.getMessage());

            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param oferente
     * @return
     */
    @Override
    public boolean updateOferente(Oferente oferente) {
        PreparedStatement ps = null;

        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            oferente.setModificadoen(fechaSQL);

            String SQL = "UPDATE TB_Oferente SET "
                    + "ID_Proyecto=?,ID_Tercero=?, Estado=?, Observacion=?,ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Oferente = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, oferente.getId_proyecto());
            ps.setInt(2, oferente.getId_tercero());
            ps.setInt(3, oferente.getEstado());
            ps.setString(4, oferente.getObservacion());
            ps.setString(5, oferente.getModificadopor());
            ps.setDate(6, oferente.getModificadoen());
            ps.setInt(7, oferente.getId_oferente());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en Oferente DAO UPDATE " + e.getMessage());

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
    public ArrayList<Oferente> getAllOferente() {
        ArrayList<Oferente> list = new ArrayList<>(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "SELECT * from TB_Oferente where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Oferente oferente = new Oferente();
                oferente.setId_oferente(rs.getInt("ID_Oferente"));
                oferente.setId_proyecto(rs.getInt("ID_Proyecto"));
                oferente.setId_tercero(rs.getInt("ID_Tercero"));
                oferente.setEstado(rs.getInt("Estado"));
                oferente.setObservacion(rs.getString("Observacion"));
                oferente.setCreadopor(rs.getString("CREADOPOR"));
                oferente.setModificadopor(rs.getString("MODIFICADOPOR"));
                oferente.setCreadoen(rs.getDate("CREADOEN"));
                oferente.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(oferente);
            }
           

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en Oferentes DAO getAllOferentes " + e.getMessage());

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
    public Oferente getOferenteById(int id) {

        Oferente oferente = new Oferente();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String SQL = "select * from TB_Oferente where ID_Oferente = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                oferente.setId_oferente(rs.getInt("ID_Oferente"));
                oferente.setId_proyecto(rs.getInt("ID_Proyecto"));
                oferente.setId_tercero(rs.getInt("ID_Tercero"));
                oferente.setEstado(rs.getInt("Estado"));
                oferente.setObservacion(rs.getString("Observacion"));
                oferente.setCreadopor(rs.getString("CREADOPOR"));
                oferente.setModificadopor(rs.getString("MODIFICADOPOR"));
                oferente.setCreadoen(rs.getDate("CREADOEN"));
                oferente.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            

            return oferente;
        } catch (SQLException e) {
            LOGGER.error("Error getOferenteById " + e.getMessage());
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
    public Oferente getOferenteByProyecto(int id) {

        Oferente oferente = new Oferente();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String SQL = "select * from TB_Oferente where ID_Proyecto = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                oferente.setId_oferente(rs.getInt("ID_Oferente"));
                oferente.setId_proyecto(rs.getInt("ID_Proyecto"));
                oferente.setId_tercero(rs.getInt("ID_Tercero"));
                oferente.setEstado(rs.getInt("Estado"));
                oferente.setObservacion(rs.getString("Observacion"));
                oferente.setCreadopor(rs.getString("CREADOPOR"));
                oferente.setModificadopor(rs.getString("MODIFICADOPOR"));
                oferente.setCreadoen(rs.getDate("CREADOEN"));
                oferente.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            

            return oferente;
        } catch (SQLException e) {
            LOGGER.error("Error OferenteDAO getOferenteByProyecto -> " + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
