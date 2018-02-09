/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.uniajc.proyeccionSocial.persistence.interfaces.IProyectoDao;
import java.sql.Connection;

/**
 *
 * @author luis.leon
 */
public class ProyectoDao implements IProyectoDao {

    Connection connection;

    public ProyectoDao(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @param proyecto
     * @return
     */
    @Override
    public int createProyecto(Proyecto proyecto) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            proyecto.setCreadoen(fechaSQL);
            proyecto.setEstado(0);
            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Proyecto.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                proyecto.setId_proyecto(codigo);
            }

            SQL = "INSERT INTO TB_PROYECTO"
                    + " (ID_Proyecto, TituloProyecto, ResumenProyecto, ID_Programa, id_servicio,"
                    + " Estado, CreadoPor, CreadoEn , facultad) values(?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, proyecto.getId_proyecto());
            ps.setString(2, proyecto.getTituloproyecto());
            ps.setString(3, proyecto.getResumenproyecto());
            ps.setInt(4, proyecto.getId_programa());
            ps.setInt(5, proyecto.getId_servicio());
            ps.setInt(6, proyecto.getEstado());
            ps.setString(7, proyecto.getCreadopor());
            ps.setDate(8, proyecto.getCreadoen());
            ps.setInt(9, proyecto.getFacultad());
            ps.execute();

            ps.close();

