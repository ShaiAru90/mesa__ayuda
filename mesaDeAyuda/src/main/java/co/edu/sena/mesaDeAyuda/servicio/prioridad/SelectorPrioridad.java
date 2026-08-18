/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.prioridad;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SelectorPrioridad {

    public static final String POR_CATEGORIA = "POR_CATEGORIA";
    public static final String POR_PALABRAS = "POR_PALABRAS";

    private final Map<String, PrioridadStrategy> estrategias;

    public SelectorPrioridad(List<PrioridadStrategy> estrategias) {

        this.estrategias = new LinkedHashMap<>();

        for (PrioridadStrategy estrategia : estrategias) {

            if (estrategia instanceof PrioridadPorCategoria) {
                this.estrategias.put(POR_CATEGORIA, estrategia);
            }

            if (estrategia instanceof PrioridadPorPalabras) {
                this.estrategias.put(POR_PALABRAS, estrategia);
            }
        }
    }

    public Optional<PrioridadStrategy> resolver(String clave) {
        return Optional.ofNullable(estrategias.get(clave));
    }

    public List<PrioridadStrategy> disponibles() {
        return List.copyOf(estrategias.values());
    }
}
