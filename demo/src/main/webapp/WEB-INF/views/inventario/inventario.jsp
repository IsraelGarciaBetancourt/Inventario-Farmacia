<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inventario - Farmacia</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-basico.css">
</head>
<body>
  <div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />
    <main class="principal">
      <header class="encabezado">
        <h1>Inventario</h1>
        <div class="insignia-usuario">
          <span>Admin</span>
          <div class="avatar"></div>
        </div>
      </header>

      <!-- Barra de búsqueda-->
      <div class="tarjeta">
        <h3 class="mb">🔍 Buscar en Inventario</h3>
        <div class="flex espacio items-centrado flex-envolver">
          <div class="grupo-formulario flex-1">
            <input type="text" class="control-formulario" placeholder="Código, nombre o presentación">
          </div>
          <div style="margin-top: 1.5rem;">
            <button class="boton boton-primario">Buscar</button>
          </div>
        </div>
      </div>

      <!-- Tabla de inventario -->
      <div class="tarjeta">
        <table class="tabla">
          <thead>
            <tr>
              <th>Código</th>
              <th>Nombre</th>
              <th>Categoría</th>
              <th class="texto-derecha">Stock Actual</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="p" items="${inventario}">
              <tr>
                <td><strong>${p.codigo}</strong></td>
                <td>${p.nombre}</td>
                <td>${p.categoriaNombre}</td>
                <td class="texto-derecha"><strong>${p.existencias}</strong></td>
                <td>
                  <c:choose>
                    <c:when test="${p.activo}">
                      <span class="insignia insignia-exito">Activo</span>
                    </c:when>
                    <c:otherwise>
                      <span class="insignia insignia-peligro">Inactivo</span>
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <!-- Resumen opcional (solo lectura) -->
      <div class="estadisticas">
        <div class="estadistica">
          <div class="etiqueta-estadistica">SKUs activos</div>
          <div class="valor-estadistica">4</div>
          <p class="texto-pequeno">del catálogo</p>
        </div>
        <div class="estadistica">
          <div class="etiqueta-estadistica">Unidades totales</div>
          <div class="valor-estadistica">525</div>
          <p class="texto-pequeno">sumadas en su unidad física</p>
        </div>
        <div class="estadistica">
          <div class="etiqueta-estadistica">Bajo stock</div>
          <div class="valor-estadistica">2</div>
          <p class="texto-pequeno">por debajo del mínimo</p>
        </div>
      </div>
    </main>
  </div>
</body>
</html>
