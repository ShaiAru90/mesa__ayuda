/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.excepcion;

/**
 *
 * @author halfo
 */
public class SinAgentesDisponiblesException extends RuntimeException {
    
    public SinAgentesDisponiblesException() {
        super("No hay agentes disponibles para asignar el ticket");
    }
    
    public SinAgentesDisponiblesException(String mensaje) {
        super(mensaje);
    }
}
