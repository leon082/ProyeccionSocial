/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.ProyectoEtapa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rlara
 */
public interface IProyectoEtapa {

    public int createProyectoEtapa(ProyectoEtapa proyectoEtapa);

    public boolean deleteProyectoEtapa(int id);

    public boolean updateProyectoEtapa(ProyectoEtapa proyectoEtapa);

    public List<ProyectoEtapa> getAllProyectoEtapaByProyecto(int idProyecto);
    public List<ProyectoEtapa> getAllProyectoEtapasAprobadasByProyecto(int idProyecto);

    public ProyectoEtapa getProyectoEtapaById(int id);

}
