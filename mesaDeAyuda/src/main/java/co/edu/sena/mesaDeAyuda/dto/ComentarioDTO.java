/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.dto;

import co.edu.sena.mesaDeAyuda.modelo.Comentario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author halfo
 */
public class ComentarioDTO {
    
    private final Long id;
    private final Long autorId;
    private final String autorNombre;
    private final String autorRol;
    private final String texto;
    private final String fechaFormateada;
    private final boolean esInterno;
    private final LocalDateTime fecha;
    
    public ComentarioDTO(Long id, Long autorId, String autorNombre, String autorRol, 
                         String texto, LocalDateTime fecha, boolean esInterno) {
        this.id = id;
        this.autorId = autorId;
        this.autorNombre = autorNombre;
        this.autorRol = autorRol;
        this.texto = texto;
        this.fecha = fecha;
        this.fechaFormateada = formatearFecha(fecha);
        this.esInterno = esInterno;
    }
    
    // Getters
    public Long getId() { return id; }
    public Long getAutorId() { return autorId; }
    public String getAutorNombre() { return autorNombre; }
    public String getAutorRol() { return autorRol; }
    public String getTexto() { return texto; }
    public String getFechaFormateada() { return fechaFormateada; }
    public LocalDateTime getFecha() { return fecha; }
    public boolean isEsInterno() { return esInterno; }
    
    public String getAutorInfo() {
        return autorNombre + " (" + autorRol + ")";
    }
    
    private String formatearFecha(LocalDateTime fecha) {
        if (fecha == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fecha.format(formatter);
    }
    
    public static ComentarioDTO desdeModelo(Comentario comentario) {
        return new ComentarioDTO(
            comentario.getId(),
            comentario.getAutor().getId(),
            comentario.getAutor().getNombre(),
            comentario.getAutor().getRol().getNombre(),
            comentario.getTexto(),
            comentario.getFecha(),
            comentario.isEsInterno()
        );
    }
}
