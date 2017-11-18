/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.FaseProyecto;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public interface IFaseProyecto {

    public int createFaseProyecto(FaseProyecto faseProyecto);

    public boolean deleteFaseProyecto(int id);

    public boolean updateFaseProyecto(FaseProyecto faseProyecto);

    public ArrayList<FaseProyecto> getAllFaseProyectos();

    public FaseProyecto getFaseProyectoById(int id);

}
