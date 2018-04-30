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
public class ListaValorDao implements IListaValorDao{
    
    Connection connection;
    private static final Logger LOGGER =  Logger.getLogger(ListaValorDao.class.getName());

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
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            listaValor.setCreadoen(fechaSQL);
            listaValor.setEstado(1);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_ListaValor.nextval ID from dual";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
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

            ps.close();

            return codigo;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao Insert -->" + e.getMessage() );
            return 0;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteListaValor(int id) {
        try {

            String SQL = "UPDATE TB_ListaValor SET Estado=0 WHERE ID_ListaValor =" + id + " ";

            PreparedStatement ps =connection.prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao delete -->" + e.getMessage() );
           return false;
        }

    }

    /**
     *
     * @param listaValor
     * @return
     */
    @Override
    public boolean updateListaValor(ListaValor listaValor) {
        try {
            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            listaValor.setModificadoen(fechaSQL);

            PreparedStatement ps = null;
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
            ps.close();
            return true;

        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao update  -->" + e.getMessage() );
            return false;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<ListaValor> getAllListaValor() {
        ArrayList<ListaValor> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_ListaValor where estado = 1";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
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
            ps.close();

            return list;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao getAllListaValor -->" + e.getMessage() );
           return null;
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ListaValor getListaValorById(int id) {

        ListaValor listaValor =  new ListaValor();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_ListaValor where ID_ListaValor =" + id + " and estado = 1";
            ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
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
            ps.close();

            return listaValor;
        } catch (SQLException e) {
            LOGGER.error("Error en  ListaValorDao getListaValorById -->" + e.getMessage() );
            return null;
        }

    }

   

}
