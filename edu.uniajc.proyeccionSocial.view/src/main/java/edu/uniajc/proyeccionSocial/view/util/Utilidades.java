/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.view.util;

import edu.uniajc.proyeccionSocial.Model.ListaValorDetalle;
import edu.uniajc.proyeccionSocial.Model.Programa;
import edu.uniajc.proyeccionSocial.Model.Servicio;
import edu.uniajc.proyeccionSocial.Model.Tercero;
import edu.uniajc.proyeccionSocial.Model.Usuario;
import edu.uniajc.proyeccionsocial.bussiness.services.ListaValorDetalleServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.interfaces.IUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis.leon
 */
public class Utilidades {

    public static String leerTipoIdentificacion = "combo.tipoIdentificacion";
    public static String leerEmail = "email.correos";
    public static String leerEmailemisor="cuenta.emisora";

    public static String generateHash(String password) throws RuntimeException, NoSuchAlgorithmException {

        if (password == null && password.length() < 0) {
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

    public static String leerArchivo(String leer) {
        try {

            /**
             * Creamos un Objeto de tipo Properties
             */
            Properties propiedades = new Properties();

            /**
             * Cargamos el archivo desde la ruta especificada
            URL url = this.getClass().getResource("/edu/uniajc/proyeccionSocial/view/util/Configuracion.properties");
            propiedades = new Properties();
            propiedades.load(new FileInputStream(new File(url.getFile())));*/
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            InputStream resourceAsStream = contextClassLoader.getResourceAsStream("/Configuracion.properties");
            propiedades.load(resourceAsStream);
            // propiedades.load(Utilidades.class.getResourceAsStream("\\Configuracion.properties"));

            /**
             * Obtenemos los parametros definidos en el archivo
             */
            String nombre = propiedades.getProperty(leer);

            /**
             * Imprimimos los valores
             */
            System.out.println("Nombre: " + nombre);

            return nombre;

        } catch (FileNotFoundException e) {
            System.out.println("Error, El archivo no exite");
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            System.out.println("Error, No se puede leer el archivo");
            return "";
        }

    }

    public static ArrayList<SelectItem> Consultar_Documentos_combo() {
        ListaValorDetalleServices servicio = new ListaValorDetalleServices();
        int idValor = Integer.valueOf(leerArchivo(leerTipoIdentificacion));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(idValor);
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            items.add(new SelectItem(obj.getId_listavalordetalle(), obj.getValor()));
        }

        return items;

    }
    
     public static ArrayList<String> findSendEmail() {
        ListaValorDetalleServices servicio = new ListaValorDetalleServices();
        int idValor = Integer.valueOf(leerArchivo(leerEmail));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(idValor);
        ArrayList<String> emails = new ArrayList<String>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            emails.add(obj.getValor());
        }

        return emails;

    }
     
        public static ArrayList<String> findEmailEmisor() {
        ListaValorDetalleServices servicio = new ListaValorDetalleServices();
        int idValor = Integer.valueOf(leerArchivo(leerEmailemisor));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(idValor);
        ArrayList<String> emails = new ArrayList<String>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            emails.add(obj.getValor());
        }

        return emails;

    }

    public static boolean validarCorreo(String email) {
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        return mather.find();
    }

    public static Date dateToSql(java.util.Date fecha) {
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }

    public static boolean validarTercero(int tipo, String doc) {
        TerceroServices terceroServices = new TerceroServices();
        Tercero tercero = terceroServices.getTerceroByIdentificacion(tipo, doc);
        try {
            if (tercero.getNumidentificacion().length() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validarUsuario(String user) {
        UsuarioServices usuarioServices = new UsuarioServices();
        Usuario usuario = usuarioServices.getUserByUsername(user);
        try {
            if (usuario.getUsuario().length() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validarFechaNacimiento(java.util.Date fecha) {
        java.util.Date fechaActual = new java.util.Date();

        if (fecha.before(fechaActual)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static ArrayList<SelectItem> llenar_Combo_Programas( List<Programa> listProgramas) {
       
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Programa obj : (ArrayList<Programa>) listProgramas) {
            items.add(new SelectItem(obj.getId_programa(), obj.getDescripcion()));
        }

        return items;

    }
    
    
    public static ArrayList<SelectItem> llenar_Combo_Servicios( List<Servicio> listServicios) {
       
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Servicio obj : (ArrayList<Servicio>) listServicios) {
            items.add(new SelectItem(obj.getId_servicio(), obj.getDescripcion()));
        }

        return items;

    }
    
    public static Usuario cargarUsuario() {
        HttpSession session = SessionUtils.getSession();
        String user = (String) session.getAttribute("username");
        IUsuario usuarioServices= new UsuarioServices();
        Usuario us = usuarioServices.getUserByUsername(user);
        return us;
    }

}
