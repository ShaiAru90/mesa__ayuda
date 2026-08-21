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
    
   
    void notificar(Usuario usuario, String mensaje);
        
    default void notificar(Usuario usuario, String asunto, String mensaje) {
        notificar(usuario, asunto + ": " + mensaje);
    }
    
    String nombre();
    
    default String descripcion() {
        return "Estrategia de notificación: " + nombre();
    }
}