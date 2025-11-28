<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Productos Catálogo - Farmacia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Gestión de Productos (Catálogo)</h1>
        </header>

        <div class="flex justificar-entre items-centrado mb-2">
            <h2>Productos Registrados</h2>
            <a href="${pageContext.request.contextPath}/productoCatalogo/crear"
               class="boton boton-primario">+ Nuevo Producto</a>
        </div>

        <div class="tarjeta">
            <table class="tabla">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Categoría</th>
                    <th>Estado</th>
                    <th class="texto-derecha">Acciones</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="p" items="${productos}">

                    <tr class="${!p.activo ? 'opacity-50' : ''}">

                        <td>${p.id}</td>
                        <td><strong>${p.codigo}</strong></td>
                        <td>${p.nombre}</td>
                        <td>${p.categoria.nombre}</td>

                        <td>
                            <span class="insignia ${p.activo ? 'insignia-exito' : 'insignia-peligro'}">
                                ${p.activo ? 'Activo' : 'Inactivo'}
                            </span>
                        </td>

                        <td class="texto-derecha">

                            <a href="${pageContext.request.contextPath}/productoCatalogo/editar/${p.id}"
                               class="boton boton-exito">
                                Editar
                            </a>

                            <c:choose>

                                <c:when test="${p.activo}">
                                    <a href="${pageContext.request.contextPath}/productoCatalogo/desactivar/${p.id}"
                                       class="boton boton-peligro">
                                        Desactivar
                                    </a>
                                </c:when>

                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/productoCatalogo/activar/${p.id}"
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
