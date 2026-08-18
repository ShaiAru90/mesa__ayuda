/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.repositorio.UsuarioRepository;
import co.edu.sena.mesaDeAyuda.servicio.asignacion.AsignacionStrategy;
import co.edu.sena.mesaDeAyuda.servicio.asignacion.SelectorAsignacion;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.SinAgentesDisponiblesException;

import java.util.List;

public class AsignacionServiceImpl implements AsignacionService {

    private final SelectorAsignacion selectorAsignacion;
    private final UsuarioRepository usuarioRepository;

    public AsignacionServiceImpl(SelectorAsignacion selectorAsignacion,
            UsuarioRepository usuarioRepository) {
        this.selectorAsignacion = selectorAsignacion;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario asignarAgente(Ticket ticket) {

        List<Usuario> agentes
                = usuarioRepository.buscarPorRol(Usuario.Rol.AGENTE);

        if (agentes.isEmpty()) {
            throw new SinAgentesDisponiblesException();
        }

        AsignacionStrategy estrategia = selectorAsignacion
                .resolver(SelectorAsignacion.POR_CARGA)
                .orElseThrow(() -> new IllegalStateException(
                "No hay estrategia de asignación por carga disponible"
        ));

        return estrategia.asignar(agentes, ticket);
    }

    @Override
    public Usuario reasignar(Ticket ticket, Usuario nuevoAgente, Usuario admin) {
        if (!admin.esAdmin()) {
            throw new AccesoDenegadoException("Solo un administrador puede reasignar tickets");
        }

        if (nuevoAgente == null || !nuevoAgente.esAgente()) {
            throw new IllegalArgumentException("El nuevo agente debe ser un agente válido");
        }

        return nuevoAgente;
    }

    @Override
    public List<Usuario> obtenerAgentesDisponibles() {
        return usuarioRepository.buscarPorRol(Usuario.Rol.AGENTE);
    }
}
