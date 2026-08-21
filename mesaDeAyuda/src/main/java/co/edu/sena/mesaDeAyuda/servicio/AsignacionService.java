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
 
    Usuario asignarAgente(Ticket ticket);
    
    Usuario reasignar(Ticket ticket, Usuario nuevoAgente, Usuario admin);
    
    List<Usuario> obtenerAgentesDisponibles();
}