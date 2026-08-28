package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.dto.EstadisticasDTO;
import co.edu.sena.mesaDeAyuda.dto.TicketPorAgenteDTO;
import co.edu.sena.mesaDeAyuda.dto.TicketPorEstadoDTO;
import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.repositorio.TicketRepository;
import co.edu.sena.mesaDeAyuda.repositorio.UsuarioRepository;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author halfo
 */
public class ReporteServiceImpl implements ReporteService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public ReporteServiceImpl(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public EstadisticasDTO obtenerEstadisticas(Usuario admin) {
        if (admin == null || !admin.esAdmin()) {
            throw new AccesoDenegadoException("Solo administradores pueden ver estadísticas");
        }

        List<Ticket> tickets = ticketRepository.listarTodos();
        long totalTickets = tickets.size();

        // 1. Tickets por estado
        List<TicketPorEstadoDTO> ticketsPorEstado = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getEstadoNombre, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new TicketPorEstadoDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        // 2. Tickets activos (no cerrados ni cancelados)
        long ticketsActivos = tickets.stream()
                .filter(Ticket::estaActivo)
                .count();

        // 3. Tickets resueltos
        long ticketsResueltos = tickets.stream()
                .filter(Ticket::estaResuelto)
                .count();

        // 4. Tickets con SLA vencido
        long ticketsVencidos = tickets.stream()
                .filter(t -> t.estaActivo() && !t.esNuevo())
                .filter(t -> {
                    LocalDateTime vencimiento = t.getFechaCreacion()
                            .plus(t.getPrioridad().getSlaHoras().longValue(), ChronoUnit.HOURS);
                    return LocalDateTime.now().isAfter(vencimiento);
                })
                .count();

        // 5. Tickets por agente
        List<TicketPorAgenteDTO> ticketsPorAgente = new ArrayList<>();
        List<Usuario> agentes = usuarioRepository.buscarPorRol(Usuario.Rol.AGENTE);
        for (Usuario agente : agentes) {
            long asignados = tickets.stream()
                    .filter(t -> t.getAgente() != null && t.getAgente().getId().equals(agente.getId()))
                    .count();
            long resueltos = tickets.stream()
                    .filter(t -> t.getAgente() != null 
                            && t.getAgente().getId().equals(agente.getId())
                            && (t.estaResuelto() || t.estaCerrado()))
                    .count();
            ticketsPorAgente.add(new TicketPorAgenteDTO(agente.getNombre(), asignados, resueltos));
        }

        // 6. Promedio de resolución (en horas)
        double promedioResolucionHoras = tickets.stream()
                .filter(t -> t.getFechaResolucion() != null)
                .mapToLong(t -> ChronoUnit.HOURS.between(t.getFechaCreacion(), t.getFechaResolucion()))
                .average()
                .orElse(0);

        return new EstadisticasDTO(
                totalTickets,
                ticketsActivos,
                ticketsResueltos,
                ticketsVencidos,
                ticketsPorEstado,
                ticketsPorAgente,
                promedioResolucionHoras
        );
    }
}
