/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public interface EstadoTicket {
    
    
    EstadoTicket asignar();
    
   
    EstadoTicket iniciar();
    
    
    EstadoTicket resolver();
    
    
    EstadoTicket cerrar();
    
    
    EstadoTicket reabrir();
    
    
    EstadoTicket cancelar();
    
   
    String nombre();
    
    
    default String descripcion() {
        return "El ticket está en estado: " + nombre();
    }
    
       
    default boolean estaActivo() {
        return !nombre().equals("CANCELADO") && !nombre().equals("CERRADO");
    }
}
