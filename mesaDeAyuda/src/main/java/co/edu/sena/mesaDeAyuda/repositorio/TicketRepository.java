/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.modelo.Prioridad;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author halfo
 */
public interface TicketRepository {
    
    List<Ticket> listarTodos();
    
    Optional<Ticket> buscarPorId(Long id);
    
    List<Ticket> buscarPorSolicitante(Usuario solicitante);
    
    List<Ticket> buscarPorAgente(Usuario agente);
    
    List<Ticket> buscarPorEstado(String estado);
    
    List<Ticket> buscarPorPrioridad(Prioridad prioridad);
    
    List<Ticket> buscarActivos();
    
    Ticket guardar(Ticket ticket);
}
