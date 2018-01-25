/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Opciones_menu;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis.leon
 */
public class Opciones_menuDAO {

    private Connection DBConnection = null;

    public Opciones_menuDAO() {
        ConexionBD bd = new ConexionBD();
        this.DBConnection = bd.conexion();
    }

    public List<Integer> cargarRoles(int idUsuario) {
        List<Integer> listaRoles = new ArrayList<>();

        try {
            PreparedStatement ps = null;

            String SQL = "select id_rol from TB_USUARIOROL where id_usuario = " + idUsuario + " and estado = 1";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int rol = rs.getInt("ID_ROL");
                listaRoles.add(rol);
            }
            ps.close();

            return listaRoles;
        } catch (SQLException e) {
            System.out.println("Error en Opcionoes Menu DAO cargarRoles " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }
    }

    public List<Opciones_menu> getMenuByUser(Usuario user) {
        List<Opciones_menu> listaModulos = new ArrayList<>();

        try {
            List<Integer> roles = cargarRoles(user.getId_usuario());
            PreparedStatement ps = null;
            for (int rol : roles) {
                String SQL = "select id_modulo, descripcion , ruta from TB_MODULO where id_rol = " + rol + " ";
                ps = this.DBConnection.prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();
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

            ps.close();

            return listaModulos;
        } catch (SQLException e) {
            System.out.println("Error en Menu DAO getMenuByUser " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
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
}
