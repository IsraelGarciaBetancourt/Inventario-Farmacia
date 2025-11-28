<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Detalles del Documento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Detalles del Documento</h1>
        </header>

        <div class="tarjeta">

            <!-- CABECERA DEL DOCUMENTO -->
            <div class="grupo-formulario">
                <label>N° Documento</label>
                <input type="text" class="control-formulario"
                       value="${documento.numeroDocumento}" disabled>
            </div>

            <div class="grupo-formulario">
                <label>Tipo de Movimiento</label>
                <input type="text" class="control-formulario"
                       value="${documento.tipoMovimiento}" disabled>
            </div>

            <div class="grupo-formulario">
                <label>Fecha</label>
                <input type="text" class="control-formulario"
                       value="${documento.fecha}" disabled>
            </div>

            <div class="grupo-formulario">
                <label>Registrado por</label>
                <input type="text" class="control-formulario"
                       value="${documento.usuario.nombreCompleto}" disabled>
            </div>

            <div class="grupo-formulario">
                <label>Observación</label>
                <textarea class="control-formulario" disabled>${documento.observacion}</textarea>
            </div>

            <hr>

            <!-- TABLA DETALLES -->
            <h3>Productos Detallados</h3>

            <table class="tabla mt-2">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                    </tr>
                </thead>

                <tbody>
                <c:forEach var="d" items="${detalles}">
                    <tr>
                        <td>${d.productoCatalogo.codigo}</td>
                        <td>${d.productoCatalogo.nombre}</td>
                        <td><strong>${d.cantidad}</strong></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <div class="flex justificar-fin mt">
                <a href="${pageContext.request.contextPath}/documentos/${documento.tipoMovimiento == 'INGRESO' ? 'ingresos' : 'salidas'}/list"
                   class="boton">
                    Volver
                </a>
            </div>

        </div>

    </main>

</div>

</body>
</html>
