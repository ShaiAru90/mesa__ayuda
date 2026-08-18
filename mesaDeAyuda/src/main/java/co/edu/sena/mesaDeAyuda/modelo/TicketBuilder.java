/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo;

/**
 *
 * @author halfo
 */
public class TicketBuilder {
    
    private Long id;
    private String titulo;
    private String descripcion;
    private Categoria categoria;
    private Prioridad prioridad;
    private Usuario solicitante;
    
    public TicketBuilder() {}
    
    public TicketBuilder id(Long id) {
        this.id = id;
        return this;
    }
    
    public TicketBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }
    
    public TicketBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }
    
    public TicketBuilder categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }
    
    public TicketBuilder prioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
        return this;
    }
    
    public TicketBuilder solicitante(Usuario solicitante) {
        this.solicitante = solicitante;
        return this;
    }
    
    public Ticket build() {
        return new Ticket(id, titulo, descripcion, categoria, prioridad, solicitante);
    }
}
