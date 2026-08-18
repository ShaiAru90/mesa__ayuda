/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.asignacion;

import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.SinAgentesDisponiblesException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsignacionPorCarga implements AsignacionStrategy {

    private final Map<Long, Integer> cargaPorAgente = new ConcurrentHashMap<>();

    @Override
    public Usuario asignar(List<Usuario> agentes, Ticket ticket) {

        if (agentes == null || agentes.isEmpty()) {
            throw new SinAgentesDisponiblesException();
        }

        Usuario agente = agentes.stream()
                .min(Comparator.comparingInt(
                        a -> cargaPorAgente.getOrDefault(a.getId(), 0)
                ))
                .orElseThrow(SinAgentesDisponiblesException::new);

        incrementarCarga(agente);

        return agente;
    }

    public void incrementarCarga(Usuario agente) {
        cargaPorAgente.merge(agente.getId(), 1, Integer::sum);
    }

    public void decrementarCarga(Usuario agente) {
        cargaPorAgente.computeIfPresent(agente.getId(), (k, v) -> v > 0 ? v - 1 : 0);
    }

    @Override
    public String nombre() {
        return "Por Menor Carga de Trabajo";
    }

    @Override
    public String descripcion() {
        return "Asigna al agente con menos tickets activos.";
    }
}
