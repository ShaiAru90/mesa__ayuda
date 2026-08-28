package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UsuarioRepositoryEnMemoria implements UsuarioRepository {
    
    private final Map<Long, Usuario> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(0);
    
    public UsuarioRepositoryEnMemoria() {
        precargarUsuarios();
    }
    
    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(datos.values());
    }
    
    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.ofNullable(datos.get(id));
    }
    
    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        if (correo == null) return Optional.empty();
        return datos.values().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                .findFirst();
    }
    
    @Override
    public List<Usuario> buscarPorRol(Usuario.Rol rol) {
        if (rol == null) return List.of();
        return datos.values().stream()
                .filter(u -> u.getRol() == rol)
                .toList();
    }
    
    @Override
    public List<Usuario> buscarAgentesDisponibles() {
        return datos.values().stream()
                .filter(Usuario::esAgente)
                .toList();
    }
    
    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        
        Long id = usuario.getId();
        if (id == null) {
            id = secuencia.incrementAndGet();
            usuario.setId(id);
        }
        datos.put(id, usuario);
        return usuario;
    }
    
    @Override
    public boolean existePorCorreo(String correo) {
        return buscarPorCorreo(correo).isPresent();
    }
    
    private void precargarUsuarios() {
       
        guardar(new Usuario("Juan Pérez", "juan@cimm.edu.co", "12345", Usuario.Rol.SOLICITANTE));
        guardar(new Usuario("María Gómez", "maria@cimm.edu.co", "12345", Usuario.Rol.SOLICITANTE));
        guardar(new Usuario("Pedro Rodríguez", "pedro@cimm.edu.co", "12345", Usuario.Rol.SOLICITANTE));
        guardar(new Usuario("Laura Martínez", "laura@cimm.edu.co", "12345", Usuario.Rol.SOLICITANTE));
        
      
        guardar(new Usuario("Carlos López", "halfonsokyar03@gmail.com", "12345", Usuario.Rol.AGENTE));
        guardar(new Usuario("Ana Martínez", "ana@cimm.edu.co", "12345", Usuario.Rol.AGENTE));
        guardar(new Usuario("Luis Sánchez", "luis@cimm.edu.co", "12345", Usuario.Rol.AGENTE));
        guardar(new Usuario("Elena Díaz", "elena@cimm.edu.co", "12345", Usuario.Rol.AGENTE));
        
     
        guardar(new Usuario("Admin SENA", "admin@cimm.edu.co", "admin123", Usuario.Rol.ADMIN));
    }
}