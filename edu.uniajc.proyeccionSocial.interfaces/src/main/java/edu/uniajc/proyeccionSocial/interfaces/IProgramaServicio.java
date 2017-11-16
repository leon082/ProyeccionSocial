/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.interfaces;

import edu.uniajc.proyeccionSocial.interfaces.model.ProgramaServicio;
import java.util.ArrayList;

/**
 *
 * @author luis.leon
 */
public interface IProgramaServicio {

    public int createProgramaServicio(ProgramaServicio progServi);

    public boolean deleteProgramaServicio(int id);

    public boolean updateProgramaServicio(ProgramaServicio progServi);

    public ArrayList<ProgramaServicio> getAllProgramaServicioByPrograma(int idPrograma);

    public ProgramaServicio getProgramaServicioById(int id);

}
