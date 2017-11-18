/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.Oferente;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public interface IOferente {

    public int createOferente(Oferente oferente);

    public boolean deleteOferente(int id);

    public boolean updateOferente(Oferente oferente);

    public ArrayList<Oferente> getAllOferentes();

    public Oferente getOferenteById(int id);
}
