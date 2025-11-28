<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Formulario Usuario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <!-- Detectar si estamos editando -->
        <c:set var="editando" value="${usuario.id > 0}" />

        <c:choose>
            <c:when test="${editando}">
                <c:set var="titulo" value="Editar Usuario" />
                <c:set var="actionUrl" value="${pageContext.request.contextPath}/usuarios/actualizar" />
            </c:when>
            <c:otherwise>
                <c:set var="titulo" value="Nuevo Usuario" />
                <c:set var="actionUrl" value="${pageContext.request.contextPath}/usuarios/guardar" />
            </c:otherwise>
        </c:choose>

        <header class="encabezado">
            <h1>${titulo}</h1>
        </header>

        <div class="tarjeta">

            <form action="${actionUrl}" method="post">

                <!-- ID oculto si editamos -->
                <c:if test="${editando}">
                    <input type="hidden" name="id" value="${usuario.id}">
                </c:if>

                <!-- USERNAME -->
                <div class="grupo-formulario">
                    <label for="username">Usuario</label>
                    <input id="username" type="text" name="username"
                           class="control-formulario"
                           placeholder="Ej: admin"
                           value="${usuario.username}"
                           required>
                </div>

                <!-- PASSWORD -->
                <div class="grupo-formulario">
                    <label for="password">Contraseña</label>
                    <input id="password" type="text" name="password"
                           class="control-formulario"
                           placeholder="Ingrese contraseña"
                           value="${usuario.password}"
                           required>
                </div>

                <!-- NOMBRE COMPLETO -->
                <div class="grupo-formulario">
                    <label for="nombreCompleto">Nombre Completo</label>
                    <input id="nombreCompleto" type="text" name="nombreCompleto"
                           class="control-formulario"
                           placeholder="Ej: Juan Pérez"
                           value="${usuario.nombreCompleto}"
                           required>
                </div>

                <!-- ROL -->
                <div class="grupo-formulario">
                    <label for="rol">Rol</label>
                    <select name="rol" id="rol" class="control-formulario" required>

                        <option value="ADMIN" ${usuario.rol == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                        <option value="USER"  ${usuario.rol == 'USER'  ? 'selected' : ''}>USER</option>

                    </select>
                </div>

                <!-- ESTADO solo al editar -->
                <c:if test="${editando}">
                    <div class="grupo-formulario">
                        <label>Estado</label>
                        <select name="activo" class="control-formulario">
                            <option value="true"  ${usuario.activo ? "selected" : ""}>Activo</option>
                            <option value="false" ${!usuario.activo ? "selected" : ""}>Inactivo</option>
                        </select>
                    </div>
                </c:if>

                <!-- Botones -->
                <div class="flex justificar-fin espacio mt">
                    <a href="${pageContext.request.contextPath}/usuarios/list" class="boton">
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
