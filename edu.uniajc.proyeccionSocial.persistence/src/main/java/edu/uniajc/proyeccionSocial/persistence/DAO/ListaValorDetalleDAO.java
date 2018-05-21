/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.ListaValorDetalle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IListaValorDetalleDao;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author rlara
 */
public class ListaValorDetalleDAO implements IListaValorDetalleDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(ListaValorDetalleDAO.class.getName());

    public ListaValorDetalleDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    /**
     *
     * @param listaValorDetalle
     * @return
     */
    @Override
    public int createListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            listaValorDetalle.setCreadoen(fechaSQL);
            listaValorDetalle.setEstado(1);

            String SQL = "select SQ_TB_ListaValorDetalle.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                listaValorDetalle.setId_listavalordetalle(codigo);
            }

            SQL = "INSERT INTO TB_ListaValorDetalle "
                    + "(ID_ListaValorDetalle, ID_ListaValor, Valor, Estado, "
                    + "CreadoPor, CreadoEn) values(?,?,?,?,?,?) ";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, listaValorDetalle.getId_listavalordetalle());
            ps.setInt(2, listaValorDetalle.getId_listavalor());
            ps.setString(3, listaValorDetalle.getValor());
            ps.setInt(4, listaValorDetalle.getEstado());
            ps.setString(5, listaValorDetalle.getCreadopor());
            ps.setDate(6, listaValorDetalle.getCreadoen());
            ps.execute();

            

            System.out.println("Codigo de ListaValorDetalle" + codigo);

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDetalleDAO insert -->" + e.getMessage());
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
    public boolean deleteListaValorDetalle(int id) {
        PreparedStatement ps = null;

        try {

            String SQL = "UPDATE TB_ListaValorDetalle SET Estado=0 WHERE ID_ListaValorDetalle =" + id + " ";

            ps = connection.prepareStatement(SQL);
            ps.execute();
            
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDetalleDAO delete -->" + e.getMessage());
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param listaValorDetalle
     * @return
     */
    @Override
    public boolean updateListaValorDetalle(ListaValorDetalle listaValorDetalle) {
        PreparedStatement ps = null;

        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            listaValorDetalle.setModificadoen(fechaSQL);

            String SQL = "UPDATE TB_ListaValorDetalle SET "
                    + "ID_ListaValor=?, Valor=?, Estado=?, "
                    + "ModificadoPor=?, ModificadoEn=? where ID_ListaValorDetalle = ?";
            ps = connection.prepareStatement(SQL);

            ps.setInt(1, listaValorDetalle.getId_listavalor());
            ps.setString(2, listaValorDetalle.getValor());
            ps.setInt(3, listaValorDetalle.getEstado());
            ps.setString(4, listaValorDetalle.getModificadopor());
            ps.setDate(5, listaValorDetalle.getModificadoen());
            ps.setInt(6, listaValorDetalle.getId_listavalordetalle());
            ps.execute();
            

            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDetalleDAO delete  -->" + e.getMessage());
            return false;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
            
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

    /**
     *
     * @param agrupa
     * @return
     */
    @Override
    public ArrayList<ListaValorDetalle> getAllListaValorDetalle(String agrupa) {
        ArrayList<ListaValorDetalle> list = new ArrayList<>(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            final String SQL = "SELECT lvd.ID_LISTAVALORDETALLE , lvd.ID_LISTAVALOR , lvd.VALOR , lvd.ESTADO , lvd.CREADOPOR, lvd.MODIFICADOPOR, lvd.CREADOEN, lvd.MODIFICADOEN from TB_ListaValorDetalle lvd inner join TB_LISTAVALOR lv on lvd.ID_LISTAVALOR = lv.ID_LISTAVALOR\n"
                    + " where upper (lv.AGRUPACION) = upper('" + agrupa + "') and lv.estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListaValorDetalle listaValorDetalle = new ListaValorDetalle();
                listaValorDetalle.setId_listavalordetalle(rs.getInt("ID_ListaValorDetalle"));
                listaValorDetalle.setId_listavalor(rs.getInt("ID_ListaValor"));
                listaValorDetalle.setValor(rs.getString("Valor"));
                listaValorDetalle.setEstado(rs.getInt("Estado"));
                listaValorDetalle.setCreadopor(rs.getString("CreadoPor"));
                listaValorDetalle.setModificadopor(rs.getString("ModificadoPor"));
                listaValorDetalle.setCreadoen(rs.getDate("CreadoEn"));
                listaValorDetalle.setModificadoen(rs.getDate("ModificadoEn"));

                list.add(listaValorDetalle);
            }
            

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDetalleDAO getAllListaValorDetalle -->" + e.getMessage());
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
    public ListaValorDetalle getListaValorDetalleById(int id) {

        ListaValorDetalle listaValorDetalle = new ListaValorDetalle();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            String SQL = "select * from TB_ListaValorDetalle where ID_ListaValorDetalle =" + id + " and estado = 1";
            ps = connection.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {

                listaValorDetalle.setId_listavalordetalle(rs.getInt("ID_ListaValorDetalle"));
                listaValorDetalle.setId_listavalor(rs.getInt("ID_ListaValor"));
                listaValorDetalle.setValor(rs.getString("Valor"));
                listaValorDetalle.setEstado(rs.getInt("Estado"));
                listaValorDetalle.setCreadopor(rs.getString("CreadoPor"));
                listaValorDetalle.setModificadopor(rs.getString("ModificadoPor"));
                listaValorDetalle.setCreadoen(rs.getDate("CreadoEn"));
                listaValorDetalle.setModificadoen(rs.getDate("ModificadoEn"));

            }
            

            return listaValorDetalle;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDetalleDAO getListaValorDetallesById -->" + e.getMessage());
            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             try { if (rs != null) rs.close(); } catch (Exception errorRS) { errorRS.getMessage(); }
             try { if (ps != null) ps.close(); } catch (Exception errorST) { errorST.getMessage(); }

        }

    }

}
