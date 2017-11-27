/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.ProyectoEtapa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IProyectoEtapa {

    public int createProyectoEtapa(ProyectoEtapa proyectoEtapa);

    public boolean deleteProyectoEtapa(int id);

    public boolean updateProyectoEtapa(ProyectoEtapa proyectoEtapa);

    public ArrayList<ProyectoEtapa> getAllProyectoEtapa();

    public ProyectoEtapa getProyectoEtapaById(int id);

}
