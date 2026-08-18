/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.dto;

import co.edu.sena.mesaDeAyuda.modelo.Prioridad;

/**
 *
 * @author halfo
 */
public class PrioridadDTO {
    
    private final String nombre;
    private final String slaDescripcion;
    private final int nivel;
    private final String claseCss; // Para estilos en la vista
    
    public PrioridadDTO(String nombre, String slaDescripcion, int nivel) {
        this.nombre = nombre;
        this.slaDescripcion = slaDescripcion;
        this.nivel = nivel;
        this.claseCss = calcularClaseCss();
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public String getSlaDescripcion() { return slaDescripcion; }
    public int getNivel() { return nivel; }
    public String getClaseCss() { return claseCss; }
    
    private String calcularClaseCss() {
        return switch (nombre.toUpperCase()) {
            case "CRÍTICA" -> "prioridad-critica";
            case "ALTA" -> "prioridad-alta";
            case "MEDIA" -> "prioridad-media";
            case "BAJA" -> "prioridad-baja";
            default -> "prioridad-media";
        };
    }
    
    public String getIcono() {
        return switch (nombre.toUpperCase()) {
            case "CRÍTICA" -> "🔴";
            case "ALTA" -> "🟠";
            case "MEDIA" -> "🟡";
            case "BAJA" -> "🟢";
            default -> "⚪";
        };
    }
    
    public static PrioridadDTO desdeModelo(Prioridad prioridad) {
        return new PrioridadDTO(
            prioridad.getNombre(),
            prioridad.getSlaDescripcion(),
            prioridad.getNivel()
        );
    }
}
