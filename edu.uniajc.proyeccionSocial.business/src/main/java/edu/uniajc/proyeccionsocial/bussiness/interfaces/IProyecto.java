/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Leon - PSUNIAJC
 *
 */
public interface IProyecto {

    public int createProyecto(Proyecto proyecto);

    public boolean deleteProyecto(int id);

    public boolean updateProyecto(Proyecto proyecto);

    public List<Proyecto> getAllProyectoPendiente();
    
    public List<Proyecto> getAllProyectoAprobado();
    
     public ArrayList<Proyecto> getAllProyectoFinalizado();
      public ArrayList<Proyecto> getAllProyectoCancelado();

    public boolean tieneProyectoPendiente(String usuario);

    public List<Proyecto> getProyectoByUser(String user);

    public Proyecto getProyectoById(int id);

}
