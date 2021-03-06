/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.ITerceroDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class TerceroDAO implements ITerceroDao {

    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(TerceroDAO.class.getName());

    public TerceroDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param tercero
     * @return
     */
    @Override
    public int createTercero(Tercero tercero) {
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            tercero.setCreadoen(fechaSQL);
            tercero.setEstado(1);

            

            String SQL = "select SQ_TB_Tercero.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                tercero.setId_tercero(codigo);
            }

            SQL = "INSERT INTO TB_Tercero "
                    + "(ID_Tercero, ID_LV_TipoIdentificacion, NumIdentificacion, "
                    + "PrimerNombre, SegundoNombre, PrimerApellido, SegundoApellido, "
                    + "FechaNacimiento, TelefonoFijo, TelefonoCelular, Correo, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, tercero.getId_tercero());
            ps.setInt(2, tercero.getId_lv_tipoidentificacion());
            ps.setString(3, tercero.getNumidentificacion());
            ps.setString(4, tercero.getPrimernombre());
            ps.setString(5, tercero.getSegundonombre());
            ps.setString(6, tercero.getPrimerapellido());
            ps.setString(7, tercero.getSegundoapellido());
            ps.setDate(8, tercero.getFechanacimiento());
            ps.setString(9, tercero.getTelefonofijo());
            ps.setString(10, tercero.getTelefonocelular());
            ps.setString(11, tercero.getCorreo());
            ps.setInt(12, tercero.getEstado());
            ps.setString(13, tercero.getCreadopor());
            ps.setDate(14, tercero.getCreadoen());
            ps.execute();

            

            LOGGER.debug("Codigo de Tercero" + codigo);

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en TerceroDAO insert -->" + e.getMessage());
            
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
    public boolean deleteTercero(int id) {
        PreparedStatement ps =null;
         
        try {

            String SQL = "UPDATE TB_Tercero SET Estado=0 WHERE ID_Tercero = ? ";

            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en TerceroDAO Delete " + e.getMessage());
            
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
           
            ConexionBD.cerrarConexiones(null, ps);

        }

    }

    /**
     *
     * @param tercero
     * @return
     */
    @Override
    public boolean updateTercero(Tercero tercero) {
        PreparedStatement ps =null;
         
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            tercero.setModificadoen(fechaSQL);

            
            String SQL = "UPDATE TB_Tercero SET "
                    + "ID_LV_TipoIdentificacion=?, NumIdentificacion=?, "
                    + "PrimerNombre=?, SegundoNombre=?, PrimerApellido=?, SegundoApellido=?, "
                    + "FechaNacimiento=?, TelefonoFijo=?, TelefonoCelular=?, Correo =?, "
                    + "Estado=?, ModificadoPor=?, ModificadoEn=? where ID_Tercero = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, tercero.getId_lv_tipoidentificacion());
            ps.setString(2, tercero.getNumidentificacion());
            ps.setString(3, tercero.getPrimernombre());
            ps.setString(4, tercero.getSegundonombre());
            ps.setString(5, tercero.getPrimerapellido());
            ps.setString(6, tercero.getSegundoapellido());
            ps.setDate(7, tercero.getFechanacimiento());
            ps.setString(8, tercero.getTelefonofijo());
            ps.setString(9, tercero.getTelefonocelular());
            ps.setString(10, tercero.getCorreo());
            ps.setInt(11, tercero.getEstado());
            ps.setString(12, tercero.getModificadopor());
            ps.setDate(13, tercero.getModificadoen());
            ps.setInt(14, tercero.getId_tercero());
            ps.execute();
            

            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en TerceroDAO UPDATE " + e.getMessage());
            
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
    public ArrayList<Tercero> getAllTercero() {
        ArrayList<Tercero> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "SELECT * from TB_Tercero where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tercero tercero = new Tercero();
                tercero.setId_tercero(rs.getInt("ID_Tercero"));
                tercero.setId_lv_tipoidentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumidentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimernombre(rs.getString("PrimerNombre"));
                tercero.setSegundonombre(rs.getString("SegundoNombre"));
                tercero.setPrimerapellido(rs.getString("PrimerApellido"));
                tercero.setSegundoapellido(rs.getString("SegundoApellido"));
                tercero.setFechanacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonofijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonocelular(rs.getString("TelefonoCelular"));
                tercero.setCorreo(rs.getString("Correo"));
                tercero.setEstado(rs.getInt("Estado"));
                tercero.setCreadopor(rs.getString("CreadoPor"));
                tercero.setModificadopor(rs.getString("ModificadoPor"));
                tercero.setCreadoen(rs.getDate("CreadoEn"));
                tercero.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(tercero);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en TerceroDAO getAllTercero " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Tercero> getAllTerceroUsuario() {
        ArrayList<Tercero> list = new ArrayList<>(0);
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            final String SQL = "select TB_tercero.* from TB_tercero inner join TB_usuario on tb_tercero.id_tercero = TB_USUARIO.ID_TERCERO";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tercero tercero = new Tercero();
                tercero.setId_tercero(rs.getInt("ID_Tercero"));
                tercero.setId_lv_tipoidentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumidentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimernombre(rs.getString("PrimerNombre"));
                tercero.setSegundonombre(rs.getString("SegundoNombre"));
                tercero.setPrimerapellido(rs.getString("PrimerApellido"));
                tercero.setSegundoapellido(rs.getString("SegundoApellido"));
                tercero.setFechanacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonofijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonocelular(rs.getString("TelefonoCelular"));
                tercero.setCorreo(rs.getString("Correo"));
                tercero.setEstado(rs.getInt("Estado"));
                tercero.setCreadopor(rs.getString("CreadoPor"));
                tercero.setModificadopor(rs.getString("ModificadoPor"));
                tercero.setCreadoen(rs.getDate("CreadoEn"));
                tercero.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(tercero);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en TerceroDAO getAllTercero " + e.getMessage());
            
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
    public Tercero getTerceroById(int id) {

        Tercero tercero = new Tercero();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Tercero where ID_Tercero = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                tercero.setId_tercero(rs.getInt("ID_Tercero"));
                tercero.setId_lv_tipoidentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumidentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimernombre(rs.getString("PrimerNombre"));
                tercero.setSegundonombre(rs.getString("SegundoNombre"));
                tercero.setPrimerapellido(rs.getString("PrimerApellido"));
                tercero.setSegundoapellido(rs.getString("SegundoApellido"));
                tercero.setFechanacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonofijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonocelular(rs.getString("TelefonoCelular"));
                tercero.setCorreo(rs.getString("Correo"));
                tercero.setEstado(rs.getInt("Estado"));
                tercero.setCreadopor(rs.getString("CreadoPor"));
                tercero.setModificadopor(rs.getString("ModificadoPor"));
                tercero.setCreadoen(rs.getDate("CreadoEn"));
                tercero.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return tercero;
        } catch (SQLException e) {
            LOGGER.error("Error en TerceroDAO getTercerosById " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    /**
     *
     * @param tipoDoc
     * @param doc
     * @return
     */
    @Override
    public Tercero getTerceroByIdentificacion(int tipoDoc, String doc) {

        Tercero tercero = new Tercero();
        PreparedStatement ps =null;
         ResultSet rs = null;
        try {

            

            String SQL = "select * from TB_Tercero where ID_LV_TipoIdentificacion = ? and NumIdentificacion= ? ";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, tipoDoc);
            ps.setString(2, doc);
            
            rs = ps.executeQuery();
            if (rs.next()) {

                tercero.setId_tercero(rs.getInt("ID_Tercero"));
                tercero.setId_lv_tipoidentificacion(rs.getInt("ID_LV_TipoIdentificacion"));
                tercero.setNumidentificacion(rs.getString("NumIdentificacion"));
                tercero.setPrimernombre(rs.getString("PrimerNombre"));
                tercero.setSegundonombre(rs.getString("SegundoNombre"));
                tercero.setPrimerapellido(rs.getString("PrimerApellido"));
                tercero.setSegundoapellido(rs.getString("SegundoApellido"));
                tercero.setFechanacimiento(rs.getDate("FechaNacimiento"));
                tercero.setTelefonofijo(rs.getString("TelefonoFijo"));
                tercero.setTelefonocelular(rs.getString("TelefonoCelular"));
                tercero.setCorreo(rs.getString("Correo"));
                tercero.setEstado(rs.getInt("Estado"));
                tercero.setCreadopor(rs.getString("CreadoPor"));
                tercero.setModificadopor(rs.getString("ModificadoPor"));
                tercero.setCreadoen(rs.getDate("CreadoEn"));
                tercero.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return tercero;
        } catch (SQLException e) {
            LOGGER.error("Error en TerceroDAO getTerceroByIdentificacion " + e.getMessage());
            
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }
    }

}
