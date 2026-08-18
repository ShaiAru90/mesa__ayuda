/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo;

import java.math.BigDecimal;

/**
 *
 * @author halfo
 */
public enum Prioridad {
    BAJA("Baja", BigDecimal.valueOf(48), 1),      // 48 horas
    MEDIA("Media", BigDecimal.valueOf(24), 2),     // 24 horas
    ALTA("Alta", BigDecimal.valueOf(8), 3),        // 8 horas
    CRITICA("Crítica", BigDecimal.valueOf(2), 4);  // 2 horas
    
    private final String nombre;
    private final BigDecimal slaHoras;
    private final int nivel; // Para ordenar
    
    Prioridad(String nombre, BigDecimal slaHoras, int nivel) {
        this.nombre = nombre;
        this.slaHoras = slaHoras;
        this.nivel = nivel;
    }
    
    public String getNombre() { return nombre; }
    public BigDecimal getSlaHoras() { return slaHoras; }
    public int getNivel() { return nivel; }
    
    public String getSlaDescripcion() {
        if (slaHoras.compareTo(BigDecimal.valueOf(24)) >= 0) {
            long horas = slaHoras.longValue();
            if (horas == 24) return "1 día";
            if (horas == 48) return "2 días";
            return horas + " horas";
        }
        return slaHoras + " horas";
    }
    
    public boolean esMayorQue(Prioridad otra) {
        return this.nivel > otra.nivel;
    }
    
    public boolean esMenorQue(Prioridad otra) {
        return this.nivel < otra.nivel;
    }
    
    public static Prioridad desdeNivel(int nivel) {
        for (Prioridad p : values()) {
            if (p.nivel == nivel) return p;
        }
        return MEDIA; // Default
    }
    
    @Override
    public String toString() {
        return nombre + " (" + getSlaDescripcion() + ")";
    }
}
