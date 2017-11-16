/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.dao.test;

import edu.uniajc.proyeccionSocial.DAO.RolDao;
import edu.uniajc.proyeccionSocial.interfaces.model.Rol;
import java.sql.Connection;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 *
 * @author luis.leon
 */
public class RolDaoTest extends TestCase {

    //falta meter la conexion
    Connection DBConnection;
    RolDao dao = new RolDao(DBConnection);
    int creado;

    @Test
    public void crearRol() {

        creado = dao.createRol(initRol());
        if (creado == 0) {
            fail("No creo el Rol");
        }

    }

    @Test
    public void updateUsuario() {
        Rol r = initRol();
        r.setId_Rol(creado);
        assertTrue(dao.updateRol(r));
    }

    @Test
    public void getAllProyectos() {

        assertNotNull(dao.getAllRol());

    }

    @Test
    public void deleteRol() {

        assertTrue(dao.deleteRol(creado));

    }

    public Rol initRol() {
        Rol rol = new Rol();

        rol.setValor(1);
        rol.setDescripcion("Rol test");
        rol.setEstadoRol(1);

        return rol;
    }

}
