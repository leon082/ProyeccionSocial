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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;

/**
 *
 * @author luis.leon
 */
public class RolDao {

    private Connection DBConnection = null;

    public RolDao() {

        this.DBConnection = new ConexionBD().conexion();
    }

    public int createRol(Rol rol) {
        try {

            PreparedStatement ps = null;

            String SQL = "select SQ_TB_Rol.nextval ID from dual";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            int codigo = 0;

            if (rs.next()) {
                codigo = rs.getInt("ID");
                rol.setId_Rol(codigo);
            }

            SQL = "INSERT INTO TB_Rol"
                    + " (ID_Rol,Valor,Descripcion, EstadoRol) "
                    + " values(?,?,?,?)";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, rol.getId_Rol());
            ps.setInt(2, rol.getValor());
            ps.setString(3, rol.getDescripcion());
            ps.setInt(4, rol.getEstadoRol());

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

            String SQL = "DELETE FROM TB_Rol WHERE ID_Rol =" + id + " ";

            PreparedStatement ps = this.DBConnection.prepareStatement(SQL);
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
                    + " Valor=?, Descripcion=?, EstadoRol=? "
                    + " where ID_Rol = ?";
            ps = this.DBConnection.prepareStatement(SQL);

            ps.setInt(1, rol.getValor());
            ps.setString(2, rol.getDescripcion());
            ps.setInt(3, rol.getEstadoRol());
            ps.setInt(4, rol.getId_Rol());

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

            final String SQL = "SELECT * from TB_Rol";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId_Rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getInt("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstadoRol(rs.getInt("EstadoRol"));

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

        Rol rol = new Rol();
        try {

            PreparedStatement ps = null;

            String SQL = "select * from TB_Rol where ID_Rol =" + id + " ";
            ps = this.DBConnection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.next();

                rol.setId_Rol(rs.getInt("ID_Rol"));
                rol.setValor(rs.getInt("Valor"));
                rol.setDescripcion(rs.getString("Descripcion"));
                rol.setEstadoRol(rs.getInt("EstadoRol"));

            }
            ps.close();

            return rol;
        } catch (SQLException e) {
            System.out.println("Error en Rol DAO getRolById " + e.getMessage());
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return null;
        }

    }

    @PreDestroy
    public void finish() {
        try {

            DBConnection.close();

        } catch (SQLException sqle) {
            System.out.println("Error en Rol DAO finish " + sqle.getMessage());
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, sqle.getMessage());
        }

    }

}
