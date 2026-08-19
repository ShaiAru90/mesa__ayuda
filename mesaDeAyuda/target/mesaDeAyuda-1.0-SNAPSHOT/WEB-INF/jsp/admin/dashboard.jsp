<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Admin</title>
        <script src="https://jsdelivr.net"></script>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/estilo.css">

        <style>

            .contenido {
                max-width: 1200px;
                margin: 2rem auto;
                padding: 0 1.5rem;
            }

            .stats {
                display: grid;
                grid-template-columns: repeat(
                    auto-fit,
                    minmax(200px, 1fr)
                    );
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
                font-size: 2rem;
                font-weight: 800;
                color: #15803d;
            }

            .stat-card .label {
                font-size: 0.85rem;
                color: #64748b;
            }

            .tabla {
                width: 100%;
                border-collapse: collapse;
                background: white;
                border-radius: 16px;
                overflow: hidden;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.04);
            }

            .tabla th,
            .tabla td {
                padding: 0.75rem 1rem;
                text-align: left;
                border-bottom: 1px solid #e2e8f0;
            }

            .tabla th {
                background: #f8fafc;
                font-weight: 700;
                font-size: 0.8rem;
                color: #64748b;
                text-transform: uppercase;
            }

            .estado-badge {
                padding: 0.2rem 0.6rem;
                border-radius: 9999px;
                font-size: 0.7rem;
                font-weight: 700;
            }

            .estado-NUEVO {
                background: #e0f2fe;
                color: #0369a1;
            }

            .estado-ASIGNADO {
                background: #fef3c7;
                color: #b45309;
            }

            .estado-EN_PROCESO {
                background: #dbeafe;
                color: #1d4ed8;
            }

            .estado-RESUELTO {
                background: #d1fae5;
                color: #065f46;
            }

            .estado-CERRADO {
                background: #e2e8f0;
                color: #475569;
            }

            .estado-CANCELADO {
                background: #fee2e2;
                color: #991b1b;
            }

            .filtros {
                margin-bottom: 1rem;
                display: flex;
                gap: 0.5rem;
                flex-wrap: wrap;
            }

            .filtros a {
                padding: 0.3rem 1rem;
                border-radius: 9999px;
                text-decoration: none;
                font-size: 0.85rem;
                font-weight: 600;
            }

            .filtro-activo {
                background: #15803d;
                color: white;
            }

            .filtro-inactivo {
                background: #f1f5f9;
                color: #475569;
            }

            .filtro-inactivo:hover {
                background: #e2e8f0;
            }

            .header-dash {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 1.5rem;
                flex-wrap: wrap;
                gap: 1rem;
            }

            .header-dash h2 {
                font-size: 1.6rem;
                font-weight: 800;
            }


            .reasignacion-form {
                margin-top: 0.5rem;
                display: flex;
                flex-direction: column;
                gap: 0.4rem;
            }

            .reasignacion-form select {
                padding: 0.4rem 0.5rem;
                border: 1px solid #cbd5e1;
                border-radius: 8px;
                font-size: 0.8rem;
                background: white;
            }

            .btn-reasignar {
                border: none;
                border-radius: 8px;
                padding: 0.4rem 0.6rem;
                background: #15803d;
                color: white;
                font-weight: 700;
                cursor: pointer;
            }

            .btn-reasignar:hover {
                background: #166534;
            }

        </style>

    </head>

    <body>

        <jsp:include
            page="/WEB-INF/jsp/fragments/header.jsp"
            />


        <main class="contenido">

            <div class="header-dash">

                <h2>
                    📊 Dashboard Administrador
                </h2>

                <div class="filtros">

                    <a href="${pageContext.request.contextPath}/admin"
                       class="${empty param.filtro
                                ? 'filtro-activo'
                                : 'filtro-inactivo'}">

                        Todos

                    </a>

                    <a href="${pageContext.request.contextPath}/admin?filtro=activos"
                       class="${param.filtro == 'activos'
                                ? 'filtro-activo'
                                : 'filtro-inactivo'}">

                        Activos

                    </a>

                </div>

            </div>


            <div class="stats">

                <div class="stat-card">

                    <div class="numero">
                        ${totalTickets}
                    </div>

                    <div class="label">
                        Total Tickets
                    </div>

                </div>


                <div class="stat-card">

                    <div class="numero">
                        ${ticketsActivos}
                    </div>

                    <div class="label">
                        En Proceso
                    </div>

                </div>


                <div class="stat-card">

                    <div class="numero">
                        ${agentes.size()}
                    </div>

                    <div class="label">
                        Agentes Disponibles
                    </div>

                </div>

            </div>


            <table class="tabla">

                <thead>

                    <tr>

                        <th>ID</th>

                        <th>Título</th>

                        <th>Solicitante</th>

                        <th>Agente</th>

                        <th>Estado</th>

                        <th>Prioridad</th>

                        <th>Acción</th>

                    </tr>

                </thead>


                <tbody>

                    <c:choose>

                        <c:when test="${empty tickets}">

                            <tr>

                                <td colspan="7"
                                    style="text-align: center;
                                    padding: 2rem;
                                    color: #94a3b8;">

                                    No hay tickets

                                </td>

                            </tr>

                        </c:when>


                        <c:otherwise>

                            <c:forEach
                                var="t"
                                items="${tickets}">

                                <tr>

                                    <td>

                                        <strong>
                                            #${t.id}
                                        </strong>

                                    </td>


                                    <td>

                                        ${t.titulo}

                                    </td>


                                    <td>

                                        ${t.solicitante.nombre}

                                    </td>


                                    <td>

                                        <c:choose>

                                            <c:when test="${not empty t.agente}">

                                                ${t.agente.nombre}

                                            </c:when>

                                            <c:otherwise>

                                                <span style="color:#94a3b8;">

                                                    Sin asignar

                                                </span>

                                            </c:otherwise>

                                        </c:choose>

                                    </td>


                                    <td>

                                        <span class="estado-badge
                                              estado-${t.estado}">

                                            ${t.estado}

                                        </span>

                                    </td>


                                    <td>

                                        ${t.prioridad.nombre}

                                    </td>


                                    <td>

                                        <a href="${pageContext.request.contextPath}/ticket?id=${t.id}"
                                           style="color:#15803d;
                                           font-weight:700;
                                           text-decoration:none;">

                                            Ver →

                                        </a>


                                        <form method="post"
                                              action="${pageContext.request.contextPath}/admin"
                                              class="reasignacion-form">

                                            <input type="hidden"
                                                   name="accion"
                                                   value="reasignar">


                                            <input type="hidden"
                                                   name="ticketId"
                                                   value="${t.id}">


                                            <select name="agenteId"
                                                    required>

                                                <option value="">

                                                    Seleccionar agente

                                                </option>


                                                <c:forEach
                                                    var="agente"
                                                    items="${agentes}">

                                                    <option
                                                        value="${agente.id}">

                                                        ${agente.nombre}

                                                    </option>

                                                </c:forEach>

                                            </select>


                                            <button type="submit"
                                                    class="btn-reasignar">

                                                🔄 Reasignar

                                            </button>

                                        </form>

                                    </td>

                                </tr>

                            </c:forEach>

                        </c:otherwise>

                    </c:choose>

                </tbody>

            </table>

        </main>


        <jsp:include
            page="/WEB-INF/jsp/fragments/footer.jsp"
            />

    </body>

</html>