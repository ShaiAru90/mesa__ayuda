/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.mapper;

import co.edu.sena.mesaDeAyuda.dto.UsuarioDTO;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author halfo
 */
public final class UsuarioMapper {

    private UsuarioMapper() {
        // Clase de utilidad, no se instancia
    }

    public static UsuarioDTO aDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol().name(),
                usuario.getRol().getNombre()
        );
    }

    public static List<UsuarioDTO> aDTO(List<Usuario> usuarios) {
        if (usuarios == null) {
            return List.of();
        }

        return usuarios.stream()
                .map(UsuarioMapper::aDTO)
                .collect(Collectors.toList());
    }

    public static Usuario aEntidad(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }

        return new Usuario(
                usuarioDTO.getId(),
                usuarioDTO.getNombre(),
                usuarioDTO.getCorreo(),
                null, // La contraseña no se incluye en el DTO por seguridad
                Usuario.Rol.valueOf(usuarioDTO.getRol())
        );
    }
}
