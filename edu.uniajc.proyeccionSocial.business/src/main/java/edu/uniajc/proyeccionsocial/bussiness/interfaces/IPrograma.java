/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Programa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rlara
 */
public interface IPrograma {

    public int createPrograma(Programa programa);

    public boolean deletePrograma(int id);

    public boolean updatePrograma(Programa programa);

    public List<Programa> getAllPrograma();

    public Programa getProgramaById(int id);
    
    public boolean isInProy(int idPrograma);

}
