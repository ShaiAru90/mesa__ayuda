/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.asignacion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SelectorAsignacion {

    public static final String POR_TURNO = "POR_TURNO";
    public static final String POR_CARGA = "POR_CARGA";
    public static final String POR_CATEGORIA = "POR_CATEGORIA";

    private final Map<String, AsignacionStrategy> estrategias;

    public SelectorAsignacion(List<AsignacionStrategy> estrategias) {

        this.estrategias = new LinkedHashMap<>();

        for (AsignacionStrategy estrategia : estrategias) {

            if (estrategia instanceof AsignacionPorTurno) {
                this.estrategias.put(POR_TURNO, estrategia);
            }

            if (estrategia instanceof AsignacionPorCarga) {
                this.estrategias.put(POR_CARGA, estrategia);
            }

            if (estrategia instanceof AsignacionPorCategoria) {
                this.estrategias.put(POR_CATEGORIA, estrategia);
            }
        }
    }

    public Optional<AsignacionStrategy> resolver(String clave) {
        return Optional.ofNullable(estrategias.get(clave));
    }

    public List<AsignacionStrategy> disponibles() {
        return List.copyOf(estrategias.values());
    }
}
