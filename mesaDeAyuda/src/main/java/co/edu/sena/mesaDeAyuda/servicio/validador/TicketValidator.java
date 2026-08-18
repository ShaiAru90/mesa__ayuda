/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.validador;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;


/**
 *
 * @author halfo
 */
public final class TicketValidator {
    
    private TicketValidator() {
        // Clase de utilidad, no se instancia
    }
    
    public static void validarCreacion(TicketDTO ticketDTO, Usuario solicitante) {
        if (solicitante == null) {
            throw new IllegalArgumentException("El solicitante es obligatorio");
        }
        
        if (ticketDTO == null) {
            throw new IllegalArgumentException("Los datos del ticket son obligatorios");
        }
        
        if (ticketDTO.getTitulo() == null || ticketDTO.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        
        if (ticketDTO.getTitulo().length() < 3) {
            throw new IllegalArgumentException("El título debe tener al menos 3 caracteres");
        }
        
        if (ticketDTO.getTitulo().length() > 100) {
            throw new IllegalArgumentException("El título no puede tener más de 100 caracteres");
        }
        
        if (ticketDTO.getDescripcion() != null && ticketDTO.getDescripcion().length() > 500) {
            throw new IllegalArgumentException("La descripción no puede tener más de 500 caracteres");
        }
    }
    
    public static void validarComentario(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío");
        }
        
        if (texto.length() > 500) {
            throw new IllegalArgumentException("El comentario no puede tener más de 500 caracteres");
        }
    }
}
