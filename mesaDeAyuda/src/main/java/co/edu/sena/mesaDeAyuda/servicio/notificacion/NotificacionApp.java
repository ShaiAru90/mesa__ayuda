/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.notificacion;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author halfo
 */
public class NotificacionApp implements NotificacionStrategy {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final Map<Long, List<String>> notificacionesPendientes = new ConcurrentHashMap<>();

    @Override
    public void notificar(Usuario usuario, String mensaje) {
        if (usuario == null) return;

        String fecha = LocalDateTime.now().format(FORMATTER);
        String notificacion = "[" + fecha + "] " + mensaje;

        notificacionesPendientes.computeIfAbsent(
            usuario.getId(),
            k -> new ArrayList<>()
        ).add(notificacion);

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📲 NOTIFICACIÓN EN APLICACIÓN");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Para: " + usuario.getNombre() + " (" + usuario.getRol().getNombre() + ")");
        System.out.println("  Fecha: " + fecha);
        System.out.println("  Mensaje: " + mensaje);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

   
    public void notificarConRol(Usuario destinatario, String mensaje, Usuario emisor) {
        String rolEmisor = emisor != null ? emisor.getRol().getNombre() : "Sistema";
        String mensajeCompleto = "[" + rolEmisor + "] " + mensaje;
        notificar(destinatario, mensajeCompleto);
    }

    public List<String> obtenerListaNotificaciones(Usuario usuario) {
        if (usuario == null) return List.of();
        List<String> notificaciones = notificacionesPendientes.get(usuario.getId());
        return notificaciones != null ? new ArrayList<>(notificaciones) : List.of();
    }

    public int contarNoLeidas(Usuario usuario) {
        if (usuario == null) return 0;
        List<String> notificaciones = notificacionesPendientes.get(usuario.getId());
        return notificaciones != null ? notificaciones.size() : 0;
    }

    public void marcarComoLeidas(Usuario usuario) {
        if (usuario == null) return;
        notificacionesPendientes.remove(usuario.getId());
    }

    public void marcarComoLeida(Usuario usuario, int index) {
        if (usuario == null) return;
        List<String> notificaciones = notificacionesPendientes.get(usuario.getId());
        if (notificaciones != null && index >= 0 && index < notificaciones.size()) {
            notificaciones.remove(index);
            if (notificaciones.isEmpty()) {
                notificacionesPendientes.remove(usuario.getId());
            }
        }
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