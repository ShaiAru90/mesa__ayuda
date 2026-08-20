package co.edu.sena.mesaDeAyuda.web;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.notificacion.NotificacionApp;
import co.edu.sena.mesaDeAyuda.web.AppContextListener;
import co.edu.sena.mesaDeAyuda.web.SesionUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author halfo
 */
@WebServlet(name = "notificacionServlet", urlPatterns = {"/notificaciones"})
public class NotificacionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        NotificacionApp notificacionApp = (NotificacionApp) getServletContext()
                .getAttribute(AppContextListener.NOTIFICACION_APP);

        if (notificacionApp == null) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        String accion = request.getParameter("accion");
        if ("marcarLeidas".equalsIgnoreCase(accion)) {
            notificacionApp.marcarComoLeidas(usuario);
            response.sendRedirect(request.getContextPath() + "/notificaciones");
            return;
        }

        List<String> listaNotificaciones = notificacionApp.obtenerListaNotificaciones(usuario);
        int total = notificacionApp.contarNoLeidas(usuario);

        request.setAttribute("listaNotificaciones", listaNotificaciones);
        request.setAttribute("total", total);
        request.setAttribute("usuario", usuario);

        request.getRequestDispatcher("/WEB-INF/jsp/notificaciones.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        NotificacionApp notificacionApp = (NotificacionApp) getServletContext()
                .getAttribute(AppContextListener.NOTIFICACION_APP);

        if (notificacionApp == null) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        String indexParam = request.getParameter("index");
        if (indexParam != null) {
            try {
                int index = Integer.parseInt(indexParam);
                notificacionApp.marcarComoLeida(usuario, index);
            } catch (NumberFormatException e) {
                // Ignorar
            }
        }

        response.sendRedirect(request.getContextPath() + "/notificaciones");
    }
}
