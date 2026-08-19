/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.dto.UsuarioDTO;
import co.edu.sena.mesaDeAyuda.mapper.UsuarioMapper;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.AuthService;
import co.edu.sena.mesaDeAyuda.servicio.excepcion.AutenticacionFallidaException;

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
@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (SesionUsuario.estaAutenticado(request)) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        AuthService authService
                = (AuthService) getServletContext().getAttribute(AppContextListener.AUTH_SERVICE);

        try {
            Usuario usuario = authService.autenticar(correo, password);
            SesionUsuario.guardar(request, usuario);

            // sweet alert
            request.getSession().setAttribute("swal_titulo", "¡Bienvenido!");
            request.getSession().setAttribute("swal_texto", "Hola de nuevo, " + usuario.getNombre());
            request.getSession().setAttribute("swal_icono", "success");

            if (usuario.esAdmin()) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/tickets");
            }

        } catch (AutenticacionFallidaException e) {
            String swalError = "<script>"
                    + "Swal.fire({ title: 'Acceso Denegado', text: 'Las credenciales ingresadas son incorrectas.', icon: 'error', confirmButtonText: 'Aceptar', confirmButtonColor: '#dc3545' });"
                    + "</script>";
            request.setAttribute("alerta", swalError);

            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            String swalInesperado = "<script>"
                    + "Swal.fire({ title: 'Error Inesperado', text: 'Error: " + e.getMessage() + "', icon: 'error', confirmButtonText: 'Aceptar', confirmButtonColor: '#dc3545' });"
                    + "</script>";
            request.setAttribute("alerta", swalInesperado);

            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
