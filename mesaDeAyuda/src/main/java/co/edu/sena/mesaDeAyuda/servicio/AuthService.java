/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.dto.UsuarioDTO;

/**
 *
 * @author halfo
 */
public interface AuthService {

    /**
     * Autentica un usuario con correo y contraseña
     */
    Usuario autenticar(String correo, String password);

    /**
     * Verifica si un usuario tiene un rol específico
     */
    boolean tieneRol(Usuario usuario, Usuario.Rol rol);

    /**
     * Verifica si un usuario puede ver un ticket
     */
    boolean puedeVerTicket(Usuario usuario, Long ticketId);

    /**
     * Verifica si un usuario puede modificar un ticket
     */
    boolean puedeModificarTicket(Usuario usuario, Long ticketId);
}
