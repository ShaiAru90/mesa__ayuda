/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.notificacion;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

/**
 *
 * @author halfo
 */
public interface NotificacionStrategy {
    
    /**
     * Envía una notificación a un usuario.
     * 
     * @param usuario Usuario destino
     * @param mensaje Mensaje a enviar
     */
    void notificar(Usuario usuario, String mensaje);
    
    /**
     * Envía una notificación a un usuario con un asunto específico.
     */
    default void notificar(Usuario usuario, String asunto, String mensaje) {
        notificar(usuario, asunto + ": " + mensaje);
    }
    
    /**
     * Nombre de la estrategia
     */
    String nombre();
    
    /**
     * Descripción de la estrategia
     */
    default String descripcion() {
        return "Estrategia de notificación: " + nombre();
    }
}