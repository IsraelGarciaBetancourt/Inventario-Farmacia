<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Categorías - Farmacia</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-basico.css">
</head>
<body>
  <div class="contenedor">
  <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />
    <main class="principal">
      <header class="encabezado">
        <h1>Gestión de Categorías</h1>
        <div class="insignia-usuario">
          <span>Admin</span>
          <div class="avatar"></div>
        </div>
      </header>

      <div class="flex justificar-entre items-centrado mb-2">
        <h2>Categorías de Productos</h2>
        <button class="boton boton-primario"><a href="#modal-crear-categoria">+ Nueva Categoría</a></button>
      </div>

      <div class="tarjeta">
        <h3 class="mb">🔍 Buscar Categoría</h3>
        <div class="flex espacio flex-envolver">
          <div class="grupo-formulario flex-1">
            <label>Buscar</label>
            <input type="text" class="control-formulario" placeholder="Buscar por nombre">
          </div>
        </div>
        <button class="boton boton-primario">🔍 Buscar</button>
      </div>

      <div class="tarjeta">
        <table class="tabla">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Flag</th>
              <th>Acciones</th>
            </tr>
          </thead>
            <tbody>
              <c:forEach var="categoria" items="${categorias}">
                <tr>
                  <td>${categoria.id}</td>
                  <td><strong>${categoria.nombre}</strong></td>
                  <td>
                    <span class="insignia ${categoria.activo ? 'insignia-exito' : 'insignia-peligro'}">
                      ${categoria.activo ? 'Activo' : 'Inactivo'}
                    </span>
                  </td>
                  <td>
                    <!-- ✅ Cada botón lleva los datos de su categoría -->
                    <button type="button"
                            class="boton boton-editar"
                            data-id="${categoria.id}"
                            data-nombre="${categoria.nombre}"
                            data-activo="${categoria.activo}">
                      Editar
                    </button>

                  </td>
                </tr>
              </c:forEach>
            </tbody>
        </table>
      </div>
    </main>
  </div>

  <!-- Modal Crear -->
  <div id="modal-crear-categoria" class="modal">
    <a href="#" class="fondo"></a>
    <div class="tarjeta-modal">
      <div class="encabezado-modal">
        <h2>Nueva Categoria</h2>
        <a href="#" class="cerrar">✕</a>
      </div>

      <div class="cuerpo-modal">
        <!-- ⬇️ CORREGIDO: /categorias/guardar -->
        <form action="${pageContext.request.contextPath}/categorias/guardar" method="post">
          <div class="grupo-formulario">
            <label for="nombre">Nombre</label>
            <input id="nombre" name="nombre" type="text" class="control-formulario" placeholder="Ej: Analgésicos" required>
          </div>
          <div class="pie-modal">
            <a href="#" class="boton">Cancelar</a>
            <button type="submit" class="boton boton-primario">Guardar</button>
          </div>
        </form>
      </div>
    </div>
  </div>


    <!-- Modal Editar -->
    <div id="modal-editar-categoria" class="modal">
      <a href="#" class="fondo"></a>
      <div class="tarjeta-modal">
        <div class="encabezado-modal">
          <h2>Editar Categoría</h2>
          <a href="#" class="cerrar">✕</a>
        </div>

        <div class="cuerpo-modal">
          <!-- ⬇️ CORREGIDO: /categorias/actualizar -->
          <form id="form-editar" action="${pageContext.request.contextPath}/categorias/actualizar" method="post">
            <input type="hidden" id="editar-id" name="id"> <!-- 👈 Campo oculto -->
            <div class="grupo-formulario">
              <label for="editar-nombre">Nombre</label>
              <input id="editar-nombre" name="nombre" type="text" class="control-formulario" required>
            </div>

            <div class="grupo-formulario">
              <label>Flag</label>
              <select id="editar-activo" name="activo" class="control-formulario">
                <option value="true">Activo</option>
                <option value="false">Inactivo</option>
              </select>
            </div>

            <div class="pie-modal">
              <a href="#" class="boton">Cancelar</a>
              <button type="submit" class="boton boton-primario">Guardar</button>
            </div>
          </form>
        </div>
      </div>
    </div>

<script>
document.addEventListener("DOMContentLoaded", function() {
  document.querySelectorAll(".boton-editar").forEach(btn => {
    btn.addEventListener("click", function() {
      const id = this.dataset.id;
      const nombre = this.dataset.nombre;
      const activo = this.dataset.activo === "true" || this.dataset.activo === true;

      document.getElementById("editar-id").value = id;
      document.getElementById("editar-nombre").value = nombre;
      document.getElementById("editar-activo").value = activo ? "true" : "false";

      window.location.hash = "modal-editar-categoria"; // abre tu modal CSS
    });
  });
});
</script>

</body>
</html>
