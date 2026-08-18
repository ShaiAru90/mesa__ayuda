/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.TicketService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.TransicionEstadoInvalidaException;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ticketActionServlet", urlPatterns = {"/ticket/accion"})
public class TicketActionServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String ticketIdParam = request.getParameter("ticketId");
        String accion = request.getParameter("accion");
        String comentario = request.getParameter("comentario");
        
        if (ticketIdParam == null || accion == null) {
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
        
        TicketService ticketService = 
            (TicketService) getServletContext().getAttribute(AppContextListener.TICKET_SERVICE);
        
        try {
            // Si hay comentario, agregarlo primero
            if (comentario != null && !comentario.trim().isEmpty()) {
                if (usuario.esAdmin() || usuario.esAgente()) {
                    ticketService.agregarComentario(ticketId, comentario, usuario);
                } else {
                    ticketService.agregarComentario(ticketId, comentario, usuario);
                }
            }
            
            // Si hay acción de estado, ejecutarla
            if (!"comentar".equals(accion)) {
                ticketService.cambiarEstado(ticketId, accion, usuario);
            }
            
            // Redirigir al detalle del ticket
            response.sendRedirect(request.getContextPath() + "/ticket?id=" + ticketId);
            
        } catch (TransicionEstadoInvalidaException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId).forward(request, response);
            
        } catch (AccesoDenegadoException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId).forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al procesar la acción: " + e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId).forward(request, response);
        }
    }
}
