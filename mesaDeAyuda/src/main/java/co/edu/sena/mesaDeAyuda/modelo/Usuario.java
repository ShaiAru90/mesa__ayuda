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
public class Usuario {
    
    private Long id;
    private final String nombre;
    private final String correo;
    private final String password;
    private final Rol rol;
    
    // Constructor para usuarios NUEVOS (sin ID)
    public Usuario(String nombre, String correo, String password, Rol rol) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.correo = Objects.requireNonNull(correo, "El correo es obligatorio");
        this.password = Objects.requireNonNull(password, "La contraseña es obligatoria");
        this.rol = Objects.requireNonNull(rol, "El rol es obligatorio");
        this.id = null;
    }
    
    // Constructor para usuarios EXISTENTES (con ID)
    public Usuario(Long id, String nombre, String correo, String password, Rol rol) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.correo = Objects.requireNonNull(correo, "El correo es obligatorio");
        this.password = Objects.requireNonNull(password, "La contraseña es obligatoria");
        this.rol = Objects.requireNonNull(rol, "El rol es obligatorio");
    }
    
    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
        }
    }
    
    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public Rol getRol() { return rol; }
    
     public String getRolNombre() {
        return rol.getNombre();
    }
    
    public boolean esSolicitante() { return rol == Rol.SOLICITANTE; }
    public boolean esAgente() { return rol == Rol.AGENTE || rol == Rol.ADMIN; }
    public boolean esAdmin() { return rol == Rol.ADMIN; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return id != null && id.equals(((Usuario) o).id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
    
    public enum Rol {
        SOLICITANTE("Solicitante"),
        AGENTE("Agente"),
        ADMIN("Administrador");
        
        private final String nombre;
        
        Rol(String nombre) {
            this.nombre = nombre;
        }
        
        public String getNombre() {
            return nombre;
        }
    }
}