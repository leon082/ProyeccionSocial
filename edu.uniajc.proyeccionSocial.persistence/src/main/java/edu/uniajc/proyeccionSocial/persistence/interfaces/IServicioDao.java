/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Servicio;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IServicioDao {

    public int createServicio(Servicio servicio);

    public boolean deleteServicio(int id);

    public boolean updateServicio(Servicio servicio);

    public ArrayList<Servicio> getAllServicio();

    public Servicio getServicioById(int id);

    public ArrayList<Servicio> getAllServicioByProg(int idProg);

    public boolean isInProg(int idServicio);

}
