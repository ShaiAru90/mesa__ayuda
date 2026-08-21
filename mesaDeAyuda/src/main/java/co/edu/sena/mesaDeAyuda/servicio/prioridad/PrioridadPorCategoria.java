/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.prioridad;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.modelo.Categoria;
import co.edu.sena.mesaDeAyuda.modelo.Prioridad;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author halfo
 */
public class PrioridadPorCategoria implements PrioridadStrategy {
    
    private final Map<String, Prioridad> prioridadPorCategoria = new ConcurrentHashMap<>();
    
    public PrioridadPorCategoria() {
        prioridadPorCategoria.put("Redes", Prioridad.ALTA);
        prioridadPorCategoria.put("Hardware", Prioridad.MEDIA);
        prioridadPorCategoria.put("Software", Prioridad.MEDIA);
        prioridadPorCategoria.put("Mantenimiento", Prioridad.BAJA);
        prioridadPorCategoria.put("Seguridad", Prioridad.CRITICA);
        prioridadPorCategoria.put("General", Prioridad.MEDIA);
    }
    
    @Override
    public Prioridad calcular(TicketDTO ticketDTO, Categoria categoria) {
        if (categoria == null) {
            return Prioridad.MEDIA;
        }
        
        return prioridadPorCategoria.getOrDefault(
            categoria.getNombre(),
            Prioridad.MEDIA
        );
    }
    
    public void configurarPrioridad(String categoria, Prioridad prioridad) {
        prioridadPorCategoria.put(categoria, prioridad);
    }
    
    @Override
    public String nombre() {
        return "Por Categoría";
    }
    
    @Override
    public String descripcion() {
        return "Asigna prioridad según la categoría del ticket.";
    }
}
