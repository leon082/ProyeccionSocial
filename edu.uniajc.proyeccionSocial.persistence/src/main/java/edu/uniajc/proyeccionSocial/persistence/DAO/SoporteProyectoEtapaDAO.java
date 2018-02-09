/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.persistence.Model.SoporteProyectoEtapa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.uniajc.proyeccionSocial.persistence.interfaces.ISoporteProyectoEtapaDao;

/**
 *
 * @author rlara
 */
public class SoporteProyectoEtapaDAO implements ISoporteProyectoEtapaDao {

    /**
     *
     * @param soporteProyectoEtapa
     * @return
     */
    @Override
    public int createSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            soporteProyectoEtapa.setCreadoen(fechaSQL);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_SoporteProyectoEtapa.nextval ID from dual";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                soporteProyectoEtapa.setId_soporteproyectoetapa(codigo);
            }

            SQL = "INSERT INTO TB_SoporteProyectoEtapa "
                    + "(ID_SoporteProyectoEtapa, id_proyectoetapa, archivo, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?) ";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);

            ps.setInt(1, soporteProyectoEtapa.getId_soporteproyectoetapa());
            ps.setInt(2, soporteProyectoEtapa.getId_proyectoetapa());

            ps.setString(3, soporteProyectoEtapa.getArchivo());
            ps.setString(4, soporteProyectoEtapa.getCreadopor());
            ps.setDate(5, soporteProyectoEtapa.getCreadoen());
            ps.execute();

            ps.close();

            System.out.println("Codigo de SoporteProyectoEtapa" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en SoporteProyectoEtapaDAO insert -->" + e.getMessage());
            Logger.getLogger(SoporteProyectoEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    /**
     *
     * @param soporteProyectoEtapa
     * @return
     */
    @Override
    public boolean updateSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            soporteProyectoEtapa.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_SoporteProyectoEtapa SET "
                    + "id_proyectoetapa=?,archivo=?, "
                    + "ModificadoPor=?, ModificadoEn=? where ID_SoporteProyectoEtapa = ?";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);

            ps.setInt(1, soporteProyectoEtapa.getId_proyectoetapa());

            ps.setString(2, soporteProyectoEtapa.getArchivo());
            ps.setString(3, soporteProyectoEtapa.getModificadopor());
            ps.setDate(4, soporteProyectoEtapa.getModificadoen());
            ps.setInt(5, soporteProyectoEtapa.getId_soporteproyectoetapa());
            ps.execute();
            ps.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error en SoporteProyectoEtapaDAO UPDATE " + e.getMessage());
            Logger.getLogger(SoporteProyectoEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<SoporteProyectoEtapa> getAllSoporteProyectoEtapa() {
        ArrayList<SoporteProyectoEtapa> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_SoporteProyectoEtapa";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
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
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en SoporteProyectoEtapaDAO getAllSoporteProyectoEtapa " + e.getMessage());
            Logger.getLogger(SoporteProyectoEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public SoporteProyectoEtapa getSoporteProyectoEtapaById(int id) {

        SoporteProyectoEtapa soporteProyectoEtapa= new SoporteProyectoEtapa();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_SoporteProyectoEtapa where ID_SoporteProyectoEtapa =" + id + " ";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                

                soporteProyectoEtapa.setId_soporteproyectoetapa(rs.getInt("ID_SoporteProyectoEtapa"));
                soporteProyectoEtapa.setId_proyectoetapa(rs.getInt("ID_ProyectoEtapa"));
                soporteProyectoEtapa.setArchivo(rs.getString("Archivo"));
                soporteProyectoEtapa.setCreadopor(rs.getString("CreadoPor"));
                soporteProyectoEtapa.setModificadopor(rs.getString("ModificadoPor"));
                soporteProyectoEtapa.setCreadoen(rs.getDate("CreadoEn"));
                soporteProyectoEtapa.setModificadoen(rs.getDate("ModificadoEn"));

            }
            ps.close();

            return soporteProyectoEtapa;
        } catch (SQLException e) {
            System.out.println("Error en SoporteProyectoEtapaDAO getSoporteProyectoEtapasById " + e.getMessage());
            Logger.getLogger(SoporteProyectoEtapaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

  
}
