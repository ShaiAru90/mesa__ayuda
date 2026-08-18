/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.dto.TicketDTO;
import co.edu.sena.mesaDeAyuda.dto.CategoriaDTO;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.TicketService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.SinAgentesDisponiblesException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author halfo
 */
@WebServlet(name = "ticketCreateServlet", urlPatterns = {"/crear-ticket"})
public class TicketCreateServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/WEB-INF/jsp/crear-ticket.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String categoriaNombre = request.getParameter("categoria");
        
        // Crear DTO - ✅ CORREGIDO: No pasar null en estado
        CategoriaDTO categoria = new CategoriaDTO(null, categoriaNombre, "");
        
        TicketDTO ticketDTO = new TicketDTO(
            null,                           // id
            titulo,                         // titulo
            descripcion,                    // descripcion
            categoria,                      // categoria
            null,                           // prioridad (se calculará)
            null,                           // solicitante (se asignará)
            null,                           // agente (se asignará)
            null,                           // estado (se calculará) ← AHORA ES NULL
            null,                           // estadoDescripcion
            null,                           // fechaCreacion
            null,                           // fechaActualizacion
            null,                           // fechaResolucion
            new ArrayList<>()               // comentarios
        );
        
        TicketService ticketService = 
            (TicketService) getServletContext().getAttribute(AppContextListener.TICKET_SERVICE);
        
        try {
            ticketService.crearTicket(ticketDTO, usuario);
            response.sendRedirect(request.getContextPath() + "/tickets?accion=creado");
            
        } catch (SinAgentesDisponiblesException e) {
            request.setAttribute("error", "No hay agentes disponibles. El ticket será asignado cuando haya agentes.");
            request.setAttribute("titulo", titulo);
            request.setAttribute("descripcion", descripcion);
            request.getRequestDispatcher("/WEB-INF/jsp/crear-ticket.jsp").forward(request, response);
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("titulo", titulo);
            request.setAttribute("descripcion", descripcion);
            request.getRequestDispatcher("/WEB-INF/jsp/crear-ticket.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al crear el ticket: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/crear-ticket.jsp").forward(request, response);
        }
    }
}