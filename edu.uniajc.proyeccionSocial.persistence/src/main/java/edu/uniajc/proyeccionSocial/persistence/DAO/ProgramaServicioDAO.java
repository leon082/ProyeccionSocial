/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.ProgramaServicio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IProgramaServicioDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class ProgramaServicioDAO implements IProgramaServicioDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(ProgramaServicioDAO.class.getName());

    public ProgramaServicioDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param progServi
     * @return
     */
    @Override
    public int createProgramaServicio(ProgramaServicio progServi) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            progServi.setCreadoen(fechaSQL);
            progServi.setEstado(1);
            

            String SQL = "select SQ_TB_ProgramaServicio.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                progServi.setId_programaservicio(codigo);
            }

            SQL = "INSERT INTO TB_ProgramaServicio"
                    + " (ID_ProgramaServicio, ID_Programa, ID_Servicio, "
                    + "Estado,CreadoPor, CreadoEn) values(?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, progServi.getId_programaservicio());
            ps.setInt(2, progServi.getId_programa());
            ps.setInt(3, progServi.getId_servicio());
            ps.setInt(4, progServi.getEstado());
            ps.setString(5, progServi.getCreadopor());
            ps.setDate(6, progServi.getCreadoen());
            ps.execute();

            

            return codigo;
        } catch (SQLException e) {
           LOGGER.error("Error en ProgramaServicioDAO Insert -->" + e.getMessage());
            
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
    public boolean deleteProgramaServicio(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "UPDATE TB_ProgramaServicio SET Estado=0 WHERE "
                    + "ID_ProgramaServicio =" + id + " ";

             ps = connection.prepareStatement(SQL);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
             LOGGER.error("Error en ProgramaServicio DAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
           
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteProgramaServicioByProg(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "DELETE FROM tb_programaservicio WHERE "
                    + "ID_Programa =" + id + " ";

             ps = connection.prepareStatement(SQL);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
             LOGGER.error("Error en ProgramaServicio DAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param progServi
     * @return
     */
    @Override
    public boolean updateProgramaServicio(ProgramaServicio progServi) {
        PreparedStatement ps =null;
         
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            progServi.setModificadoen(fechaSQL);

            
            String SQL = "UPDATE TB_ProgramaServicio SET "
                    + "ID_Programa=?, ID_Servicio=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_ProgramaServicio = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, progServi.getId_programa());
            ps.setInt(2, progServi.getId_servicio());
            ps.setInt(3, progServi.getEstado());
            ps.setString(4, progServi.getModificadopor());
            ps.setDate(5, progServi.getModificadoen());
            ps.setInt(6, progServi.getId_programaservicio());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ProgramaServicio DAO UPDATE " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
           
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param idPrograma
     * @return
     */
    @Override
    public ArrayList<ProgramaServicio> getAllProgramaServicioByPrograma(int idPrograma) {
        ArrayList<ProgramaServicio> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_ProgramaServicio where ID_Programa = " + idPrograma + " and estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProgramaServicio progServi = new ProgramaServicio();
                progServi.setId_programaservicio(rs.getInt("ID_ProgramaServicio"));
                progServi.setId_programa(rs.getInt("ID_Programa"));
                progServi.setId_servicio(rs.getInt("ID_Servicio"));
                progServi.setEstado(rs.getInt("Estado"));
                progServi.setCreadopor(rs.getString("CREADOPOR"));
                progServi.setModificadopor(rs.getString("MODIFICADOPOR"));
                progServi.setCreadoen(rs.getDate("CREADOEN"));
                progServi.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(progServi);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en ProgramaServicio DAO getAllProgramaServicioByPrograma " + e.getMessage());
            
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
    public ProgramaServicio getProgramaServicioById(int id) {

        ProgramaServicio progServi = new ProgramaServicio();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_ProgramaServicio where ID_ProgramaServicio =" + id + " and estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {

                progServi.setId_programaservicio(rs.getInt("ID_ProgramaServicio"));
                progServi.setId_programa(rs.getInt("ID_Programa"));
                progServi.setId_servicio(rs.getInt("ID_Servicio"));
                progServi.setEstado(rs.getInt("Estado"));
                progServi.setCreadopor(rs.getString("CREADOPOR"));
                progServi.setModificadopor(rs.getString("MODIFICADOPOR"));
                progServi.setCreadoen(rs.getDate("CREADOEN"));
                progServi.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            

            return progServi;
        } catch (SQLException e) {
             LOGGER.error("Error ProgramaServicioDAO getProgramaServicioById "+e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
