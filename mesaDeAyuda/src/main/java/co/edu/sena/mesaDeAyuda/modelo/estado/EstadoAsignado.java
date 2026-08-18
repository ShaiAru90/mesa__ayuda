/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public class EstadoAsignado implements EstadoTicket {
    
    @Override
    public EstadoTicket asignar() {
        throw new IllegalStateException(
            "El ticket ya está asignado a un agente."
        );
    }
    
    @Override
    public EstadoTicket iniciar() {
        return new EstadoEnProceso();
    }
    
    @Override
    public EstadoTicket resolver() {
        throw new IllegalStateException(
            "Un ticket en estado ASIGNADO no se puede resolver. Debe estar en proceso."
        );
    }
    
    @Override
    public EstadoTicket cerrar() {
        throw new IllegalStateException(
            "Un ticket en estado ASIGNADO no se puede cerrar. Debe ser resuelto primero."
        );
    }
    
    @Override
    public EstadoTicket reabrir() {
        throw new IllegalStateException(
            "Un ticket en estado ASIGNADO no se puede reabrir."
        );
    }
    
    @Override
    public EstadoTicket cancelar() {
        return new EstadoCancelado();
    }
    
    @Override
    public String nombre() {
        return "ASIGNADO";
    }
    
    @Override
    public String descripcion() {
        return "Ticket asignado a un agente, esperando inicio de atención.";
    }
}

