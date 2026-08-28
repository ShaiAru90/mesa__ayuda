/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.dto.OTPDTO;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;

/**
 *
 * @author halfo
 */
public interface OTPService {

    OTPDTO generarOTP(Long ticketId, Usuario solicitante);

    boolean validarOTP(String codigo, Long ticketId, Usuario usuario);

    void reenviarOTP(Long ticketId, Usuario usuario);
}
