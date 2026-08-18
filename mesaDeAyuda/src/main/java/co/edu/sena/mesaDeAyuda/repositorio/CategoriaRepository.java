/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.modelo.Categoria;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author halfo
 */
public interface CategoriaRepository {
    
    List<Categoria> listarTodos();
    
    Optional<Categoria> buscarPorId(Long id);
    
    Optional<Categoria> buscarPorNombre(String nombre);
    
    Categoria guardar(Categoria categoria);
}
