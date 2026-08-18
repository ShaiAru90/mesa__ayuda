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
    
    /**
     * Asigna un agente al ticket
     * NUEVO → ASIGNADO
     */
    EstadoTicket asignar();
    
    /**
     * Inicia la atención del ticket
     * ASIGNADO → EN_PROCESO
     */
    EstadoTicket iniciar();
    
    /**
     * Resuelve el ticket
     * EN_PROCESO → RESUELTO
     */
    EstadoTicket resolver();
    
    /**
     * Cierra el ticket (confirmado por el solicitante)
     * RESUELTO → CERRADO
     */
    EstadoTicket cerrar();
    
    /**
     * Reabre un ticket resuelto
     * RESUELTO → EN_PROCESO
     */
    EstadoTicket reabrir();
    
    /**
     * Cancela el ticket (administrador)
     * Cualquier estado → CANCELADO
     */
    EstadoTicket cancelar();
    
    /**
     * Nombre del estado para mostrar en la interfaz
     */
    String nombre();
    
    /**
     * Descripción del estado para el usuario
     */
    default String descripcion() {
        return "El ticket está en estado: " + nombre();
    }
    
    /**
     * Indica si el ticket está activo (no cancelado ni cerrado)
     */
    default boolean estaActivo() {
        return !nombre().equals("CANCELADO") && !nombre().equals("CERRADO");
    }
}
