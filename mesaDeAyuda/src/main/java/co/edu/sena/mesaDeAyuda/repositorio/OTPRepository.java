package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.modelo.OTP;

import java.util.Optional;

/**
 *
 * @author halfo
 */
public interface OTPRepository {
    void guardar(OTP otp);
    Optional<OTP> buscarPorTicketId(Long ticketId);
    Optional<OTP> buscarPorCodigo(String codigo);
    void eliminar(Long ticketId);
    void eliminarExpirados();
}
