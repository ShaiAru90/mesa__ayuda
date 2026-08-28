/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.dto.OTPDTO;
import co.edu.sena.mesaDeAyuda.modelo.OTP;
import co.edu.sena.mesaDeAyuda.modelo.Ticket;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.repositorio.OTPRepository;
import co.edu.sena.mesaDeAyuda.repositorio.TicketRepository;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.TicketNoEncontradoException;
import co.edu.sena.mesaDeAyuda.servicio.notificacion.NotificacionStrategy;

import java.security.SecureRandom;
import java.util.List;

/**
 *
 * @author halfo
 */
public class OTPServiceImpl implements OTPService {

    private static final int LONGITUD_OTP = 6;
    private static final int MINUTOS_VALIDEZ = 15;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final OTPRepository otpRepository;
    private final TicketRepository ticketRepository;
    private final List<NotificacionStrategy> notificadores;

    public OTPServiceImpl(OTPRepository otpRepository, TicketRepository ticketRepository,
            List<NotificacionStrategy> notificadores) {
        this.otpRepository = otpRepository;
        this.ticketRepository = ticketRepository;
        this.notificadores = notificadores;
    }

    @Override
    public OTPDTO generarOTP(Long ticketId, Usuario solicitante) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("🔐 INICIANDO GENERACIÓN DE OTP");
        System.out.println("═══════════════════════════════════════");
        System.out.println("  Ticket ID: " + ticketId);
        System.out.println("  Solicitante: " + solicitante.getNombre() + " (" + solicitante.getCorreo() + ")");

        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        if (!ticket.puedeVer(solicitante)) {
            throw new AccesoDenegadoException("No tienes permiso para este ticket");
        }

        if (!ticket.estaResuelto()) {
            System.err.println("❌ El ticket NO está en estado RESUELTO. Estado actual: " + ticket.getEstadoNombre());
            throw new IllegalStateException("El ticket debe estar en estado RESUELTO para generar OTP");
        }

        System.out.println("✅ Ticket está en estado RESUELTO");

        // Eliminar OTP anterior si existe
        otpRepository.eliminar(ticketId);

        // Generar nuevo OTP
        String codigo = generarCodigo();
        System.out.println("  Código generado: " + codigo);

        OTP otp = new OTP(codigo, ticketId, solicitante.getId(), MINUTOS_VALIDEZ);
        otpRepository.guardar(otp);
        System.out.println("✅ OTP guardado en repositorio");

        // Enviar OTP por correo
        try {
            enviarOTPPorCorreo(solicitante, ticket, codigo);
            System.out.println("✅ OTP enviado por correo");
        } catch (Exception e) {
            System.err.println("❌ Error al enviar OTP por correo: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("═══════════════════════════════════════");

        return new OTPDTO(ticketId, codigo, "OTP enviado al correo del solicitante");
    }

    @Override
    public boolean validarOTP(String codigo, Long ticketId, Usuario usuario) {
        OTP otp = otpRepository.buscarPorTicketId(ticketId)
                .orElseThrow(() -> new IllegalStateException("No hay OTP activo para este ticket"));

        // Validar que el usuario sea el solicitante
        if (!otp.getUsuarioId().equals(usuario.getId())) {
            throw new AccesoDenegadoException("Este OTP no pertenece a tu usuario");
        }

        // Validar expiración
        if (otp.estaExpirado()) {
            otpRepository.eliminar(ticketId);
            throw new IllegalStateException("El OTP ha expirado. Solicita uno nuevo.");
        }

        // Validar bloqueo
        if (otp.estaBloqueado()) {
            otpRepository.eliminar(ticketId);
            throw new IllegalStateException("Demasiados intentos fallidos. Solicita un nuevo OTP.");
        }

        // Validar código
        if (otp.esValido(codigo)) {
            otp.marcarComoUsado();
            otpRepository.guardar(otp);
            return true;
        } else {
            otp.incrementarIntentos();
            otpRepository.guardar(otp);

            int intentosRestantes = 3 - otp.getIntentosFallidos();
            throw new IllegalStateException("Código incorrecto. Te quedan " + intentosRestantes + " intentos.");
        }
    }

    @Override
    public void reenviarOTP(Long ticketId, Usuario usuario) {
        Ticket ticket = ticketRepository.buscarPorId(ticketId)
                .orElseThrow(() -> new TicketNoEncontradoException(ticketId));

        if (!ticket.puedeVer(usuario)) {
            throw new AccesoDenegadoException("No tienes permiso para este ticket");
        }

        OTP otp = otpRepository.buscarPorTicketId(ticketId)
                .orElseThrow(() -> new IllegalStateException("No hay OTP activo para este ticket"));

        if (!otp.getUsuarioId().equals(usuario.getId())) {
            throw new AccesoDenegadoException("Este OTP no pertenece a tu usuario");
        }

        if (otp.estaBloqueado() || otp.estaExpirado()) {
            // Generar uno nuevo
            generarOTP(ticketId, usuario);
            return;
        }

        // Reenviar el mismo OTP
        enviarOTPPorCorreo(usuario, ticket, otp.getCodigo());
    }

    private String generarCodigo() {
        StringBuilder codigo = new StringBuilder(LONGITUD_OTP);
        for (int i = 0; i < LONGITUD_OTP; i++) {
            codigo.append(RANDOM.nextInt(10));
        }
        return codigo.toString();
    }

    private void enviarOTPPorCorreo(Usuario solicitante, Ticket ticket, String codigo) {
        String asunto = "🔐 Código OTP para cerrar ticket #" + ticket.getId();
        String mensaje = String.format(
                "Hola %s,\n\n"
                + "Has solicitado cerrar el ticket #%d: '%s'.\n\n"
                + "Tu código de verificación es: %s\n\n"
                + "Este código es válido por %d minutos.\n"
                + "Tienes 3 intentos antes de que el código se bloquee.\n\n"
                + "Ingresa este código en el sistema para confirmar el cierre.\n\n"
                + "Si no solicitaste este cierre, ignora este mensaje.\n\n"
                + "---\n"
                + "Mesa de Ayuda CIMM - SENA Regional Boyacá",
                solicitante.getNombre(),
                ticket.getId(),
                ticket.getTitulo(),
                codigo,
                MINUTOS_VALIDEZ
        );

        // Enviar por todos los canales de notificación disponibles
        for (NotificacionStrategy notificador : notificadores) {
            try {
                if (notificador.nombre().contains("Correo")) {
                    notificador.notificar(solicitante, asunto, mensaje);
                }
            } catch (Exception e) {
                System.err.println("Error enviando OTP por " + notificador.nombre() + ": " + e.getMessage());
            }
        }
    }
}
