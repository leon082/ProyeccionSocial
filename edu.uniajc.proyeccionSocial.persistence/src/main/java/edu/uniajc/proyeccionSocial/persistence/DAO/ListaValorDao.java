/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.ListaValor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IListaValorDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class ListaValorDao implements IListaValorDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(ListaValorDao.class.getName());

    public ListaValorDao(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param listaValor
     * @return
     */
    @Override
    public int createListaValor(ListaValor listaValor) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            listaValor.setCreadoen(fechaSQL);
            listaValor.setEstado(1);

            String SQL = "select SQ_TB_ListaValor.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                listaValor.setId_listavalor(codigo);
            }

            SQL = "INSERT INTO TB_ListaValor"
                    + " (ID_ListaValor, Agrupacion, Descripcion, Estado, CreadoPor, CreadoEn) "
                    + " values(?,?,?,?,?,?)";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, listaValor.getId_listavalor());
            ps.setString(2, listaValor.getAgrupacion());
            ps.setString(3, listaValor.getDescripcion());
            ps.setInt(4, listaValor.getEstado());
            ps.setString(5, listaValor.getCreadopor());
            ps.setDate(6, listaValor.getCreadoen());

            ps.execute();

            

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao Insert -->" + e.getMessage());
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
    public boolean deleteListaValor(int id) {
        PreparedStatement ps = null;

        try {

            String SQL = "UPDATE TB_ListaValor SET Estado=0 WHERE ID_ListaValor = ? ";

            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao delete -->" + e.getMessage());
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param listaValor
     * @return
     */
    @Override
    public boolean updateListaValor(ListaValor listaValor) {
        PreparedStatement ps = null;

        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            listaValor.setModificadoen(fechaSQL);

            String SQL = "UPDATE TB_ListaValor SET "
                    + " Agrupacion=?, Descripcion=?, Estado=?, ModificadoPor=?, "
                    + "ModificadoEn=? WHERE ID_ListaValor = ?";
            ps = connection.prepareStatement(SQL);

            ps.setString(1, listaValor.getAgrupacion());
            ps.setString(2, listaValor.getDescripcion());
            ps.setInt(3, listaValor.getEstado());
            ps.setString(4, listaValor.getModificadopor());
            ps.setDate(5, listaValor.getModificadoen());
            ps.setInt(6, listaValor.getId_listavalor());

            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao update  -->" + e.getMessage());
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
    public ArrayList<ListaValor> getAllListaValor() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ListaValor> list = new ArrayList<>(0);
        try {

            final String SQL = "SELECT * from TB_ListaValor where estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListaValor listaValor = new ListaValor();
                listaValor.setId_listavalor(rs.getInt("ID_ListaValor"));
                listaValor.setAgrupacion(rs.getString("Agrupacion"));
                listaValor.setDescripcion(rs.getString("Descripcion"));
                listaValor.setEstado(rs.getInt("Estado"));
                listaValor.setCreadopor(rs.getString("CREADOPOR"));
                listaValor.setModificadopor(rs.getString("MODIFICADOPOR"));
                listaValor.setCreadoen(rs.getDate("CREADOEN"));
                listaValor.setModificadoen(rs.getDate("MODIFICADOEN"));

                list.add(listaValor);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao getAllListaValor -->" + e.getMessage());
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
    public ListaValor getListaValorById(int id) {

        ListaValor listaValor = new ListaValor();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String SQL = "select * from TB_ListaValor where ID_ListaValor = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {

                listaValor.setId_listavalor(rs.getInt("ID_ListaValor"));
                listaValor.setAgrupacion(rs.getString("Agrupacion"));
                listaValor.setDescripcion(rs.getString("Descripcion"));
                listaValor.setEstado(rs.getInt("Estado"));
                listaValor.setCreadopor(rs.getString("CREADOPOR"));
                listaValor.setModificadopor(rs.getString("MODIFICADOPOR"));
                listaValor.setCreadoen(rs.getDate("CREADOEN"));
                listaValor.setModificadoen(rs.getDate("MODIFICADOEN"));

            }
            

            return listaValor;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao getListaValorById -->" + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
