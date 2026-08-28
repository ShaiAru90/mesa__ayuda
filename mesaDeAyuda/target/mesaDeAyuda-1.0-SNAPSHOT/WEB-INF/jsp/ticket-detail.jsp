<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="UTF-8">

        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>Ticket #${ticket.id} - Mesa de Ayuda CIMM</title>

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
                    transparent 30%
                    ),
                    radial-gradient(
                    circle at bottom right,
                    rgba(37, 99, 235, 0.05),
                    transparent 30%
                    ),
                    var(--fondo);

                color: var(--texto);

                min-height: 100vh;
            }

            .contenido {

                max-width: 1050px;

                margin: 2.5rem auto;

                padding: 0 1.5rem;
            }

            .header-detail {

                display: flex;

                justify-content: space-between;

                align-items: flex-start;

                gap: 1.5rem;

                flex-wrap: wrap;

                margin-bottom: 1.5rem;
            }

            .header-left {

                display: flex;

                flex-direction: column;

                gap: 0.75rem;
            }

            .btn-volver {

                display: inline-flex;

                align-items: center;

                gap: 0.45rem;

                width: fit-content;

                color: #475569;

                text-decoration: none;

                font-size: 0.82rem;

                font-weight: 700;

                transition: color 0.2s ease;
            }

            .btn-volver:hover {

                color: var(--verde);
            }

            .ticket-heading {

                display: flex;

                align-items: center;

                gap: 0.8rem;

                flex-wrap: wrap;
            }

            .ticket-heading h1 {

                font-size: 2rem;

                font-weight: 800;

                letter-spacing: -0.04em;
            }

            .ticket-id {

                padding: 0.4rem 0.7rem;

                border-radius: 999px;

                background: var(--verde-suave);

                color: var(--verde-oscuro);

                font-size: 0.72rem;

                font-weight: 800;
            }

            .estado-badge {

                display: inline-flex;

                align-items: center;

                gap: 0.4rem;

                padding: 0.55rem 0.95rem;

                border-radius: 9999px;

                font-weight: 800;

                font-size: 0.8rem;

                border: 1px solid transparent;
            }

            .estado-NUEVO {

                background: #e0f2fe;

                color: #0369a1;

                border-color: #bae6fd;
            }

            .estado-ASIGNADO {

                background: #fef3c7;

                color: #b45309;

                border-color: #fde68a;
            }

            .estado-EN_PROCESO {

                background: #dbeafe;

                color: #1d4ed8;

                border-color: #bfdbfe;
            }

            .estado-RESUELTO {

                background: #d1fae5;

                color: #065f46;

                border-color: #a7f3d0;
            }

            .estado-CERRADO {

                background: #e2e8f0;

                color: #475569;

                border-color: #cbd5e1;
            }

            .estado-CANCELADO {

                background: #fee2e2;

                color: #991b1b;

                border-color: #fecaca;
            }

            .ticket-info {

                background: rgba(255,255,255,0.96);

                border: 1px solid var(--borde);

                border-radius: 22px;

                padding: 1.7rem;

                margin-bottom: 1.5rem;

                box-shadow:
                    0 15px 35px
                    rgba(15, 23, 42, 0.045);
            }

            .info-grid {

                display: grid;

                grid-template-columns:
                    repeat(
                    2,
                    minmax(0, 1fr)
                    );

                gap: 1rem;
            }

            .info-item {

                padding: 1rem;

                border-radius: 14px;

                background: #f8fafc;

                border: 1px solid #edf2f7;
            }

            .info-item.full {

                grid-column: 1 / -1;
            }

            .info-label {

                display: block;

                margin-bottom: 0.35rem;

                color: #94a3b8;

                font-size: 0.7rem;

                text-transform: uppercase;

                letter-spacing: 0.05em;

                font-weight: 800;
            }

            .info-value {

                color: var(--texto);

                font-size: 0.88rem;

                line-height: 1.5;
            }

            .descripcion-box {

                margin-top: 0.25rem;

                padding-top: 0.8rem;

                border-top: 1px solid var(--borde);
            }

            .descripcion-texto {

                color: #334155;

                font-size: 0.88rem;

                line-height: 1.7;

                white-space: pre-line;
            }

            .prioridad-badge {

                display: inline-flex;

                align-items: center;

                gap: 0.35rem;

                padding: 0.35rem 0.7rem;

                border-radius: 9999px;

                font-weight: 800;

                font-size: 0.72rem;
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

            .sla {

                margin-left: 0.5rem;

                color: #64748b;

                font-size: 0.72rem;
            }

            .acciones {

                background: white;

                border: 1px solid var(--borde);

                border-radius: 18px;

                padding: 1rem 1.2rem;

                margin-bottom: 1.5rem;

                display: flex;

                align-items: center;

                gap: 0.65rem;

                flex-wrap: wrap;

                box-shadow:
                    0 8px 20px
                    rgba(15, 23, 42, 0.035);
            }

            .acciones-label {

                color: #475569;

                font-size: 0.8rem;

                font-weight: 800;

                margin-right: 0.2rem;
            }

            .acciones form {

                margin: 0;
            }

            .acciones .btn {

                min-height: 40px;

                padding: 0.45rem 0.85rem;

                border: none;

                border-radius: 10px;

                font-family: inherit;

                font-weight: 800;

                font-size: 0.76rem;

                cursor: pointer;

                display: inline-flex;

                align-items: center;

                justify-content: center;

                gap: 0.35rem;

                transition:
                    transform 0.2s ease,
                    box-shadow 0.2s ease,
                    filter 0.2s ease;
            }

            .btn-accion {

                background: var(--verde);

                color: white;

                box-shadow:
                    0 6px 15px
                    rgba(21, 128, 61, 0.15);
            }

            .btn-accion:hover {

                background: var(--verde-oscuro);

                transform: translateY(-1px);
            }

            .btn-accion-danger {

                background: var(--rojo);

                color: white;

                box-shadow:
                    0 6px 15px
                    rgba(220, 38, 38, 0.14);
            }

            .btn-accion-danger:hover {

                background: #b91c1c;

                transform: translateY(-1px);
            }

            .comentarios {

                background: rgba(255,255,255,0.96);

                padding: 1.7rem;

                border-radius: 22px;

                border: 1px solid var(--borde);

                box-shadow:
                    0 15px 35px
                    rgba(15, 23, 42, 0.045);
            }

            .comentarios-header {

                display: flex;

                align-items: center;

                justify-content: space-between;

                gap: 1rem;

                margin-bottom: 1.25rem;
            }

            .comentarios h2 {

                font-size: 1.15rem;

                font-weight: 800;

                letter-spacing: -0.02em;
            }

            .comentarios-count {

                min-width: 30px;

                height: 30px;

                padding: 0 0.55rem;

                border-radius: 999px;

                background: #f1f5f9;

                color: #475569;

                display: flex;

                align-items: center;

                justify-content: center;

                font-size: 0.72rem;

                font-weight: 800;
            }

            .comentario {

                padding: 1rem 0;

                border-bottom: 1px solid #f1f5f9;
            }

            .comentario:last-child {

                border-bottom: none;
            }

            .comentario-header {

                display: flex;

                align-items: center;

                justify-content: space-between;

                gap: 1rem;

                flex-wrap: wrap;

                margin-bottom: 0.45rem;
            }

            .autor {

                display: inline-flex;

                align-items: center;

                gap: 0.4rem;

                font-weight: 800;

                font-size: 0.82rem;

                color: #1e293b;
            }

            .autor-rol {

                color: #94a3b8;

                font-size: 0.7rem;

                font-weight: 600;
            }

            .fecha {

                color: #94a3b8;

                font-size: 0.7rem;
            }

            .texto {

                color: #334155;

                font-size: 0.84rem;

                line-height: 1.65;
            }

            .comentario-interno {

                background: var(--amarillo-suave);

                border: 1px solid #fde68a;

                border-radius: 14px;

                padding: 0.9rem 1rem;

            }

            .comentario-interno .tag {

                display: inline-flex;

                align-items: center;

                gap: 0.25rem;

                padding: 0.2rem 0.5rem;

                border-radius: 999px;

                background: #fef3c7;

                color: #b45309;

                font-size: 0.62rem;

                font-weight: 800;

                text-transform: uppercase;

                margin-left: 0.4rem;
            }

            .sin-comentarios {

                padding: 2rem 1rem;

                text-align: center;

                color: #94a3b8;

                font-size: 0.82rem;
            }

            .form-comentario {

                margin-top: 1.5rem;

                padding-top: 1.5rem;

                border-top: 1px solid var(--borde);
            }

            .form-comentario textarea {

                width: 100%;

                min-height: 110px;

                padding: 0.9rem 1rem;

                border: 1px solid var(--borde);

                border-radius: 14px;

                background: #f8fafc;

                color: var(--texto);

                resize: vertical;

                font-family: inherit;

                font-size: 0.85rem;

                line-height: 1.5;

                transition:
                    border-color 0.2s ease,
                    box-shadow 0.2s ease,
                    background 0.2s ease;
            }

            .form-comentario textarea:focus {

                outline: none;

                background: white;

                border-color: var(--verde);

                box-shadow:
                    0 0 0 4px
                    rgba(21,128,61,0.10);
            }

            .comentario-footer {

                display: flex;

                align-items: center;

                justify-content: space-between;

                gap: 1rem;

                flex-wrap: wrap;

                margin-top: 0.7rem;
            }

            .comentario-ayuda {

                color: #94a3b8;

                font-size: 0.7rem;
            }

            .comentario-botones {

                display: flex;

                align-items: center;

                gap: 0.5rem;

                flex-wrap: wrap;
            }

            .btn-submit {

                min-height: 42px;

                padding: 0.5rem 1rem;

                background: var(--verde);

                color: white;

                border: none;

                border-radius: 10px;

                font-family: inherit;

                font-weight: 800;

                font-size: 0.76rem;

                cursor: pointer;

                transition:
                    transform 0.2s ease,
                    box-shadow 0.2s ease;
            }

            .btn-submit:hover {

                background: var(--verde-oscuro);

                transform: translateY(-1px);
            }

            .btn-interno {

                background: var(--amarillo);

                color: white;
            }

            .btn-interno:hover {

                background: #b45309;

            }

            .alerta-error {

                display: flex;

                align-items: flex-start;

                gap: 0.7rem;

                background: var(--rojo-suave);

                color: #991b1b;

                padding: 0.9rem 1rem;

                border-radius: 14px;

                margin-bottom: 1rem;

                border: 1px solid #fecaca;

                font-size: 0.82rem;

                line-height: 1.5;
            }

            @media (max-width: 760px) {

                .contenido {

                    margin: 1.5rem auto;

                }

                .info-grid {

                    grid-template-columns: 1fr;

                }

                .info-item.full {

                    grid-column: auto;

                }

                .ticket-heading h1 {

                    font-size: 1.55rem;

                }

                .ticket-info,
                .comentarios {

                    padding: 1.25rem;

                }

                .comentario-footer {

                    align-items: stretch;

                    flex-direction: column;
                }

                .comentario-botones {

                    width: 100%;
                }

            }

            @media (max-width: 520px) {

                .contenido {

                    padding: 0 1rem;
                }

                .header-detail {

                    flex-direction: column;
                }

                .header-detail > .estado-badge {

                    align-self: flex-start;
                }

                .acciones {

                    align-items: stretch;

                    flex-direction: column;
                }

                .acciones form,
                .acciones .btn {

                    width: 100%;
                }

            }

        </style>

    </head>

    <body>

        <jsp:include
            page="/WEB-INF/jsp/fragments/header.jsp"
            />


        <main class="contenido">


            <div class="header-detail">

                <div class="header-left">

                    <a
                        href="${pageContext.request.contextPath}/tickets"
                        class="btn-volver">

                        ← Volver a mis tickets

                    </a>

                    <div class="ticket-heading">

                        <span class="ticket-id">

                            #${ticket.id}

                        </span>

                        <h1>

                            ${ticket.titulo}

                        </h1>

                    </div>

                </div>


                <span class="estado-badge estado-${ticket.estado}">

                    ${ticket.estadoIcono}

                    ${ticket.estado}

                </span>

            </div>


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


            <section class="ticket-info">

                <div class="info-grid">


                    <div class="info-item">

                        <span class="info-label">
                            Categoría
                        </span>

                        <span class="info-value">

                            ${ticket.categoria.nombre}

                        </span>

                    </div>


                    <div class="info-item">

                        <span class="info-label">
                            Prioridad
                        </span>

                        <span class="info-value">

                            <span class="prioridad-badge prioridad-${ticket.prioridad.nombre.toLowerCase()}">

                                ${ticket.prioridad.icono}

                                ${ticket.prioridad.nombre}

                            </span>

                            <span class="sla">

                                ${ticket.prioridad.slaDescripcion}

                            </span>

                        </span>

                    </div>


                    <div class="info-item">

                        <span class="info-label">
                            Solicitante
                        </span>

                        <span class="info-value">

                            ${ticket.solicitante.nombre}

                        </span>

                    </div>


                    <div class="info-item">

                        <span class="info-label">
                            Agente asignado
                        </span>

                        <span class="info-value">

                            <c:choose>

                                <c:when test="${not empty ticket.agente}">

                                    ${ticket.agente.nombre}

                                </c:when>

                                <c:otherwise>

                                    <span style="color:#94a3b8;">

                                        Sin asignar

                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </span>

                    </div>


                    <div class="info-item">

                        <span class="info-label">
                            Fecha de creación
                        </span>

                        <span class="info-value">

                            ${ticket.fechaCreacion}

                        </span>

                    </div>


                    <c:if test="${ticket.estado == 'RESUELTO' or ticket.estado == 'CERRADO'}">

                        <div class="info-item">

                            <span class="info-label">
                                Fecha de resolución
                            </span>

                            <span class="info-value">

                                ${ticket.fechaResolucion}

                            </span>

                        </div>

                    </c:if>


                    <div class="info-item full">

                        <span class="info-label">
                            Descripción
                        </span>

                        <div class="descripcion-box">

                            <p class="descripcion-texto">

                                ${ticket.descripcion}

                            </p>

                        </div>

                    </div>

                </div>

            </section>


            <c:if test="${puedeModificar and not empty estadosDisponibles}">

                <section class="acciones">

                    <span class="acciones-label">

                        Acciones:

                    </span>


                    <c:forEach
                        var="estado"
                        items="${estadosDisponibles}">

                        <form
                            method="post"
                            action="${pageContext.request.contextPath}/ticket/accion">

                            <input
                                type="hidden"
                                name="ticketId"
                                value="${ticket.id}">

                            <input
                                type="hidden"
                                name="accion"
                                value="${estado}">


                            <button
                                type="submit"
                                class="btn btn-accion">


                                <c:choose>

                                    <c:when test="${estado == 'asignar'}">

                                        📌 Asignar Agente

                                    </c:when>

                                    <c:when test="${estado == 'iniciar'}">

                                        ▶️ Iniciar Atención

                                    </c:when>

                                    <c:when test="${estado == 'resuelto'}">

                                        ✅ Resolver

                                    </c:when>

                                    <c:when test="${estado == 'cerrado'}">

                                        📁 Cerrar

                                    </c:when>

                                    <c:when test="${estado == 'reabrir'}">

                                        🔄 Reabrir

                                    </c:when>

                                    <c:otherwise>

                                        ${estado}

                                    </c:otherwise>

                                </c:choose>

                            </button>

                        </form>

                    </c:forEach>


                    <c:if test="${usuario.esAdmin()}">

                        <form
                            method="post"
                            action="${pageContext.request.contextPath}/ticket/accion">

                            <input
                                type="hidden"
                                name="ticketId"
                                value="${ticket.id}">

                            <input
                                type="hidden"
                                name="accion"
                                value="cancelar">


                            <button
                                type="submit"
                                class="btn btn-accion-danger"
                                onclick="return confirm('¿Cancelar este ticket?')">

                                ❌ Cancelar

                            </button>

                        </form>

                    </c:if>

                </section>

            </c:if>


            <section class="comentarios">

                <div class="comentarios-header">

                    <h2>

                        💬 Comentarios

                    </h2>

                    <span class="comentarios-count">

                        ${ticket.totalComentarios}

                    </span>

                </div>


                <c:choose>

                    <c:when test="${empty ticket.comentarios}">

                        <div class="sin-comentarios">

                            Todavía no hay comentarios en este ticket.

                        </div>

                    </c:when>


                    <c:otherwise>

                        <c:forEach
                            var="c"
                            items="${ticket.comentarios}">

                            <div class="comentario ${c.esInterno ? 'comentario-interno' : ''}">

                                <div class="comentario-header">

                                    <div>

                                        <span class="autor">

                                            💬 ${c.autorNombre}

                                        </span>

                                        <span class="autor-rol">

                                            (${c.autorRol})

                                        </span>

                                        <c:if test="${c.esInterno}">

                                            <span class="tag">

                                                🔒 Interno

                                            </span>

                                        </c:if>

                                    </div>


                                    <span class="fecha">

                                        ${c.fechaFormateada}

                                    </span>

                                </div>


                                <div class="texto">

                                    ${c.texto}

                                </div>

                            </div>

                        </c:forEach>

                    </c:otherwise>

                </c:choose>


                <div class="form-comentario">

                    <form method="post" action="/mesaDeAyuda/ticket/accion">

                        <input
                            type="hidden"
                            name="ticketId"
                            value="${ticket.id}">

                        <input
                            type="hidden"
                            name="accion"
                            value="comentar">


                        <textarea
                            name="comentario"
                            placeholder="Escribe un comentario o actualización sobre este ticket..."
                            required></textarea>


                        <div class="comentario-footer">

                            <span class="comentario-ayuda">

                                Mantén la conversación relacionada
                                con la solicitud.

                            </span>


                            <div class="comentario-botones">

                                <button
                                    type="submit"
                                    class="btn-submit">

                                    Enviar comentario

                                </button>


                                <c:if test="${usuario.esAgente() or usuario.esAdmin()}">

                                    <button
                                        type="submit"
                                        name="accion"
                                        value="comentar-interno"
                                        class="btn-submit btn-interno">

                                        🔒 Interno

                                    </button>

                                </c:if>

                            </div>

                        </div>

                    </form>

                </div>

            </section>

            <c:if test="${mostrarOTP}">
                <div class="otp-section" style="
                     background: #f0fdf4;
                     border: 1px solid #bbf7d0;
                     border-radius: 16px;
                     padding: 1.2rem;
                     margin-top: 1rem;
                     ">
                    <h4 style="font-size: 0.9rem; font-weight: 800; color: #166534; margin-bottom: 0.5rem;">
                        🔐 Confirmar cierre con OTP
                    </h4>
                    <p style="font-size: 0.8rem; color: #4b7c5e; margin-bottom: 0.8rem;">
                        Ingresa el código de 6 dígitos que recibiste por correo para cerrar el ticket.
                    </p>

                    <form method="post" action="${pageContext.request.contextPath}/ticket/accion">
                        <input type="hidden" name="ticketId" value="${ticket.id}">
                        <input type="hidden" name="accion" value="cerrar-con-otp">

                        <div style="display: flex; gap: 0.5rem; flex-wrap: wrap; align-items: center;">
                            <input
                                type="text"
                                name="codigoOTP"
                                placeholder="Ej: 123456"
                                pattern="[0-9]{6}"
                                maxlength="6"
                                style="
                                width: 180px;
                                padding: 0.6rem 1rem;
                                border: 1px solid #bbf7d0;
                                border-radius: 10px;
                                font-size: 1.2rem;
                                font-weight: 700;
                                letter-spacing: 0.3em;
                                text-align: center;
                                background: white;
                                font-family: monospace;
                                "
                                required
                                >
                            <button type="submit" class="btn btn-accion" style="min-height: 46px;">
                                ✅ Cerrar con OTP
                            </button>
                            <button
                                type="button"
                                onclick="reenviarOTP(${ticket.id})"
                                class="btn btn-secundario"
                                style="min-height: 46px; background: #f1f5f9; border: 1px solid #e2e8f0; padding: 0 1rem; border-radius: 10px; font-weight: 700; cursor: pointer;"
                                >
                                📨 Reenviar OTP
                            </button>
                        </div>
                    </form>
                </div>
            </c:if>


        </main>


        <jsp:include
            page="/WEB-INF/jsp/fragments/footer.jsp"
            />

    </body>

    <script>
        function reenviarOTP(ticketId) {
            if (confirm('¿Reenviar el código OTP al correo del solicitante?')) {
                fetch('${pageContext.request.contextPath}/ticket/accion', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: 'ticketId=' + ticketId + '&accion=reenviar-otp'
                })
                        .then(response => {
                            if (response.ok) {
                                alert('✅ Código OTP reenviado al correo del solicitante.');
                            } else {
                                alert('❌ Error al reenviar el OTP.');
                            }
                        })
                        .catch(error => {
                            alert('❌ Error: ' + error.message);
                        });
            }
        }
    </script>

</html>