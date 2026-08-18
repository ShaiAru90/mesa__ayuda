/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.mapper;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.dto.TicketDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author halfo
 */
public final class TicketMapper {

    private TicketMapper() {
        // Clase de utilidad, no se instancia
    }

    public static TicketDTO aDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return TicketDTO.desdeModelo(ticket);
    }

    public static List<TicketDTO> aDTO(List<Ticket> tickets) {
        if (tickets == null) {
            return List.of();
        }

        return tickets.stream()
                .map(TicketMapper::aDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de tickets a DTO, filtrados por visibilidad del
     * usuario
     */
    public static List<TicketDTO> aDTOConVisibilidad(List<Ticket> tickets) {
        if (tickets == null) {
            return List.of();
        }

        return tickets.stream()
                .map(TicketMapper::aDTO)
                .collect(Collectors.toList());
    }
}
