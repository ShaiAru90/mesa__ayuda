<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="barra">

    <%-- Obtener contador de notificaciones --%>
    <%
        co.edu.sena.mesaDeAyuda.modelo.Usuario usuarioActual
                = co.edu.sena.mesaDeAyuda.web.SesionUsuario.obtener(request);
        int notificacionesNoLeidas = 0;
        if (usuarioActual != null) {
            co.edu.sena.mesaDeAyuda.servicio.notificacion.NotificacionApp app
                    = (co.edu.sena.mesaDeAyuda.servicio.notificacion.NotificacionApp) application.getAttribute(co.edu.sena.mesaDeAyuda.web.AppContextListener.NOTIFICACION_APP);
            if (app != null) {
                notificacionesNoLeidas = app.contarNoLeidas(usuarioActual);
            }
        }
        request.setAttribute("notificacionesNoLeidas", notificacionesNoLeidas);
    %>

    <div class="brand-container">

        <a href="${pageContext.request.contextPath}/tickets"
           class="brand-link">

            <div class="brand-icon">

                <svg
                    width="22"
                    height="22"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round">

                <path d="M12 3 4 7v5c0 5 3.4 8.1 8 9 4.6-.9 8-4 8-9V7l-8-4Z"/>

                <path d="m9 12 2 2 4-4"/>

                </svg>

            </div>

            <div class="brand-text">

                <strong>
                    Mesa de Ayuda CIMM
                </strong>

                <span>
                    SENA · Regional Boyacá
                </span>

            </div>

        </a>

    </div>


    <c:if test="${not empty usuario}">

        <nav class="nav-links">

            <a
                href="${pageContext.request.contextPath}/tickets"
                class="nav-link">

                <span class="nav-icon">

                    <svg
                        width="17"
                        height="17"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="2"
                        stroke-linecap="round"
                        stroke-linejoin="round">

                    <path d="M8 6h13"/>
                    <path d="M8 12h13"/>
                    <path d="M8 18h13"/>

                    <path d="M3 6h.01"/>
                    <path d="M3 12h.01"/>
                    <path d="M3 18h.01"/>

                    </svg>

                </span>

                Mis Tickets

            </a>

            <a href="${pageContext.request.contextPath}/notificaciones" class="nav-link notificaciones-link">
                <span class="nav-icon">
                    <svg width="17" height="17" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/>
                    <path d="M13.73 21a2 2 0 0 1-3.46 0"/>
                    </svg>
                </span>
                Notificaciones
                <c:if test="${notificacionesNoLeidas > 0}">
                    <span class="badge-notificacion">${notificacionesNoLeidas}</span>
                </c:if>
            </a>


            <c:if test="${usuario.esAgente()}">

                <a
                    href="${pageContext.request.contextPath}/tickets?estado=ASIGNADO"
                    class="nav-link">

                    <span class="nav-icon">

                        <svg
                            width="17"
                            height="17"
                            viewBox="0 0 24 24"
                            fill="none"
                            stroke="currentColor"
                            stroke-width="2"
                            stroke-linecap="round"
                            stroke-linejoin="round">

                        <rect
                            x="3"
                            y="4"
                            width="18"
                            height="16"
                            rx="2"/>

                        <path d="M8 9h8"/>
                        <path d="M8 13h5"/>

                        </svg>

                    </span>

                    Asignados

                </a>

            </c:if>


            <c:if test="${usuario.esAdmin()}">

                <a
                    href="${pageContext.request.contextPath}/admin"
                    class="nav-link">

                    <span class="nav-icon">

                        <svg
                            width="17"
                            height="17"
                            viewBox="0 0 24 24"
                            fill="none"
                            stroke="currentColor"
                            stroke-width="2"
                            stroke-linecap="round"
                            stroke-linejoin="round">

                        <rect
                            x="3"
                            y="3"
                            width="7"
                            height="7"
                            rx="1"/>

                        <rect
                            x="14"
                            y="3"
                            width="7"
                            height="7"
                            rx="1"/>

                        <rect
                            x="3"
                            y="14"
                            width="7"
                            height="7"
                            rx="1"/>

                        <rect
                            x="14"
                            y="14"
                            width="7"
                            height="7"
                            rx="1"/>

                        </svg>

                    </span>

                    Dashboard

                </a>

            </c:if>


            <a
                href="${pageContext.request.contextPath}/crear-ticket"
                class="btn-crear">

                <span class="btn-crear-icon">

                    +

                </span>

                Nuevo Ticket

            </a>

        </nav>


        <div class="user-area">

            <div class="user-profile">

                <div class="avatar">

                    ${usuario.nombre.substring(0,1)}

                </div>

                <div class="user-text">

                    <strong>
                        ${usuario.nombre}
                    </strong>

                    <span>
                        ${usuario.rolNombre}
                    </span>

                </div>

            </div>


            <div class="user-separator"></div>


            <a
                href="${pageContext.request.contextPath}/logout"
                class="btn-logout"
                title="Cerrar sesión">

                <svg
                    width="17"
                    height="17"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round">

                <path d="M10 17l5-5-5-5"/>

                <path d="M15 12H3"/>

                <path d="M21 19V5a2 2 0 0 0-2-2h-6"/>

                </svg>

                <span>
                    Salir
                </span>

            </a>

        </div>

    </c:if>

</header>


