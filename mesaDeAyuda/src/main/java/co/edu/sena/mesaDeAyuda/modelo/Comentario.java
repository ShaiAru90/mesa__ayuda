/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author halfo
 */
public class Comentario {
    
    private Long id;
    private final Usuario autor;
    private final String texto;
    private final LocalDateTime fecha;
    private boolean esInterno;
    
    // Constructor para comentarios NUEVOS (sin ID)
    public Comentario(Usuario autor, String texto) {
        this(autor, texto, false);
    }
    
    public Comentario(Usuario autor, String texto, boolean esInterno) {
        this.autor = Objects.requireNonNull(autor, "El autor es obligatorio");
        this.texto = Objects.requireNonNull(texto, "El texto es obligatorio");
        this.fecha = LocalDateTime.now();
        this.esInterno = esInterno;
        this.id = null;
    }
    
    // Constructor para comentarios EXISTENTES (con ID)
    public Comentario(Long id, Usuario autor, String texto, boolean esInterno) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.autor = Objects.requireNonNull(autor, "El autor es obligatorio");
        this.texto = Objects.requireNonNull(texto, "El texto es obligatorio");
        this.fecha = LocalDateTime.now();
        this.esInterno = esInterno;
    }
    
    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
        }
    }
    
    // Getters
    public Long getId() { return id; }
    public Usuario getAutor() { return autor; }
    public String getTexto() { return texto; }
    public LocalDateTime getFecha() { return fecha; }
    public boolean isEsInterno() { return esInterno; }
    
    public void setEsInterno(boolean esInterno) {
        this.esInterno = esInterno;
    }
    
    public String getNombreAutor() {
        return autor.getNombre();
    }
    
    public String getRolAutor() {
        return autor.getRol().getNombre();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comentario)) return false;
        return id != null && id.equals(((Comentario) o).id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}