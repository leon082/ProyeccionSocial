/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.ServicioEtapa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rlara
 */
public interface IServicioEtapa {

    public int createServicioEtapa(ServicioEtapa servicioEtapa);

    public boolean deleteServicioEtapa(int id);

    public boolean updateServicioEtapa(ServicioEtapa servicioEtapa);

    public List<ServicioEtapa> getAllServicioEtapa();

    public ServicioEtapa getServicioEtapaById(int id);

    public boolean deleteEtapaServicioByServicio(int id);

}
