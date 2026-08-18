<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="UTF-8">

        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <title>Error - Mesa de Ayuda CIMM</title>

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
                --texto: #0f172a;
                --texto-secundario: #64748b;
                --borde: #e2e8f0;
                --fondo: #f8fafc;
                --blanco: #ffffff;
                --rojo: #dc2626;
                --rojo-suave: #fef2f2;
            }

            body {

                min-height: 100vh;

                display: flex;

                align-items: center;

                justify-content: center;

                padding: 2rem;

                font-family:
                    'Plus Jakarta Sans',
                    sans-serif;

                background:
                    radial-gradient(
                    circle at top left,
                    rgba(220, 38, 38, 0.08),
                    transparent 30%
                    ),
                    radial-gradient(
                    circle at bottom right,
                    rgba(21, 128, 61, 0.08),
                    transparent 30%
                    ),
                    var(--fondo);

                color: var(--texto);
            }

            .error-page {

                width: 100%;

                max-width: 600px;

                background: var(--blanco);

                border: 1px solid var(--borde);

                border-radius: 26px;

                padding: 3rem;

                text-align: center;

                box-shadow:
                    0 25px 60px
                    rgba(15, 23, 42, 0.08);
            }

            .error-icon {

                width: 84px;

                height: 84px;

                margin: 0 auto 1.5rem;

                border-radius: 24px;

                background: var(--rojo-suave);

                color: var(--rojo);

                display: flex;

                align-items: center;

                justify-content: center;
            }

            .error-icon svg {

                width: 42px;

                height: 42px;
            }

            .etiqueta {

                display: inline-flex;

                align-items: center;

                gap: 0.45rem;

                padding: 0.4rem 0.75rem;

                border-radius: 999px;

                background: #f1f5f9;

                color: #475569;

                font-size: 0.72rem;

                font-weight: 800;

                margin-bottom: 1rem;
            }

            h1 {

                font-size: 2rem;

                font-weight: 800;

                letter-spacing: -0.04em;

                margin-bottom: 0.75rem;
            }

            .descripcion {

                color: var(--texto-secundario);

                font-size: 0.9rem;

                line-height: 1.7;

                max-width: 430px;

                margin: 0 auto;
            }

            .detalle {

                margin-top: 1.5rem;

                padding: 1rem 1.2rem;

                border-radius: 14px;

                background: #f8fafc;

                border: 1px solid var(--borde);

                color: #475569;

                font-size: 0.8rem;

                line-height: 1.6;
            }

            .acciones {

                margin-top: 1.8rem;

                display: flex;

                justify-content: center;

                gap: 0.8rem;

                flex-wrap: wrap;
            }

            .btn {

                min-height: 48px;

                padding: 0 1.2rem;

                border-radius: 12px;

                display: inline-flex;

                align-items: center;

                justify-content: center;

                gap: 0.5rem;

                text-decoration: none;

                font-family: inherit;

                font-size: 0.82rem;

                font-weight: 800;

                transition:
                    transform 0.2s ease,
                    box-shadow 0.2s ease,
                    border-color 0.2s ease;
            }

            .btn-principal {

                background:
                    linear-gradient(
                    135deg,
                    var(--verde),
                    #16a34a
                    );

                color: white;

                box-shadow:
                    0 10px 22px
                    rgba(21, 128, 61, 0.18);
            }

            .btn-principal:hover {

                transform: translateY(-1px);

                box-shadow:
                    0 14px 28px
                    rgba(21, 128, 61, 0.24);
            }

            .btn-secundario {

                background: white;

                color: #475569;

                border: 1px solid var(--borde);
            }

            .btn-secundario:hover {

                transform: translateY(-1px);

                border-color: #cbd5e1;

                box-shadow:
                    0 8px 18px
                    rgba(15, 23, 42, 0.06);
            }

            .footer {

                margin-top: 2rem;

                color: #94a3b8;

                font-size: 0.7rem;

                line-height: 1.5;
            }

            @media (max-width: 600px) {

                body {

                    padding: 1rem;

                }

                .error-page {

                    padding: 2rem 1.4rem;

                    border-radius: 20px;

                }

                h1 {

                    font-size: 1.6rem;

                }

                .acciones {

                    flex-direction: column;

                }

                .btn {

                    width: 100%;

                }

            }

        </style>

    </head>

    <body>

        <main class="error-page">

            <div class="error-icon">

                <svg
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round">

                <circle
                    cx="12"
                    cy="12"
                    r="9"/>

                <line
                    x1="12"
                    y1="8"
                    x2="12"
                    y2="13"/>

                <line
                    x1="12"
                    y1="16"
                    x2="12.01"
                    y2="16"/>

                </svg>

            </div>


            <span class="etiqueta">

                ⚠️ Algo salió mal

            </span>


            <h1>

                No pudimos completar la solicitud

            </h1>


            <p class="descripcion">

                Ocurrió un problema al procesar la página.
                Puedes volver al inicio o intentar nuevamente
                la acción que estabas realizando.

            </p>


            <div class="detalle">

                El sistema de Mesa de Ayuda CIMM continúa
                disponible. Si el problema persiste,
                verifica los datos ingresados o intenta
                nuevamente más tarde.

            </div>


            <div class="acciones">

                <a
                    href="${pageContext.request.contextPath}/tickets"
                    class="btn btn-principal">

                    ← Volver a mis tickets

                </a>


                <a
                    href="${pageContext.request.contextPath}/login"
                    class="btn btn-secundario">

                    Iniciar sesión

                </a>

            </div>


            <div class="footer">

                Mesa de Ayuda CIMM · SENA Regional Boyacá

                <br>

                © 2026 Mesa de Ayuda

            </div>

        </main>

    </body>

</html>