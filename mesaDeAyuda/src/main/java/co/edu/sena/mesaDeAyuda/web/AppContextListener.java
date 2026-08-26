/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.repositorio.*;
import co.edu.sena.mesaDeAyuda.servicio.*;
import co.edu.sena.mesaDeAyuda.servicio.asignacion.*;
import co.edu.sena.mesaDeAyuda.servicio.notificacion.*;
import co.edu.sena.mesaDeAyuda.servicio.prioridad.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class AppContextListener implements ServletContextListener {

    public static final String TICKET_SERVICE = "ticketService";
    public static final String ASIGNACION_SERVICE = "asignacionService";
    public static final String AUTH_SERVICE = "authService";
    public static final String SELECTOR_ASIGNACION = "selectorAsignacion";
    public static final String SELECTOR_PRIORIDAD = "selectorPrioridad";
    public static final String SELECTOR_NOTIFICACION = "selectorNotificacion";
    public static final String CATEGORIA_REPOSITORY = "categoriaRepository";
    public static final String USUARIO_REPOSITORY = "usuarioRepository";
    public static final String TICKET_REPOSITORY = "ticketRepository";
    public static final String NOTIFICACION_APP = "notificacionApp";

    @Override
    public void contextInitialized(ServletContextEvent evento) {
        ServletContext contexto = evento.getServletContext();

        UsuarioRepository usuarioRepository = new UsuarioRepositoryEnMemoria();
        TicketRepository ticketRepository = new TicketRepositoryEnMemoria();
        CategoriaRepository categoriaRepository = new CategoriaRepositoryEnMemoria();
        NotificacionApp notificacionApp = new NotificacionApp();

        NotificacionCorreoReal notificacionCorreoReal = null;
        try {
            // 🔹 INTENTAR CON VARIABLES DE ENTORNO (RECOMENDADO)
            String correoEmisor = System.getenv("mesiasgoat777@gmail.com");
            String passwordEmisor = System.getenv("qousnbnvolyairrq");

           
            if (correoEmisor == null || correoEmisor.isEmpty() || passwordEmisor == null || passwordEmisor.isEmpty()) {
                
                correoEmisor = "tu_correo@gmail.com";
                passwordEmisor = "tu_contraseña_app";
                System.out.println("⚠️ [CorreoReal] Usando credenciales directas (solo pruebas)");
            }

            if (correoEmisor != null && !correoEmisor.isEmpty()) {
                notificacionCorreoReal = new NotificacionCorreoReal(correoEmisor, passwordEmisor);
                System.out.println("✅ [CorreoReal] Configurado con: " + correoEmisor);
            } else {
                System.out.println("⚠️ [CorreoReal] No se encontraron credenciales");
                notificacionCorreoReal = new NotificacionCorreoReal("test@test.com", "password");
            }
        } catch (Exception e) {
            System.err.println("❌ [CorreoReal] Error al configurar: " + e.getMessage());
            notificacionCorreoReal = new NotificacionCorreoReal("test@test.com", "password");
        }

        SelectorAsignacion selectorAsignacion = new SelectorAsignacion(List.of(
                new AsignacionPorTurno(),
                new AsignacionPorCarga(),
                new AsignacionPorCategoria()
        ));

        SelectorPrioridad selectorPrioridad = new SelectorPrioridad(List.of(
                new PrioridadPorCategoria(),
                new PrioridadPorPalabras()
        ));

        SelectorNotificacion selectorNotificacion = new SelectorNotificacion(List.of(
                new NotificacionCorreo(),
                new NotificacionSMS(),
                notificacionApp,
                notificacionCorreoReal
        ));

        AsignacionService asignacionService = new AsignacionServiceImpl(
                selectorAsignacion,
                usuarioRepository
        );

        TicketService ticketService = new TicketServiceImpl(
                ticketRepository,
                usuarioRepository,
                selectorPrioridad,
                selectorAsignacion,
                selectorNotificacion,
                asignacionService
        );

        AuthService authService = new AuthServiceImpl(
                usuarioRepository,
                ticketRepository
        );

        contexto.setAttribute(TICKET_SERVICE, ticketService);
        contexto.setAttribute(ASIGNACION_SERVICE, asignacionService);
        contexto.setAttribute(AUTH_SERVICE, authService);
        contexto.setAttribute(SELECTOR_ASIGNACION, selectorAsignacion);
        contexto.setAttribute(SELECTOR_PRIORIDAD, selectorPrioridad);
        contexto.setAttribute(SELECTOR_NOTIFICACION, selectorNotificacion);
        contexto.setAttribute(CATEGORIA_REPOSITORY, categoriaRepository);
        contexto.setAttribute(USUARIO_REPOSITORY, usuarioRepository);
        contexto.setAttribute(TICKET_REPOSITORY, ticketRepository);
        contexto.setAttribute(NOTIFICACION_APP, notificacionApp);

        System.out.println("=============================================");
        System.out.println("🚀 MESA DE AYUDA CIMM - INICIADA");
        System.out.println("=============================================");
        System.out.println("  Usuarios precargados:");
        System.out.println("    - Solicitantes: juan@cimm.edu.co, maria@cimm.edu.co");
        System.out.println("    - Agentes: carlos@cimm.edu.co, ana@cimm.edu.co");
        System.out.println("    - Admin: admin@cimm.edu.co");
        System.out.println("  Contraseña para todos: 12345");
        System.out.println("=============================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent evento) {
        System.out.println("🛑 MESA DE AYUDA CIMM - DETENIDA");
    }
}
