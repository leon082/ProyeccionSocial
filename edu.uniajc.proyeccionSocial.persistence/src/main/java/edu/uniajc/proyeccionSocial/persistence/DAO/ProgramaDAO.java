/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Programa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IProgramaDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class ProgramaDAO implements IProgramaDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(ProgramaDAO.class.getName());

    public ProgramaDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param programa
     * @return
     */
    @Override
    public int createPrograma(Programa programa) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            programa.setCreadoen(fechaSQL);
            programa.setEstado(1);
            

            String SQL = "select SQ_TB_Programa.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
             rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                programa.setId_programa(codigo);
            }

            SQL = "INSERT INTO TB_Programa "
                    + "(ID_Programa, Descripcion, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?) ";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, programa.getId_programa());
            ps.setString(2, programa.getDescripcion());
            ps.setInt(3, programa.getEstado());
            ps.setString(4, programa.getCreadopor());
            ps.setDate(5, programa.getCreadoen());
            ps.execute();

            

            System.out.println("Codigo de Programa" + codigo);

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en ProgramaDAO insert -->" + e.getMessage());
            
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
    public boolean deletePrograma(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "UPDATE TB_Programa SET Estado=0 WHERE ID_Programa = ? ";

             ps = connection.prepareStatement(SQL);
             ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
             LOGGER.error("Error en ProgramaDAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param programa
     * @return
     */
    @Override
    public boolean updatePrograma(Programa programa) {
        PreparedStatement ps =null;
         
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            programa.setModificadoen(fechaSQL);

            
            String SQL = "UPDATE TB_Programa SET "
                    + "Descripcion=?, Estado=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Programa = ?";
            ps = connection.prepareStatement(SQL);

            ps.setString(1, programa.getDescripcion());
            ps.setInt(2, programa.getEstado());
            ps.setString(3, programa.getModificadopor());
            ps.setDate(4, programa.getModificadoen());
            ps.setInt(5, programa.getId_programa());
            ps.execute();
            

            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en ProgramaDAO UPDATE " + e.getMessage());
            
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
    public ArrayList<Programa> getAllPrograma() {
        PreparedStatement ps =null;
         ResultSet rs = null;
        ArrayList<Programa> list = new ArrayList<>(0);
        try {

            

            final String SQL = "SELECT * from TB_Programa where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Programa programa = new Programa();
                programa.setId_programa(rs.getInt("ID_Programa"));
                programa.setDescripcion(rs.getString("Descripcion"));
                programa.setEstado(rs.getInt("Estado"));
                programa.setCreadopor(rs.getString("CreadoPor"));
                programa.setModificadopor(rs.getString("ModificadoPor"));
                programa.setCreadoen(rs.getDate("CreadoEn"));
                programa.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(programa);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en ProgramaDAO getAllPrograma " + e.getMessage());
            
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
    public Programa getProgramaById(int id) {

        Programa programa = new Programa();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Programa where ID_Programa = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {

                programa.setId_programa(rs.getInt("ID_Programa"));
                programa.setDescripcion(rs.getString("Descripcion"));
                programa.setEstado(rs.getInt("Estado"));
                programa.setCreadopor(rs.getString("CreadoPor"));
                programa.setModificadopor(rs.getString("ModificadoPor"));
                programa.setCreadoen(rs.getDate("CreadoEn"));
                programa.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return programa;
        } catch (SQLException e) {
             LOGGER.error("Error enProgramaDAO getProgramasById " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }
    
     /**
     *
     * @param idPrograma
     * @return
     */
    @Override
    public boolean isInProy(int idPrograma) {

        boolean result = false;
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "select * from TB_PROYECTO where ID_PROGRAMA = ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idPrograma);
            rs = ps.executeQuery();

            result = rs.next();

            

            return result;
        } catch (SQLException e) {
             LOGGER.error("Error en ProgramaDAO isInProy " + e.getMessage());
            
            return result;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
