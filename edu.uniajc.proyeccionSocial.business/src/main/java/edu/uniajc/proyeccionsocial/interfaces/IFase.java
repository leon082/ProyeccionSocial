/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.Fase;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IFase {

    public int createFase(Fase fase);

    public boolean deleteFase(int id);

    public boolean updateFase(Fase fase);

    public ArrayList<Fase> getAllFase();

    public Fase getFaseById(int id);

}
