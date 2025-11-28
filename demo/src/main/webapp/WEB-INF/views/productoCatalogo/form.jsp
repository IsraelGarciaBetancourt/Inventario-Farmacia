<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Formulario Producto Catálogo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <!-- Detectar si estamos editando -->
        <c:set var="editando" value="${producto.id > 0}" />

        <c:choose>
            <c:when test="${editando}">
                <c:set var="titulo" value="Editar Producto del Catálogo" />
                <c:set var="actionUrl" value="${pageContext.request.contextPath}/productoCatalogo/actualizar" />
            </c:when>
            <c:otherwise>
                <c:set var="titulo" value="Nuevo Producto del Catálogo" />
                <c:set var="actionUrl" value="${pageContext.request.contextPath}/productoCatalogo/guardar" />
            </c:otherwise>
        </c:choose>

        <header class="encabezado">
            <h1>${titulo}</h1>
        </header>

        <div class="tarjeta">

            <form action="${actionUrl}" method="post">

                <!-- ID oculto cuando editamos -->
                <c:if test="${editando}">
                    <input type="hidden" name="id" value="${producto.id}">
                </c:if>

                <!-- CÓDIGO -->
                <div class="grupo-formulario">
                    <label for="codigo">Código</label>
                    <input id="codigo" type="text" name="codigo"
                           class="control-formulario"
                           value="${producto.codigo}"
                           placeholder="Ej: P-001"
                           required>
                </div>

                <!-- NOMBRE -->
                <div class="grupo-formulario">
                    <label for="nombre">Nombre del Producto</label>
                    <input id="nombre" type="text" name="nombre"
                           class="control-formulario"
                           value="${producto.nombre}"
                           placeholder="Ej: Paracetamol 500mg"
                           required>
                </div>

                <!-- SELECT DE CATEGORÍAS ACTIVAS -->
                <div class="grupo-formulario">
                    <label for="categoria">Categoría</label>
                    <select id="categoria" name="categoria.id"
                            class="control-formulario" required>

                        <option value="">Seleccione una categoría</option>

                        <c:forEach var="cat" items="${categorias}">
                            <c:if test="${cat.activo}">
                                <option value="${cat.id}"
                                    ${producto.categoria.id == cat.id ? "selected" : ""}>
                                    ${cat.nombre}
                                </option>
                            </c:if>
                        </c:forEach>

                    </select>
                </div>

                <!-- ESTADO (solo al editar) -->
                <c:if test="${editando}">
                    <div class="grupo-formulario">
                        <label>Estado</label>
                        <select name="activo" class="control-formulario">
                            <option value="true"  ${producto.activo ? "selected" : ""}>Activo</option>
                            <option value="false" ${!producto.activo ? "selected" : ""}>Inactivo</option>
                        </select>
                    </div>
                </c:if>

                <!-- BOTONES -->
                <div class="flex justificar-fin espacio mt">
                    <a href="${pageContext.request.contextPath}/productoCatalogo/list" class="boton">
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
