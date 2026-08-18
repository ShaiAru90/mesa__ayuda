/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.dto.UsuarioDTO;
import co.edu.sena.mesaDeAyuda.mapper.UsuarioMapper;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.AsignacionService;
import co.edu.sena.mesaDeAyuda.servicio.TicketService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.TicketNoEncontradoException;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "adminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = SesionUsuario.obtener(request);

        if (usuario == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        if (!usuario.esAdmin()) {
            response.sendRedirect(
                    request.getContextPath() + "/tickets"
            );
            return;
        }

        cargarDashboard(request, response, usuario);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = SesionUsuario.obtener(request);

        if (usuario == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        if (!usuario.esAdmin()) {
            response.sendRedirect(
                    request.getContextPath() + "/tickets"
            );
            return;
        }

        String accion = request.getParameter("accion");

        if (!"reasignar".equalsIgnoreCase(accion)) {
            response.sendRedirect(
                    request.getContextPath() + "/admin"
            );
            return;
        }

        String ticketIdParam
                = request.getParameter("ticketId");

        String agenteIdParam
                = request.getParameter("agenteId");

        if (ticketIdParam == null
                || agenteIdParam == null) {

            request.setAttribute(
                    "error",
                    "Debes seleccionar un ticket y un agente"
            );

            cargarDashboard(request, response, usuario);
            return;
        }

        Long ticketId;
        Long agenteId;

        try {

            ticketId = Long.parseLong(ticketIdParam);
            agenteId = Long.parseLong(agenteIdParam);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Los identificadores del ticket y del agente no son válidos"
            );

            cargarDashboard(request, response, usuario);
            return;
        }

        TicketService ticketService
                = (TicketService) getServletContext()
                        .getAttribute(
                                AppContextListener.TICKET_SERVICE
                        );

        try {

            ticketService.reasignarAgente(
                    ticketId,
                    agenteId,
                    usuario
            );

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin?mensaje=reasignado"
            );

        } catch (AccesoDenegadoException
                | TicketNoEncontradoException
                | IllegalArgumentException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            cargarDashboard(
                    request,
                    response,
                    usuario
            );
        }
    }

    private void cargarDashboard(
            HttpServletRequest request,
            HttpServletResponse response,
            Usuario usuario)
            throws ServletException, IOException {

        TicketService ticketService
                = (TicketService) getServletContext()
                        .getAttribute(
                                AppContextListener.TICKET_SERVICE
                        );

        AsignacionService asignacionService
                = (AsignacionService) getServletContext()
                        .getAttribute(
                                AppContextListener.ASIGNACION_SERVICE
                        );

        String filtro = request.getParameter("filtro");

        List<TicketDTO> tickets;

        if ("activos".equals(filtro)) {

            tickets = ticketService.buscarPorEstado(
                    "NUEVO",
                    usuario
            );

            tickets.addAll(
                    ticketService.buscarPorEstado(
                            "ASIGNADO",
                            usuario
                    )
            );

            tickets.addAll(
                    ticketService.buscarPorEstado(
                            "EN_PROCESO",
                            usuario
                    )
            );

        } else {

            tickets
                    = ticketService.listarTodosTickets(usuario);
        }

        List<UsuarioDTO> agentes
                = UsuarioMapper.aDTO(
                        asignacionService
                                .obtenerAgentesDisponibles()
                );

        request.setAttribute(
                "tickets",
                tickets
        );

        request.setAttribute(
                "usuario",
                UsuarioMapper.aDTO(usuario)
        );

        request.setAttribute(
                "agentes",
                agentes
        );

        request.setAttribute(
                "totalTickets",
                tickets.size()
        );

        request.setAttribute(
                "ticketsActivos",
                ticketService
                        .buscarPorEstado(
                                "EN_PROCESO",
                                usuario
                        )
                        .size()
        );

        request.getRequestDispatcher(
                "/WEB-INF/jsp/admin/dashboard.jsp"
        ).forward(
                request,
                response
        );
    }
}
