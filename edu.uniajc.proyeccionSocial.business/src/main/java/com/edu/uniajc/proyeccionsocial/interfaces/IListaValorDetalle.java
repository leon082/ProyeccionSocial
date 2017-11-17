/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.ListaValorDetalle;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IListaValorDetalle {

    public int createListaValorDetalle(ListaValorDetalle listaValorDetalle);

    public boolean deleteListaValorDetalle(int id);

    public boolean updateListaValorDetalle(ListaValorDetalle listaValorDetalle);

    public ArrayList<ListaValorDetalle> getAllListaValorDetalle();

    public ListaValorDetalle getListaValorDetalleById(int id);

}
