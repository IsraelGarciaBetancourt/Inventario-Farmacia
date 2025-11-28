<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Inventario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="contenedor">

    <!-- Barra lateral -->
    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Inventario de Productos</h1>
        </header>

        <div class="tarjeta">

            <table class="tabla">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Producto</th>
                        <th>Existencias</th>
                        <th>Estado</th>
                        <th class="texto-derecha">Acciones</th>
                    </tr>
                </thead>

                <tbody>

                <c:forEach var="item" items="${items}">

                    <tr class="${!item.activo ? 'opacity-50' : ''}">

                        <td>${item.productoCatalogo.codigo}</td>

                        <td>${item.productoCatalogo.nombre}</td>

                        <td><strong>${item.existencias}</strong></td>

                        <td>
                            <span class="insignia ${item.activo ? 'insignia-exito' : 'insignia-peligro'}">
                                ${item.activo ? 'Activo' : 'Inactivo'}
                            </span>
                        </td>

                        <td class="texto-derecha">

                            <c:choose>

                                <c:when test="${item.activo}">
                                    <a href="${pageContext.request.contextPath}/inventario/desactivar/${item.productoCatalogo.id}"
                                       class="boton boton-peligro">
                                        Desactivar
                                    </a>
                                </c:when>

                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/inventario/activar/${item.productoCatalogo.id}"
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
