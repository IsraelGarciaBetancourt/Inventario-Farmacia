<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<div class="contenedor" style="justify-content:center; align-items:center;">

    <div class="tarjeta" style="max-width: 400px; width:100%;">

        <h2 class="mb-2">Iniciar Sesión</h2>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="grupo-formulario">
                <label>Usuario</label>
                <input type="text" name="username" class="control-formulario" required>
            </div>

            <div class="grupo-formulario">
                <label>Contraseña</label>
                <input type="password" name="password" class="control-formulario" required>
            </div>

            <c:if test="${not empty error}">
                <div class="alerta alerta-peligro">
                    ${error}
                </div>
            </c:if>

            <div class="flex justificar-fin mt">
                <button class="boton boton-primario" type="submit">Ingresar</button>
            </div>

        </form>

    </div>

</div>

</body>
</html>
