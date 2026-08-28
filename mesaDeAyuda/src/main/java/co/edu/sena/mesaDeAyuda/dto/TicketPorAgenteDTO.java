package co.edu.sena.mesaDeAyuda.dto;

/**
 *
 * @author halfo
 */
public class TicketPorAgenteDTO {

    private final String agenteNombre;
    private final long ticketsAsignados;
    private final long ticketsResueltos;

    public TicketPorAgenteDTO(String agenteNombre, long ticketsAsignados, long ticketsResueltos) {
        this.agenteNombre = agenteNombre;
        this.ticketsAsignados = ticketsAsignados;
        this.ticketsResueltos = ticketsResueltos;
    }

    public String getAgenteNombre() {
        return agenteNombre;
    }

    public long getTicketsAsignados() {
        return ticketsAsignados;
    }

    public long getTicketsResueltos() {
        return ticketsResueltos;
    }

    public double getTasaResolucion() {
        return ticketsAsignados > 0
                ? (double) ticketsResueltos / ticketsAsignados * 100
                : 0;
    }
}
