<%@ page contentType="text/html;charset=UTF-8" %>

<footer class="pie">

    <div class="pie-contenido">

        <div class="pie-marca">

            <div class="pie-icono">

                <svg
                    width="20"
                    height="20"
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

            <div>

                <strong>
                    Mesa de Ayuda CIMM
                </strong>

                <span>
                    SENA · Regional Boyacá
                </span>

            </div>

        </div>


        <div class="pie-info">

            <span>
                Soporte y gestión de tickets
            </span>

            <span class="separador">
                •
            </span>

            <span>
                © 2026
            </span>

            <span class="separador">
                •
            </span>

            <span>
                Taller Integrador ADSO
            </span>

        </div>

    </div>

</footer>

<style>

    .pie {

        margin-top: 4rem;

        padding: 1.5rem 2rem;

        background: rgba(255, 255, 255, 0.9);

        border-top: 1px solid #e2e8f0;

        color: #64748b;

        font-family:
            'Plus Jakarta Sans',
            Arial,
            sans-serif;

    }


    .pie-contenido {

        max-width: 1250px;

        margin: 0 auto;

        display: flex;

        align-items: center;

        justify-content: space-between;

        gap: 1.5rem;

        flex-wrap: wrap;

    }


    .pie-marca {

        display: flex;

        align-items: center;

        gap: 0.75rem;

    }


    .pie-icono {

        width: 38px;

        height: 38px;

        border-radius: 11px;

        display: flex;

        align-items: center;

        justify-content: center;

        background: #dcfce7;

        color: #15803d;

    }


    .pie-marca div:last-child {

        display: flex;

        flex-direction: column;

        gap: 0.15rem;

    }


    .pie-marca strong {

        color: #334155;

        font-size: 0.8rem;

        font-weight: 800;

    }


    .pie-marca span {

        color: #94a3b8;

        font-size: 0.68rem;

    }


    .pie-info {

        display: flex;

        align-items: center;

        justify-content: flex-end;

        gap: 0.5rem;

        flex-wrap: wrap;

        color: #94a3b8;

        font-size: 0.68rem;

        text-align: right;

    }


    .separador {

        color: #cbd5e1;

    }


    @media (max-width: 700px) {

        .pie {

            padding: 1.25rem 1rem;

        }

        .pie-contenido {

            justify-content: center;

            text-align: center;

        }

        .pie-info {

            justify-content: center;

            text-align: center;

        }

    }

</style>