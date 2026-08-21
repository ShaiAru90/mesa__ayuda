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
    
    
    Usuario asignar(List<Usuario> agentes, Ticket ticket);
    
    String nombre();
    
    default String descripcion() {
        return "Estrategia de asignación: " + nombre();
    }
}
