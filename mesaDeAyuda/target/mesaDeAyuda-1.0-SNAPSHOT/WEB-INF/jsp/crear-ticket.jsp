<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="UTF-8">

        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>Crear Ticket - Mesa de Ayuda</title>

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
                --rojo-fondo: #fef2f2;
            }

            body {
                font-family: 'Plus Jakarta Sans', sans-serif;
                background:
                    radial-gradient(
                    circle at top left,
                    rgba(34, 197, 94, 0.08),
                    transparent 30%
                    ),
                    radial-gradient(
                    circle at bottom right,
                    rgba(21, 128, 61, 0.08),
                    transparent 30%
                    ),
                    var(--fondo);
                color: var(--texto);
                min-height: 100vh;
            }

            .contenido {
                max-width: 920px;
                margin: 2.5rem auto;
                padding: 0 1.5rem;
            }

            .pagina-header {
                display: flex;
                justify-content: space-between;
                align-items: flex-end;
                gap: 1rem;
                margin-bottom: 1.8rem;
            }

            .titulo-bloque {
                display: flex;
                flex-direction: column;
                gap: 0.35rem;
            }

            .titulo-bloque .etiqueta {
                display: inline-flex;
                align-items: center;
                gap: 0.45rem;
                width: fit-content;
                padding: 0.35rem 0.7rem;
                border-radius: 999px;
                background: var(--verde-suave);
                color: var(--verde-oscuro);
                font-size: 0.72rem;
                font-weight: 800;
            }

            .titulo-bloque h1 {
                font-size: 2rem;
                font-weight: 800;
                letter-spacing: -0.04em;
            }

            .titulo-bloque p {
                color: var(--texto-secundario);
                font-size: 0.9rem;
                line-height: 1.5;
            }

            .btn-volver {
                display: inline-flex;
                align-items: center;
                gap: 0.45rem;
                padding: 0.7rem 1rem;
                border: 1px solid var(--borde);
                border-radius: 12px;
                background: var(--blanco);
                color: #475569;
                text-decoration: none;
                font-size: 0.82rem;
                font-weight: 700;
                transition: all 0.2s ease;
            }

            .btn-volver:hover {
                color: var(--texto);
                border-color: #cbd5e1;
                transform: translateY(-1px);
                box-shadow: 0 8px 20px rgba(15, 23, 42, 0.06);
            }

            .form-card {
                background: rgba(255,255,255,0.96);
                border: 1px solid rgba(226,232,240,0.9);
                border-radius: 24px;
                padding: 2rem;
                box-shadow:
                    0 20px 50px rgba(15, 23, 42, 0.06),
                    0 4px 16px rgba(15, 23, 42, 0.03);
            }

            .form-top {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 1rem;
                margin-bottom: 1rem;
            }

            .form-group {
                margin-bottom: 1.25rem;
            }

            .form-group label {
                display: flex;
                align-items: center;
                gap: 0.25rem;
                margin-bottom: 0.55rem;
                color: #334155;
                font-size: 0.82rem;
                font-weight: 800;
            }

            .obligatorio {
                color: var(--rojo);
            }

            .campo-ayuda {
                display: block;
                margin-top: 0.45rem;
                color: #94a3b8;
                font-size: 0.72rem;
                line-height: 1.4;
            }

            .input-wrapper {
                position: relative;
            }

            .input-icon {
                position: absolute;
                left: 1rem;
                top: 50%;
                transform: translateY(-50%);
                color: #94a3b8;
                pointer-events: none;
            }

            .textarea-icon {
                position: absolute;
                left: 1rem;
                top: 1rem;
                color: #94a3b8;
                pointer-events: none;
            }

            .form-group input,
            .form-group textarea,
            .form-group select {
                width: 100%;
                border: 1px solid var(--borde);
                border-radius: 14px;
                background: #f8fafc;
                color: var(--texto);
                font-family: inherit;
                font-size: 0.9rem;
                transition:
                    border-color 0.2s ease,
                    box-shadow 0.2s ease,
                    background 0.2s ease;
            }

            .form-group input,
            .form-group select {
                height: 54px;
                padding: 0 1rem;
            }

            .form-group input {
                padding-left: 2.8rem;
            }

            .form-group select {
                padding-left: 1rem;
                cursor: pointer;
            }

            .form-group textarea {
                min-height: 180px;
                padding: 1rem 1rem 1rem 2.8rem;
                resize: vertical;
                line-height: 1.6;
            }

            .form-group input:hover,
            .form-group textarea:hover,
            .form-group select:hover {
                border-color: #cbd5e1;
            }

            .form-group input:focus,
            .form-group textarea:focus,
            .form-group select:focus {
                outline: none;
                background: #ffffff;
                border-color: var(--verde);
                box-shadow:
                    0 0 0 4px
                    rgba(21, 128, 61, 0.10);
            }

            .categoria-wrapper {
                position: relative;
            }

            .categoria-wrapper select {
                padding-right: 2.5rem;
            }

            .info-panel {
                display: flex;
                align-items: flex-start;
                gap: 0.8rem;
                padding: 1rem 1.1rem;
                margin-bottom: 1.5rem;
                border: 1px solid #bbf7d0;
                border-radius: 14px;
                background: #f0fdf4;
            }

            .info-icon {
                width: 36px;
                height: 36px;
                flex-shrink: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                border-radius: 10px;
                background: #dcfce7;
                color: var(--verde-oscuro);
                font-weight: 800;
            }

            .info-panel strong {
                display: block;
                color: #166534;
                font-size: 0.8rem;
                margin-bottom: 0.2rem;
            }

            .info-panel span {
                color: #4b7c5e;
                font-size: 0.75rem;
                line-height: 1.5;
            }

            .form-footer {
                display: flex;
                align-items: center;
                justify-content: space-between;
                gap: 1rem;
                padding-top: 1rem;
                border-top: 1px solid var(--borde);
            }

            .form-footer-text {
                color: #94a3b8;
                font-size: 0.72rem;
                line-height: 1.5;
            }

            .btn-submit {
                min-width: 190px;
                height: 54px;
                padding: 0 1.5rem;
                background: linear-gradient(
                    135deg,
                    #15803d,
                    #16a34a
                    );
                color: white;
                border: none;
                border-radius: 14px;
                font-family: inherit;
                font-size: 0.88rem;
                font-weight: 800;
                cursor: pointer;
                display: inline-flex;
                align-items: center;
                justify-content: center;
                gap: 0.6rem;
                box-shadow:
                    0 10px 24px
                    rgba(21, 128, 61, 0.18);
                transition:
                    transform 0.2s ease,
                    box-shadow 0.2s ease,
                    filter 0.2s ease;
            }

            .btn-submit:hover {
                transform: translateY(-1px);
                box-shadow:
                    0 14px 28px
                    rgba(21, 128, 61, 0.24);
                filter: brightness(1.03);
            }

            .btn-submit:active {
                transform: translateY(0);
            }

            .alerta-error {
                display: flex;
                align-items: flex-start;
                gap: 0.7rem;
                background: var(--rojo-fondo);
                color: #991b1b;
                padding: 0.9rem 1rem;
                border-radius: 14px;
                margin-bottom: 1rem;
                border: 1px solid #fecaca;
                font-size: 0.82rem;
                line-height: 1.5;
            }

            @media (max-width: 720px) {

                .contenido {
                    margin: 1.5rem auto;
                }

                .pagina-header {
                    align-items: flex-start;
                    flex-direction: column;
                }

                .form-card {
                    padding: 1.4rem;
                }

                .form-top {
                    grid-template-columns: 1fr;
                    gap: 0;
                }

                .form-footer {
                    flex-direction: column;
                    align-items: stretch;
                }

                .btn-submit {
                    width: 100%;
                }

            }

            @media (max-width: 480px) {

                .contenido {
                    padding: 0 1rem;
                }

                .titulo-bloque h1 {
                    font-size: 1.6rem;
                }

                .form-card {
                    border-radius: 18px;
                }

            }

        </style>

    </head>

    <body>

        <jsp:include
            page="/WEB-INF/jsp/fragments/header.jsp"
            />


        <main class="contenido">

            <div class="pagina-header">

                <div class="titulo-bloque">

                    <span class="etiqueta">
                        🎫 Mesa de Ayuda
                    </span>

                    <h1>
                        Crear nuevo ticket
                    </h1>

                    <p>
                        Describe tu solicitud y nuestro equipo
                        se encargará de atenderla.
                    </p>

                </div>


                <a
                    href="${pageContext.request.contextPath}/tickets"
                    class="btn-volver">

                    ← Volver a mis tickets

                </a>

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


            <div class="form-card">

                <div class="info-panel">

                    <div class="info-icon">
                        ✓
                    </div>

                    <div>

                        <strong>
                            Cuéntanos qué está pasando
                        </strong>

                        <span>
                            Mientras más detalles proporciones,
                            más fácil será para el equipo identificar
                            y solucionar el problema.
                        </span>

                    </div>

                </div>


                <form
                    method="post"
                    action="${pageContext.request.contextPath}/crear-ticket">


                    <div class="form-top">

                        <div class="form-group">

                            <label for="titulo">

                                Título
                                <span class="obligatorio">*</span>

                            </label>

                            <div class="input-wrapper">

                                <div class="input-icon">

                                    <svg
                                        width="18"
                                        height="18"
                                        viewBox="0 0 24 24"
                                        fill="none"
                                        stroke="currentColor"
                                        stroke-width="2"
                                        stroke-linecap="round"
                                        stroke-linejoin="round">

                                    <path d="M4 5h16"/>

                                    <path d="M4 12h16"/>

                                    <path d="M4 19h10"/>

                                    </svg>

                                </div>

                                <input
                                    type="text"
                                    id="titulo"
                                    name="titulo"
                                    value="${titulo}"
                                    placeholder="Ej. Problema con la impresora"
                                    maxlength="120"
                                    required>

                            </div>

                            <span class="campo-ayuda">
                                Resume el problema en una frase corta.
                            </span>

                        </div>


                        <div class="form-group">

                            <label for="categoria">

                                Categoría
                                <span class="obligatorio">*</span>

                            </label>

                            <div class="categoria-wrapper">

                                <select
                                    id="categoria"
                                    name="categoria"
                                    required>

                                    <option value="">
                                        Seleccione una categoría...
                                    </option>

                                    <option value="Redes">
                                        🌐 Redes
                                    </option>

                                    <option value="Hardware">
                                        💻 Hardware
                                    </option>

                                    <option value="Software">
                                        💻 Software
                                    </option>

                                    <option value="Mantenimiento">
                                        🔧 Mantenimiento
                                    </option>

                                    <option value="Seguridad">
                                        🔒 Seguridad
                                    </option>

                                    <option value="Telecomunicaciones">
                                        📞 Telecomunicaciones
                                    </option>

                                    <option value="General">
                                        📋 General
                                    </option>

                                </select>

                            </div>

                            <span class="campo-ayuda">
                                Selecciona el área que mejor describe tu solicitud.
                            </span>

                        </div>

                    </div>


                    <div class="form-group">

                        <label for="descripcion">

                            Descripción del problema

                        </label>

                        <div class="input-wrapper">

                            <div class="textarea-icon">

                                <svg
                                    width="18"
                                    height="18"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    stroke-width="2"
                                    stroke-linecap="round"
                                    stroke-linejoin="round">

                                <path d="M4 6h16"/>

                                <path d="M4 12h16"/>

                                <path d="M4 18h10"/>

                                </svg>

                            </div>

                            <textarea
                                id="descripcion"
                                name="descripcion"
                                placeholder="Describe detalladamente qué ocurrió, desde cuándo ocurre y qué intentaste hacer..."
                                maxlength="2000">${descripcion}</textarea>

                        </div>

                        <span class="campo-ayuda">
                            Puedes incluir mensajes de error, equipo afectado
                            o cualquier detalle relevante.
                        </span>

                    </div>


                    <div class="form-footer">

                        <div class="form-footer-text">

                            <strong>
                                Campos obligatorios *
                            </strong>

                            <br>

                            Tu ticket será asignado automáticamente
                            según las reglas del sistema.

                        </div>


                        <button
                            type="submit"
                            class="btn-submit">

                            <span>
                                Crear ticket
                            </span>

                            <svg
                                width="18"
                                height="18"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="2"
                                stroke-linecap="round"
                                stroke-linejoin="round">

                            <path d="M5 12h14"/>

                            <path d="m13 6 6 6-6 6"/>

                            </svg>

                        </button>

                    </div>

                </form>

            </div>

        </main>


        <jsp:include
            page="/WEB-INF/jsp/fragments/footer.jsp"
            />

    </body>

</html>