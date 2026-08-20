/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.dto.ComentarioDTO;
import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.mapper.TicketMapper;
import co.edu.sena.mesaDeAyuda.mapper.UsuarioMapper;
import co.edu.sena.mesaDeAyuda.modelo.*;
import co.edu.sena.mesaDeAyuda.repositorio.TicketRepository;
import co.edu.sena.mesaDeAyuda.repositorio.UsuarioRepository;
import co.edu.sena.mesaDeAyuda.servicio.asignacion.AsignacionStrategy;
import co.edu.sena.mesaDeAyuda.servicio.asignacion.SelectorAsignacion;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.*;
import co.edu.sena.mesaDeAyuda.servicio.notificacion.NotificacionStrategy;
import co.edu.sena.mesaDeAyuda.servicio.notificacion.SelectorNotificacion;
import co.edu.sena.mesaDeAyuda.servicio.prioridad.PrioridadStrategy;
import co.edu.sena.mesaDeAyuda.servicio.prioridad.SelectorPrioridad;
import co.edu.sena.mesaDeAyuda.servicio.validador.TicketValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final SelectorPrioridad selectorPrioridad;
    private final SelectorAsignacion selectorAsignacion;
    private final SelectorNotificacion selectorNotificacion;
    private final AsignacionService asignacionService;
    private final AtomicLong secuenciaId;

    public TicketServiceImpl(TicketRepository ticketRepository,
            UsuarioRepository usuarioRepository,
            SelectorPrioridad selectorPrioridad,
            SelectorAsignacion selectorAsignacion,
            SelectorNotificacion selectorNotificacion,
            AsignacionService asignacionService) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
        this.selectorPrioridad = selectorPrioridad;
        this.selectorAsignacion = selectorAsignacion;
        this.selectorNotificacion = selectorNotificacion;
        this.asignacionService = asignacionService;
        this.secuenciaId = new AtomicLong(0);
    }

    @Override
    public TicketDTO crearTicket(TicketDTO ticketDTO, Usuario solicitante) {

        TicketValidator.validarCreacion(ticketDTO, solicitante);

        Categoria categoria = obtenerCategoria(ticketDTO);

        PrioridadStrategy prioridadStrategy = selectorPrioridad
                .resolver(SelectorPrioridad.POR_CATEGORIA)
                .orElseThrow(() -> new IllegalStateException(
                "No hay estrategia de prioridad por categoría disponible"
        ));

        Prioridad prioridad = prioridadStrategy.calcular(ticketDTO, categoria);

        Long id = secuenciaId.incrementAndGet();
        Ticket ticket = new Ticket(
                id,
                ticketDTO.getTitulo(),
                ticketDTO.getDescripcion(),
                categoria,
                prioridad,
                solicitante
        );

        try {
            Usuario agente = asignacionService.asignarAgente(ticket);
            ticket.asignar(agente);
        } catch (SinAgentesDisponiblesException e) {

        }

        ticketRepository.guardar(ticket);

        notificarCambioEstado(ticket, "creado", solicitante);

        return TicketMapper.aDTO(ticket);
    }

    @Override
    public List<TicketDTO> listarTickets(Usuario usuario) {
        if (usuario == null) {
            return List.of();
        }

        List<Ticket> tickets;
        if (usuario.esAdmin()) {
            tickets = ticketRepository.listarTodos();
        } else if (usuario.esAgente()) {
            tickets = ticketRepository.buscarPorAgente(usuario);
        } else {
            tickets = ticketRepository.buscarPorSolicitante(usuario);
        }

        return TicketMapper.aDTO(tickets);
    }

    @Override
    public TicketDTO verDetalle(Long ticketId, Usuario usuario) {
        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        if (!ticket.puedeVer(usuario)) {
            throw new AccesoDenegadoException("No tienes permiso para ver este ticket");
        }

        return TicketMapper.aDTO(ticket);
    }

    @Override
    public Ticket obtenerTicket(Long ticketId) {
        return ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));
    }

    @Override
    public TicketDTO cambiarEstado(Long ticketId, String accion, Usuario usuario) {
        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        if (!ticket.puedeModificar(usuario)) {
            throw new AccesoDenegadoException("No tienes permiso para modificar este ticket");
        }

        try {
            switch (accion.toLowerCase()) {
                case "asignar" -> {
                    Usuario agente = asignacionService.asignarAgente(ticket);
                    ticket.asignar(agente);
                }
                case "iniciar" ->
                    ticket.iniciar();
                case "resuelto" ->
                    ticket.resolver();
                case "cerrado" ->
                    ticket.cerrar();
                case "reabrir" ->
                    ticket.reabrir();
                case "cancelar" -> {
                    if (!usuario.esAdmin()) {
                        throw new AccesoDenegadoException("Solo un administrador puede cancelar tickets");
                    }
                    ticket.cancelar();
                }
                default ->
                    throw new IllegalArgumentException("Acción no válida: " + accion);
            }
        } catch (IllegalStateException e) {
            throw new TransicionEstadoInvalidaException(e.getMessage());
        }

        ticketRepository.guardar(ticket);

        notificarCambioEstado(ticket, accion, usuario);

        return TicketMapper.aDTO(ticket);
    }

    @Override
    public TicketDTO agregarComentario(Long ticketId, String texto, Usuario usuario) {
        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        if (!ticket.puedeVer(usuario)) {
            throw new AccesoDenegadoException("No tienes permiso para comentar en este ticket");
        }

        Comentario comentario = new Comentario(usuario, texto);
        ticket.agregarComentario(comentario);
        ticketRepository.guardar(ticket);

        return TicketMapper.aDTO(ticket);
    }

    @Override
    public TicketDTO agregarComentarioInterno(Long ticketId, String texto, Usuario usuario) {
        if (!usuario.esAgente()) {
            throw new AccesoDenegadoException("Solo agentes y administradores pueden agregar comentarios internos");
        }

        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        Comentario comentario = new Comentario(null, usuario, texto, true);
        ticket.agregarComentario(comentario);
        ticketRepository.guardar(ticket);

        return TicketMapper.aDTO(ticket);
    }

    @Override
    public TicketDTO asignarAgente(Long ticketId, Usuario admin) {
        if (!admin.esAdmin()) {
            throw new AccesoDenegadoException("Solo un administrador puede asignar agentes");
        }

        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        Usuario agente = asignacionService.asignarAgente(ticket);
        ticket.asignar(agente);
        ticketRepository.guardar(ticket);

        notificarCambioEstado(ticket, "asignado", agente);

        return TicketMapper.aDTO(ticket);
    }

    @Override
    public TicketDTO reasignarAgente(
            Long ticketId,
            Long agenteId,
            Usuario admin) {

        if (admin == null || !admin.esAdmin()) {
            throw new AccesoDenegadoException(
                    "Solo un administrador puede reasignar tickets"
            );
        }

        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(()
                        -> new TicketNoEncontradoException(ticketId));

        Usuario nuevoAgente = usuarioRepository.buscarPorId(agenteId)
                .orElseThrow(()
                        -> new IllegalArgumentException(
                        "El agente seleccionado no existe"
                ));

        if (!nuevoAgente.esAgente()) {
            throw new IllegalArgumentException(
                    "El usuario seleccionado no es un agente"
            );
        }

        ticket.reasignar(nuevoAgente);

        ticketRepository.guardar(ticket);

        notificarCambioEstado(ticket, "reasignado a " + nuevoAgente.getNombre(), admin);

        return TicketMapper.aDTO(ticket);
    }

    @Override
    public List<TicketDTO> listarTodosTickets(Usuario usuario) {
        if (!usuario.esAdmin()) {
            throw new AccesoDenegadoException("Solo administradores pueden ver todos los tickets");
        }
        return TicketMapper.aDTO(ticketRepository.listarTodos());
    }

    @Override
    public List<TicketDTO> buscarPorEstado(String estado, Usuario usuario) {
        List<Ticket> tickets = ticketRepository.buscarPorEstado(estado);

        if (!usuario.esAdmin()) {
            tickets = tickets.stream()
                    .filter(t -> t.puedeVer(usuario))
                    .toList();
        }

        return TicketMapper.aDTO(tickets);
    }

    @Override
    public List<TicketDTO> buscarPorPrioridad(String prioridad, Usuario usuario) {
        List<Ticket> tickets = ticketRepository.listarTodos().stream()
                .filter(t -> t.getPrioridad().getNombre().equalsIgnoreCase(prioridad))
                .toList();

        if (!usuario.esAdmin()) {
            tickets = tickets.stream()
                    .filter(t -> t.puedeVer(usuario))
                    .toList();
        }

        return TicketMapper.aDTO(tickets);
    }

    private Categoria obtenerCategoria(TicketDTO ticketDTO) {

        if (ticketDTO.getCategoria() == null) {
            return new Categoria(
                    "General",
                    "Categoría general",
                    "General"
            );
        }

        String nombre = ticketDTO.getCategoria().getNombre();

        if (nombre == null || nombre.trim().isEmpty()) {
            return new Categoria(
                    "General",
                    "Categoría general",
                    "General"
            );
        }

        return new Categoria(
                nombre,
                ticketDTO.getCategoria().getDescripcion(),
                nombre
        );
    }

    private void notificarCambioEstado(Ticket ticket, String accion, Usuario ejecutor) {
        try {
            NotificacionStrategy notificador = selectorNotificacion.resolver("DEFAULT")
                    .orElseThrow(() -> new IllegalStateException("No hay notificador disponible"));

            String mensaje = String.format(
                    "Ticket #%d: %s - %s",
                    ticket.getId(),
                    ticket.getTitulo(),
                    accion
            );

            // 1. NOTIFICAR AL SOLICITANTE 
            notificador.notificar(ticket.getSolicitante(), mensaje);

            // 2. NOTIFICAR AL AGENTE
            if (ticket.getAgente() != null
                    && (ejecutor == null || !ejecutor.getId().equals(ticket.getAgente().getId()))) {
                notificador.notificar(ticket.getAgente(), mensaje);
            }

            // 3. NOTIFICAR AL ADMINISTRADOR 
            if ("resuelto".equalsIgnoreCase(accion) || "cerrado".equalsIgnoreCase(accion)) {
                List<Usuario> admins = usuarioRepository.buscarPorRol(Usuario.Rol.ADMIN);
                for (Usuario admin : admins) {
                    // No notificar al admin si fue él quien ejecutó la acción
                    if (ejecutor == null || !ejecutor.getId().equals(admin.getId())) {
                        notificador.notificar(admin, "📊 " + mensaje + " - Ticket finalizado");
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error al notificar: " + e.getMessage());
        }
    }
}
