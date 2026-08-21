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
    
   
    Prioridad calcular(TicketDTO ticketDTO, Categoria categoria);
       
    String nombre();
    
    default String descripcion() {
        return "Estrategia de prioridad: " + nombre();
    }
}