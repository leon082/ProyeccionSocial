/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.Etapa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IEtapa {

    public int createEtapa(Etapa etapa);

    public boolean deleteEtapa(int id);

    public boolean updateEtapa(Etapa etapa);

    public ArrayList<Etapa> getAllEtapa();

    public Etapa getEtapaById(int id);

}
