<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Usuarios - Farmacia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Gestión de Usuarios</h1>
        </header>

        <div class="flex justificar-entre items-centrado mb-2">
            <h2>Usuarios Registrados</h2>
            <a href="${pageContext.request.contextPath}/usuarios/crear"
               class="boton boton-primario">+ Nuevo Usuario</a>
        </div>

        <div class="tarjeta">
            <table class="tabla">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Nombre Completo</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th class="texto-derecha">Acciones</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="u" items="${usuarios}">

                    <!-- Opacidad si está inactivo -->
                    <tr class="${!u.activo ? 'opacity-50' : ''}">

                        <td>${u.id}</td>
                        <td><strong>${u.username}</strong></td>
                        <td>${u.nombreCompleto}</td>
                        <td>${u.rol}</td>

                        <td>
                            <span class="insignia ${u.activo ? 'insignia-exito' : 'insignia-peligro'}">
                                ${u.activo ? 'Activo' : 'Inactivo'}
                            </span>
                        </td>

                        <td class="texto-derecha">

                            <!-- Botón Editar (siempre disponible) -->
                            <a href="${pageContext.request.contextPath}/usuarios/editar/${u.id}"
                               class="boton boton-exito">
                                Editar
                            </a>

                            <!-- Switch: Activar / Desactivar -->
                            <c:choose>

                                <c:when test="${u.activo}">
                                    <a href="${pageContext.request.contextPath}/usuarios/desactivar/${u.id}"
                                       class="boton boton-peligro">
                                        Desactivar
                                    </a>
                                </c:when>

                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/usuarios/activar/${u.id}"
                                       class="boton boton-exito">
                                        Activar
                                    </a>
                                </c:otherwise>

                            </c:choose>

                        </td>
                    </tr>

                </c:forEach>
                </tbody>

            </table>
        </div>

    </main>
</div>

</body>
</html>
