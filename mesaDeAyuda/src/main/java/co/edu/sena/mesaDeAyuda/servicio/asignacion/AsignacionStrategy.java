/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.asignacion;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.util.List;

/**
 *
 * @author halfo
 */
public interface AsignacionStrategy {
    
    /**
     * Asigna un agente de la lista al ticket según la estrategia.
     * 
     * @param agentes Lista de agentes disponibles
     * @param ticket  Ticket a asignar
     * @return Usuario agente asignado
     */
    Usuario asignar(List<Usuario> agentes, Ticket ticket);
    
    /**
     * Nombre de la estrategia para mostrar en la interfaz
     */
    String nombre();
    
    /**
     * Descripción de la estrategia
     */
    default String descripcion() {
        return "Estrategia de asignación: " + nombre();
    }
}
