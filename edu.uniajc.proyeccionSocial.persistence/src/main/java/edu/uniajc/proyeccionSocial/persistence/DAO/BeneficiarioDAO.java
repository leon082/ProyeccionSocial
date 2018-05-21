/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Beneficiario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IBeneficiarioDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class BeneficiarioDAO implements IBeneficiarioDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(BeneficiarioDAO.class.getName());

    public BeneficiarioDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param beneficiario
     * @return
     */
    @Override
    public int createBeneficiario(Beneficiario beneficiario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            beneficiario.setCreadoen(fechaSQL);
            beneficiario.setEstado(1);

            String SQL = "select SQ_TB_Beneficiario.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                beneficiario.setId_beneficiario(codigo);
            }

            SQL = "INSERT INTO TB_Beneficiario"
                    + " (ID_Beneficiario, ID_Proyecto, ID_Tercero, Estado, "
                    + "Observacion, CreadoPor, CreadoEn) values(?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, beneficiario.getId_beneficiario());
            ps.setInt(2, beneficiario.getId_proyecto());
            ps.setInt(3, beneficiario.getId_tercero());
            ps.setInt(4, beneficiario.getEstado());
            ps.setString(5, beneficiario.getObservacion());
            ps.setString(6, beneficiario.getCreadopor());
            ps.setDate(7, beneficiario.getCreadoen());
            ps.execute();

            return codigo;
        } catch (SQLException e) {

            LOGGER.error("Error en BeneficiarioDAO Insert -->" + e.getMessage());
            return 0;
        } finally {// Cerramos las conexiones, en orden inverso a su apertura
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
    public boolean deleteBeneficiario(int id) {
        PreparedStatement ps = null;

        try {

            String SQL = "UPDATE TB_Beneficiario SET Estado=0 WHERE ID_Beneficiario = ?";

            ps = connection.prepareStatement(SQL);
            ps.setInt(1,id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {

            LOGGER.error("Error en BeneficiarioDAO Delete -->" + e.getMessage());

            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param beneficiario
     * @return
     */
    @Override
    public boolean updateBeneficiario(Beneficiario beneficiario) {
        PreparedStatement ps = null;

        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            beneficiario.setModificadoen(fechaSQL);

            String SQL = "UPDATE TB_Beneficiario SET "
                    + "ID_Proyecto=?, ID_Tercero=?, Estado=?, "
                    + "Observacion=?, ModificadoPor=?, ModificadoEn=? "
                    + "where ID_Beneficiario = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, beneficiario.getId_proyecto());
            ps.setInt(2, beneficiario.getId_tercero());
            ps.setInt(3, beneficiario.getEstado());
            ps.setString(4, beneficiario.getObservacion());
            ps.setString(5, beneficiario.getModificadopor());
            ps.setDate(6, beneficiario.getModificadoen());
            ps.setInt(7, beneficiario.getId_beneficiario());

            ps.execute();
            
            return true;

        } catch (SQLException e) {

            LOGGER.error("Error en BeneficiarioDAO Update -->" + e.getMessage());

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
    public ArrayList<Beneficiario> getAllBeneficiario() {
        ArrayList<Beneficiario> list = new ArrayList<>(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "SELECT * from TB_Beneficiario where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Beneficiario beneficiario = new Beneficiario();
                beneficiario.setId_beneficiario(rs.getInt("ID_Beneficiario"));
                beneficiario.setId_proyecto(rs.getInt("ID_Proyecto"));
                beneficiario.setId_tercero(rs.getInt("ID_Tercero"));
                beneficiario.setEstado(rs.getInt("Estado"));
                beneficiario.setObservacion(rs.getString("Observacion"));
                beneficiario.setCreadopor(rs.getString("CreadoPor"));
                beneficiario.setModificadopor(rs.getString("ModificadoPor"));
                beneficiario.setCreadoen(rs.getDate("CreadoEn"));
                beneficiario.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(beneficiario);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en BeneficiarioDAO getAllBeneficiario -->" + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param idProyect
     * @return
     */
    @Override
    public ArrayList<Beneficiario> getAllBeneficiarioByProyect(int idProyect) {
        ArrayList<Beneficiario> list = new ArrayList<>(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "SELECT * from TB_Beneficiario where estado = 1 and id_proyecto = ?";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idProyect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Beneficiario beneficiario = new Beneficiario();
                beneficiario.setId_beneficiario(rs.getInt("ID_Beneficiario"));
                beneficiario.setId_proyecto(rs.getInt("ID_Proyecto"));
                beneficiario.setId_tercero(rs.getInt("ID_Tercero"));
                beneficiario.setEstado(rs.getInt("Estado"));
                beneficiario.setObservacion(rs.getString("Observacion"));
                beneficiario.setCreadopor(rs.getString("CreadoPor"));
                beneficiario.setModificadopor(rs.getString("ModificadoPor"));
                beneficiario.setCreadoen(rs.getDate("CreadoEn"));
                beneficiario.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(beneficiario);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en BeneficiarioDAO getAllBeneficiario -->" + e.getMessage());
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
    public Beneficiario getBeneficiarioById(int id) {

        Beneficiario beneficiario = new Beneficiario();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String SQL = "select * from TB_Beneficiario where ID_Beneficiario = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                beneficiario.setId_beneficiario(rs.getInt("ID_Beneficiario"));
                beneficiario.setId_proyecto(rs.getInt("ID_Proyecto"));
                beneficiario.setId_tercero(rs.getInt("ID_Tercero"));
                beneficiario.setEstado(rs.getInt("Estado"));
                beneficiario.setObservacion(rs.getString("Observacion"));
                beneficiario.setCreadopor(rs.getString("CreadoPor"));
                beneficiario.setModificadopor(rs.getString("ModificadoPor"));
                beneficiario.setCreadoen(rs.getDate("CreadoEn"));
                beneficiario.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return beneficiario;
        } catch (SQLException e) {
            LOGGER.error("Error en BeneficiarioDAO getBeneficiarioById -->" + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
