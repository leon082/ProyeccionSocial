/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionsocial.bussiness.services;

import edu.uniajc.proyeccionSocial.persistence.Model.Proyecto;
import edu.uniajc.proyeccionSocial.persistence.Model.Usuario;
import edu.uniajc.proyeccionsocial.bussiness.interfaces.IEnvioCorreo;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author luis.leon
 */
 public class EnvioCorreoServices implements IEnvioCorreo{
    
    
    @Override
    public  String getTextOfEmailCreacion() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario "
                + "ha realizado la creacion de un proyecto para su aprobacion. ";
        text += "Titulo del proyecto: :titulo";

        return text;
    }

    @Override
    public  String getTextOfEmailAprobacion() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario ha aprobado su proyecto con titulo :titulo ";

        return text;
    }

    @Override
    public  String getTextOfEmailRechazado() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario ha rechazado su proyecto con titulo :titulo , favor comunicarse con el administrador.";

        return text;
    }

    @Override
    public  String getTextOfEmailAdjunto() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario ha subido un adjunto para su aprobacion, como entrega del proyecto :titulo , Id Etapa: :idProyecto";

        return text;
    }

    @Override
    public  String getTextOfEmailAprobarEntrega() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario ha realizado la aprobacion de la etapa del proyecto :titulo .";

        return text;
    }

    @Override
    public  String getTextOfEmailRechazarEntrega() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario ha rechazado la aprobacion de la etapa del proyecto :titulo . Favor realizar de nuevo la entrega";

        return text;
    }

    @Override
    public  String getTextOfEmailCancelarProyecto() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario ha cancelado su proyecto :titulo .";

        return text;
    }

    @Override
    public  String getTextOfEmailRecuperarContraAdmin() {
        String text = "El sistema de Proyeccion Social le notifica que el usuario :usuario requiere recuperar la contraseña.";

        return text;
    }

    @Override
    public  String getTextOfEmailRecuperarContraUser() {
        String text = "El sistema de Proyeccion Social le notifica que su solicitud de recuperar clave ha sido enviada al administrador, quien en breve se pondra en contacto.";

        return text;
    }

    @Override
    public  String getTextOfEmailContrasenaCambiada() {
        String text = "El sistema de Proyeccion Social le notifica que el adminsitrador ha realizado el cambio de contraseña, su nueva contraseña es :contra";

        return text;
    }
    
     @Override
    public  String getTextOfEmailCorreoPrueba() {
        String text = "Esto es un correo de prueba.";

        return text;
    }
    //tipo correo, 0 creacion, 1 aprobacion, 2 rechazado , 3 entrega , 4 aprobarEntrega , 5 Rechazar entrega , 6 cancelar proyecto , 7 recuperar contraseña admin, 8 recuperar contraseña user , 9 contraseña cambiada

    @Override
    public  boolean envioCorreo(List<String> correosDestino,
            List<String> emisor, Usuario usuario, Proyecto proyecto, int tipoCorreo, String asunto, int idEtapa) {

        String text = "";
        boolean result = false;
        //Texto
        if (tipoCorreo == 0) {
            text = getTextOfEmailCreacion();
            text = text.replace(":usuario", usuario.getUsuario());
            text = text.replace(":titulo", proyecto.getTituloproyecto());
        }
        if (tipoCorreo == 1) {

            text = getTextOfEmailAprobacion();
            text = text.replace(":usuario", usuario.getUsuario());
            text = text.replace(":titulo", proyecto.getTituloproyecto());
        }
        if (tipoCorreo == 2) {

            text = getTextOfEmailRechazado();
            text = text.replace(":usuario", usuario.getUsuario());
            text = text.replace(":titulo", proyecto.getTituloproyecto());
        }
        if (tipoCorreo == 3) {
            text = getTextOfEmailAdjunto();
            text = text.replace(":usuario", usuario.getUsuario());
            text = text.replace(":titulo", proyecto.getTituloproyecto());
            text = text.replace(":idProyecto", String.valueOf(idEtapa));

        }
        if (tipoCorreo == 4) {

            text = getTextOfEmailAprobarEntrega();
            text = text.replace(":usuario", usuario.getUsuario());
            text = text.replace(":titulo", proyecto.getTituloproyecto());
        }
        if (tipoCorreo == 5) {

            text = getTextOfEmailRechazarEntrega();
            text = text.replace(":usuario", usuario.getUsuario());
            text = text.replace(":titulo", proyecto.getTituloproyecto());
        }
        if (tipoCorreo == 6) {

            text = getTextOfEmailCancelarProyecto();
            text = text.replace(":usuario", usuario.getUsuario());
            text = text.replace(":titulo", proyecto.getTituloproyecto());
        }
        if (tipoCorreo == 7) {

            text = getTextOfEmailRecuperarContraAdmin();
            text = text.replace(":usuario", usuario.getUsuario());

        }
        if (tipoCorreo == 8) {

            text = getTextOfEmailRecuperarContraUser();

        }
        if (tipoCorreo == 9) {

            text = getTextOfEmailContrasenaCambiada();
            String[] lista = asunto.split(",");
            asunto = lista[0];
            String clave = lista[1];
            text = text.replace(":contra", clave);

        }

        for (String receptor : correosDestino) {
            Properties properties = new Properties();
            Session session;
            String cuenta = "";
            String password = "";
            if (emisor != null && emisor.size() > 0) {
                cuenta = emisor.get(0);
                password = emisor.get(1);
            }

            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", 25);
            properties.put("mail.smtp.mail.sender", cuenta);
            properties.put("mail.smtp.user", cuenta);
            properties.put("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(properties);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
                message.setSubject(asunto);
                message.setText(text);
                Transport t = session.getTransport("smtp");
                t.connect((String) properties.get("mail.smtp.user"), password);
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                result = true;
            } catch (MessagingException me) {
                System.out.println("Error, no se pudo enviar el correo --> " + me.getMessage());
                result = false;
            }
        }

        return result;
    }

    @Override
    public boolean envioCorreoPrueba(List<String> correosDestino,
            List<String> emisor) {
        boolean result=false;
       for (String receptor : correosDestino) {
            Properties properties = new Properties();
            Session session;
            String cuenta = "";
            String password = "";
            if (emisor != null && emisor.size() > 0) {
                cuenta = emisor.get(0);
                password = emisor.get(1);
            }

            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", 25);
            properties.put("mail.smtp.mail.sender", cuenta);
            properties.put("mail.smtp.user", cuenta);
            properties.put("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(properties);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
                message.setSubject("Correo Prueba");
                message.setText(getTextOfEmailCorreoPrueba());
                Transport t = session.getTransport("smtp");
                t.connect((String) properties.get("mail.smtp.user"), password);
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                result = true;
            } catch (MessagingException me) {
                System.out.println("Error, no se pudo enviar el correo --> " + me.getMessage());
                result = false;
            }
        }
       return result;
    }

    
}
