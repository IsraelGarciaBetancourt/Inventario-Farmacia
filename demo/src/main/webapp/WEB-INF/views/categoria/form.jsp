<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Categoría</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <c:set var="editando" value="${categoria.id > 0}" />

        <c:choose>
            <c:when test="${editando}">
                <c:set var="titulo" value="Editar Categoría" />
                <c:set var="actionUrl" value="${pageContext.request.contextPath}/categorias/actualizar" />
            </c:when>
            <c:otherwise>
                <c:set var="titulo" value="Nueva Categoría" />
                <c:set var="actionUrl" value="${pageContext.request.contextPath}/categorias/guardar" />
            </c:otherwise>
        </c:choose>

        <header class="encabezado">
            <h1>${titulo}</h1>
        </header>

        <div class="tarjeta">
            <form action="${actionUrl}" method="post">

                <c:if test="${editando}">
                    <input type="hidden" name="id" value="${categoria.id}">
                </c:if>

                <div class="grupo-formulario">
                    <label for="nombre">Nombre</label>
                    <input id="nombre"
                           name="nombre"
                           type="text"
                           class="control-formulario"
                           placeholder="Ej: Analgésicos"
                           value="${categoria.nombre}"
                           required>
                </div>

                <c:if test="${editando}">
                    <div class="grupo-formulario">
                        <label>Estado</label>
                        <select name="activo" class="control-formulario">
                            <option value="true"  ${categoria.activo ? 'selected' : ''}>Activo</option>
                            <option value="false" ${!categoria.activo ? 'selected' : ''}>Inactivo</option>
                        </select>
                    </div>
                </c:if>

                <div class="flex justificar-fin espacio mt">
                    <a href="${pageContext.request.contextPath}/categorias/list" class="boton">
                        Cancelar
                    </a>
                    <button type="submit" class="boton boton-primario">
                        Guardar
                    </button>
                </div>

            </form>
        </div>

    </main>
</div>
</body>
</html>
