/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo;

import co.edu.sena.mesaDeAyuda.modelo.estado.EstadoTicket;
import co.edu.sena.mesaDeAyuda.modelo.estado.EstadoNuevo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ticket {

    private Long id;
    private final String titulo;
    private final String descripcion;
    private final Categoria categoria;
    private Prioridad prioridad;
    private final Usuario solicitante;
    private Usuario agente;
    private EstadoTicket estado;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private final List<Comentario> comentarios;
    private LocalDateTime fechaResolucion;

    public Ticket(String titulo, String descripcion, Categoria categoria,
            Prioridad prioridad, Usuario solicitante) {
        this.titulo = Objects.requireNonNull(titulo, "El título es obligatorio");
        this.descripcion = descripcion != null ? descripcion : "";
        this.categoria = Objects.requireNonNull(categoria, "La categoría es obligatoria");
        this.prioridad = Objects.requireNonNull(prioridad, "La prioridad es obligatoria");
        this.solicitante = Objects.requireNonNull(solicitante, "El solicitante es obligatorio");
        this.estado = new EstadoNuevo();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = this.fechaCreacion;
        this.comentarios = new ArrayList<>();
        this.fechaResolucion = null;
        this.id = null;
    }

    public Ticket(Long id, String titulo, String descripcion, Categoria categoria,
            Prioridad prioridad, Usuario solicitante) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.titulo = Objects.requireNonNull(titulo, "El título es obligatorio");
        this.descripcion = descripcion != null ? descripcion : "";
        this.categoria = Objects.requireNonNull(categoria, "La categoría es obligatoria");
        this.prioridad = Objects.requireNonNull(prioridad, "La prioridad es obligatoria");
        this.solicitante = Objects.requireNonNull(solicitante, "El solicitante es obligatorio");
        this.estado = new EstadoNuevo();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = this.fechaCreacion;
        this.comentarios = new ArrayList<>();
        this.fechaResolucion = null;
    }

    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    public void asignar(Usuario agente) {
        if (agente == null) {
            throw new IllegalArgumentException("El agente no puede ser nulo");
        }
        if (!agente.esAgente()) {
            throw new IllegalArgumentException("El usuario no es un agente válido");
        }
        this.estado = this.estado.asignar();
        this.agente = agente;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void reasignar(Usuario nuevoAgente) {

        if (nuevoAgente == null) {
            throw new IllegalArgumentException(
                    "El agente no puede ser nulo"
            );
        }

        if (!nuevoAgente.esAgente()) {
            throw new IllegalArgumentException(
                    "El usuario seleccionado no es un agente válido"
            );
        }

        this.agente = nuevoAgente;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void iniciar() {
        this.estado = this.estado.iniciar();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void resolver() {
        this.estado = this.estado.resolver();
        this.fechaActualizacion = LocalDateTime.now();
        this.fechaResolucion = LocalDateTime.now();
    }

    public void cerrar() {
        this.estado = this.estado.cerrar();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void reabrir() {
        this.estado = this.estado.reabrir();
        this.fechaActualizacion = LocalDateTime.now();
        this.fechaResolucion = null;
    }

    public void cancelar() {
        this.estado = this.estado.cancelar();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void cambiarPrioridad(Prioridad nuevaPrioridad) {
        if (this.estado.nombre().equals("CERRADO") || this.estado.nombre().equals("CANCELADO")) {
            throw new IllegalStateException("No se puede cambiar prioridad de un ticket "
                    + this.estado.nombre().toLowerCase());
        }
        this.prioridad = Objects.requireNonNull(nuevaPrioridad, "La prioridad es obligatoria");
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void agregarComentario(Comentario comentario) {
        this.comentarios.add(Objects.requireNonNull(comentario, "El comentario es obligatorio"));
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void agregarComentario(Usuario autor, String texto) {
        agregarComentario(new Comentario(autor, texto));
    }

    public void agregarComentarioInterno(Usuario autor, String texto) {
        Comentario comentario = new Comentario(autor, texto, true);
        agregarComentario(comentario);
    }

    public List<Comentario> getComentariosPublicos() {
        return comentarios.stream()
                .filter(c -> !c.isEsInterno())
                .toList();
    }

    public List<Comentario> getComentariosCompletos() {
        return Collections.unmodifiableList(comentarios);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public Usuario getAgente() {
        return agente;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public String getEstadoNombre() {
        return estado.nombre();
    }

    public boolean estaActivo() {
        return !estado.nombre().equals("CANCELADO") && !estado.nombre().equals("CERRADO");
    }

    public boolean estaResuelto() {
        return estado.nombre().equals("RESUELTO");
    }

    public boolean estaCerrado() {
        return estado.nombre().equals("CERRADO");
    }

    public boolean estaCancelado() {
        return estado.nombre().equals("CANCELADO");
    }

    public boolean estaAsignado() {
        return estado.nombre().equals("ASIGNADO");
    }

    public boolean estaEnProceso() {
        return estado.nombre().equals("EN_PROCESO");
    }

    public boolean esNuevo() {
        return estado.nombre().equals("NUEVO");
    }

    public boolean puedeVer(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        if (usuario.esAdmin()) {
            return true;
        }
        if (usuario.esAgente()) {
            return this.agente != null && this.agente.getId().equals(usuario.getId());
        }
        return this.solicitante.getId().equals(usuario.getId());
    }

    public boolean puedeModificar(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        if (usuario.esAdmin()) {
            return true;
        }
        if (usuario.esAgente()) {
            return this.agente != null && this.agente.getId().equals(usuario.getId());
        }
        // ✅ NUEVO: El solicitante puede modificar el ticket SOLO si está en RESUELTO
        if (usuario.esSolicitante()) {
            return this.estado.nombre().equals("RESUELTO")
                    && this.solicitante.getId().equals(usuario.getId());
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "#" + id + " - " + titulo + " (" + estado.nombre() + ")";
    }
}
