/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.view.util;

import edu.uniajc.proyeccionSocial.Model.ListaValorDetalle;
import edu.uniajc.proyeccionSocial.Model.Tercero;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import edu.uniajc.proyeccionsocial.bussiness.services.ListaValorDetalleServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.model.SelectItem;

/**
 *
 * @author luis.leon
 */
public class Utilidades {
    
    public static String generateHash(String password) throws RuntimeException, NoSuchAlgorithmException{

		if (password==null && password.length() < 0) {
			System.err.println("String to MD5 digest should be first and only parameter");
			throw new RuntimeException();
		}
		String original = password;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}

		return sb.toString();
	}
    
    public static ArrayList<SelectItem> Consultar_Documentos_combo(int idValor) {
        ListaValorDetalleServices servicio = new ListaValorDetalleServices();

        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(idValor);
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            items.add(new SelectItem(obj.getId_ListaValorDetalle(), obj.getValor()));
        }
        
       
            return items;
        
            
        
    }
    
    public static boolean validarCorreo(String email){
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        
 
        Matcher mather = pattern.matcher(email);
 
       return mather.find();
    }
    
    public static Date dateToSql (java.util.Date fecha){
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }
    
    public static boolean validarTercero(int tipo, String doc){
       TerceroServices terceroServices = new TerceroServices();
       Tercero tercero= terceroServices.getTerceroByIdentificacion(tipo,doc);
       try{
           if(tercero.getNumIdentificacion().length() >0){
               return true;
           }else{
               return false;
           }
       }catch(Exception e){
           return false;
       }
    }
    
    public static boolean validarUsuario(String user){
       UsuarioServices usuarioServices = new UsuarioServices();
       Usuario usuario= usuarioServices.getUserByUsername(user);
       try{
           if(usuario.getUsuario().length() >0){
               return true;
           }else{
               return false;
           }
       }catch(Exception e){
           return false;
       }
    }
    
    public static boolean validarFechaNacimiento(java.util.Date fecha){
        java.util.Date fechaActual =  new java.util.Date();
        
        if(fecha.before(fechaActual)){
            return true;
        }else{
            return false;
        }
    }
    
}
