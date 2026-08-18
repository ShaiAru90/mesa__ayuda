/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.excepcion;

/**
 *
 * @author halfo
 */
public class TransicionEstadoInvalidaException extends RuntimeException {
    
    public TransicionEstadoInvalidaException(String mensaje) {
        super(mensaje);
    }
}
