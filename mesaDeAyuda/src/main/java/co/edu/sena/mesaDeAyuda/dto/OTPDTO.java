/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.dto;

/**
 *
 * @author halfo
 */
public class OTPDTO {
    private final Long ticketId;
    private final String codigo;
    private final String mensaje;

    public OTPDTO(Long ticketId, String codigo, String mensaje) {
        this.ticketId = ticketId;
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public Long getTicketId() { return ticketId; }
    public String getCodigo() { return codigo; }
    public String getMensaje() { return mensaje; }
}
