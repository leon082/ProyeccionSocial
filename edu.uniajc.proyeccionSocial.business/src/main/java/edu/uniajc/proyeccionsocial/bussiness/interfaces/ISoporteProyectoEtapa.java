/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.SoporteProyectoEtapa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rlara
 */
public interface ISoporteProyectoEtapa {
    public List<SoporteProyectoEtapa> getSoporteProyectoEtapaByIdProyectoEtapa(int id);

    public int createSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa);

    public boolean updateSoporteProyectoEtapa(SoporteProyectoEtapa soporteProyectoEtapa);

    public ArrayList<SoporteProyectoEtapa> getAllSoporteProyectoEtapa();

    public SoporteProyectoEtapa getSoporteProyectoEtapaById(int id);

}
