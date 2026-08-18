/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.dto;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

/**
 *
 * @author halfo
 */
public class UsuarioDTO {
    
    private final Long id;
    private final String nombre;
    private final String correo;
    private final String rol;
    private final String rolNombre;
    
    public UsuarioDTO(Long id, String nombre, String correo, String rol, String rolNombre) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.rolNombre = rolNombre;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getRol() { return rol; }
    public String getRolNombre() { return rolNombre; }
    
    // Métodos de conveniencia para la vista
    public boolean esSolicitante() {
        return "SOLICITANTE".equals(rol);
    }
    
    public boolean esAgente() {
        return "AGENTE".equals(rol) || "ADMIN".equals(rol);
    }
    
    public boolean esAdmin() {
        return "ADMIN".equals(rol);
    }
    
    public String getNombreCompleto() {
        return nombre + " (" + rolNombre + ")";
    }
}

