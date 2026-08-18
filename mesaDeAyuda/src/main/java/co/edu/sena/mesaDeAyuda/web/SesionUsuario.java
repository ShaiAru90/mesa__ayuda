/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.web;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author halfo
 */
public final class SesionUsuario {
    
    private static final String ATRIBUTO_USUARIO = "usuarioActual";
    
    private SesionUsuario() {
        // Clase de utilidad, no se instancia
    }
    
    public static void guardar(HttpServletRequest request, Usuario usuario) {
        HttpSession sesion = request.getSession(true);
        sesion.setAttribute(ATRIBUTO_USUARIO, usuario);
    }
    
    public static Usuario obtener(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion == null) return null;
        return (Usuario) sesion.getAttribute(ATRIBUTO_USUARIO);
    }
    
    public static void eliminar(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            sesion.removeAttribute(ATRIBUTO_USUARIO);
        }
    }
    
    public static boolean estaAutenticado(HttpServletRequest request) {
        return obtener(request) != null;
    }
    
    public static boolean esSolicitante(HttpServletRequest request) {
        Usuario usuario = obtener(request);
        return usuario != null && usuario.esSolicitante();
    }
    
    public static boolean esAgente(HttpServletRequest request) {
        Usuario usuario = obtener(request);
        return usuario != null && usuario.esAgente();
    }
    
    public static boolean esAdmin(HttpServletRequest request) {
        Usuario usuario = obtener(request);
        return usuario != null && usuario.esAdmin();
    }
}
