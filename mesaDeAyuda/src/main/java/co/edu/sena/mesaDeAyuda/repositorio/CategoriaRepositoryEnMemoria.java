/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.modelo.Categoria;
import co.edu.sena.mesaDeAyuda.repositorio.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * @author halfo
 */
public class CategoriaRepositoryEnMemoria implements CategoriaRepository {
    
    private final Map<Long, Categoria> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(0);
    
    public CategoriaRepositoryEnMemoria() {
        precargarCategorias();
    }
    
    @Override
    public List<Categoria> listarTodos() {
        return new ArrayList<>(datos.values());
    }
    
    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return Optional.ofNullable(datos.get(id));
    }
    
    @Override
    public Optional<Categoria> buscarPorNombre(String nombre) {
        if (nombre == null) return Optional.empty();
        return datos.values().stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }
    
    @Override
    public Categoria guardar(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
        
        Long id = categoria.getId();
        if (id == null) {
            id = secuencia.incrementAndGet();
            categoria.setId(id);
        }
        datos.put(id, categoria);
        return categoria;
    }
    
    private void precargarCategorias() {
        guardar(new Categoria("Redes", "Problemas de red, conectividad, internet"));
        guardar(new Categoria("Hardware", "Problemas con equipos, periféricos, impresoras"));
        guardar(new Categoria("Software", "Problemas con aplicaciones, sistemas operativos"));
        guardar(new Categoria("Mantenimiento", "Mantenimiento preventivo y correctivo de equipos"));
        guardar(new Categoria("Seguridad", "Problemas de seguridad, virus, accesos"));
        guardar(new Categoria("Telecomunicaciones", "Problemas con telefonía, videoconferencias"));
        guardar(new Categoria("General", "Otros problemas no clasificados"));
    }
}