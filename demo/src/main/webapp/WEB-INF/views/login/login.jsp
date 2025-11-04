<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login - Sistema Farmacia</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css">
</head>
<body>
  <div class="contenedor-login">
    <div class="tarjeta tarjeta-login">
      <div class="logo-farmacia">
        <h1>💊 Sistema Farmacia Juley</h1>
        <p>Gestión de inventario</p>
      </div>

      <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="grupo-formulario">
          <label>Usuario</label>
          <input type="text" class="control-formulario" name="username" required>
        </div>

        <div class="grupo-formulario">
          <label>Contraseña</label>
          <input type="password" class="control-formulario" name="password" required>
        </div>

        <button type="submit" class="boton boton-primario">Ingresar</button>

        <c:if test="${not empty error}">
          <p class="texto-error">${error}</p>
        </c:if>
      </form>

      <div class="pie-login">
        <p>&copy; 2025 Sistema Farmacia. Todos los derechos reservados.</p>
      </div>
    </div>
  </div>
</body>
</html>