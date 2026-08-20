/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.dto.UsuarioDTO;
import co.edu.sena.mesaDeAyuda.mapper.UsuarioMapper;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.TicketService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.TicketNoEncontradoException;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ticketDetailServlet", urlPatterns = {"/ticket"})
public class TicketDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        Long ticketId;
        try {
            ticketId = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        TicketService ticketService
                = (TicketService) getServletContext().getAttribute(AppContextListener.TICKET_SERVICE);

        try {
            TicketDTO ticket = ticketService.verDetalle(ticketId, usuario);

            request.setAttribute("ticket", ticket);
            request.setAttribute("usuario", UsuarioMapper.aDTO(usuario));

            request.setAttribute("puedeModificar", usuario.esAdmin()
                    || (usuario.esAgente() && ticket.getAgente() != null
                    && ticket.getAgente().getId().equals(usuario.getId())));

            request.setAttribute("puedeComentar", true);
            request.setAttribute("estadosDisponibles", getEstadosDisponibles(ticket));

            request.getRequestDispatcher("/WEB-INF/jsp/ticket-detail.jsp").forward(request, response);

        } catch (TicketNoEncontradoException e) {
            request.setAttribute("error", "Ticket no encontrado");
            request.getRequestDispatcher("/WEB-INF/jsp/ticket-detail.jsp").forward(request, response);

        } catch (AccesoDenegadoException e) {
            request.setAttribute("error", "No tienes permiso para ver este ticket");
            request.getRequestDispatcher("/WEB-INF/jsp/ticket-detail.jsp").forward(request, response);
        }
    }

    private String[] getEstadosDisponibles(TicketDTO ticket) {
        String estado = ticket.getEstado();
        return switch (estado) {
            case "NUEVO" ->
                new String[]{"asignar"};
            case "ASIGNADO" ->
                new String[]{"iniciar"};
            case "EN_PROCESO" ->
                new String[]{"resuelto"};
            case "RESUELTO" ->
                new String[]{"cerrado", "reabrir"};
            default ->
                new String[]{};
        };
    }
}