            System.out.println("Codigo de Proyecto" + codigo);

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en Proyecto DAO" + e.getMessage());
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteProyecto(int id) {
        try {

            String SQL = "UPDATE TB_Proyecto SET Estado=0 WHERE ID_Proyecto =" + id + " ";

            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Proyecto DAO Delete " + e.getMessage());
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    /**
     *
     * @param proyecto
     * @return
     */
    @Override
    public boolean updateProyecto(Proyecto proyecto) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            proyecto.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Proyecto SET "
                    + " TituloProyecto=?, ResumenProyecto=?, ID_Programa=?, id_servicio=?, "
                    + " Estado=?, ModificadoPor=?, ModificadoEn=? , facultad=?"
                    + " where ID_Proyecto = ?";
            ps = connection.prepareStatement(SQL);

            ps.setString(1, proyecto.getTituloproyecto());
            ps.setString(2, proyecto.getResumenproyecto());
            ps.setInt(3, proyecto.getId_programa());
            ps.setInt(4, proyecto.getId_servicio());
            ps.setInt(5, proyecto.getEstado());
            ps.setString(6, proyecto.getModificadopor());
            ps.setDate(7, proyecto.getModificadoen());
            ps.setInt(8, proyecto.getFacultad());
            ps.setInt(9, proyecto.getId_proyecto());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Proyecto DAO UPDATE " + e.getMessage());
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Proyecto> getAllProyectoPendiente() {
        ArrayList<Proyecto> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_PROYECTO where estado = 0";
            ps = connection.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyecto.setTituloproyecto(rs.getString("TituloProyecto"));
                proyecto.setResumenproyecto(rs.getString("ResumenProyecto"));
                proyecto.setId_programa(rs.getInt("ID_Programa"));
                proyecto.setId_servicio(rs.getInt("ID_Servicio"));
                proyecto.setEstado(rs.getInt("Estado"));
                proyecto.setFacultad(rs.getInt("Facultad"));
                proyecto.setCreadopor(rs.getString("CREADOPOR"));
                proyecto.setModificadopor(rs.getString("MODIFICADOPOR"));
                proyecto.setCreadoen(rs.getDate("CREADOEN"));
                proyecto.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(proyecto);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Proyecto> getAllProyectoAprobado() {
        ArrayList<Proyecto> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_PROYECTO where estado = 1";
            ps = connection.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyecto.setTituloproyecto(rs.getString("TituloProyecto"));
                proyecto.setResumenproyecto(rs.getString("ResumenProyecto"));
                proyecto.setId_programa(rs.getInt("ID_Programa"));
                proyecto.setId_servicio(rs.getInt("ID_Servicio"));
                proyecto.setEstado(rs.getInt("Estado"));
                proyecto.setFacultad(rs.getInt("Facultad"));
                proyecto.setCreadopor(rs.getString("CREADOPOR"));
                proyecto.setModificadopor(rs.getString("MODIFICADOPOR"));
                proyecto.setCreadoen(rs.getDate("CREADOEN"));
                proyecto.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(proyecto);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Proyecto> getAllProyectoFinalizado() {
        ArrayList<Proyecto> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_PROYECTO where estado = 3";
            ps = connection.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyecto.setTituloproyecto(rs.getString("TituloProyecto"));
                proyecto.setResumenproyecto(rs.getString("ResumenProyecto"));
                proyecto.setId_programa(rs.getInt("ID_Programa"));
                proyecto.setId_servicio(rs.getInt("ID_Servicio"));
                proyecto.setEstado(rs.getInt("Estado"));
                proyecto.setFacultad(rs.getInt("Facultad"));
                proyecto.setCreadopor(rs.getString("CREADOPOR"));
                proyecto.setModificadopor(rs.getString("MODIFICADOPOR"));
                proyecto.setCreadoen(rs.getDate("CREADOEN"));
                proyecto.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(proyecto);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Proyecto> getAllProyectoCancelado() {
        ArrayList<Proyecto> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_PROYECTO where estado = 4";
            ps = connection.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyecto.setTituloproyecto(rs.getString("TituloProyecto"));
                proyecto.setResumenproyecto(rs.getString("ResumenProyecto"));
                proyecto.setId_programa(rs.getInt("ID_Programa"));
                proyecto.setId_servicio(rs.getInt("ID_Servicio"));
                proyecto.setEstado(rs.getInt("Estado"));
                proyecto.setFacultad(rs.getInt("Facultad"));
                proyecto.setCreadopor(rs.getString("CREADOPOR"));
                proyecto.setModificadopor(rs.getString("MODIFICADOPOR"));
                proyecto.setCreadoen(rs.getDate("CREADOEN"));
                proyecto.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(proyecto);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    /**
     *
     * @param usuario
     * @return
     */
    @Override
    public boolean tieneProyectoPendiente(String usuario) {
        ArrayList<Proyecto> list = new ArrayList<>(0);
        boolean result = false;
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_PROYECTO where creadopor = ? and (estado = 0 or estado = 1)";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            result = rs.next();

            ps.close();

            return result;
        } catch (SQLException e) {
            Logger.getLogger(ProyectoDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return result;
        }

    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public Proyecto getProyectoByUser(String user) {

        Proyecto proyecto = null;
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Proyecto where creadopor = '" + user + "' and estado = 1";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proyecto = new Proyecto();

                proyecto.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyecto.setTituloproyecto(rs.getString("TituloProyecto"));
                proyecto.setResumenproyecto(rs.getString("ResumenProyecto"));
                proyecto.setId_programa(rs.getInt("ID_Programa"));
                proyecto.setId_servicio(rs.getInt("ID_Servicio"));
                proyecto.setEstado(rs.getInt("Estado"));
                proyecto.setFacultad(rs.getInt("Facultad"));
                proyecto.setCreadopor(rs.getString("CREADOPOR"));
                proyecto.setModificadopor(rs.getString("MODIFICADOPOR"));
                proyecto.setCreadoen(rs.getDate("CREADOEN"));
                proyecto.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            ps.close();

            return proyecto;
        } catch (SQLException e) {
            System.out.println("Error proyectoDao getProyectoByUser " + e.getMessage());
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
    public Proyecto getProyectoById(int id) {

        Proyecto proyecto = new Proyecto();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Proyecto where id_proyecto = " + id + " and estado = 1";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                proyecto.setId_proyecto(rs.getInt("ID_Proyecto"));
                proyecto.setTituloproyecto(rs.getString("TituloProyecto"));
                proyecto.setResumenproyecto(rs.getString("ResumenProyecto"));
                proyecto.setId_programa(rs.getInt("ID_Programa"));
                proyecto.setId_servicio(rs.getInt("ID_Servicio"));
                proyecto.setEstado(rs.getInt("Estado"));
                proyecto.setFacultad(rs.getInt("Facultad"));
                proyecto.setCreadopor(rs.getString("CREADOPOR"));
                proyecto.setModificadopor(rs.getString("MODIFICADOPOR"));
                proyecto.setCreadoen(rs.getDate("CREADOEN"));
                proyecto.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            ps.close();

            return proyecto;
        } catch (SQLException e) {
            System.out.println("Error proyectoDao getProyectoByUser " + e.getMessage());
            Logger.getLogger(ProgramaDAO.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

}
