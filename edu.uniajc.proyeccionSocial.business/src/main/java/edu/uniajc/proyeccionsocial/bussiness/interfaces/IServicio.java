/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Servicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rlara
 */
public interface IServicio {

    public int createServicio(Servicio servicio);

    public boolean deleteServicio(int id);

    public boolean updateServicio(Servicio servicio);

    public List<Servicio> getAllServicio();

    public Servicio getServicioById(int id);

    public List<Servicio> getAllServicioByProg(int idProg);

    public boolean isInProg(int idServicio);

}
