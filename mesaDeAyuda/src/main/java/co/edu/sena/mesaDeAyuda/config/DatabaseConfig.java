/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.mesaDeAyuda.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author halfo
 */
public class DatabaseConfig {
    
    // ========== CONFIGURACIÓN PARA XAMPP ==========
    private static final String URL = "jdbc:mysql://localhost:3306/mesa_ayuda?useSSL=false&serverTimezone=America/Bogota";
    private static final String USER = "root";
    private static final String PASSWORD = "";  
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static DatabaseConfig instance;
    private Connection connection;
    
    private DatabaseConfig() {
        try {
            Class.forName(DRIVER);
            System.out.println("✅ Driver MySQL cargado correctamente");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión a base de datos establecida");
            
            crearTablas();
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver MySQL no encontrado: " + e.getMessage());
            System.err.println("   → Asegúrate de tener mysql-connector-j en el classpath");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
            System.err.println("   → Verifica que MySQL esté corriendo en XAMPP");
            e.printStackTrace();
        }
    }
    
    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener conexión: " + e.getMessage());
        }
        return connection;
    }
    
    private void crearTablas() {
        // ========== TABLA: usuarios (SOLO para FOREIGN KEY) ==========
        String sqlUsuario = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                nombre VARCHAR(100) NOT NULL,
                correo VARCHAR(100) UNIQUE NOT NULL,
                password VARCHAR(100) NOT NULL,
                rol VARCHAR(20) NOT NULL
            )
        """;
        
        // ========== TABLA: categorias (SOLO para FOREIGN KEY) ==========
        String sqlCategoria = """
            CREATE TABLE IF NOT EXISTS categorias (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                nombre VARCHAR(50) UNIQUE NOT NULL,
                descripcion VARCHAR(200)
            )
        """;
        
        // ========== TABLA: tickets (¡LA IMPORTANTE!) ==========
        String sqlTicket = """
            CREATE TABLE IF NOT EXISTS tickets (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                titulo VARCHAR(200) NOT NULL,
                descripcion TEXT,
                categoria_id BIGINT NOT NULL,
                prioridad VARCHAR(20) NOT NULL,
                solicitante_id BIGINT NOT NULL,
                agente_id BIGINT,
                estado VARCHAR(20) NOT NULL,
                fecha_creacion DATETIME NOT NULL,
                fecha_actualizacion DATETIME,
                fecha_resolucion DATETIME,
                FOREIGN KEY (categoria_id) REFERENCES categorias(id),
                FOREIGN KEY (solicitante_id) REFERENCES usuarios(id),
                FOREIGN KEY (agente_id) REFERENCES usuarios(id)
            )
        """;
        
        // ========== TABLA: comentarios ==========
        String sqlComentario = """
            CREATE TABLE IF NOT EXISTS comentarios (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                ticket_id BIGINT NOT NULL,
                autor_id BIGINT NOT NULL,
                texto TEXT NOT NULL,
                fecha DATETIME NOT NULL,
                es_interno BOOLEAN DEFAULT FALSE,
                FOREIGN KEY (ticket_id) REFERENCES tickets(id) ON DELETE CASCADE,
                FOREIGN KEY (autor_id) REFERENCES usuarios(id)
            )
        """;
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlUsuario);
            stmt.execute(sqlCategoria);
            stmt.execute(sqlTicket);
            stmt.execute(sqlComentario);
            System.out.println("✅ Tablas creadas/verificadas correctamente");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tablas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
