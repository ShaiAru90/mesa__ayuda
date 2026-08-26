package co.edu.sena.mesaDeAyuda.servicio.notificacion;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 *
 * @author PC_21
 */


public class NotificacionCorreoReal implements NotificacionStrategy {

    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ========== CONFIGURACIÓN DEL SERVIDOR SMTP ==========
    private final String smtpHost;
    private final int smtpPort;
    private final String username;
    private final String password;
    private final boolean useTls;
    private final boolean useAuth;

    /**
     * Constructor con configuración completa.
     */
    public NotificacionCorreoReal(String smtpHost, int smtpPort, 
                                   String username, String password,
                                   boolean useTls, boolean useAuth) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
        this.useTls = useTls;
        this.useAuth = useAuth;
    }

    /**
     * Constructor para Gmail (configuración común).
     */
    public NotificacionCorreoReal(String username, String password) {
        this("smtp.gmail.com", 587, username, password, true, true);
    }

    @Override
    public void notificar(Usuario usuario, String mensaje) {
        if (usuario == null) {
            System.err.println("⚠️ [CorreoReal] Usuario es NULL, no se puede notificar");
            return;
        }

        String correoDestino = usuario.getCorreo();
        if (correoDestino == null || correoDestino.isEmpty()) {
            System.err.println("⚠️ [CorreoReal] El usuario no tiene correo registrado: " + usuario.getNombre());
            return;
        }

        String fecha = LocalDateTime.now().format(FORMATTER);
        String asunto = "🔔 Mesa de Ayuda CIMM - Notificación";
        String cuerpo = construirCuerpoCorreo(usuario, mensaje, fecha);

        try {
            enviarCorreo(correoDestino, asunto, cuerpo);
            System.out.println("✅ [CorreoReal] Correo enviado a: " + correoDestino);
        } catch (Exception e) {
            System.err.println("❌ [CorreoReal] Error al enviar correo a " + correoDestino + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Envía un correo electrónico usando JavaMail.
     */
    private void enviarCorreo(String destinatario, String asunto, String cuerpo) 
            throws MessagingException {

        // 1. Configurar propiedades
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", String.valueOf(useAuth));
        
        if (useTls) {
            props.put("mail.smtp.starttls.enable", "true");
        }

        // 2. Crear sesión con autenticación
        Session session;
        if (useAuth) {
            session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } else {
            session = Session.getInstance(props);
        }

        // 3. Crear mensaje
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, 
            InternetAddress.parse(destinatario));
        message.setSubject(asunto);
        message.setText(cuerpo);

        // 4. Enviar
        Transport.send(message);
    }

    /**
     * Construye el cuerpo del correo con formato HTML.
     */
    private String construirCuerpoCorreo(Usuario usuario, String mensaje, String fecha) {
        return String.format(
            "========================================\n" +
            "📧 MESA DE AYUDA CIMM\n" +
            "========================================\n\n" +
            "Hola %s,\n\n" +
            "Tienes una nueva notificación sobre tu solicitud de soporte:\n\n" +
            "📌 %s\n\n" +
            "📅 Fecha: %s\n\n" +
            "---\n" +
            "Para ver más detalles, ingresa al sistema:\n" +
            "🔗 http://localhost:8080/mesaDeAyuda/tickets\n\n" +
            "========================================\n" +
            "Centro Industrial de Mantenimiento y Manufactura\n" +
            "SENA - Regional Boyacá\n" +
            "© 2026 Mesa de Ayuda CIMM\n" +
            "========================================",
            usuario.getNombre(),
            mensaje,
            fecha
        );
    }

    @Override
    public String nombre() {
        return "Correo Electrónico Real (JavaMail)";
    }

    @Override
    public String descripcion() {
        return "Envía notificaciones por correo electrónico usando JavaMail.";
    }
}