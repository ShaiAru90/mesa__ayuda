/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author halfo
 */
public class OTP {
    private final String codigo;
    private final Long ticketId;
    private final Long usuarioId;
    private final LocalDateTime fechaCreacion;
    private final LocalDateTime fechaExpiracion;
    private int intentosFallidos;
    private boolean usado;

    public OTP(String codigo, Long ticketId, Long usuarioId, int minutosValidez) {
        this.codigo = Objects.requireNonNull(codigo, "El código es obligatorio");
        this.ticketId = Objects.requireNonNull(ticketId, "El ticketId es obligatorio");
        this.usuarioId = Objects.requireNonNull(usuarioId, "El usuarioId es obligatorio");
        this.fechaCreacion = LocalDateTime.now();
        this.fechaExpiracion = this.fechaCreacion.plusMinutes(minutosValidez);
        this.intentosFallidos = 0;
        this.usado = false;
    }

    public String getCodigo() { return codigo; }
    public Long getTicketId() { return ticketId; }
    public Long getUsuarioId() { return usuarioId; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaExpiracion() { return fechaExpiracion; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isUsado() { return usado; }

    public void incrementarIntentos() {
        this.intentosFallidos++;
    }

    public void marcarComoUsado() {
        this.usado = true;
    }

    public boolean estaExpirado() {
        return LocalDateTime.now().isAfter(fechaExpiracion);
    }

    public boolean estaBloqueado() {
        return intentosFallidos >= 3;
    }

    public boolean esValido(String codigoIngresado) {
        return !estaExpirado() && !estaBloqueado() && !usado && this.codigo.equals(codigoIngresado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OTP)) return false;
        OTP otp = (OTP) o;
        return Objects.equals(codigo, otp.codigo) && Objects.equals(ticketId, otp.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, ticketId);
    }
}