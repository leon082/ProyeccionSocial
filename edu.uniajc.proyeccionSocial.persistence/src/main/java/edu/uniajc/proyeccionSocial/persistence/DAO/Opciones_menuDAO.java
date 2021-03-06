/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.DAO;

import edu.uniajc.proyeccionSocial.persistence.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uniajc.proyeccionSocial.persistence.interfaces.IOpciones_menuDao;
import edu.uniajc.proyeccionSocial.persistence.utils.ConexionBD;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author luis.leon
 */
public class Opciones_menuDAO implements IOpciones_menuDao {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(Opciones_menuDAO.class.getName());

    public Opciones_menuDAO(Connection connection) {
        this.connection = connection;
        org.apache.log4j.BasicConfigurator.configure();
    }

    public List<Integer> cargarRoles(int idUsuario) {
        List<Integer> listaRoles = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String SQL = "select id_rol from TB_USUARIOROL where id_usuario = ? and estado = 1";
            ps = connection.prepareStatement(SQL);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                int rol = rs.getInt("ID_ROL");
                listaRoles.add(rol);
            }
            

            return listaRoles;
        } catch (SQLException e) {
            LOGGER.error("Error en Opcionoes Menu DAO cargarRoles " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public List<Opciones_menu> getMenuCuentaByUser(Usuario user) {
        List<Opciones_menu> listaModulos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            List<Integer> roles = cargarRoles(user.getId_usuario());

            for (int rol : roles) {
                String SQL = "select id_modulo, descripcion , ruta from TB_MODULO where id_rol = ? and menu = 'cuenta'";
                ps = connection.prepareStatement(SQL);
                ps.setInt(1, rol);
                rs = ps.executeQuery();
                while (rs.next()) {

                    String ruta = rs.getString("RUTA");

                    if (!validarRepetido(listaModulos, ruta)) {

                        Opciones_menu menu = new Opciones_menu();
                        menu.setDescripcion(rs.getString("DESCRIPCION"));
                        menu.setRuta(rs.getString("RUTA"));
                        menu.setIdModulo(rs.getInt("ID_MODULO"));
                        listaModulos.add(menu);
                    }

                }
            }           

            return listaModulos;
        } catch (SQLException e) {
            LOGGER.error("Error en Menu DAO getMenuByUser " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    public boolean validarRepetido(List<Opciones_menu> lista, String ruta) {
        boolean flag = false;
        if (!lista.isEmpty()) {
            for (Opciones_menu menu : lista) {
                if (menu.getRuta().trim().equalsIgnoreCase(ruta.trim())) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    @Override
    public List<Opciones_menu> getMenuParametrizarByUser(Usuario user) {
        List<Opciones_menu> listaModulos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            List<Integer> roles = cargarRoles(user.getId_usuario());

            for (int rol : roles) {
                String SQL = "select id_modulo, descripcion , ruta from TB_MODULO where id_rol = ? and menu = 'parametrizar'";
                ps = connection.prepareStatement(SQL);
                ps.setInt(1, rol);
                rs = ps.executeQuery();
                while (rs.next()) {

                    String ruta = rs.getString("RUTA");

                    if (!validarRepetido(listaModulos, ruta)) {
                        Opciones_menu menu = new Opciones_menu();
                        menu.setDescripcion(rs.getString("DESCRIPCION"));
                        menu.setRuta(rs.getString("RUTA"));
                        menu.setIdModulo(rs.getInt("ID_MODULO"));
                        listaModulos.add(menu);
                    }

                }
            }

            

            return listaModulos;
        } catch (SQLException e) {
            LOGGER.error("Error en Menu DAO getMenuByUser " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    @Override
    public List<Opciones_menu> getMenuProyectosByUser(Usuario user) {
        List<Opciones_menu> listaModulos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<Integer> roles = cargarRoles(user.getId_usuario());

            for (int rol : roles) {
                String SQL = "select id_modulo, descripcion , ruta from TB_MODULO where id_rol = ? and menu = 'proyectos'";
                ps = connection.prepareStatement(SQL);
                ps.setInt(1, rol);
                rs = ps.executeQuery();
                while (rs.next()) {

                    String ruta = rs.getString("RUTA");

                    if (!validarRepetido(listaModulos, ruta)) {
                        Opciones_menu menu = new Opciones_menu();
                        menu.setDescripcion(rs.getString("DESCRIPCION"));
                        menu.setRuta(rs.getString("RUTA"));
                        menu.setIdModulo(rs.getInt("ID_MODULO"));
                        listaModulos.add(menu);
                    }

                }
            }

            

            return listaModulos;
        } catch (SQLException e) {
            LOGGER.error("Error en Menu DAO getMenuByUser " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
             ConexionBD.cerrarConexiones(rs, ps);
        }

    }

    @Override
    public List<Opciones_menu> getMenuUsuariosByUser(Usuario user) {
        List<Opciones_menu> listaModulos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            List<Integer> roles = cargarRoles(user.getId_usuario());

            for (int rol : roles) {
                String SQL = "select id_modulo, descripcion , ruta from TB_MODULO where id_rol = ? and menu = 'usuarios'";
                ps = connection.prepareStatement(SQL);
                ps.setInt(1, rol);
                rs = ps.executeQuery();
                while (rs.next()) {

                    String ruta = rs.getString("RUTA");

                    if (!validarRepetido(listaModulos, ruta)) {
                        Opciones_menu menu = new Opciones_menu();
                        menu.setDescripcion(rs.getString("DESCRIPCION"));
                        menu.setRuta(rs.getString("RUTA"));
                        menu.setIdModulo(rs.getInt("ID_MODULO"));
                        listaModulos.add(menu);
                    }

                }
            }

            

            return listaModulos;
        } catch (SQLException e) {
            LOGGER.error("Error en Menu DAO getMenuByUser " + e.getMessage());

            return null;
        }finally {// Cerramos las conexiones, en orden inverso a su apertura
              ConexionBD.cerrarConexiones(rs, ps);
        }

    }

}
