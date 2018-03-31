/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.ProyectoEtapa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IProyectoEtapaDao {

    public int createProyectoEtapa(ProyectoEtapa proyectoEtapa);

    public boolean deleteProyectoEtapa(int id);

    public boolean updateProyectoEtapa(ProyectoEtapa proyectoEtapa);

    public ArrayList<ProyectoEtapa> getAllProyectoEtapaByProyecto(int idProyecto);

    public ProyectoEtapa getProyectoEtapaById(int id);
    public ArrayList<ProyectoEtapa> getAllProyectoEtapasAprobadasByProyecto(int idProyecto);
    

}
