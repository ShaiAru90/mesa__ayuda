/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.OTPService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.TicketNoEncontradoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author halfo
 */
@WebServlet(name = "otpServlet", urlPatterns = {"/otp"})
public class OTPServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String accion = request.getParameter("accion");
        String ticketIdParam = request.getParameter("ticketId");

        if (ticketIdParam == null || ticketIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        Long ticketId;
        try {
            ticketId = Long.parseLong(ticketIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        OTPService otpService = (OTPService) getServletContext()
                .getAttribute(AppContextListener.OTP_SERVICE);

        try {
            if ("generar".equalsIgnoreCase(accion)) {
                // Generar nuevo OTP
                otpService.generarOTP(ticketId, usuario);
                request.setAttribute("mensaje", "✅ Código OTP enviado al correo del solicitante.");
                
            } else if ("reenviar".equalsIgnoreCase(accion)) {
                // Reenviar OTP existente
                otpService.reenviarOTP(ticketId, usuario);
                request.setAttribute("mensaje", "✅ Código OTP reenviado al correo del solicitante.");
                
            } else if ("validar".equalsIgnoreCase(accion)) {
                // Validar OTP para cerrar ticket
                String codigoOTP = request.getParameter("codigoOTP");
                if (codigoOTP == null || codigoOTP.isEmpty()) {
                    request.setAttribute("error", "❌ Debes ingresar el código OTP.");
                    request.getRequestDispatcher("/ticket?id=" + ticketId)
                            .forward(request, response);
                    return;
                }
                
                boolean valido = otpService.validarOTP(codigoOTP, ticketId, usuario);
                if (valido) {
                    request.setAttribute("mensaje", "✅ OTP válido. El ticket se ha cerrado correctamente.");
                }
            }
            
            // Redirigir al detalle del ticket
            response.sendRedirect(request.getContextPath() + "/ticket?id=" + ticketId);

        } catch (TicketNoEncontradoException e) {
            request.setAttribute("error", "❌ Ticket no encontrado.");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp")
                    .forward(request, response);
            
        } catch (AccesoDenegadoException e) {
            request.setAttribute("error", "❌ " + e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId)
                    .forward(request, response);
            
        } catch (IllegalStateException e) {
            request.setAttribute("error", "❌ " + e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId)
                    .forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Error: " + e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId)
                    .forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si alguien intenta acceder por GET, redirigir
        response.sendRedirect(request.getContextPath() + "/tickets");
    }
}
