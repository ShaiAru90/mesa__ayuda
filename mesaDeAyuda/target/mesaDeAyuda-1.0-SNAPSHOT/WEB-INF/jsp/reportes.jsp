<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes - Dashboard CIMM</title>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: 'Plus Jakarta Sans', sans-serif;
            background: #f8fafc;
            color: #0f172a;
            min-height: 100vh;
        }
        .contenido { max-width: 1200px; margin: 2rem auto; padding: 0 1.5rem; }
        .header { margin-bottom: 2rem; }
        .header h1 { font-size: 2rem; font-weight: 800; letter-spacing: -0.04em; }
        .header p { color: #64748b; }

        /* Tarjetas de estadísticas */
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 1rem;
            margin-bottom: 2rem;
        }
        .stat-card {
            background: white;
            padding: 1.5rem;
            border-radius: 16px;
            border: 1px solid #e2e8f0;
            text-align: center;
        }
        .stat-card .numero {
            font-size: 2.2rem;
            font-weight: 800;
            color: #15803d;
        }
        .stat-card .label {
            font-size: 0.82rem;
            color: #64748b;
            margin-top: 0.25rem;
        }
        .stat-card .sub {
            font-size: 0.7rem;
            color: #94a3b8;
            margin-top: 0.3rem;
        }
        .stat-card .vencido { color: #dc2626; }
        .stat-card .activo { color: #2563eb; }

        .grid-2 {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1.5rem;
            margin-bottom: 2rem;
        }
        .card {
            background: white;
            border-radius: 16px;
            border: 1px solid #e2e8f0;
            padding: 1.5rem;
        }
        .card h3 {
            font-size: 0.9rem;
            font-weight: 800;
            color: #334155;
            margin-bottom: 1rem;
        }
        .card .empty {
            color: #94a3b8;
            text-align: center;
            padding: 1rem;
        }

        .barra-container {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }
        .barra-item {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }
        .barra-item .label {
            min-width: 80px;
            font-size: 0.75rem;
            font-weight: 600;
            color: #475569;
        }
        .barra-item .track {
            flex: 1;
            height: 24px;
            background: #f1f5f9;
            border-radius: 999px;
            overflow: hidden;
        }
        .barra-item .fill {
            height: 100%;
            border-radius: 999px;
            transition: width 0.5s ease;
        }
        .barra-item .valor {
            min-width: 35px;
            font-size: 0.75rem;
            font-weight: 700;
            color: #334155;
            text-align: right;
        }

        .agente-item {
            display: flex;
            justify-content: space-between;
            padding: 0.5rem 0;
            border-bottom: 1px solid #f1f5f9;
        }
        .agente-item:last-child { border-bottom: none; }
        .agente-item .nombre { font-weight: 600; font-size: 0.85rem; }
        .agente-item .stats { font-size: 0.75rem; color: #64748b; }
        .agente-item .stats .resueltos { color: #22c55e; font-weight: 700; }

        .promedio {
            text-align: center;
            padding: 0.5rem;
            background: #f0fdf4;
            border-radius: 12px;
            color: #166534;
            font-size: 0.85rem;
            font-weight: 600;
        }

        @media (max-width: 768px) {
            .grid-2 { grid-template-columns: 1fr; }
        }
    </style>
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/fragments/header.jsp" />

    <main class="contenido">
        <div class="header">
            <h1>📊 Reportes y Métricas</h1>
            <p>Resumen general del rendimiento de la mesa de ayuda</p>
        </div>

        <!-- Tarjetas de resumen -->
        <div class="stats-grid">
            <div class="stat-card">
                <div class="numero">${estadisticas.totalTickets}</div>
                <div class="label">Total Tickets</div>
            </div>
            <div class="stat-card">
                <div class="numero activo">${estadisticas.ticketsActivos}</div>
                <div class="label">Tickets Activos</div>
            </div>
            <div class="stat-card">
                <div class="numero" style="color:#22c55e;">${estadisticas.ticketsResueltos}</div>
                <div class="label">Tickets Resueltos</div>
            </div>
            <div class="stat-card">
                <div class="numero vencido">${estadisticas.ticketsVencidos}</div>
                <div class="label">SLA Vencidos</div>
                <div class="sub">⚠️ Requieren atención urgente</div>
            </div>
        </div>

        <!-- Gráficos -->
        <div class="grid-2">
            <!-- Tickets por estado -->
            <div class="card">
                <h3>📌 Tickets por Estado</h3>
                <c:choose>
                    <c:when test="${empty estadisticas.ticketsPorEstado}">
                        <div class="empty">No hay datos</div>
                    </c:when>
                    <c:otherwise>
                        <div class="barra-container">
                            <c:forEach var="item" items="${estadisticas.ticketsPorEstado}">
                                <div class="barra-item">
                                    <span class="label">${item.estado}</span>
                                    <div class="track">
                                        <div class="fill" style="width: ${item.cantidad / estadisticas.totalTickets * 100}%; background: ${item.color};"></div>
                                    </div>
                                    <span class="valor">${item.cantidad}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Tickets por agente -->
            <div class="card">
                <h3>👤 Tickets por Agente</h3>
                <c:choose>
                    <c:when test="${empty estadisticas.ticketsPorAgente}">
                        <div class="empty">No hay agentes registrados</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="agente" items="${estadisticas.ticketsPorAgente}">
                            <div class="agente-item">
                                <span class="nombre">${agente.agenteNombre}</span>
                                <span class="stats">
                                    Asignados: ${agente.ticketsAsignados} |
                                    Resueltos: <span class="resueltos">${agente.ticketsResueltos}</span>
                                    (${agente.tasaResolucion >= 0 ? agente.tasaResolucion : 0}%)
                                </span>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- Promedio de resolución -->
        <div class="promedio">
            ⏱️ Tiempo promedio de resolución:
            <fmt:formatNumber value="${estadisticas.promedioResolucionHoras}" maxFractionDigits="1" />
            horas
        </div>
    </main>

    <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp" />
</body>
</html>