/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.interfaces;

import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import java.util.List;

/**
 *
 * @author luis.leon
 */
public interface IEnvioCorreo {
    
    public  String getTextOfEmailCreacion();

    public  String getTextOfEmailAprobacion() ;

    public  String getTextOfEmailRechazado() ;

    public  String getTextOfEmailAdjunto() ;

    public  String getTextOfEmailAprobarEntrega() ;

    public  String getTextOfEmailRechazarEntrega() ;

    public  String getTextOfEmailCancelarProyecto();

    public  String getTextOfEmailRecuperarContraAdmin() ;

    public  String getTextOfEmailRecuperarContraUser(); 

    public  String getTextOfEmailContrasenaCambiada();
    //tipo correo, 0 creacion, 1 aprobacion, 2 rechazado , 3 entrega , 4 aprobarEntrega , 5 Rechazar entrega , 6 cancelar proyecto , 7 recuperar contraseña admin, 8 recuperar contraseña user , 9 contraseña cambiada

    public  boolean envioCorreo(List<String> correosDestino,
            List<String> emisor, Usuario usuario, Proyecto proyecto, int tipoCorreo, String asunto, int idEtapa) ;
    
}
