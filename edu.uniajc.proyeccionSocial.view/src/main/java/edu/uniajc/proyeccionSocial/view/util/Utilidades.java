/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.view.util;

import edu.uniajc.proyeccionSocial.persistence.Model.ListaValorDetalle;
import edu.uniajc.proyeccionSocial.persistence.Model.Programa;
import edu.uniajc.proyeccionSocial.persistence.Model.Servicio;
import edu.uniajc.proyeccionSocial.persistence.Model.Tercero;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionSocial.persistence.Model.UsuarioRol;
import static edu.uniajc.proyeccionSocial.view.util.Utilidades.getConnection;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IListaValorDetalle;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.ITercero;
import edu.uniajc.proyeccionsocial.bussiness.services.ListaValorDetalleServices;
import edu.uniajc.proyeccionsocial.bussiness.services.TerceroServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioRolServices;
import edu.uniajc.proyeccionsocial.bussiness.services.UsuarioServices;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuario;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IUsuarioRol;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
    public static String leerFacultades = "combo.facultades";
    public static String leerEmail = "email.correos";
    public static String leerEmailemisor = "cuenta.emisora";
    public static String leerRolCreador = "rol.creador";
    public static String leerRuta = "ruta";

    public static Connection getConnection() {
        HttpSession session = SessionUtils.getSession();
        Connection connection = (Connection) session.getAttribute("connection");

        return connection;
    }

    public static String leerArchivo(String leer) {
        try {

            /**
             * Creamos un Objeto de tipo Properties
             */
            Properties propiedades = new Properties();

            /**
             * Cargamos el archivo desde la ruta especificada URL url =
             * this.getClass().getResource("/edu/uniajc/proyeccionSocial/view/util/Configuracion.properties");
             * propiedades = new Properties(); propiedades.load(new
             * FileInputStream(new File(url.getFile())));
             */
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

    public static String getRuta() {
        IListaValorDetalle servicio = new ListaValorDetalleServices(getConnection());
        String agrupa = String.valueOf(leerArchivo(leerRuta));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(agrupa);
        if (lista != null && lista.size() > 0) {
            return lista.get(0).getValor();
        } else {
            return "";
        }

    }

    public static ArrayList<SelectItem> Consultar_Documentos_combo() {
        IListaValorDetalle servicio = new ListaValorDetalleServices(getConnection());
        String agrupa = String.valueOf(leerArchivo(leerTipoIdentificacion));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(agrupa);
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            items.add(new SelectItem(obj.getId_listavalordetalle(), obj.getValor()));
        }

        return items;

    }

    public static ArrayList<SelectItem> Consultar_Facultades_combo() {
        IListaValorDetalle servicio = new ListaValorDetalleServices(getConnection());
        String agrupa = String.valueOf(leerArchivo(leerFacultades));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(agrupa);
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            items.add(new SelectItem(obj.getId_listavalordetalle(), obj.getValor()));
        }

        return items;

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
        if (fecha != null) {
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            return fechaSQL;
        } else {
            return null;
        }

    }

    public static boolean validarTercero(int tipo, String doc) {
        ITercero terceroServices = new TerceroServices(getConnection());
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
        IUsuario usuarioServices = new UsuarioServices(getConnection());
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

    public static ArrayList<SelectItem> llenar_Combo_Programas(List<Programa> listProgramas) {

        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Programa obj : (ArrayList<Programa>) listProgramas) {
            items.add(new SelectItem(obj.getId_programa(), obj.getDescripcion()));
        }

        return items;

    }

    public static ArrayList<SelectItem> llenar_Combo_ServiciosByPrograma(List<Servicio> listservicios) {

        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Servicio obj : (ArrayList<Servicio>) listservicios) {
            items.add(new SelectItem(obj.getId_servicio(), obj.getDescripcion()));
        }

        return items;

    }

    public static ArrayList<SelectItem> llenar_Combo_Terceros(List<Tercero> listterceros) {

        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Tercero obj : (ArrayList<Tercero>) listterceros) {
            items.add(new SelectItem(obj.getId_tercero(), obj.getNombreCompleto()));
        }

        return items;

    }

    public static ArrayList<SelectItem> llenar_Combo_TerceroUsuarios(List<Tercero> listterceros) {

        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Tercero obj : (ArrayList<Tercero>) listterceros) {
            items.add(new SelectItem(obj.getId_tercero(), obj.getNombreCompleto()));
        }

        return items;

    }

    public static ArrayList<SelectItem> llenar_Combo_Servicios(List<Servicio> listServicios) {

        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Servicio obj : (ArrayList<Servicio>) listServicios) {
            items.add(new SelectItem(obj.getId_servicio(), obj.getDescripcion()));
        }

        return items;

    }

    public static void asignarRolCreador(Usuario user) {
        int creador = Integer.valueOf(leerArchivo(leerRolCreador));
        IUsuarioRol asignar = new UsuarioRolServices(getConnection());
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setId_usuario(user.getId_usuario());
        usuarioRol.setId_rol(creador);
        usuarioRol.setCreadopor("System");
        usuarioRol.setEstado(1);
        asignar.createUsuarioRol(usuarioRol);
    }

    public static Usuario cargarUsuario() {
        HttpSession session = SessionUtils.getSession();
        String user = (String) session.getAttribute("username");
        IUsuario usuarioServices = new UsuarioServices(getConnection());
        Usuario us = usuarioServices.getUserByUsername(user);
        return us;
    }

    public static ArrayList<String> findSendEmail() {
        IListaValorDetalle servicio = new ListaValorDetalleServices(getConnection());
        String agrupa = String.valueOf(leerArchivo(leerEmail));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(agrupa);
        ArrayList<String> emails = new ArrayList<String>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            emails.add(obj.getValor());
        }

        return emails;

    }

    public static ArrayList<String> findEmailEmisor() {
        IListaValorDetalle servicio = new ListaValorDetalleServices(getConnection());
        String agrupa = String.valueOf(leerArchivo(leerEmailemisor));
        List<ListaValorDetalle> lista = servicio.getAllListaValorDetalle(agrupa);
        ArrayList<String> emails = new ArrayList<String>();
        for (ListaValorDetalle obj : (ArrayList<ListaValorDetalle>) lista) {
            emails.add(obj.getValor());
        }

        return emails;

    }

}
