/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.interfaces;

import edu.uniajc.proyeccionSocial.Model.ReporteProyecto;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LuisLeon
 */
public interface IReporteProyecto {
    public ArrayList<ReporteProyecto> getAllProyect(int idPrograma, int idServicio, int idTerceroOferente, int idTerceroCreadoPor, Date fechaDesde, Date fechaHasta, int estado) ;
    
}
