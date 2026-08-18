/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.dto;

import co.edu.sena.mesaDeAyuda.mapper.CategoriaMapper;
import co.edu.sena.mesaDeAyuda.mapper.ComentarioMapper;
import co.edu.sena.mesaDeAyuda.mapper.UsuarioMapper;
import co.edu.sena.mesaDeAyuda.modelo.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author halfo
 */
public class TicketDTO {

    private final Long id;
    private final String titulo;
    private final String descripcion;
    private final String descripcionCorta;
    private final CategoriaDTO categoria;
    private final PrioridadDTO prioridad;
    private final UsuarioDTO solicitante;
    private final UsuarioDTO agente;
    private final String estado;
    private final String estadoDescripcion;
    private final String fechaCreacion;
    private final String fechaActualizacion;
    private final String fechaResolucion;
    private final int totalComentarios;
    private final List<ComentarioDTO> comentarios;
    private final boolean activo;
    private final String tiempoTranscurrido;

    public TicketDTO(Long id, String titulo, String descripcion, CategoriaDTO categoria,
            PrioridadDTO prioridad, UsuarioDTO solicitante, UsuarioDTO agente,
            String estado, String estadoDescripcion, LocalDateTime fechaCreacion,
            LocalDateTime fechaActualizacion, LocalDateTime fechaResolucion,
            List<ComentarioDTO> comentarios) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.descripcionCorta = truncarDescripcion(descripcion);
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.solicitante = solicitante;
        this.agente = agente;
        this.estado = estado;
        this.estadoDescripcion = estadoDescripcion;
        this.fechaCreacion = formatearFecha(fechaCreacion);
        this.fechaActualizacion = formatearFecha(fechaActualizacion);
        this.fechaResolucion = formatearFecha(fechaResolucion);
        this.comentarios = comentarios;
        this.totalComentarios = comentarios != null ? comentarios.size() : 0;
        this.activo = estado != null && !estado.equals("CERRADO") && !estado.equals("CANCELADO");
        this.tiempoTranscurrido = calcularTiempoTranscurrido(fechaCreacion);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public PrioridadDTO getPrioridad() {
        return prioridad;
    }

    public UsuarioDTO getSolicitante() {
        return solicitante;
    }

    public UsuarioDTO getAgente() {
        return agente;
    }

    public String getEstado() {
        return estado;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public int getTotalComentarios() {
        return totalComentarios;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public boolean tieneAgente() {
        return agente != null;
    }

    public boolean tieneComentarios() {
        return comentarios != null && !comentarios.isEmpty();
    }

    public String getEstadoClaseCss() {
        return switch (estado) {
            case "NUEVO" ->
                "estado-nuevo";
            case "ASIGNADO" ->
                "estado-asignado";
            case "EN_PROCESO" ->
                "estado-proceso";
            case "RESUELTO" ->
                "estado-resuelto";
            case "CERRADO" ->
                "estado-cerrado";
            case "CANCELADO" ->
                "estado-cancelado";
            default ->
                "estado-desconocido";
        };
    }

    public String getEstadoIcono() {
        return switch (estado) {
            case "NUEVO" ->
                "🆕";
            case "ASIGNADO" ->
                "📌";
            case "EN_PROCESO" ->
                "🔧";
            case "RESUELTO" ->
                "✅";
            case "CERRADO" ->
                "📁";
            case "CANCELADO" ->
                "❌";
            default ->
                "❓";
        };
    }

    private String truncarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            return "";
        }
        if (descripcion.length() <= 100) {
            return descripcion;
        }
        return descripcion.substring(0, 97) + "...";
    }

    private String formatearFecha(LocalDateTime fecha) {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fecha.format(formatter);
    }

    private String calcularTiempoTranscurrido(LocalDateTime fecha) {
        if (fecha == null) {
            return "";
        }
        LocalDateTime ahora = LocalDateTime.now();
        java.time.Duration duracion = java.time.Duration.between(fecha, ahora);

        long horas = duracion.toHours();
        if (horas < 1) {
            long minutos = duracion.toMinutes();
            return minutos + " min";
        }
        if (horas < 24) {
            return horas + " h";
        }
        long dias = horas / 24;
        return dias + " d";
    }

    public static TicketDTO desdeModelo(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        return new TicketDTO(
                ticket.getId(),
                ticket.getTitulo(),
                ticket.getDescripcion(),
                CategoriaMapper.aDTO(ticket.getCategoria()),
                PrioridadDTO.desdeModelo(ticket.getPrioridad()),
                UsuarioMapper.aDTO(ticket.getSolicitante()),
                ticket.getAgente() != null ? UsuarioMapper.aDTO(ticket.getAgente()) : null,
                ticket.getEstadoNombre(),
                ticket.getEstado().descripcion(),
                ticket.getFechaCreacion(),
                ticket.getFechaActualizacion(),
                ticket.getFechaResolucion(),
                ticket.getComentariosCompletos().stream()
                        .map(ComentarioMapper::aDTO)
                        .collect(Collectors.toList())
        );
    }
}
