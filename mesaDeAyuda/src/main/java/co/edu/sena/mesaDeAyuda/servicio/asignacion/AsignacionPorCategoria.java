/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.asignacion;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.SinAgentesDisponiblesException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsignacionPorCategoria implements AsignacionStrategy {

    private final Map<String, Long> especialistaPorCategoria = new ConcurrentHashMap<>();

    public AsignacionPorCategoria() {
        especialistaPorCategoria.put("Redes", 3L);
        especialistaPorCategoria.put("Hardware", 4L);
        especialistaPorCategoria.put("Software", 3L);
        especialistaPorCategoria.put("Mantenimiento", 4L);
        especialistaPorCategoria.put("Seguridad", 3L);
        especialistaPorCategoria.put("General", 3L);
    }

    @Override
    public Usuario asignar(List<Usuario> agentes, Ticket ticket) {
        if (agentes == null || agentes.isEmpty()) {
            throw new SinAgentesDisponiblesException();
        }

        String categoria = ticket.getCategoria().getNombre();
        Long agenteIdEspecialista = especialistaPorCategoria.get(categoria);

        if (agenteIdEspecialista != null) {
            return agentes.stream()
                    .filter(a -> a.getId().equals(agenteIdEspecialista))
                    .findFirst()
                    .orElseGet(() -> asignarPorDefecto(agentes));
        }

        return asignarPorDefecto(agentes);
    }

    private Usuario asignarPorDefecto(List<Usuario> agentes) {

        return agentes.get(0);
    }

    public void registrarEspecialista(String categoria, Long agenteId) {
        especialistaPorCategoria.put(categoria, agenteId);
    }

    @Override
    public String nombre() {
        return "Por Especialidad por Categoría";
    }

    @Override
    public String descripcion() {
        return "Asigna agentes según su especialidad en la categoría del ticket.";
    }
}
