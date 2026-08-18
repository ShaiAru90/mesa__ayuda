/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.excepcion;

/**
 *
 * @author halfo
 */
public class AutenticacionFallidaException extends RuntimeException {
    
    public AutenticacionFallidaException() {
        super("Credenciales incorrectas");
    }
    
    public AutenticacionFallidaException(String mensaje) {
        super(mensaje);
    }
}