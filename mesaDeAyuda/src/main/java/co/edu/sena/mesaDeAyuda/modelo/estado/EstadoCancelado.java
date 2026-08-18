/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public class EstadoCancelado implements EstadoTicket {
    
    @Override
    public EstadoTicket asignar() {
        throw new IllegalStateException(
            "Un ticket cancelado no se puede asignar."
        );
    }
    
    @Override
    public EstadoTicket iniciar() {
        throw new IllegalStateException(
            "Un ticket cancelado no se puede iniciar."
        );
    }
    
    @Override
    public EstadoTicket resolver() {
        throw new IllegalStateException(
            "Un ticket cancelado no se puede resolver."
        );
    }
    
    @Override
    public EstadoTicket cerrar() {
        throw new IllegalStateException(
            "Un ticket cancelado no se puede cerrar."
        );
    }
    
    @Override
    public EstadoTicket reabrir() {
        throw new IllegalStateException(
            "Un ticket cancelado no se puede reabrir."
        );
    }
    
    @Override
    public EstadoTicket cancelar() {
        throw new IllegalStateException(
            "El ticket ya está cancelado."
        );
    }
    
    @Override
    public String nombre() {
        return "CANCELADO";
    }
    
    @Override
    public String descripcion() {
        return "Ticket cancelado por el administrador.";
    }
    
    @Override
    public boolean estaActivo() {
        return false; // El ticket ya no está activo
    }
}
