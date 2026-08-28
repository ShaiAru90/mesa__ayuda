package co.edu.sena.mesaDeAyuda.servicio;

import co.edu.sena.mesaDeAyuda.dto.EstadisticasDTO;
import co.edu.sena.mesaDeAyuda.modelo.Usuario;

/**
 *
 * @author halfo
 */
public interface ReporteService {
    EstadisticasDTO obtenerEstadisticas(Usuario admin);    
}
