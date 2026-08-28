package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.dto.EstadisticasDTO;
import co.edu.sena.mesaDeAyuda.mapper.UsuarioMapper;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;
import co.edu.sena.mesaDeAyuda.servicio.ReporteService;

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
@WebServlet(name = "reporteServlet", urlPatterns = {"/reportes"})
public class ReporteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = SesionUsuario.obtener(request);
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (!usuario.esAdmin()) {
            response.sendRedirect(request.getContextPath() + "/tickets");
            return;
        }

        ReporteService reporteService = (ReporteService) getServletContext()
                .getAttribute(AppContextListener.REPORTE_SERVICE);

        EstadisticasDTO estadisticas = reporteService.obtenerEstadisticas(usuario);

        request.setAttribute("estadisticas", estadisticas);
        request.setAttribute("usuario", UsuarioMapper.aDTO(usuario));

        request.getRequestDispatcher("/WEB-INF/jsp/reportes.jsp")
                .forward(request, response);
    }
}