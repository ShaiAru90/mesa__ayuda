/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public class EstadoEnProceso implements EstadoTicket {
    
    @Override
    public EstadoTicket asignar() {
        throw new IllegalStateException(
            "El ticket ya está asignado a un agente."
        );
    }
    
    @Override
    public EstadoTicket iniciar() {
        throw new IllegalStateException(
            "El ticket ya está en proceso de atención."
        );
    }
    
    @Override
    public EstadoTicket resolver() {
        return new EstadoResuelto();
    }
    
    @Override
    public EstadoTicket cerrar() {
        throw new IllegalStateException(
            "Un ticket en estado EN_PROCESO no se puede cerrar. Debe ser resuelto primero."
        );
    }
    
    @Override
    public EstadoTicket reabrir() {
        throw new IllegalStateException(
            "Un ticket en estado EN_PROCESO no se puede reabrir."
        );
    }
    
    @Override
    public EstadoTicket cancelar() {
        return new EstadoCancelado();
    }
    
    @Override
    public String nombre() {
        return "EN_PROCESO";
    }
    
    @Override
    public String descripcion() {
        return "Ticket en proceso de atención por el agente asignado.";
    }
}