<style>

    .barra {

        position: sticky;

        top: 0;

        z-index: 1000;

        width: 100%;

        min-height: 76px;

        padding: 0 2rem;

        display: flex;

        align-items: center;

        justify-content: space-between;

        gap: 2rem;

        background: rgba(255,255,255,0.94);

        border-bottom: 1px solid #e2e8f0;

        box-shadow:
            0 8px 25px
            rgba(15,23,42,0.045);

        backdrop-filter: blur(14px);

        font-family:
            'Plus Jakarta Sans',
            Arial,
            sans-serif;
    }


    .brand-container {

        flex-shrink: 0;
    }


    .brand-link {

        display: flex;

        align-items: center;

        gap: 0.75rem;

        text-decoration: none;
    }


    .brand-icon {

        width: 44px;

        height: 44px;

        flex-shrink: 0;

        border-radius: 13px;

        display: flex;

        align-items: center;

        justify-content: center;

        background:
            linear-gradient(
            135deg,
            #15803d,
            #22c55e
            );

        color: white;

        box-shadow:
            0 8px 18px
            rgba(21,128,61,0.18);
    }


    .brand-text {

        display: flex;

        flex-direction: column;

        gap: 0.1rem;
    }


    .brand-text strong {

        color: #0f172a;

        font-size: 0.9rem;

        font-weight: 800;

        letter-spacing: -0.02em;
    }


    .brand-text span {

        color: #94a3b8;

        font-size: 0.68rem;

        font-weight: 600;
    }


    .nav-links {

        display: flex;

        align-items: center;

        justify-content: center;

        gap: 0.35rem;

        flex: 1;
    }


    .nav-link {

        min-height: 42px;

        padding: 0 0.8rem;

        display: inline-flex;

        align-items: center;

        justify-content: center;

        gap: 0.45rem;

        border-radius: 10px;

        color: #475569;

        text-decoration: none;

        font-size: 0.76rem;

        font-weight: 700;

        transition:
            background 0.2s ease,
            color 0.2s ease,
            transform 0.2s ease;
    }


    .nav-link:hover {

        background: #f1f5f9;

        color: #15803d;

        transform: translateY(-1px);
    }


    .nav-icon {

        display: flex;

        align-items: center;

        justify-content: center;

        color: currentColor;
    }


    .btn-crear {

        min-height: 42px;

        padding: 0 1rem;

        display: inline-flex;

        align-items: center;

        justify-content: center;

        gap: 0.45rem;

        border-radius: 11px;

        background:
            linear-gradient(
            135deg,
            #15803d,
            #16a34a
            );

        color: white !important;

        text-decoration: none;

        font-size: 0.76rem;

        font-weight: 800;

        box-shadow:
            0 8px 18px
            rgba(21,128,61,0.16);

        transition:
            transform 0.2s ease,
            box-shadow 0.2s ease;
    }


    .btn-crear:hover {

        color: white !important;

        transform: translateY(-1px);

        box-shadow:
            0 12px 24px
            rgba(21,128,61,0.23);
    }


    .btn-crear-icon {

        width: 20px;

        height: 20px;

        border-radius: 7px;

        display: flex;

        align-items: center;

        justify-content: center;

        background: rgba(255,255,255,0.16);

        font-size: 1rem;

        line-height: 1;
    }


    .user-area {

        display: flex;

        align-items: center;

        gap: 0.8rem;

        flex-shrink: 0;
    }


    .user-profile {

        display: flex;

        align-items: center;

        gap: 0.6rem;
    }


    .avatar {

        width: 38px;

        height: 38px;

        border-radius: 12px;

        display: flex;

        align-items: center;

        justify-content: center;

        background: #dcfce7;

        color: #166534;

        font-size: 0.78rem;

        font-weight: 800;

        text-transform: uppercase;
    }


    .user-text {

        display: flex;

        flex-direction: column;

        gap: 0.1rem;
    }


    .user-text strong {

        max-width: 130px;

        overflow: hidden;

        text-overflow: ellipsis;

        white-space: nowrap;

        color: #334155;

        font-size: 0.76rem;

        font-weight: 800;
    }


    .user-text span {

        display: inline-flex;

        width: fit-content;

        padding: 0.18rem 0.45rem;

        border-radius: 999px;

        background: #f1f5f9;

        color: #64748b;

        font-size: 0.61rem;

        font-weight: 700;
    }


    .user-separator {

        width: 1px;

        height: 28px;

        background: #e2e8f0;
    }


    .btn-logout {

        display: inline-flex;

        align-items: center;

        justify-content: center;

        gap: 0.35rem;

        min-height: 38px;

        padding: 0 0.65rem;

        border-radius: 9px;

        color: #dc2626;

        text-decoration: none;

        font-size: 0.72rem;

        font-weight: 800;

        transition:
            background 0.2s ease,
            color 0.2s ease;
    }


    .btn-logout:hover {

        background: #fef2f2;

        color: #b91c1c;
    }


    @media (max-width: 1050px) {

        .barra {

            gap: 1rem;

            padding: 0 1.25rem;
        }

        .brand-text span {

            display: none;
        }

        .user-text {

            display: none;
        }

        .user-separator {

            display: none;
        }

    }


    @media (max-width: 820px) {

        .barra {

            min-height: auto;

            padding: 0.8rem 1rem;

            flex-wrap: wrap;

        }

        .brand-container {

            flex: 1;
        }

        .nav-links {

            order: 3;

            width: 100%;

            overflow-x: auto;

            justify-content: flex-start;

            padding-bottom: 0.15rem;
        }

        .nav-link,
        .btn-crear {

            white-space: nowrap;
        }

    }


    @media (max-width: 520px) {

        .barra {

            padding: 0.75rem;

        }

        .brand-text strong {

            font-size: 0.8rem;
        }

        .brand-icon {

            width: 40px;

            height: 40px;
        }

        .btn-logout span {

            display: none;
        }

    }

</style>