/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo;

import java.util.Objects;

/**
 *
 * @author halfo
 */
public class Categoria {
    
    private Long id;
    private final String nombre;
    private final String descripcion;
    private final String claveAsignacion;
    
    // Constructor para categorías NUEVAS (sin ID)
    public Categoria(String nombre, String descripcion, String claveAsignacion) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.descripcion = descripcion;
        this.claveAsignacion = claveAsignacion != null ? claveAsignacion : nombre;
        this.id = null;
    }
    
    public Categoria(String nombre, String descripcion) {
        this(nombre, descripcion, nombre);
    }
    
    // Constructor para categorías EXISTENTES (con ID)
    public Categoria(Long id, String nombre, String descripcion, String claveAsignacion) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.descripcion = descripcion;
        this.claveAsignacion = claveAsignacion != null ? claveAsignacion : nombre;
    }
    
    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
        }
    }
    
    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getClaveAsignacion() { return claveAsignacion; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        return id != null && id.equals(((Categoria) o).id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
