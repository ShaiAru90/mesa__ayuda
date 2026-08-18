/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.notificacion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 *
 * @author halfo
 */
public class SelectorNotificacion {
    
    private final Map<String, NotificacionStrategy> estrategias = new LinkedHashMap<>();
    
    public SelectorNotificacion(List<NotificacionStrategy> estrategias) {
        for (NotificacionStrategy estrategia : estrategias) {
            this.estrategias.put(estrategia.getClass().getSimpleName(), estrategia);
        }
        // Estrategia por defecto (Correo)
        this.estrategias.put("DEFAULT", new NotificacionCorreo());
    }
    
    public Optional<NotificacionStrategy> resolver(String clave) {
        return Optional.ofNullable(estrategias.get(clave));
    }
    
    public List<NotificacionStrategy> disponibles() {
        return List.copyOf(estrategias.values());
    }
}
