/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.excepcion;

/**
 *
 * @author halfo
 */
public class TicketNoEncontradoException extends RuntimeException {
    
    public TicketNoEncontradoException(Long id) {
        super("No se encontró el ticket con ID: " + id);
    }
    
    public TicketNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}