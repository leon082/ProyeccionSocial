/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.ListaValorDetalle;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IListaValorDetalle {

    public int createListaValorDetalle(ListaValorDetalle listaValorDetalle);

    public boolean deleteListaValorDetalle(int id);

    public boolean updateListaValorDetalle(ListaValorDetalle listaValorDetalle);

    public ArrayList<ListaValorDetalle> getAllListaValorDetalle(int idValor);

    public ListaValorDetalle getListaValorDetalleById(int id);

}
