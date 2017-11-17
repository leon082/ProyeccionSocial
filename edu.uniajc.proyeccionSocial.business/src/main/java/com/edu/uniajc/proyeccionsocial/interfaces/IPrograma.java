/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.Programa;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IPrograma {

    public int createPrograma(Programa programa);

    public boolean deletePrograma(int id);

    public boolean updatePrograma(Programa programa);

    public ArrayList<Programa> getAllPrograma();

    public Programa getProgramaById(int id);

}
