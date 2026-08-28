package co.edu.sena.mesaDeAyuda.dto;

import java.util.List;

/**
 *
 * @author halfo
 */
public class EstadisticasDTO {
    private final long totalTickets;
    private final long ticketsActivos;
    private final long ticketsResueltos;
    private final long ticketsVencidos;
    private final List<TicketPorEstadoDTO> ticketsPorEstado;
    private final List<TicketPorAgenteDTO> ticketsPorAgente;
    private final double promedioResolucionHoras;

    public EstadisticasDTO(long totalTickets, long ticketsActivos, long ticketsResueltos,
                          long ticketsVencidos, List<TicketPorEstadoDTO> ticketsPorEstado,
                          List<TicketPorAgenteDTO> ticketsPorAgente, double promedioResolucionHoras) {
        this.totalTickets = totalTickets;
        this.ticketsActivos = ticketsActivos;
        this.ticketsResueltos = ticketsResueltos;
        this.ticketsVencidos = ticketsVencidos;
        this.ticketsPorEstado = ticketsPorEstado;
        this.ticketsPorAgente = ticketsPorAgente;
        this.promedioResolucionHoras = promedioResolucionHoras;
    }

    // Getters
    public long getTotalTickets() { return totalTickets; }
    public long getTicketsActivos() { return ticketsActivos; }
    public long getTicketsResueltos() { return ticketsResueltos; }
    public long getTicketsVencidos() { return ticketsVencidos; }
    public List<TicketPorEstadoDTO> getTicketsPorEstado() { return ticketsPorEstado; }
    public List<TicketPorAgenteDTO> getTicketsPorAgente() { return ticketsPorAgente; }
    public double getPromedioResolucionHoras() { return promedioResolucionHoras; }
}
