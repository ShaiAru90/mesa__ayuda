/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo;

/**
 *
 * @author halfo
 */
public class UsuarioBuilder {
    
    private Long id;
    private String nombre;
    private String correo;
    private String password;
    private Usuario.Rol rol;
    
    public UsuarioBuilder() {}
    
    public UsuarioBuilder id(Long id) {
        this.id = id;
        return this;
    }
    
    public UsuarioBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    
    public UsuarioBuilder correo(String correo) {
        this.correo = correo;
        return this;
    }
    
    public UsuarioBuilder password(String password) {
        this.password = password;
        return this;
    }
    
    public UsuarioBuilder rol(Usuario.Rol rol) {
        this.rol = rol;
        return this;
    }
    
    public Usuario build() {
        return new Usuario(id, nombre, correo, password, rol);
    }
}
