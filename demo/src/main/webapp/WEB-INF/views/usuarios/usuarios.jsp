<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestión de Usuarios</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-basico.css">
</head>

<body>
  <div class="contenedor">
    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">
      <header class="encabezado">
        <h1>Gestión de Usuarios</h1>
        <div class="insignia-usuario">
          <span>${sessionScope.nombreUsuario}</span>
          <div class="avatar"></div>
        </div>
      </header>

      <!-- ENCABEZADO -->
      <div class="flex justificar-entre items-centrado mb-2">
        <h2>Usuarios</h2>
        <a class="boton boton-primario" href="#modal-crear-usuario">+ Nuevo Usuario</a>
      </div>

      <!-- TABLA -->
      <div class="tarjeta">
        <table class="tabla">
          <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Nombre Completo</th>
              <th>Rol</th>
              <th>Password</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>

          <tbody>
            <c:forEach var="u" items="${usuarios}">
              <tr style="${!u.activo ? 'opacity:0.6;' : ''}">
                <td>${u.id}</td>
                <td><strong>${u.username}</strong></td>
                <td>${u.nombreCompleto}</td>
                <td>${u.rol}</td>
                <td>${u.passwordHash}</td>

                <td>
                  <span class="insignia ${u.activo ? 'insignia-exito' : 'insignia-peligro'}">
                    ${u.activo ? 'Activo' : 'Inactivo'}
                  </span>
                </td>

                <td>
                  <a href="#modal-editar-usuario"
                     class="boton"
                     onclick="editarUsuario('${u.id}', '${u.username}', '${u.nombreCompleto}', '${u.rol}', '${u.passwordHash}', '${u.activo}')">
                     Editar
                  </a>

                  <a href="${pageContext.request.contextPath}/usuarios/toggle/${u.id}"
                     class="boton ${u.activo ? 'boton-peligro' : 'boton-exito'}">
                     ${u.activo ? 'Desactivar' : 'Activar'}
                  </a>
                </td>

              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>

  <!-- MODAL CREAR -->
  <div id="modal-crear-usuario" class="modal">
    <a href="#" class="fondo"></a>

    <div class="tarjeta-modal">
      <div class="encabezado-modal">
        <h2>Nuevo Usuario</h2>
        <a href="#" class="cerrar">✕</a>
      </div>

      <form action="${pageContext.request.contextPath}/usuarios/guardar" method="post">
        <div class="cuerpo-modal">

          <div class="grupo-formulario">
            <label>Username</label>
            <input type="text" name="username" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Nombre Completo</label>
            <input type="text" name="nombreCompleto" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Rol</label>
            <select name="rol" class="control-formulario" required>
              <option value="ADMIN">ADMIN</option>
              <option value="ALMACENERO">ALMACENERO</option>
            </select>
          </div>

          <div class="grupo-formulario">
            <label>Password</label>
            <input type="text" name="passwordHash" class="control-formulario" required>
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


  <!-- MODAL EDITAR -->
  <div id="modal-editar-usuario" class="modal">
    <a href="#" class="fondo"></a>

    <div class="tarjeta-modal">
      <div class="encabezado-modal">
        <h2>Editar Usuario</h2>
        <a href="#" class="cerrar">✕</a>
      </div>

      <form action="${pageContext.request.contextPath}/usuarios/actualizar" method="post">

        <input type="hidden" id="edit-id" name="id">

        <div class="cuerpo-modal">

          <div class="grupo-formulario">
            <label>Username</label>
            <input id="edit-username" type="text" name="username" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Nombre Completo</label>
            <input id="edit-nombre" type="text" name="nombreCompleto" class="control-formulario" required>
          </div>

          <div class="grupo-formulario">
            <label>Rol</label>
            <select id="edit-rol" name="rol" class="control-formulario" required>
              <option value="ADMIN">ADMIN</option>
              <option value="ALMACENERO">ALMACENERO</option>
            </select>
          </div>

          <div class="grupo-formulario">
            <label>Password</label>
            <input id="edit-password" type="text" name="passwordHash" class="control-formulario" required>
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
    function editarUsuario(id, username, nombre, rol, password, activo) {
      document.getElementById("edit-id").value = id;
      document.getElementById("edit-username").value = username;
      document.getElementById("edit-nombre").value = nombre;
      document.getElementById("edit-rol").value = rol;
      document.getElementById("edit-password").value = password;
      document.getElementById("edit-activo").value = (activo === true || activo === 'true') ? 'true' : 'false';
    }
  </script>

</body>
</html>
