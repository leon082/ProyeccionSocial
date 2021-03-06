/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rlara
 */
public interface ITercero {

    public int createTercero(Tercero tercero);

    public boolean deleteTercero(int id);

    public boolean updateTercero(Tercero tercero);

    public List<Tercero> getAllTercero();

    public Tercero getTerceroById(int id);

    public Tercero getTerceroByIdentificacion(int tipoDoc, String doc);
    
    public List<Tercero> getAllTerceroUsuario();

}
