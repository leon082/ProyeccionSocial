/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface ITerceroDao {

    public int createTercero(Tercero tercero);

    public boolean deleteTercero(int id);

    public boolean updateTercero(Tercero tercero);

    public ArrayList<Tercero> getAllTercero();

    public Tercero getTerceroById(int id);

    public Tercero getTerceroByIdentificacion(int tipoDoc, String doc);
    
    public ArrayList<Tercero> getAllTerceroUsuario();

}
