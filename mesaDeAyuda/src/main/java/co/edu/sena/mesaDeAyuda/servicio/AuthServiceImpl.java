/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.repositorio.TicketRepository;
import co.edu.sena.mesaDeAyuda.repositorio.UsuarioRepository;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AutenticacionFallidaException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.TicketNoEncontradoException;

/**
 *
 * @author halfo
 */
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final TicketRepository ticketRepository;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, TicketRepository ticketRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Usuario autenticar(String correo, String password) {
        if (correo == null || correo.isBlank()) {
            throw new AutenticacionFallidaException("El correo es obligatorio");
        }
        if (password == null || password.isBlank()) {
            throw new AutenticacionFallidaException("La contraseña es obligatoria");
        }

        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(AutenticacionFallidaException::new);

        // IMPORTANTE: Verificar la contraseña
        if (!usuario.getPassword().equals(password)) {
            throw new AutenticacionFallidaException();
        }

        return usuario;
    }

    @Override
    public boolean tieneRol(Usuario usuario, Usuario.Rol rol) {
        if (usuario == null) {
            return false;
        }
        return usuario.getRol() == rol;
    }

    @Override
    public boolean puedeVerTicket(Usuario usuario, Long ticketId) {
        if (usuario == null) {
            return false;
        }
        if (usuario.esAdmin()) {
            return true;
        }

        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        return ticket.puedeVer(usuario);
    }

    @Override
    public boolean puedeModificarTicket(Usuario usuario, Long ticketId) {
        if (usuario == null) {
            return false;
        }
        if (usuario.esAdmin()) {
            return true;
        }

        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        return ticket.puedeModificar(usuario);
    }
}
