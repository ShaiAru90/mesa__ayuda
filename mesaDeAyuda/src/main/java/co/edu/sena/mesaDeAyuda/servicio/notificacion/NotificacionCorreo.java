/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.notificacion;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author halfo
 */
public class NotificacionCorreo implements NotificacionStrategy {
    
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    @Override
    public void notificar(Usuario usuario, String mensaje) {
        if (usuario == null) return;
        
        String correo = usuario.getCorreo();
        String fecha = LocalDateTime.now().format(FORMATTER);
        
        // Simulación de envío de correo
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📧 ENVIANDO CORREO ELECTRÓNICO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Para: " + correo);
        System.out.println("  Usuario: " + usuario.getNombre());
        System.out.println("  Fecha: " + fecha);
        System.out.println("  Mensaje: " + mensaje);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        // En un sistema real, aquí iría la integración con JavaMail
        // Ejemplo: Transport.send(email);
    }
    
    @Override
    public String nombre() {
        return "Correo Electrónico";
    }
    
    @Override
    public String descripcion() {
        return "Envía notificaciones por correo electrónico.";
    }
}
