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
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IProgramaDao;
import java.sql.Connection;

/**
 *
 * @author rlara
 */
public class ProgramaDAO implements IProgramaDao {

    Connection connection;

    public ProgramaDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @param programa
     * @return
     */
    @Override
    public int createPrograma(Programa programa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            programa.setCreadoen(fechaSQL);
            programa.setEstado(1);
            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Programa.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
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

            ps.close();

            System.out.println("Codigo de Programa" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO insert -->" + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean deletePrograma(int id) {
        try {

            String SQL = "UPDATE TB_Programa SET Estado=0 WHERE ID_Programa =" + id + " ";

            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO Delete " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    /**
     *
     * @param programa
     * @return
     */
    @Override
    public boolean updatePrograma(Programa programa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            programa.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
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
            ps.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO UPDATE " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Programa> getAllPrograma() {
        ArrayList<Programa> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Programa where estado = 1";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
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
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO getAllPrograma " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
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
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Programa where ID_Programa =" + id + " and estado = 1";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                programa.setId_programa(rs.getInt("ID_Programa"));
                programa.setDescripcion(rs.getString("Descripcion"));
                programa.setEstado(rs.getInt("Estado"));
                programa.setCreadopor(rs.getString("CreadoPor"));
                programa.setModificadopor(rs.getString("ModificadoPor"));
                programa.setCreadoen(rs.getDate("CreadoEn"));
                programa.setModificadoen(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return programa;
        } catch (SQLException e) {
            System.out.println("Error enProgramaDAO getProgramasById " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
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
        try {

            PreparedStatement ps = null;

            final String SQL = "select * from TB_PROYECTO where ID_PROGRAMA = " +idPrograma;
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            result = rs.next();

            ps.close();

            return result;
        } catch (SQLException e) {
            System.out.println("Error en ProgramaDAO isInProy " + e.getMessage());
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return result;
        }

    }

}
