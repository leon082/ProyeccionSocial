/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Oferente;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public interface IOferente {

    public int createOferente(Oferente oferente);

    public boolean deleteOferente(int id);

    public boolean updateOferente(Oferente oferente);

    public List<Oferente> getAllOferente();

    public Oferente getOferenteById(int id);

    public Oferente getOferenteByProyecto(int id);
}
