/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.modelo.OTP;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author halfo
 */
public class OTPRepositoryEnMemoria implements OTPRepository {

    private final Map<Long, OTP> datos = new ConcurrentHashMap<>();

    @Override
    public void guardar(OTP otp) {
        datos.put(otp.getTicketId(), otp);
    }

    @Override
    public Optional<OTP> buscarPorTicketId(Long ticketId) {
        return Optional.ofNullable(datos.get(ticketId));
    }

    @Override
    public Optional<OTP> buscarPorCodigo(String codigo) {
        return datos.values().stream()
                .filter(otp -> otp.getCodigo().equals(codigo))
                .findFirst();
    }

    @Override
    public void eliminar(Long ticketId) {
        datos.remove(ticketId);
    }

    @Override
    public void eliminarExpirados() {
        datos.values().removeIf(otp -> otp.estaExpirado());
    }
}

