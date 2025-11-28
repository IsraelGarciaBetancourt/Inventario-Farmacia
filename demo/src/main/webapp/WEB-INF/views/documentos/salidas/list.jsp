<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Salidas - Documentos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Salidas de Productos</h1>
        </header>

        <div class="flex justificar-entre items-centrado mb-2">
            <h2>Documentos de Salida</h2>

            <a href="${pageContext.request.contextPath}/documentos/salidas/crear"
               class="boton boton-primario">
                + Nueva Salida
            </a>
        </div>

        <div class="tarjeta">

            <table class="tabla">
                <thead>
                <tr>
                    <th>N° Documento</th>
                    <th>Fecha</th>
                    <th>Registrado por</th>
                    <th>Total Cantidad</th>
                    <th class="texto-derecha">Acciones</th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="d" items="${items}">
                    <tr>
                        <td><strong>${d.numeroDocumento}</strong></td>
                        <td>${d.fecha}</td>
                        <td>${d.usuario.nombreCompleto}</td>
                        <td><strong>${d.totalCantidad}</strong></td>

                        <td class="texto-derecha">
                            <a href="${pageContext.request.contextPath}/documentos/detalles/${d.id}"
                               class="boton boton-exito">
                                Ver Detalles
                            </a>
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
