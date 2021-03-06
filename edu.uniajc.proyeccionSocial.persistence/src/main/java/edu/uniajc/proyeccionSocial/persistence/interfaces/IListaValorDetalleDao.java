/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.persistence.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.ListaValorDetalle;
import java.util.ArrayList;

/**
 *
 * @author rlara
 */
public interface IListaValorDetalleDao {

    public int createListaValorDetalle(ListaValorDetalle listaValorDetalle);

    public boolean deleteListaValorDetalle(int id);

    public boolean updateListaValorDetalle(ListaValorDetalle listaValorDetalle);

    public ArrayList<ListaValorDetalle> getAllListaValorDetalle(String agrupa);

    public ListaValorDetalle getListaValorDetalleById(int id);

}
