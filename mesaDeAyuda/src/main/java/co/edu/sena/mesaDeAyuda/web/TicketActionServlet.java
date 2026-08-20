package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.TicketService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AccesoDenegadoException;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.TransicionEstadoInvalidaException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ticketActionServlet", urlPatterns = {"/ticket/accion"})
public class TicketActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("✅ [TicketActionServlet] INICIALIZADO");
        System.out.println("   URL: /ticket/accion");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("🔍 [TicketActionServlet] doPost() EJECUTADO");
        
        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            System.out.println("⚠️ Usuario no autenticado");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String ticketIdParam = request.getParameter("ticketId");
        String accion = request.getParameter("accion");
        String comentario = request.getParameter("comentario");
        
        System.out.println("  → ticketId: " + ticketIdParam);
        System.out.println("  → accion: " + accion);
        System.out.println("  → usuario: " + usuario.getNombre() + " (" + usuario.getRol().getNombre() + ")");
        
        if (ticketIdParam == null || accion == null) {
            System.out.println("⚠️ ticketId o accion son null");
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }
        
        Long ticketId;
        try {
            ticketId = Long.parseLong(ticketIdParam);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ ticketId no es un número válido");
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }
        
        TicketService ticketService = 
            (TicketService) getServletContext().getAttribute(AppContextListener.TICKET_SERVICE);
        
        try {
            // Procesar comentario
            if (comentario != null && !comentario.trim().isEmpty()) {
                if ("comentar-interno".equalsIgnoreCase(accion)) {
                    System.out.println("  → Agregando comentario INTERNO");
                    ticketService.agregarComentarioInterno(ticketId, comentario, usuario);
                } else if ("comentar".equalsIgnoreCase(accion)) {
                    System.out.println("  → Agregando comentario");
                    ticketService.agregarComentario(ticketId, comentario, usuario);
                }
            }
            
            // Procesar acción de estado
            if (!"comentar".equalsIgnoreCase(accion) && !"comentar-interno".equalsIgnoreCase(accion)) {
                System.out.println("  → Cambiando estado con acción: " + accion);
                ticketService.cambiarEstado(ticketId, accion, usuario);
            }
            
            response.sendRedirect(request.getContextPath() + "/ticket?id=" + ticketId);
            
        } catch (TransicionEstadoInvalidaException e) {
            System.out.println("❌ Transición inválida: " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId).forward(request, response);
            
        } catch (AccesoDenegadoException e) {
            System.out.println("❌ Acceso denegado: " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId).forward(request, response);
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la acción: " + e.getMessage());
            request.getRequestDispatcher("/ticket?id=" + ticketId).forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("⚠️ [TicketActionServlet] Acceso por GET - redirigiendo");
        String ticketId = request.getParameter("ticketId");
        if (ticketId != null && !ticketId.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/ticket?id=" + ticketId);
        } else {
            response.sendRedirect(request.getContextPath() + "/tickets");
        }
    }
}