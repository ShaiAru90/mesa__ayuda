/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.asignacion;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.SinAgentesDisponiblesException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author halfo
 */
public class AsignacionPorTurno implements AsignacionStrategy {
    
    private final AtomicInteger contador = new AtomicInteger(0);
    
    @Override
    public Usuario asignar(List<Usuario> agentes, Ticket ticket) {
        if (agentes == null || agentes.isEmpty()) {
            throw new SinAgentesDisponiblesException();
        }
        
        // Calcular índice siguiente (Round Robin)
        int indice = contador.getAndIncrement() % agentes.size();
        return agentes.get(indice);
    }
    
    @Override
    public String nombre() {
        return "Por Turno Rotativo (Round Robin)";
    }
    
    @Override
    public String descripcion() {
        return "Asigna agentes en orden rotativo, asegurando carga equilibrada.";
    }
}
