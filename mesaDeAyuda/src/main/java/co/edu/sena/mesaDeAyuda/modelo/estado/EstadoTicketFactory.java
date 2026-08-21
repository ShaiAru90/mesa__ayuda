/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo.estado;

/**
 *
 * @author halfo
 */
public class EstadoTicketFactory {
    
    private EstadoTicketFactory() {
        
    }
    
    public static EstadoTicket crear(String nombre) {
        if (nombre == null) {
            return new EstadoNuevo();
        }
        
        return switch (nombre.toUpperCase()) {
            case "NUEVO" -> new EstadoNuevo();
            case "ASIGNADO" -> new EstadoAsignado();
            case "EN_PROCESO" -> new EstadoEnProceso();
            case "RESUELTO" -> new EstadoResuelto();
            case "CERRADO" -> new EstadoCerrado();
            case "CANCELADO" -> new EstadoCancelado();
            default -> new EstadoNuevo();
        };
    }
    
    public static boolean esEstadoTerminal(String nombre) {
        return "CERRADO".equalsIgnoreCase(nombre) || "CANCELADO".equalsIgnoreCase(nombre);
    }
}
