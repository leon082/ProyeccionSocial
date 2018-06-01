/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.ProgramaServicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public interface IProgramaServicio {

    public int createProgramaServicio(ProgramaServicio progServi);

    public boolean deleteProgramaServicio(int id);

    public boolean updateProgramaServicio(ProgramaServicio progServi);

    public List<ProgramaServicio> getAllProgramaServicioByPrograma(int idPrograma);

    public ProgramaServicio getProgramaServicioById(int id);

    public boolean deleteProgramaServicioByProg(int id);

}
