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
public class NotificacionSMS implements NotificacionStrategy {
    
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    @Override
    public void notificar(Usuario usuario, String mensaje) {
        if (usuario == null) return;
        
        String fecha = LocalDateTime.now().format(FORMATTER);
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📱 ENVIANDO SMS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Para: " + usuario.getNombre() + " (celular simulado)");
        System.out.println("  Fecha: " + fecha);
        System.out.println("  Mensaje: " + truncarMensaje(mensaje, 160));
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
    }
   
    private String truncarMensaje(String mensaje, int maxLength) {
        if (mensaje == null) return "";
        if (mensaje.length() <= maxLength) return mensaje;
        return mensaje.substring(0, maxLength - 3) + "...";
    }
    
    @Override
    public String nombre() {
        return "SMS";
    }
    
    @Override
    public String descripcion() {
        return "Envía notificaciones por mensaje de texto (SMS).";
    }
}
