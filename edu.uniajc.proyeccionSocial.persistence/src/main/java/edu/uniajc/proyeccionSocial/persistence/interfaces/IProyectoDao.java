/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emmanuel Barrera - IRIS 12/05/2017 Nombre Clase:Iproyecto
 * Descripcion: Tabla que contiene todo el DAS
 */
public interface IProyectoDao {

    public int createProyecto(Proyecto proyecto);

    public boolean deleteProyecto(int ID);

    public boolean updateProyecto(Proyecto proyecto);

    public ArrayList<Proyecto> getAllProyectoPendiente();
    
    public ArrayList<Proyecto> getAllProyectoAprobado();
    
     public ArrayList<Proyecto> getAllProyectoFinalizado();
      public ArrayList<Proyecto> getAllProyectoCancelado();

    public boolean tieneProyectoPendiente(String usuario);

    public List<Proyecto> getProyectoByUser(String user);

    public Proyecto getProyectoById(int id);

}
