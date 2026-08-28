package co.edu.sena.mesaDeAyuda.dto;

/**
 *
 * @author halfo
 */
public class TicketPorEstadoDTO {

    private final String estado;
    private final long cantidad;
    private final String color;

    public TicketPorEstadoDTO(String estado, long cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
        this.color = calcularColor(estado);
    }

    private String calcularColor(String estado) {
        return switch (estado.toUpperCase()) {
            case "NUEVO" ->
                "#3b82f6";
            case "ASIGNADO" ->
                "#f59e0b";
            case "EN_PROCESO" ->
                "#8b5cf6";
            case "RESUELTO" ->
                "#22c55e";
            case "CERRADO" ->
                "#64748b";
            case "CANCELADO" ->
                "#ef4444";
            default ->
                "#94a3b8";
        };
    }

    public String getEstado() {
        return estado;
    }

    public long getCantidad() {
        return cantidad;
    }

    public String getColor() {
        return color;
    }
}
