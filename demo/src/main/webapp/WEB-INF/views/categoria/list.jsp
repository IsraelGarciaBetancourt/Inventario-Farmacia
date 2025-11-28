<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Categorías - Farmacia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Gestión de Categorías</h1>
        </header>

        <div class="flex justificar-entre items-centrado mb-2">
            <h2>Categorías Registradas</h2>
            <a href="${pageContext.request.contextPath}/categorias/crear"
               class="boton boton-primario">+ Nueva Categoría</a>
        </div>

        <div class="tarjeta">
            <table class="tabla">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Estado</th>
                    <th class="texto-derecha">Acciones</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="categoria" items="${categorias}">

                    <!-- Opacidad si está inactiva -->
                    <tr class="${!categoria.activo ? 'opacity-50' : ''}">

                        <td>${categoria.id}</td>
                        <td><strong>${categoria.nombre}</strong></td>

                        <td>
                            <span class="insignia ${categoria.activo ? 'insignia-exito' : 'insignia-peligro'}">
                                ${categoria.activo ? 'Activo' : 'Inactivo'}
                            </span>
                        </td>

                        <td class="texto-derecha">

                            <a href="${pageContext.request.contextPath}/categorias/editar/${categoria.id}"
                               class="boton boton-exito">
                                Editar
                            </a>

                            <c:choose>

                                <c:when test="${categoria.activo}">
                                    <a href="${pageContext.request.contextPath}/categorias/eliminar/${categoria.id}"
                                       class="boton boton-peligro">
                                        Desactivar
                                    </a>
                                </c:when>

                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/categorias/activar/${categoria.id}"
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
