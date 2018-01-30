/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.DAO;

import com.edu.uniajc.proyeccionsocial.utils.ConexionBD;
import edu.uniajc.proyeccionSocial.Model.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author luis.leon
 */
public class RolDao {

    public int createRol(Rol rol) {
        try {

            java.util.Date fecha = new java.util.Date();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            rol.setCreadoen(fechaSQL);
            rol.setEstado(1);

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Rol.nextval ID from dual";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                rol.setId_rol(codigo);
            }

            SQL = "INSERT INTO TB_Rol"
                    + " (ID_Rol,Valor,Descripcion, Estado) "
                    + " values(?,?,?,?)";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);

            ps.setInt(1, rol.getId_rol());
            ps.setString(2, rol.getValor());
            ps.setString(3, rol.getDescripcion());
            ps.setInt(4, rol.getEstado());

            ps.execute();

            ps.close();

            return codigo;
        } catch (SQLException e) {
            System.out.println("Error en RolDao Insert -->" + e.getMessage());
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return 0;
        }

    }

    public boolean deleteRol(int id) {
        try {

            String SQL = "UPDATE TB_Rol SET Estado=0 WHERE ID_Rol =" + id + " ";

            PreparedStatement ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Rol DAO Delete " + e.getMessage());
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public boolean updateRol(Rol rol) {
        try {

            PreparedStatement ps = null;
            String SQL = "UPDATE TB_Rol SET "
                    + " Valor=?, Descripcion=?, Estado=? "
                    + " where ID_Rol = ?";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);

            ps.setString(1, rol.getValor());
            ps.setString(2, rol.getDescripcion());
            ps.setInt(3, rol.getEstado());
            ps.setInt(4, rol.getId_rol());

            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error en Rol DAO UPDATE " + e.getMessage());
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return false;
        }

    }

    public ArrayList<Rol> getAllRol() {
        ArrayList<Rol> list = new ArrayList<>(0);
        try {

            PreparedStatement ps = null;

            final String SQL = "SELECT * from TB_Rol where estado = 1";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getString("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstado(rs.getInt("Estado"));

                list.add(rol);
            }
            ps.close();

            return list;
        } catch (SQLException e) {
            System.out.println("Error en Rol DAO getAllUsuarios" + e.getMessage());
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public Rol getRolById(int id) {

        Rol   rol = new Rol();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Rol where ID_Rol =" + id + " and estado = 1";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              

                rol.setId_rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getString("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstado(rs.getInt("Estado"));

            }
            ps.close();

            return rol;
        } catch (SQLException e) {
            System.out.println("Error en Rol DAO getRolById " + e.getMessage());
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    public List<Rol> getRolesByUser(int idUsuario) {
        List<Rol> listaRoles = new ArrayList<>();

        try {
            PreparedStatement ps = null;

            String SQL = "select TB_Rol.* from TB_ROL inner join TB_USUARIOROL on TB_ROL.ID_ROL = TB_USUARIOROL.ID_ROL and TB_USUARIOROL.ID_USUARIO = " + idUsuario + " and TB_ROL.estado = 1";
            ps = ConexionBD.getInstance().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId_rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getString("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstado(rs.getInt("Estado"));
                listaRoles.add(rol);
            }
            ps.close();

            return listaRoles;
        } catch (SQLException e) {
            System.out.println("Error en Rol DAO getRolesByUser " + e.getMessage());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }
    }


}
