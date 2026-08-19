<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="UTF-8">

        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>Mis Tickets - Mesa de Ayuda CIMM</title>
        <script src="https://jsdelivr.net"></script>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/estilo.css">

        <link rel="preconnect"
              href="https://fonts.googleapis.com">

        <link rel="preconnect"
              href="https://fonts.gstatic.com"
              crossorigin>

        <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap"
              rel="stylesheet">

        <style>

            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            :root {
                --verde: #15803d;
                --verde-oscuro: #166534;
                --verde-claro: #22c55e;
                --verde-suave: #dcfce7;

                --texto: #0f172a;
                --texto-secundario: #64748b;

                --borde: #e2e8f0;
                --fondo: #f8fafc;
                --blanco: #ffffff;

                --rojo: #dc2626;
                --rojo-suave: #fef2f2;

                --amarillo: #d97706;
                --amarillo-suave: #fffbeb;

                --azul: #2563eb;
                --azul-suave: #eff6ff;
            }

            body {
                font-family:
                    'Plus Jakarta Sans',
                    sans-serif;

                background:
                    radial-gradient(
                    circle at top left,
                    rgba(34, 197, 94, 0.07),
                    transparent 28%
                    ),
                    radial-gradient(
                    circle at bottom right,
                    rgba(37, 99, 235, 0.05),
                    transparent 28%
                    ),
                    var(--fondo);

                color: var(--texto);

                min-height: 100vh;
            }

            .contenido {
                max-width: 1250px;
                margin: 2.5rem auto;
                padding: 0 1.5rem;
            }

            .header-tickets {
                display: flex;
                justify-content: space-between;
                align-items: flex-end;
                gap: 1.5rem;
                flex-wrap: wrap;
                margin-bottom: 1.5rem;
            }

            .titulo-bloque {
                display: flex;
                flex-direction: column;
                gap: 0.4rem;
            }

            .etiqueta {
                display: inline-flex;
                align-items: center;
                gap: 0.4rem;
                width: fit-content;
                padding: 0.35rem 0.7rem;
                border-radius: 999px;
                background: var(--verde-suave);
                color: var(--verde-oscuro);
                font-size: 0.7rem;
                font-weight: 800;
            }

            .header-tickets h1 {
                font-size: 2rem;
                font-weight: 800;
                letter-spacing: -0.04em;
            }

            .header-tickets p {
                color: var(--texto-secundario);
                font-size: 0.85rem;
                line-height: 1.5;
            }

            .btn-nuevo {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                gap: 0.5rem;
                min-height: 46px;
                padding: 0 1rem;
                border-radius: 12px;
                background:
                    linear-gradient(
                    135deg,
                    var(--verde),
                    var(--verde-claro)
                    );
                color: white;
                text-decoration: none;
                font-size: 0.78rem;
                font-weight: 800;
                box-shadow:
                    0 10px 22px
                    rgba(21, 128, 61, 0.15);
                transition:
                    transform 0.2s ease,
                    box-shadow 0.2s ease;
            }

            .btn-nuevo:hover {
                transform: translateY(-1px);
                box-shadow:
                    0 14px 28px
                    rgba(21, 128, 61, 0.22);
            }

            .filtros-contenedor {
                display: flex;
                align-items: center;
                gap: 0.75rem;
                flex-wrap: wrap;
                padding: 0.8rem;
                margin-bottom: 1.5rem;
                background: rgba(255,255,255,0.86);
                border: 1px solid var(--borde);
                border-radius: 16px;
                box-shadow:
                    0 8px 20px
                    rgba(15, 23, 42, 0.035);
            }

            .filtros-label {
                color: #475569;
                font-size: 0.75rem;
                font-weight: 800;
                margin-right: 0.2rem;
            }

            .filtros {
                display: flex;
                gap: 0.45rem;
                flex-wrap: wrap;
            }

            .filtros a,
            .filtros span {
                min-height: 36px;
                display: inline-flex;
                align-items: center;
                justify-content: center;
                padding: 0 0.85rem;
                border-radius: 999px;
                text-decoration: none;
                font-size: 0.72rem;
                font-weight: 800;
                transition:
                    background 0.2s ease,
                    color 0.2s ease,
                    transform 0.2s ease;
            }

            .filtro-activo {
                background: var(--verde);
                color: white;
                box-shadow:
                    0 5px 12px
                    rgba(21, 128, 61, 0.15);
            }

            .filtro-inactivo {
                background: #f1f5f9;
                color: #475569;
            }

            .filtro-inactivo:hover {
                background: #e2e8f0;
                transform: translateY(-1px);
            }

            .alerta-exito,
            .alerta-error {
                display: flex;
                align-items: flex-start;
                gap: 0.7rem;
                padding: 0.9rem 1rem;
                border-radius: 14px;
                margin-bottom: 1rem;
                font-size: 0.8rem;
                line-height: 1.5;
            }

            .alerta-exito {
                background: #f0fdf4;
                color: #166534;
                border: 1px solid #bbf7d0;
            }

            .alerta-error {
                background: var(--rojo-suave);
                color: #991b1b;
                border: 1px solid #fecaca;
            }

            .tabla-contenedor {
                background: rgba(255,255,255,0.96);
                border: 1px solid var(--borde);
                border-radius: 22px;
                overflow: hidden;
                box-shadow:
                    0 18px 40px
                    rgba(15, 23, 42, 0.05);
            }

            .tabla-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 1rem;
                padding: 1.2rem 1.3rem;
                border-bottom: 1px solid var(--borde);
            }

            .tabla-header h2 {
                font-size: 0.95rem;
                font-weight: 800;
            }

            .tabla-header span {
                color: #94a3b8;
                font-size: 0.72rem;
            }

            .tabla-scroll {
                overflow-x: auto;
            }

            .tabla {
                width: 100%;
                min-width: 850px;
                border-collapse: collapse;
            }

            .tabla th,
            .tabla td {
                padding: 1rem 1.2rem;
                text-align: left;
                border-bottom: 1px solid #eef2f7;
                vertical-align: middle;
            }

            .tabla th {
                background: #f8fafc;
                color: #64748b;
                font-size: 0.68rem;
                font-weight: 800;
                text-transform: uppercase;
                letter-spacing: 0.04em;
            }

            .tabla td {
                color: #334155;
                font-size: 0.8rem;
            }

            .tabla tbody tr {
                transition:
                    background 0.2s ease,
                    transform 0.2s ease;
            }

            .tabla tbody tr:hover {
                background: #f8fafc;
            }

            .tabla tbody tr:last-child td {
                border-bottom: none;
            }

            .ticket-id {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                min-width: 42px;
                padding: 0.3rem 0.5rem;
                border-radius: 8px;
                background: #f1f5f9;
                color: #475569;
                font-size: 0.72rem;
            }

            .ticket-titulo {
                color: var(--texto);
                font-weight: 800;
                max-width: 260px;
            }

            .ticket-categoria {
                color: #64748b;
            }

            .fecha {
                white-space: nowrap;
                color: #94a3b8;
                font-size: 0.72rem;
            }

            .estado-badge {
                display: inline-flex;
                align-items: center;
                gap: 0.3rem;
                padding: 0.35rem 0.65rem;
                border-radius: 9999px;
                font-size: 0.68rem;
                font-weight: 800;
                white-space: nowrap;
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

            .prioridad-badge {
                display: inline-flex;
                align-items: center;
                gap: 0.25rem;
                padding: 0.3rem 0.6rem;
                border-radius: 9999px;
                font-size: 0.68rem;
                font-weight: 800;
                white-space: nowrap;
            }

            .prioridad-critica {
                background: #fee2e2;
                color: #991b1b;
            }

            .prioridad-alta {
                background: #fed7aa;
                color: #9a3412;
            }

            .prioridad-media {
                background: #fef3c7;
                color: #92400e;
            }

            .prioridad-baja {
                background: #d1fae5;
                color: #065f46;
            }

            .btn-ver {
                display: inline-flex;
                align-items: center;
                gap: 0.3rem;
                padding: 0.45rem 0.7rem;
                border-radius: 9px;
                background: var(--verde-suave);
                color: var(--verde-oscuro);
                text-decoration: none;
                font-size: 0.7rem;
                font-weight: 800;
                transition:
                    background 0.2s ease,
                    transform 0.2s ease;
            }

            .btn-ver:hover {
                background: #bbf7d0;
                transform: translateY(-1px);
            }

            .empty {
                text-align: center;
                padding: 4rem 1.5rem !important;
                color: #94a3b8;
            }

            .empty-icon {
                width: 64px;
                height: 64px;
                margin: 0 auto 1rem;
                border-radius: 18px;
                display: flex;
                align-items: center;
                justify-content: center;
                background: #f1f5f9;
                font-size: 1.5rem;
            }

            .empty strong {
                display: block;
                color: #475569;
                font-size: 0.9rem;
                margin-bottom: 0.35rem;
            }

            .empty span {
                display: block;
                font-size: 0.75rem;
                margin-bottom: 1.2rem;
            }

            .btn-empty {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                min-height: 42px;
                padding: 0 1rem;
                border-radius: 10px;
                background: var(--verde);
                color: white;
                text-decoration: none;
                font-size: 0.75rem;
                font-weight: 800;
            }

            .pie {
                text-align: center;
                padding: 2.5rem 1rem;
                color: #94a3b8;
                font-size: 0.72rem;
            }

            @media (max-width: 800px) {

                .contenido {
                    margin: 1.5rem auto;
                }

                .header-tickets {
                    align-items: flex-start;
                    flex-direction: column;
                }

                .btn-nuevo {
                    width: 100%;
                }

                .filtros-contenedor {
                    align-items: flex-start;
                    flex-direction: column;
                }
            }

            @media (max-width: 520px) {

                .contenido {
                    padding: 0 1rem;
                }

                .header-tickets h1 {
                    font-size: 1.65rem;
                }

                .tabla-header {
                    align-items: flex-start;
                    flex-direction: column;
                }

            }

        </style>

    </head>

    <body>

        <jsp:include
            page="/WEB-INF/jsp/fragments/header.jsp"
            />


        <main class="contenido">


            <div class="header-tickets">

                <div class="titulo-bloque">

                    <span class="etiqueta">
                        📋 Mesa de Ayuda
                    </span>

                    <h1>
                        Mis Tickets
                    </h1>

                    <p>
                        Consulta y realiza seguimiento de todas
                        tus solicitudes de soporte.
                    </p>

                </div>


            </div>


            <div class="filtros-contenedor">

                <span class="filtros-label">
                    Filtrar por estado:
                </span>


                <div class="filtros">

                    <a
                        href="${pageContext.request.contextPath}/tickets"
                        class="${empty param.estado and empty param.prioridad
                                 ? 'filtro-activo'
                                 : 'filtro-inactivo'}">

                        Todos

                    </a>


                    <a
                        href="${pageContext.request.contextPath}/tickets?estado=NUEVO"
                        class="${param.estado == 'NUEVO'
                                 ? 'filtro-activo'
                                 : 'filtro-inactivo'}">

                        Nuevos

                    </a>


                    <a
                        href="${pageContext.request.contextPath}/tickets?estado=ASIGNADO"
                        class="${param.estado == 'ASIGNADO'
                                 ? 'filtro-activo'
                                 : 'filtro-inactivo'}">

                        Asignados

                    </a>


                    <a
                        href="${pageContext.request.contextPath}/tickets?estado=EN_PROCESO"
                        class="${param.estado == 'EN_PROCESO'
                                 ? 'filtro-activo'
                                 : 'filtro-inactivo'}">

                        En proceso

                    </a>


                    <a
                        href="${pageContext.request.contextPath}/tickets?estado=RESUELTO"
                        class="${param.estado == 'RESUELTO'
                                 ? 'filtro-activo'
                                 : 'filtro-inactivo'}">

                        Resueltos

                    </a>


                    <a
                        href="${pageContext.request.contextPath}/tickets?estado=CERRADO"
                        class="${param.estado == 'CERRADO'
                                 ? 'filtro-activo'
                                 : 'filtro-inactivo'}">

                        Cerrados

                    </a>

                </div>

            </div>


            <c:if test="${not empty mensajeExito}">

                <div class="alerta-exito">

                    <span>
                        ✅
                    </span>

                    <span>
                        ${mensajeExito}
                    </span>

                </div>

            </c:if>


            <c:if test="${not empty error}">

                <div class="alerta-error">

                    <span>
                        ⚠️
                    </span>

                    <span>
                        ${error}
                    </span>

                </div>

            </c:if>


            <section class="tabla-contenedor">


                <div class="tabla-header">

                    <div>

                        <h2>
                            Solicitudes registradas
                        </h2>

                    </div>

                    <span>
                        Consulta tus tickets y su estado actual
                    </span>

                </div>


                <div class="tabla-scroll">

                    <table class="tabla">

                        <thead>

                            <tr>

                                <th>
                                    ID
                                </th>

                                <th>
                                    Ticket
                                </th>

                                <th>
                                    Categoría
                                </th>

                                <th>
                                    Prioridad
                                </th>

                                <th>
                                    Estado
                                </th>

                                <th>
                                    Fecha
                                </th>

                                <th>
                                    Acción
                                </th>

                            </tr>

                        </thead>


                        <tbody>

                            <c:choose>

                                <c:when test="${empty tickets}">

                                    <tr>

                                        <td
                                            colspan="7"
                                            class="empty">

                                            <div class="empty-icon">
                                                📭
                                            </div>

                                            <strong>
                                                No tienes tickets registrados
                                            </strong>

                                            <span>
                                                Cuando crees una solicitud,
                                                aparecerá aquí.
                                            </span>

                                            <a
                                                href="${pageContext.request.contextPath}/crear-ticket"
                                                class="btn-empty">

                                                Crear mi primer ticket

                                            </a>

                                        </td>

                                    </tr>

                                </c:when>


                                <c:otherwise>

                                    <c:forEach
                                        var="t"
                                        items="${tickets}">

                                        <tr>


                                            <td>

                                                <span class="ticket-id">

                                                    #${t.id}

                                                </span>

                                            </td>


                                            <td>

                                                <div class="ticket-titulo">

                                                    ${t.titulo}

                                                </div>

                                            </td>


                                            <td>

                                                <span class="ticket-categoria">

                                                    ${t.categoria.nombre}

                                                </span>

                                            </td>


                                            <td>

                                                <span
                                                    class="prioridad-badge prioridad-${t.prioridad.nombre.toLowerCase()}">

                                                    ${t.prioridad.icono}

                                                    ${t.prioridad.nombre}

                                                </span>

                                            </td>


                                            <td>

                                                <span
                                                    class="estado-badge estado-${t.estado}">

                                                    ${t.estadoIcono}

                                                    ${t.estado}

                                                </span>

                                            </td>


                                            <td>

                                                <span class="fecha">

                                                    ${t.fechaCreacion}

                                                </span>

                                            </td>


                                            <td>

                                                <a
                                                    href="${pageContext.request.contextPath}/ticket?id=${t.id}"
                                                    class="btn-ver">

                                                    Ver detalle

                                                    <span>
                                                        →
                                                    </span>

                                                </a>

                                            </td>


                                        </tr>

                                    </c:forEach>

                                </c:otherwise>

                            </c:choose>

                        </tbody>

                    </table>

                </div>

            </section>


        </main>


        <div class="pie">

            Mesa de Ayuda CIMM · SENA Regional Boyacá
            <br>
            © 2026 Mesa de Ayuda

        </div>


        <jsp:include
            page="/WEB-INF/jsp/fragments/footer.jsp"
            />


    </body>

</html>