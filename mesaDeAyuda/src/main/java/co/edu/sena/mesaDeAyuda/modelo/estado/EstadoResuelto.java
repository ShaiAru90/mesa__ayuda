/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public class EstadoResuelto implements EstadoTicket {
    
    @Override
    public EstadoTicket asignar() {
        throw new IllegalStateException(
            "El ticket ya está asignado y resuelto."
        );
    }
    
    @Override
    public EstadoTicket iniciar() {
        throw new IllegalStateException(
            "Un ticket resuelto no puede iniciarse. Puede cerrarse o reabrirse."
        );
    }
    
    @Override
    public EstadoTicket resolver() {
        throw new IllegalStateException(
            "El ticket ya está resuelto."
        );
    }
    
    @Override
    public EstadoTicket cerrar() {
        return new EstadoCerrado();
    }
    
    @Override
    public EstadoTicket reabrir() {
        return new EstadoEnProceso();
    }
    
    @Override
    public EstadoTicket cancelar() {
        return new EstadoCancelado();
    }
    
    @Override
    public String nombre() {
        return "RESUELTO";
    }
    
    @Override
    public String descripcion() {
        return "Ticket resuelto. Esperando confirmación del solicitante.";
    }
}
