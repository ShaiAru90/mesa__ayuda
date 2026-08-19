<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="UTF-8">

        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>Iniciar sesión - Mesa de Ayuda CIMM</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/estilo.css">
        
        <script src="https://cloudflare.com"></script>

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
                    rgba(34, 197, 94, 0.10),
                    transparent 35%
                    ),
                    radial-gradient(
                    circle at bottom right,
                    rgba(21, 128, 61, 0.10),
                    transparent 35%
                    ),
                    var(--fondo);

                min-height: 100vh;

                display: flex;
                align-items: center;
                justify-content: center;

                padding: 2rem;
            }


            .login-page {

                width: 100%;
                max-width: 1100px;

                min-height: 680px;

                background: var(--blanco);

                border-radius: 28px;

                overflow: hidden;

                display: grid;

                grid-template-columns: 1fr 1fr;

                box-shadow:
                    0 25px 70px rgba(15, 23, 42, 0.10),
                    0 8px 25px rgba(15, 23, 42, 0.05);
            }

            .login-brand {

                position: relative;

                background:
                    radial-gradient(
                    circle at 15% 15%,
                    rgba(255,255,255,0.15),
                    transparent 25%
                    ),
                    radial-gradient(
                    circle at 90% 90%,
                    rgba(255,255,255,0.10),
                    transparent 30%
                    ),
                    linear-gradient(
                    145deg,
                    #166534,
                    #15803d 55%,
                    #16a34a
                    );

                color: white;

                padding: 3.5rem;

                display: flex;
                flex-direction: column;

                justify-content: space-between;

                overflow: hidden;
            }


            .login-brand::before {

                content: "";

                position: absolute;

                width: 320px;
                height: 320px;

                border-radius: 50%;

                border: 1px solid rgba(255,255,255,0.10);

                top: -130px;
                right: -130px;
            }


            .login-brand::after {

                content: "";

                position: absolute;

                width: 260px;
                height: 260px;

                border-radius: 50%;

                border: 1px solid rgba(255,255,255,0.08);

                bottom: -120px;
                left: -100px;
            }


            .brand-content {

                position: relative;
                z-index: 2;
            }


            .brand-logo {

                width: 68px;
                height: 68px;

                border-radius: 20px;

                background: rgba(255,255,255,0.15);

                border: 1px solid rgba(255,255,255,0.18);

                display: flex;
                align-items: center;
                justify-content: center;

                margin-bottom: 2rem;

                backdrop-filter: blur(8px);
            }


            .brand-logo svg {

                width: 34px;
                height: 34px;
            }


            .brand-content .tag {

                display: inline-flex;

                align-items: center;
                gap: 0.5rem;

                padding: 0.45rem 0.75rem;

                border-radius: 999px;

                background: rgba(255,255,255,0.13);

                border: 1px solid rgba(255,255,255,0.14);

                font-size: 0.75rem;

                font-weight: 700;

                margin-bottom: 1.2rem;
            }


            .brand-content h1 {

                font-size: clamp(
                    2rem,
                    4vw,
                    3.2rem
                    );

                line-height: 1.08;

                letter-spacing: -0.04em;

                margin-bottom: 1.3rem;
            }


            .brand-content p {

                max-width: 430px;

                color: rgba(255,255,255,0.78);

                line-height: 1.7;

                font-size: 0.95rem;
            }


            .brand-features {

                position: relative;

                z-index: 2;

                display: grid;

                gap: 0.8rem;
            }


            .feature {

                display: flex;

                align-items: center;

                gap: 0.8rem;

                color: rgba(255,255,255,0.92);

                font-size: 0.85rem;
            }


            .feature-icon {

                width: 34px;
                height: 34px;

                flex-shrink: 0;

                border-radius: 10px;

                background: rgba(255,255,255,0.12);

                display: flex;

                align-items: center;
                justify-content: center;
            }


            .brand-footer {

                position: relative;

                z-index: 2;

                padding-top: 2rem;

                border-top: 1px solid rgba(255,255,255,0.12);

                color: rgba(255,255,255,0.62);

                font-size: 0.75rem;
            }


            .login-form-panel {

                padding: 3.5rem;

                display: flex;
                align-items: center;
                justify-content: center;
            }


            .login-form-container {

                width: 100%;
                max-width: 390px;
            }


            .welcome {

                margin-bottom: 2rem;
            }


            .welcome h2 {

                color: var(--texto);

                font-size: 1.8rem;

                letter-spacing: -0.03em;

                margin-bottom: 0.5rem;
            }


            .welcome p {

                color: var(--texto-secundario);

                font-size: 0.9rem;

                line-height: 1.6;
            }

            .error {

                display: flex;

                align-items: flex-start;

                gap: 0.75rem;

                background: var(--rojo-fondo);

                color: var(--rojo);

                border: 1px solid #fecaca;

                border-radius: 14px;

                padding: 0.9rem 1rem;

                margin-bottom: 1.3rem;

                font-size: 0.85rem;

                line-height: 1.5;
            }


            .error-icon {

                flex-shrink: 0;

                margin-top: 1px;
            }

            .form-group {

                margin-bottom: 1.25rem;
            }


            .form-group label {

                display: block;

                color: #334155;

                font-size: 0.82rem;

                font-weight: 700;

                margin-bottom: 0.5rem;
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


            .form-group input {

                width: 100%;

                height: 54px;

                padding:
                    0
                    3rem
                    0
                    2.9rem;

                background: #f8fafc;

                border: 1px solid var(--borde);

                border-radius: 14px;

                color: var(--texto);

                font-family: inherit;

                font-size: 0.9rem;

                transition:
                    border-color 0.2s,
                    box-shadow 0.2s,
                    background 0.2s;
            }


            .form-group input::placeholder {

                color: #94a3b8;
            }


            .form-group input:hover {

                border-color: #cbd5e1;
            }


            .form-group input:focus {

                outline: none;

                background: white;

                border-color: var(--verde);

                box-shadow:
                    0 0 0 4px
                    rgba(21,128,61,0.10);
            }


            .password-toggle {

                position: absolute;

                right: 0.8rem;

                top: 50%;

                transform: translateY(-50%);

                width: 36px;
                height: 36px;

                display: flex;

                align-items: center;
                justify-content: center;

                border: none;

                background: transparent;

                color: #94a3b8;

                cursor: pointer;

                border-radius: 10px;
            }


            .password-toggle:hover {

                background: #f1f5f9;

                color: #475569;
            }


            .btn-login {

                width: 100%;

                height: 54px;

                margin-top: 0.5rem;

                border: none;

                border-radius: 14px;

                background:
                    linear-gradient(
                    135deg,
                    #15803d,
                    #16a34a
                    );

                color: white;

                font-family: inherit;

                font-size: 0.92rem;

                font-weight: 800;

                letter-spacing: -0.01em;

                cursor: pointer;

                display: flex;

                align-items: center;
                justify-content: center;

                gap: 0.7rem;

                box-shadow:
                    0 10px 25px
                    rgba(21,128,61,0.20);

                transition:
                    transform 0.2s,
                    box-shadow 0.2s,
                    filter 0.2s;
            }


            .btn-login:hover {

                transform: translateY(-1px);

                box-shadow:
                    0 14px 30px
                    rgba(21,128,61,0.27);

                filter: brightness(1.03);
            }


            .btn-login:active {

                transform: translateY(0);
            }

            .test-access {

                margin-top: 1.5rem;

                border: 1px solid var(--borde);

                border-radius: 14px;

                overflow: hidden;

                background: #f8fafc;
            }


            .test-access summary {

                cursor: pointer;

                padding: 0.9rem 1rem;

                color: #475569;

                font-size: 0.78rem;

                font-weight: 700;

                list-style: none;

                display: flex;

                align-items: center;

                justify-content: space-between;
            }


            .test-access summary::-webkit-details-marker {

                display: none;
            }


            .test-access summary::after {

                content: "+";

                font-size: 1.1rem;

                font-weight: 500;
            }


            .test-access[open] summary::after {

                content: "−";
            }


            .credentials {

                padding:
                    0
                    1rem
                    1rem;
            }


            .credential {

                padding: 0.7rem 0;

                border-top: 1px solid var(--borde);

                font-size: 0.75rem;

                color: #64748b;
            }


            .credential strong {

                color: #334155;
            }


            .credential code {

                background: #e2e8f0;

                color: #334155;

                padding: 0.15rem 0.35rem;

                border-radius: 5px;

                font-size: 0.7rem;
            }

            .login-footer {

                text-align: center;

                margin-top: 2rem;

                color: #94a3b8;

                font-size: 0.72rem;

                line-height: 1.5;
            }


            @media (max-width: 850px) {

                body {
                    padding: 1rem;
                }

                .login-page {

                    grid-template-columns: 1fr;

                    max-width: 520px;

                    min-height: auto;
                }

                .login-brand {

                    padding: 2.5rem;

                    min-height: 340px;
                }

                .brand-features {
                    display: none;
                }

                .brand-footer {
                    margin-top: 2rem;
                }

                .login-form-panel {

                    padding: 2.5rem;
                }
            }


            @media (max-width: 500px) {

                .login-page {

                    border-radius: 20px;
                }

                .login-brand {

                    padding: 2rem;
                }

                .login-form-panel {

                    padding: 2rem 1.5rem;
                }

                .brand-content h1 {

                    font-size: 2rem;
                }
            }

        </style>

    </head>


    <body>


        <div class="login-page">


            <section class="login-brand">

                <div class="brand-content">

                    <div class="brand-logo">

                        <svg
                            viewBox="0 0 24 24"
                            fill="none"
                            stroke="currentColor"
                            stroke-width="1.8"
                            stroke-linecap="round"
                            stroke-linejoin="round">

                        <path d="M12 3 4 7v5c0 5 3.4 8.1 8 9 4.6-.9 8-4 8-9V7l-8-4Z"/>

                        <path d="m9 12 2 2 4-4"/>

                        </svg>

                    </div>


                    <div class="tag">

                        <span>●</span>

                        Mesa de Ayuda CIMM

                    </div>


                    <h1>

                        Soporte técnico,
                        <br>
                        más simple.

                    </h1>


                    <p>

                        Gestiona tus solicitudes de soporte,
                        realiza seguimiento de tus tickets y
                        mantén toda la atención técnica
                        organizada en un solo lugar.

                    </p>

                </div>


                <div class="brand-features">

                    <div class="feature">

                        <div class="feature-icon">

                            ✓

                        </div>

                        <span>

                            Seguimiento de tus solicitudes

                        </span>

                    </div>


                    <div class="feature">

                        <div class="feature-icon">

                            ⚡

                        </div>

                        <span>

                            Atención organizada y eficiente

                        </span>

                    </div>


                    <div class="feature">

                        <div class="feature-icon">

                            🔔

                        </div>

                        <span>

                            Notificaciones sobre tus tickets

                        </span>

                    </div>

                </div>


                <div class="brand-footer">

                    SENA · Centro Industrial de Mantenimiento
                    y Manufactura · Regional Boyacá

                </div>

            </section>


            <section class="login-form-panel">

                <div class="login-form-container">


                    <div class="welcome">

                        <h2>
                            Bienvenido 👋
                        </h2>

                        <p>
                            Ingresa con tus credenciales
                            para continuar.
                        </p>

                    </div>

                    <c:if test="${not empty error}">

                        <div class="error">

                            <div class="error-icon">

                                ⚠️

                            </div>

                            <div>

                                ${error}

                            </div>

                        </div>

                    </c:if>

                    <form
                        method="post"
                        action="${pageContext.request.contextPath}/login">


                        <div class="form-group">

                            <label for="correo">

                                Correo electrónico

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

                                    <rect
                                        x="3"
                                        y="5"
                                        width="18"
                                        height="14"
                                        rx="2"/>

                                    <path
                                        d="m3 7 9 6 9-6"/>

                                    </svg>

                                </div>


                                <input
                                    type="email"
                                    id="correo"
                                    name="correo"
                                    placeholder="nombre@cimm.edu.co"
                                    autocomplete="email"
                                    required>

                            </div>

                        </div>


                        <div class="form-group">

                            <label for="password">

                                Contraseña

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

                                    <rect
                                        x="3"
                                        y="11"
                                        width="18"
                                        height="10"
                                        rx="2"/>

                                    <path
                                        d="M7 11V7a5 5 0 0 1 10 0v4"/>

                                    </svg>

                                </div>


                                <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    placeholder="Ingresa tu contraseña"
                                    autocomplete="current-password"
                                    required>


                                <button
                                    type="button"
                                    class="password-toggle"
                                    id="togglePassword"
                                    aria-label="Mostrar contraseña">

                                    <svg
                                        id="eyeOpen"
                                        width="18"
                                        height="18"
                                        viewBox="0 0 24 24"
                                        fill="none"
                                        stroke="currentColor"
                                        stroke-width="2"
                                        stroke-linecap="round"
                                        stroke-linejoin="round">

                                    <path
                                        d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7S2 12 2 12Z"/>

                                    <circle
                                        cx="12"
                                        cy="12"
                                        r="3"/>

                                    </svg>


                                    <svg
                                        id="eyeClosed"
                                        width="18"
                                        height="18"
                                        viewBox="0 0 24 24"
                                        fill="none"
                                        stroke="currentColor"
                                        stroke-width="2"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                        style="display:none;">

                                    <path
                                        d="M3 3l18 18"/>

                                    <path
                                        d="M10.6 10.6a2 2 0 0 0 2.8 2.8"/>

                                    <path
                                        d="M9.9 5.1A10.9 10.9 0 0 1 12 5c6.5 0 10 7 10 7a18.4 18.4 0 0 1-3 3.8"/>

                                    <path
                                        d="M6.6 6.6C3.8 8.7 2 12 2 12s3.5 7 10 7a9.4 9.4 0 0 0 4-.9"/>

                                    </svg>

                                </button>

                            </div>

                        </div>


                        <button
                            type="submit"
                            class="btn-login">

                            <span>
                                Ingresar al sistema
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

                            <path
                                d="M5 12h14"/>

                            <path
                                d="m13 6 6 6-6 6"/>

                            </svg>

                        </button>


                    </form>

                    <details class="test-access">

                        <summary>

                            🧪 Ver credenciales de prueba

                        </summary>


                        <div class="credentials">


                            <div class="credential">

                                <strong>
                                    Solicitante:
                                </strong>

                                <br>

                                <code>
                                    juan@cimm.edu.co
                                </code>

                                /

                                <code>
                                    12345
                                </code>

                            </div>


                            <div class="credential">

                                <strong>
                                    Agente:
                                </strong>

                                <br>

                                <code>
                                    carlos@cimm.edu.co
                                </code>

                                /

                                <code>
                                    12345
                                </code>

                            </div>


                            <div class="credential">

                                <strong>
                                    Administrador:
                                </strong>

                                <br>

                                <code>
                                    admin@cimm.edu.co
                                </code>

                                /

                                <code>
                                    admin123
                                </code>

                            </div>

                        </div>

                    </details>


                    <div class="login-footer">

                        © 2026 Mesa de Ayuda CIMM

                        <br>

                        SENA · Regional Boyacá

                    </div>


                </div>

            </section>


        </div>


        <script>

            const passwordInput =
                    document.getElementById("password");

            const togglePassword =
                    document.getElementById("togglePassword");

            const eyeOpen =
                    document.getElementById("eyeOpen");

            const eyeClosed =
                    document.getElementById("eyeClosed");


            togglePassword.addEventListener(
                    "click",
                    function () {

                        const isPassword =
                                passwordInput.type === "password";


                        passwordInput.type =
                                isPassword
                                ? "text"
                                : "password";


                        eyeOpen.style.display =
                                isPassword
                                ? "none"
                                : "block";


                        eyeClosed.style.display =
                                isPassword
                                ? "block"
                                : "none";


                        togglePassword.setAttribute(
                                "aria-label",
                                isPassword
                                ? "Ocultar contraseña"
                                : "Mostrar contraseña"
                                );

                    }
            );

        </script>

        <script>
            // Esperamos a que la página cargue por completo
            document.addEventListener("DOMContentLoaded", function () {
                // Capturamos los valores enviados desde los catch del Servlet
                const titulo = "${swal_titulo}";
                const texto = "${swal_texto}";
                const icono = "${swal_icono}";

                // Si el servlet envió datos, disparamos la alerta de SweetAlert2
                if (titulo && titulo.trim() !== "") {
                    Swal.fire({
                        title: titulo,
                        text: texto,
                        icon: icono,
                        confirmButtonText: 'Aceptar',
                        confirmButtonColor: '#dc3545' // Color rojo para el botón
                    });
                }
            });
        </script>


    </body>

</html>