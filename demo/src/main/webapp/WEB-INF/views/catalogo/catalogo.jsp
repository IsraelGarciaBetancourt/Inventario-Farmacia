<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Catálogo - Farmacia</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-basico.css">
</head>
<body>
  <div class="contenedor">
    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">
      <header class="encabezado">
        <h1>Catálogo de Productos</h1>
        <div class="insignia-usuario">
          <span>Admin</span>
          <div class="avatar"></div>
        </div>
      </header>

      <!-- 🔍 BUSCADOR -->
      <div class="tarjeta">
        <h3 class="mb">🔍 Buscar Producto</h3>
        <div class="flex espacio flex-envolver">
          <div class="grupo-formulario flex-1">
            <label>Buscar</label>
            <input type="text" class="control-formulario" placeholder="Código o nombre del producto">
          </div>
          <div class="grupo-formulario flex-1">
            <label>Categoría</label>
            <select class="control-formulario">
              <option>-- Todas --</option>
              <c:forEach var="cat" items="${categorias}">
                <option>${cat.nombre}</option>
              </c:forEach>
            </select>
          </div>
          <div class="grupo-formulario flex-1">
            <label>Estado</label>
            <select class="control-formulario">
              <option>-- Todos --</option>
              <option>Activo</option>
              <option>Inactivo</option>
            </select>
          </div>
        </div>
        <button class="boton boton-primario">🔍 Buscar</button>
      </div>

      <!-- 📋 LISTA DE PRODUCTOS -->
      <div class="flex justificar-entre items-centrado mb-2">
        <h2>Productos</h2>
        <a class="boton boton-primario" href="#modal-crear-producto">+ Nuevo Producto</a>
      </div>

      <div class="tarjeta">
        <table class="tabla">
          <thead>
            <tr>
              <th>Código</th>
              <th>Nombre</th>
              <th>Categoría</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="p" items="${productos}">
              <tr style="${!p.activo ? 'opacity:0.6;' : ''}">
                <td><strong>${p.codigo}</strong></td>
                <td>${p.nombre}</td>
                <td>${p.nombreCategoria}</td>
                <td>
                  <span class="insignia ${p.activo ? 'insignia-exito' : 'insignia-peligro'}">
                    ${p.activo ? 'Activo' : 'Inactivo'}
                  </span>
                </td>
                <td>
                  <a href="#modal-editar-producto"
                     class="boton"
                     onclick="editarProducto('${p.id}', '${p.codigo}', '${p.nombre}', '${p.idCategoria}', '${p.activo}')">
                     Editar
                  </a>
                  <a href="${pageContext.request.contextPath}/catalogo/toggle/${p.id}"
                     class="boton ${p.activo ? 'boton-peligro' : 'boton-exito'}">
                     ${p.activo ? 'Desactivar' : 'Activar'}
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>

  <!-- 🟢 MODAL CREAR -->
  <div id="modal-crear-producto" class="modal">
    <a href="#" class="fondo"></a>
    <div class="tarjeta-modal">
      <div class="encabezado-modal">
        <h2>Nuevo producto</h2>
        <a href="#" class="cerrar">✕</a>
      </div>

      <form action="${pageContext.request.contextPath}/catalogo/guardar" method="post">
        <div class="cuerpo-modal">
          <div class="grupo-formulario">
            <label>Código</label>
            <input type="text" name="codigo" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Nombre</label>
            <input type="text" name="nombre" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Categoría</label>
            <select name="idCategoria" class="control-formulario" required>
              <option value="">-- Seleccionar --</option>
              <c:forEach var="cat" items="${categorias}">
                <option value="${cat.id}">${cat.nombre}</option>
              </c:forEach>
            </select>
          </div>

          <div class="grupo-formulario">
            <label>Estado</label>
            <select name="activo" class="control-formulario">
              <option value="true" selected>Activo</option>
              <option value="false">Inactivo</option>
            </select>
          </div>
        </div>

        <div class="pie-modal">
          <a href="#" class="boton">Cancelar</a>
          <button type="submit" class="boton boton-primario">Guardar</button>
        </div>
      </form>
    </div>
  </div>

  <!-- 🟡 MODAL EDITAR -->
  <div id="modal-editar-producto" class="modal">
    <a href="#" class="fondo"></a>
    <div class="tarjeta-modal">
      <div class="encabezado-modal">
        <h2>Editar producto</h2>
        <a href="#" class="cerrar">✕</a>
      </div>

      <form action="${pageContext.request.contextPath}/catalogo/actualizar" method="post">
        <input type="hidden" id="edit-id" name="id">
        <div class="cuerpo-modal">
          <div class="grupo-formulario">
            <label>Código</label>
            <input id="edit-codigo" type="text" name="codigo" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Nombre</label>
            <input id="edit-nombre" type="text" name="nombre" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Categoría</label>
            <select id="edit-categoria" name="idCategoria" class="control-formulario" required>
              <c:forEach var="cat" items="${categorias}">
                <option value="${cat.id}">${cat.nombre}</option>
              </c:forEach>
            </select>
          </div>

          <div class="grupo-formulario">
            <label>Estado</label>
            <select id="edit-activo" name="activo" class="control-formulario">
              <option value="true">Activo</option>
              <option value="false">Inactivo</option>
            </select>
          </div>
        </div>

        <div class="pie-modal">
          <a href="#" class="boton">Cancelar</a>
          <button type="submit" class="boton boton-primario">Guardar Cambios</button>
        </div>
      </form>
    </div>
  </div>

  <script>
    function editarProducto(id, codigo, nombre, idCategoria, activo) {
      document.getElementById("edit-id").value = id;
      document.getElementById("edit-codigo").value = codigo;
      document.getElementById("edit-nombre").value = nombre;
      document.getElementById("edit-categoria").value = idCategoria;
      document.getElementById("edit-activo").value = (activo === true || activo === 'true') ? 'true' : 'false';
    }
  </script>

</body>
</html>
