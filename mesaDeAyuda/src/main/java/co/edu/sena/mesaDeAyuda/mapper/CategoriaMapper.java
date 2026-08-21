/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.mapper;

import co.edu.sena.mesaDeAyuda.modelo.Categoria;
import co.edu.sena.mesaDeAyuda.dto.CategoriaDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author halfo
 */
public final class CategoriaMapper {

    private CategoriaMapper() {
        // Clase de utilidad, no se instancia
    }
    
    public static CategoriaDTO aDTO(Categoria categoria) {
        if (categoria == null) return null;
        
        return new CategoriaDTO(
            categoria.getId(),
            categoria.getNombre(),
            categoria.getDescripcion()
        );
    }
    
    public static List<CategoriaDTO> aDTO(List<Categoria> categorias) {
        if (categorias == null) return List.of();
        
        return categorias.stream()
                .map(CategoriaMapper::aDTO)
                .collect(Collectors.toList());
    }
    
    public static Categoria aEntidad(CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null) return null;
        
       
        if (categoriaDTO.getId() != null) {
            return new Categoria(
                categoriaDTO.getId(),
                categoriaDTO.getNombre(),
                categoriaDTO.getDescripcion(),
                categoriaDTO.getNombre() 
            );
        }
        
        return new Categoria(
            categoriaDTO.getNombre(),
            categoriaDTO.getDescripcion(),
            categoriaDTO.getNombre()  
        );
    }
}
