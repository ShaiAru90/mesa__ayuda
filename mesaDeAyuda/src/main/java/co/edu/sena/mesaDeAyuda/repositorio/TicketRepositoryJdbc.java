/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.repositorio;

import co.edu.sena.mesaDeAyuda.config.DatabaseConfig;
import co.edu.sena.mesaDeAyuda.modelo.*;
import co.edu.sena.mesaDeAyuda.modelo.estado.EstadoTicketFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author halfo
 */
public class TicketRepositoryJdbc implements TicketRepository {

    private final DatabaseConfig dbConfig;

    public TicketRepositoryJdbc() {
        this.dbConfig = DatabaseConfig.getInstance();
        System.out.println("✅ [JDBC] TicketRepositoryJdbc inicializado");
    }

    // ============================================================
    // 🔍 MÉTODOS DE CONSULTA
    // ============================================================
    @Override
    public List<Ticket> listarTodos() {
        String sql = """
            SELECT t.*, 
                   c.nombre as categoria_nombre, c.descripcion as categoria_descripcion,
                   s.nombre as solicitante_nombre, s.correo as solicitante_correo, s.password as solicitante_password, s.rol as solicitante_rol,
                   a.nombre as agente_nombre, a.correo as agente_correo, a.password as agente_password, a.rol as agente_rol
            FROM tickets t
            LEFT JOIN categorias c ON t.categoria_id = c.id
            LEFT JOIN usuarios s ON t.solicitante_id = s.id
            LEFT JOIN usuarios a ON t.agente_id = a.id
            ORDER BY t.fecha_creacion DESC
        """;

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = dbConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tickets.add(mapearTicket(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al listar tickets: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public Optional<Ticket> buscarPorId(Long id) {
        String sql = """
            SELECT t.*, 
                   c.nombre as categoria_nombre, c.descripcion as categoria_descripcion,
                   s.nombre as solicitante_nombre, s.correo as solicitante_correo, s.password as solicitante_password, s.rol as solicitante_rol,
                   a.nombre as agente_nombre, a.correo as agente_correo, a.password as agente_password, a.rol as agente_rol
            FROM tickets t
            LEFT JOIN categorias c ON t.categoria_id = c.id
            LEFT JOIN usuarios s ON t.solicitante_id = s.id
            LEFT JOIN usuarios a ON t.agente_id = a.id
            WHERE t.id = ?
        """;

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearTicket(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al buscar ticket por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Ticket> buscarPorSolicitante(Usuario solicitante) {
        String sql = """
            SELECT t.*, 
                   c.nombre as categoria_nombre, c.descripcion as categoria_descripcion,
                   s.nombre as solicitante_nombre, s.correo as solicitante_correo, s.password as solicitante_password, s.rol as solicitante_rol,
                   a.nombre as agente_nombre, a.correo as agente_correo, a.password as agente_password, a.rol as agente_rol
            FROM tickets t
            LEFT JOIN categorias c ON t.categoria_id = c.id
            LEFT JOIN usuarios s ON t.solicitante_id = s.id
            LEFT JOIN usuarios a ON t.agente_id = a.id
            WHERE t.solicitante_id = ?
            ORDER BY t.fecha_creacion DESC
        """;

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, solicitante.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(mapearTicket(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al buscar tickets por solicitante: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public List<Ticket> buscarPorAgente(Usuario agente) {
        String sql = """
            SELECT t.*, 
                   c.nombre as categoria_nombre, c.descripcion as categoria_descripcion,
                   s.nombre as solicitante_nombre, s.correo as solicitante_correo, s.password as solicitante_password, s.rol as solicitante_rol,
                   a.nombre as agente_nombre, a.correo as agente_correo, a.password as agente_password, a.rol as agente_rol
            FROM tickets t
            LEFT JOIN categorias c ON t.categoria_id = c.id
            LEFT JOIN usuarios s ON t.solicitante_id = s.id
            LEFT JOIN usuarios a ON t.agente_id = a.id
            WHERE t.agente_id = ?
            ORDER BY t.fecha_creacion DESC
        """;

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, agente.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(mapearTicket(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al buscar tickets por agente: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public List<Ticket> buscarPorEstado(String estado) {
        String sql = """
            SELECT t.*, 
                   c.nombre as categoria_nombre, c.descripcion as categoria_descripcion,
                   s.nombre as solicitante_nombre, s.correo as solicitante_correo, s.password as solicitante_password, s.rol as solicitante_rol,
                   a.nombre as agente_nombre, a.correo as agente_correo, a.password as agente_password, a.rol as agente_rol
            FROM tickets t
            LEFT JOIN categorias c ON t.categoria_id = c.id
            LEFT JOIN usuarios s ON t.solicitante_id = s.id
            LEFT JOIN usuarios a ON t.agente_id = a.id
            WHERE t.estado = ?
            ORDER BY t.fecha_creacion DESC
        """;

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(mapearTicket(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al buscar tickets por estado: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public List<Ticket> buscarPorPrioridad(Prioridad prioridad) {
        String sql = """
            SELECT t.*, 
                   c.nombre as categoria_nombre, c.descripcion as categoria_descripcion,
                   s.nombre as solicitante_nombre, s.correo as solicitante_correo, s.password as solicitante_password, s.rol as solicitante_rol,
                   a.nombre as agente_nombre, a.correo as agente_correo, a.password as agente_password, a.rol as agente_rol
            FROM tickets t
            LEFT JOIN categorias c ON t.categoria_id = c.id
            LEFT JOIN usuarios s ON t.solicitante_id = s.id
            LEFT JOIN usuarios a ON t.agente_id = a.id
            WHERE t.prioridad = ?
            ORDER BY t.fecha_creacion DESC
        """;

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prioridad.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tickets.add(mapearTicket(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al buscar tickets por prioridad: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public List<Ticket> buscarActivos() {
        String sql = """
            SELECT t.*, 
                   c.nombre as categoria_nombre, c.descripcion as categoria_descripcion,
                   s.nombre as solicitante_nombre, s.correo as solicitante_correo, s.password as solicitante_password, s.rol as solicitante_rol,
                   a.nombre as agente_nombre, a.correo as agente_correo, a.password as agente_password, a.rol as agente_rol
            FROM tickets t
            LEFT JOIN categorias c ON t.categoria_id = c.id
            LEFT JOIN usuarios s ON t.solicitante_id = s.id
            LEFT JOIN usuarios a ON t.agente_id = a.id
            WHERE t.estado NOT IN ('CERRADO', 'CANCELADO')
            ORDER BY t.fecha_creacion DESC
        """;

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = dbConfig.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tickets.add(mapearTicket(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al buscar tickets activos: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    // ============================================================
    // 💾 MÉTODOS DE PERSISTENCIA
    // ============================================================
    @Override
    public Ticket guardar(Ticket ticket) {
        if (ticket.getId() == null) {
            return insertar(ticket);
        } else {
            return actualizar(ticket);
        }
    }

    private Ticket insertar(Ticket ticket) {
        String sql = """
            INSERT INTO tickets (titulo, descripcion, categoria_id, prioridad, 
                                 solicitante_id, agente_id, estado, 
                                 fecha_creacion, fecha_actualizacion, fecha_resolucion)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, ticket.getTitulo());
            ps.setString(2, ticket.getDescripcion());
            ps.setLong(3, ticket.getCategoria().getId());
            ps.setString(4, ticket.getPrioridad().name());
            ps.setLong(5, ticket.getSolicitante().getId());
            ps.setObject(6, ticket.getAgente() != null ? ticket.getAgente().getId() : null);
            ps.setString(7, ticket.getEstadoNombre());
            ps.setTimestamp(8, Timestamp.valueOf(ticket.getFechaCreacion()));
            ps.setTimestamp(9, Timestamp.valueOf(ticket.getFechaActualizacion()));
            ps.setTimestamp(10, ticket.getFechaResolucion() != null
                    ? Timestamp.valueOf(ticket.getFechaResolucion())
                    : null);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    ticket.setId(rs.getLong(1));
                }
            }

            guardarComentarios(ticket);
            System.out.println("✅ [JDBC] Ticket insertado: #" + ticket.getId());

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al insertar ticket: " + e.getMessage());
            e.printStackTrace();
        }

        return ticket;
    }

    private Ticket actualizar(Ticket ticket) {
        String sql = """
            UPDATE tickets 
            SET titulo = ?, descripcion = ?, categoria_id = ?, prioridad = ?,
                solicitante_id = ?, agente_id = ?, estado = ?,
                fecha_actualizacion = ?, fecha_resolucion = ?
            WHERE id = ?
        """;

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ticket.getTitulo());
            ps.setString(2, ticket.getDescripcion());
            ps.setLong(3, ticket.getCategoria().getId());
            ps.setString(4, ticket.getPrioridad().name());
            ps.setLong(5, ticket.getSolicitante().getId());
            ps.setObject(6, ticket.getAgente() != null ? ticket.getAgente().getId() : null);
            ps.setString(7, ticket.getEstadoNombre());
            ps.setTimestamp(8, Timestamp.valueOf(ticket.getFechaActualizacion()));
            ps.setTimestamp(9, ticket.getFechaResolucion() != null
                    ? Timestamp.valueOf(ticket.getFechaResolucion())
                    : null);
            ps.setLong(10, ticket.getId());

            ps.executeUpdate();

            // Actualizar comentarios (eliminar y volver a insertar)
            eliminarComentarios(ticket.getId());
            guardarComentarios(ticket);

            System.out.println("✅ [JDBC] Ticket actualizado: #" + ticket.getId());

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al actualizar ticket: " + e.getMessage());
            e.printStackTrace();
        }

        return ticket;
    }

    // ============================================================
    // 📝 MÉTODOS PARA COMENTARIOS
    // ============================================================
    private void guardarComentarios(Ticket ticket) {
        if (ticket.getComentariosCompletos().isEmpty()) {
            return;
        }

        String sql = """
            INSERT INTO comentarios (ticket_id, autor_id, texto, fecha, es_interno)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Comentario comentario : ticket.getComentariosCompletos()) {
                ps.setLong(1, ticket.getId());
                ps.setLong(2, comentario.getAutor().getId());
                ps.setString(3, comentario.getTexto());
                ps.setTimestamp(4, Timestamp.valueOf(comentario.getFecha()));
                ps.setBoolean(5, comentario.isEsInterno());
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al guardar comentarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void eliminarComentarios(Long ticketId) {
        String sql = "DELETE FROM comentarios WHERE ticket_id = ?";

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, ticketId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al eliminar comentarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ============================================================
    // 🔄 MAPEO DE RESULTADOS
    // ============================================================
    private Ticket mapearTicket(ResultSet rs) throws SQLException {
        // Categoria
        Categoria categoria = new Categoria(
                rs.getLong("categoria_id"),
                rs.getString("categoria_nombre"),
                rs.getString("categoria_descripcion"),
                rs.getString("categoria_nombre")
        );

        // Solicitante
        Usuario solicitante = new Usuario(
                rs.getLong("solicitante_id"),
                rs.getString("solicitante_nombre"),
                rs.getString("solicitante_correo"),
                rs.getString("solicitante_password"),
                Usuario.Rol.valueOf(rs.getString("solicitante_rol"))
        );

        // Agente (puede ser null)
        Usuario agente = null;
        if (rs.getObject("agente_id") != null) {
            agente = new Usuario(
                    rs.getLong("agente_id"),
                    rs.getString("agente_nombre"),
                    rs.getString("agente_correo"),
                    rs.getString("agente_password"),
                    Usuario.Rol.valueOf(rs.getString("agente_rol"))
            );
        }

        // Ticket
        Ticket ticket = new Ticket(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getString("descripcion"),
                categoria,
                Prioridad.valueOf(rs.getString("prioridad")),
                solicitante
        );

        // Setear agente
        if (agente != null) {
            ticket.asignar(agente);
        }

        // Cargar comentarios
        cargarComentarios(ticket);

        return ticket;
    }

    private void cargarComentarios(Ticket ticket) {
        String sql = """
            SELECT c.*, 
                   u.nombre as autor_nombre, u.correo as autor_correo, u.password as autor_password, u.rol as autor_rol
            FROM comentarios c
            JOIN usuarios u ON c.autor_id = u.id
            WHERE c.ticket_id = ?
            ORDER BY c.fecha ASC
        """;

        try (Connection conn = dbConfig.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, ticket.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario autor = new Usuario(
                        rs.getLong("autor_id"),
                        rs.getString("autor_nombre"),
                        rs.getString("autor_correo"),
                        rs.getString("autor_password"),
                        Usuario.Rol.valueOf(rs.getString("autor_rol"))
                );

                Comentario comentario = new Comentario(
                        rs.getLong("id"),
                        autor,
                        rs.getString("texto"),
                        rs.getBoolean("es_interno")
                );

                ticket.agregarComentario(comentario);
            }

        } catch (SQLException e) {
            System.err.println("❌ [JDBC] Error al cargar comentarios: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
