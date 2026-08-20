<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Notificaciones - Mesa de Ayuda CIMM</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: 'Plus Jakarta Sans', Arial, sans-serif;
            background: #f4f6f9;
            padding: 20px;
            min-height: 100vh;
        }
        .container { max-width: 800px; margin: 0 auto; }
        
        .header {
            background: #2c3e50;
            color: white;
            padding: 20px 25px;
            border-radius: 12px 12px 0 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header h1 { font-size: 24px; }
        .header .badge {
            background: #e74c3c;
            color: white;
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: bold;
        }
        
        .notificaciones-container {
            background: white;
            border-radius: 0 0 12px 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        
        .notificacion-item {
            padding: 15px 20px;
            border-bottom: 1px solid #ecf0f1;
            display: flex;
            justify-content: space-between;
            align-items: center;
            transition: background 0.2s;
        }
        .notificacion-item:hover { background: #f8f9fa; }
        .notificacion-item:last-child { border-bottom: none; }
        
        .notificacion-item .contenido { flex: 1; }
        .notificacion-item .mensaje {
            font-size: 14px;
            color: #2c3e50;
        }
        .notificacion-item .btn-leer {
            background: #27ae60;
            color: white;
            border: none;
            padding: 5px 12px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 12px;
            font-weight: 600;
            transition: background 0.2s;
            flex-shrink: 0;
            margin-left: 10px;
        }
        .notificacion-item .btn-leer:hover { background: #1e8449; }
        
        .sin-notificaciones {
            padding: 50px 20px;
            text-align: center;
            color: #7f8c8d;
        }
        .sin-notificaciones .emoji { font-size: 48px; display: block; margin-bottom: 15px; }
        .sin-notificaciones h3 { font-size: 20px; color: #2c3e50; margin-bottom: 8px; }
        .sin-notificaciones p { font-size: 14px; }
        
        .acciones {
            padding: 15px 20px;
            background: #f8f9fa;
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
            justify-content: space-between;
            align-items: center;
        }
        .acciones .btn {
            padding: 8px 20px;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
            font-weight: 600;
        }
        .btn-volver {
            background: #3498db;
            color: white;
        }
        .btn-volver:hover { background: #2980b9; }
        
        .volver-inicio {
            text-align: center;
            margin-top: 20px;
        }
        .volver-inicio a {
            color: #3498db;
            text-decoration: none;
            font-weight: 600;
        }
        .volver-inicio a:hover { text-decoration: underline; }
        
        @media (max-width: 600px) {
            .notificacion-item {
                flex-direction: column;
                align-items: stretch;
                gap: 8px;
            }
            .notificacion-item .btn-leer {
                align-self: flex-end;
            }
            .acciones {
                flex-direction: column;
                align-items: stretch;
            }
            .acciones .btn {
                text-align: center;
            }
        }
    </style>
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/fragments/header.jsp" />

    <div class="container">
        <div class="header">
            <h1>🔔 Mis Notificaciones</h1>
            <span class="badge">${total} pendiente${total != 1 ? 's' : ''}</span>
        </div>

        <div class="notificaciones-container">
            <c:choose>
                <c:when test="${empty listaNotificaciones}">
                    <div class="sin-notificaciones">
                        <span class="emoji">🎉</span>
                        <h3>¡No tienes notificaciones!</h3>
                        <p>Cuando haya novedades en tus tickets, aparecerán aquí.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="notificacion" items="${listaNotificaciones}" varStatus="status">
                        <div class="notificacion-item">
                            <div class="contenido">
                                <span class="mensaje">📨 ${notificacion}</span>
                            </div>
                            <form action="${pageContext.request.contextPath}/notificaciones" method="post" style="display:inline;">
                                <input type="hidden" name="index" value="${status.index}">
                                <button type="submit" class="btn-leer">✓ Leída</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <div class="acciones">
                <a href="${pageContext.request.contextPath}/tickets" class="btn btn-volver">
                    ← Volver a mis tickets
                </a>
            </div>
        </div>

        <div class="volver-inicio">
            <a href="${pageContext.request.contextPath}/tickets">← Volver al inicio</a>
        </div>
    </div>

    <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp" />

</body>
</html>