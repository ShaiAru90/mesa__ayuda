/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import java.util.List;


/**
 *
 * @author halfo
 */
public interface AsignacionService {
    
    /**
     * Asigna un agente a un ticket según la estrategia configurada
     */
    Usuario asignarAgente(Ticket ticket);
    
    /**
     * Reasigna un ticket a otro agente (solo admin)
     */
    Usuario reasignar(Ticket ticket, Usuario nuevoAgente, Usuario admin);
    
    /**
     * Obtiene todos los agentes disponibles
     */
    List<Usuario> obtenerAgentesDisponibles();
}