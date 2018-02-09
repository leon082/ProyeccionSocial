/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.ListaValor;
import java.util.ArrayList;

/**
 *
 * @author Leon
 */
public interface IListaValor {

    public int createListaValor(ListaValor listaValor);

    public boolean deleteListaValor(int id);

    public boolean updateListaValor(ListaValor listaValor);

    public ArrayList<ListaValor> getAllListaValor();

    public ListaValor getListaValorById(int id);

}
