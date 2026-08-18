/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio.prioridad;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.modelo.Categoria;
import co.edu.sena.mesaDeAyuda.modelo.Prioridad;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PrioridadPorPalabras implements PrioridadStrategy {
    
    private final Map<Prioridad, List<String>> palabrasClave = new ConcurrentHashMap<>();
    
    public PrioridadPorPalabras() {
        palabrasClave.put(Prioridad.CRITICA, List.of(
            "urgente", "crítico", "crítica", "emergencia", 
            "sistema caído", "producción", "pago"
        ));
        palabrasClave.put(Prioridad.ALTA, List.of(
            "grave", "importante", "bloqueado", "no funciona",
            "error crítico", "detenido"
        ));
        palabrasClave.put(Prioridad.MEDIA, List.of(
            "falla", "problema", "error", "lento", "demora"
        ));
    }
    
    @Override
    public Prioridad calcular(TicketDTO ticketDTO, Categoria categoria) {
        String texto = (ticketDTO.getTitulo() + " " + ticketDTO.getDescripcion()).toLowerCase();
        
        if (contienePalabraClave(texto, Prioridad.CRITICA)) {
            return Prioridad.CRITICA;
        }
        if (contienePalabraClave(texto, Prioridad.ALTA)) {
            return Prioridad.ALTA;
        }
        if (contienePalabraClave(texto, Prioridad.MEDIA)) {
            return Prioridad.MEDIA;
        }
        
        return Prioridad.BAJA;
    }
    
    private boolean contienePalabraClave(String texto, Prioridad prioridad) {
        List<String> palabras = palabrasClave.get(prioridad);
        if (palabras == null) return false;
        
        return palabras.stream().anyMatch(texto::contains);
    }
    
    public void agregarPalabraClave(Prioridad prioridad, String palabra) {
        palabrasClave.computeIfAbsent(prioridad, k -> List.of());
        
    }
    
    @Override
    public String nombre() {
        return "Por Palabras Clave";
    }
    
    @Override
    public String descripcion() {
        return "Analiza el título y descripción en busca de palabras clave.";
    }
}
