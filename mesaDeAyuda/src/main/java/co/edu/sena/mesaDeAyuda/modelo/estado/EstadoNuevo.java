/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public class EstadoNuevo implements EstadoTicket {
    
    @Override
    public EstadoTicket asignar() {
        return new EstadoAsignado();
    }
    
    @Override
    public EstadoTicket iniciar() {
        throw new IllegalStateException(
            "Un ticket en estado NUEVO no se puede iniciar. Debe ser asignado primero."
        );
    }
    
    @Override
    public EstadoTicket resolver() {
        throw new IllegalStateException(
            "Un ticket en estado NUEVO no se puede resolver. Debe ser asignado primero."
        );
    }
    
    @Override
    public EstadoTicket cerrar() {
        throw new IllegalStateException(
            "Un ticket en estado NUEVO no se puede cerrar. Debe ser resuelto primero."
        );
    }
    
    @Override
    public EstadoTicket reabrir() {
        throw new IllegalStateException(
            "Un ticket en estado NUEVO no se puede reabrir."
        );
    }
    
    @Override
    public EstadoTicket cancelar() {
        return new EstadoCancelado();
    }
    
    @Override
    public String nombre() {
        return "NUEVO";
    }
    
    @Override
    public String descripcion() {
        return "Ticket recién creado, esperando asignación de agente.";
    }
}