/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author halfo
 */
public interface UsuarioRepository {
    
    List<Usuario> listarTodos();
    
    Optional<Usuario> buscarPorId(Long id);
    
    Optional<Usuario> buscarPorCorreo(String correo);
    
    List<Usuario> buscarPorRol(Usuario.Rol rol);
    
    List<Usuario> buscarAgentesDisponibles();
    
    Usuario guardar(Usuario usuario);
    
    boolean existePorCorreo(String correo);
}
