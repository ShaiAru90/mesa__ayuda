/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.mapper;

import co.edu.sena.mesaDeAyuda.modelo.Comentario;
import co.edu.sena.mesaDeAyuda.dto.ComentarioDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author halfo
 */
public final class ComentarioMapper {

    private ComentarioMapper() {
        // Clase de utilidad, no se instancia
    }

    public static ComentarioDTO aDTO(Comentario comentario) {
        if (comentario == null) {
            return null;
        }

        return new ComentarioDTO(
                comentario.getId(),
                comentario.getAutor().getId(),
                comentario.getAutor().getNombre(),
                comentario.getAutor().getRol().getNombre(),
                comentario.getTexto(),
                comentario.getFecha(),
                comentario.isEsInterno()
        );
    }

    public static List<ComentarioDTO> aDTO(List<Comentario> comentarios) {
        if (comentarios == null) {
            return List.of();
        }

        return comentarios.stream()
                .map(ComentarioMapper::aDTO)
                .collect(Collectors.toList());
    }
}
