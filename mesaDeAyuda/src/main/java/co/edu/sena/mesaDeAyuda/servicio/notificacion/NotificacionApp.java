/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.notificacion;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author halfo
 */
public class NotificacionApp implements NotificacionStrategy {
    
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    // Almacena notificaciones no leídas por usuario
    private final Map<Long, StringBuilder> notificacionesPendientes = new ConcurrentHashMap<>();
    
    @Override
    public void notificar(Usuario usuario, String mensaje) {
        if (usuario == null) return;
        
        String fecha = LocalDateTime.now().format(FORMATTER);
        
        // Almacenar notificación en memoria
        notificacionesPendientes.computeIfAbsent(
            usuario.getId(), 
            k -> new StringBuilder()
        ).append("[").append(fecha).append("] ").append(mensaje).append("\n");
        
        // Mostrar en consola
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📲 NOTIFICACIÓN EN APLICACIÓN");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Para: " + usuario.getNombre());
        System.out.println("  Fecha: " + fecha);
        System.out.println("  Mensaje: " + mensaje);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        // En un sistema real, aquí se guardaría en la base de datos
        // y se enviaría mediante WebSocket para notificaciones en tiempo real
    }
    
    /**
     * Obtiene todas las notificaciones pendientes de un usuario
     */
    public String obtenerNotificaciones(Usuario usuario) {
        if (usuario == null) return "";
        StringBuilder notificaciones = notificacionesPendientes.get(usuario.getId());
        return notificaciones != null ? notificaciones.toString() : "";
    }
    
    /**
     * Marca como leídas todas las notificaciones de un usuario
     */
    public void marcarComoLeidas(Usuario usuario) {
        if (usuario == null) return;
        notificacionesPendientes.remove(usuario.getId());
    }
    
    @Override
    public String nombre() {
        return "Notificación en App";
    }
    
    @Override
    public String descripcion() {
        return "Muestra notificaciones dentro de la aplicación.";
    }
}
