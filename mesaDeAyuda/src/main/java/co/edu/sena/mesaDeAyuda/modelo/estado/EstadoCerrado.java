/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public class EstadoCerrado implements EstadoTicket {
    
    @Override
    public EstadoTicket asignar() {
        throw new IllegalStateException(
            "Un ticket cerrado no se puede asignar."
        );
    }
    
    @Override
    public EstadoTicket iniciar() {
        throw new IllegalStateException(
            "Un ticket cerrado no se puede iniciar."
        );
    }
    
    @Override
    public EstadoTicket resolver() {
        throw new IllegalStateException(
            "Un ticket cerrado no se puede resolver."
        );
    }
    
    @Override
    public EstadoTicket cerrar() {
        throw new IllegalStateException(
            "El ticket ya está cerrado."
        );
    }
    
    @Override
    public EstadoTicket reabrir() {
        throw new IllegalStateException(
            "Un ticket cerrado no se puede reabrir."
        );
    }
    
    @Override
    public EstadoTicket cancelar() {
        throw new IllegalStateException(
            "Un ticket cerrado no se puede cancelar."
        );
    }
    
    @Override
    public String nombre() {
        return "CERRADO";
    }
    
    @Override
    public String descripcion() {
        return "Ticket cerrado. No se pueden realizar más acciones.";
    }
    
    @Override
    public boolean estaActivo() {
        return false; // El ticket ya no está activo
    }
}
