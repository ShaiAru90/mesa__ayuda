/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.dto.UsuarioDTO;
import co.edu.sena.mesaDeAyuda.mapper.TicketMapper;
import co.edu.sena.mesaDeAyuda.mapper.UsuarioMapper;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.TicketService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author halfo
 */
@WebServlet(name = "ticketServlet", urlPatterns = {"/tickets"})
public class TicketServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Usuario usuario = SesionUsuario.obtener(request);
        
        // Verificar autenticación
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String estado = request.getParameter("estado");
        String prioridad = request.getParameter("prioridad");
        String accion = request.getParameter("accion");
        
        TicketService ticketService = 
            (TicketService) getServletContext().getAttribute(AppContextListener.TICKET_SERVICE);
        
        try {
            List<TicketDTO> tickets;
            
            // Filtros
            if (estado != null && !estado.isEmpty()) {
                tickets = ticketService.buscarPorEstado(estado, usuario);
            } else if (prioridad != null && !prioridad.isEmpty()) {
                tickets = ticketService.buscarPorPrioridad(prioridad, usuario);
            } else {
                tickets = ticketService.listarTickets(usuario);
            }
            
            request.setAttribute("tickets", tickets);
            request.setAttribute("usuario", UsuarioMapper.aDTO(usuario));
            request.setAttribute("esAdmin", usuario.esAdmin());
            request.setAttribute("esAgente", usuario.esAgente());
            
            // Mensaje de éxito si existe
            if (accion != null) {
                request.setAttribute("mensajeExito", switch (accion) {
                    case "creado" -> "Ticket creado exitosamente";
                    case "actualizado" -> "Ticket actualizado exitosamente";
                    default -> null;
                });
            }
            
            request.getRequestDispatcher("/WEB-INF/jsp/tickets.jsp").forward(request, response);
            
        } catch (AccesoDenegadoException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/tickets.jsp").forward(request, response);
        }
    }
}
