/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.repositorio;


import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.modelo.Prioridad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author halfo
 */
public class TicketRepositoryEnMemoria implements TicketRepository {
    
    private final Map<Long, Ticket> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(0);
    
    @Override
    public List<Ticket> listarTodos() {
        return new ArrayList<>(datos.values());
    }
    
    @Override
    public Optional<Ticket> buscarPorId(Long id) {
        return Optional.ofNullable(datos.get(id));
    }
    
    @Override
    public List<Ticket> buscarPorSolicitante(Usuario solicitante) {
        if (solicitante == null) return List.of();
        return datos.values().stream()
                .filter(t -> t.getSolicitante().getId().equals(solicitante.getId()))
                .toList();
    }
    
    @Override
    public List<Ticket> buscarPorAgente(Usuario agente) {
        if (agente == null) return List.of();
        return datos.values().stream()
                .filter(t -> t.getAgente() != null && t.getAgente().getId().equals(agente.getId()))
                .toList();
    }
    
    @Override
    public List<Ticket> buscarPorEstado(String estado) {
        if (estado == null) return List.of();
        return datos.values().stream()
                .filter(t -> t.getEstadoNombre().equalsIgnoreCase(estado))
                .toList();
    }
    
    @Override
    public List<Ticket> buscarPorPrioridad(Prioridad prioridad) {
        if (prioridad == null) return List.of();
        return datos.values().stream()
                .filter(t -> t.getPrioridad() == prioridad)
                .toList();
    }
    
    @Override
    public List<Ticket> buscarActivos() {
        return datos.values().stream()
                .filter(Ticket::estaActivo)
                .toList();
    }
    
    @Override
    public Ticket guardar(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("El ticket no puede ser nulo");
        }
        
        Long id = ticket.getId();
        if (id == null) {
            id = secuencia.incrementAndGet();
            ticket.setId(id);
        }
        datos.put(id, ticket);
        return ticket;
    }
}