/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Etapa;

import java.util.List;

/**
 *
 * @author rlara
 */
public interface IEtapa {

    public int createEtapa(Etapa etapa);

    public boolean deleteEtapa(int id);

    public boolean updateEtapa(Etapa etapa);

    public List<Etapa> getAllEtapa();

    public Etapa getEtapaById(int id);

    public boolean isInServ(int idEtapa);

    public List<Etapa> getAllEtapaByServicio(int idServicio);
}
