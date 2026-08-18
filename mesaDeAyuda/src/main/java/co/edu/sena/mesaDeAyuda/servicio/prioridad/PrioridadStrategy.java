/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.prioridad;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.modelo.Categoria;
import co.edu.sena.mesaDeAyuda.modelo.Prioridad;

/**
 *
 * @author halfo
 */
public interface PrioridadStrategy {
    
    /**
     * Calcula la prioridad del ticket según la estrategia.
     * 
     * @param ticketDTO Datos del ticket
     * @param categoria Categoría del ticket
     * @return Prioridad calculada
     */
    Prioridad calcular(TicketDTO ticketDTO, Categoria categoria);
    
    /**
     * Nombre de la estrategia
     */
    String nombre();
    
    /**
     * Descripción de la estrategia
     */
    default String descripcion() {
        return "Estrategia de prioridad: " + nombre();
    }
}