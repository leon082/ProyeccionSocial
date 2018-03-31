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
public class EnvioCorreoServices implements IEnvioCorreo {

    @Override
    public String getTextOfEmailCreacion() {
        String text = "<p> El sistema de Proyección Social le notifica que el usuario <b> :usuario </b>"
                + "ha realizado la creación de un proyecto para su aprobación.</p> <br> ";
        text += "Titulo del proyecto: <b> :titulo </b>";

        return text;
    }

    @Override
    public String getTextOfEmailAprobacion() {
        String text = "<p> El sistema de Proyección Social le notifica que el usuario <b> :usuario </b> ha aprobado su proyecto. </p> <br>"
                + "Titulo del Proyecto <b> :titulo </b>";

        return text;
    }

    @Override
    public String getTextOfEmailRechazado() {

        String text = "<p> El sistema de Proyección Social le notifica que el usuario <b> :usuario </b> ha rechazado su proyecto. </p> <br>"
                + "<p>Titulo del Proyecto <b> :titulo </b></p> <br>"
                + "Favor comuniquese con el administrador.";

        return text;

    }

    @Override
    public String getTextOfEmailAdjunto() {
        String text = "<p>El sistema de Proyección Social le notifica que el usuario <b> :usuario </b> ha subido un adjunto para su aprobación, como entrega del proyecto <b>:titulo </b> .</p> <br>"
                + " <p> Codigo de la Etapa: <b> :idProyecto </b></p>";

        return text;
    }

    @Override
    public String getTextOfEmailAprobarEntrega() {
        String text = "<p>El sistema de Proyección Social le notifica que el usuario <b> :usuario </b> ha realizado la aprobación de la etapa del proyecto <b> :titulo </b>.</p> ";

        return text;
    }

    @Override
    public String getTextOfEmailRechazarEntrega() {
        String text = "<p> El sistema de Proyección Social le notifica que el usuario <b> :usuario </b> ha rechazado la etapa del proyecto <b> :titulo </b>.</p> <br>"
                + "<p> Favor realizar de nuevo la entrega o ponerse en contacto con el administrador.</p>";

        return text;
    }

    @Override
    public String getTextOfEmailCancelarProyecto() {
        String text = "<p> El sistema de Proyección Social le notifica que el usuario <b> :usuario </b> ha cancelado su proyecto con titulo <b> :titulo </b>.</p> <br>";

        return text;
    }

    @Override
    public String getTextOfEmailRecuperarContraAdmin() {
        String text = "<p>El sistema de Proyección Social le notifica que el usuario <b> :usuario </b> ha solicitado el cambio de contraseña.</p>";

        return text;
    }

    @Override
    public String getTextOfEmailRecuperarContraUser() {
        String text = "<p> El sistema de Proyección Social le notifica que su solicitud de cambio de clave ha sido enviada al administrador, quien en breve se pondra en contacto.</p>";

        return text;
    }

    @Override
    public String getTextOfEmailContrasenaCambiada() {
        String text = "<p>El sistema de Proyección Social le notifica que el adminsitrador ha realizado el cambio de su contraseña.</p> <br>"
                + "<p>Su nueva contraseña: <b> :contra </b> </p><br>"
                + "<p>Recuerde realizar el cambio de contraseña una vez ingrese al sistema.</p>";

        return text;
    }

    @Override
    public String getTextOfEmailCorreoPrueba() {

        return getTextOfEmailContrasenaCambiada();
    }
    //tipo correo, 0 creacion, 1 aprobacion, 2 rechazado , 3 entrega , 4 aprobarEntrega , 5 Rechazar entrega , 6 cancelar proyecto , 7 recuperar contraseña admin, 8 recuperar contraseña user , 9 contraseña cambiada

    @Override
    public boolean envioCorreo(List<String> correosDestino,
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
                message.setText(text, "utf-8", "html");
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
        boolean result = false;
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
                message.setText(getTextOfEmailCorreoPrueba(), "utf-8", "html");
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
