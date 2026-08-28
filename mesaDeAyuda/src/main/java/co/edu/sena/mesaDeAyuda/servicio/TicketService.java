/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.dto.ComentarioDTO;
import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.util.List;

public interface TicketService {

    TicketDTO crearTicket(TicketDTO ticketDTO, Usuario solicitante);

    List<TicketDTO> listarTickets(Usuario usuario);

    TicketDTO verDetalle(Long ticketId, Usuario usuario);

    Ticket obtenerTicket(Long ticketId);

    TicketDTO cambiarEstado(Long ticketId, String accion, Usuario usuario);

    TicketDTO agregarComentario(Long ticketId, String texto, Usuario usuario);

    TicketDTO agregarComentarioInterno(Long ticketId, String texto, Usuario usuario);

    TicketDTO asignarAgente(Long ticketId, Usuario admin);

    TicketDTO reasignarAgente(
            Long ticketId,
            Long agenteId,
            Usuario admin);

    List<TicketDTO> listarTodosTickets(Usuario usuario);

    List<TicketDTO> buscarPorEstado(String estado, Usuario usuario);

    List<TicketDTO> buscarPorPrioridad(String prioridad, Usuario usuario);

    TicketDTO cerrarConOTP(Long ticketId, String codigoOTP, Usuario usuario);

    void reenviarOTP(Long ticketId, Usuario usuario);
}
