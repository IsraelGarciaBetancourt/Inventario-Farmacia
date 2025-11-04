<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Salidas - Farmacia</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-basico.css">
</head>
<body>
  <div class="contenedor">
    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">
      <header class="encabezado">
        <h1>Registro de Salidas</h1>
        <div class="insignia-usuario">
          <span>Almacenero1</span>
          <div class="avatar"></div>
        </div>
      </header>

      <!-- 📝 NUEVA SALIDA -->
      <div class="tarjeta">
        <h3 class="mb">📝 Nueva Salida</h3>
        <form action="${pageContext.request.contextPath}/salidas/crear" method="post">
          <div class="flex espacio flex-envolver">
            <div class="grupo-formulario flex-1">
              <label>Número de Documento</label>
              <input type="text" name="numeroDocumento" class="control-formulario" value="${nuevoNumero}" readonly>
            </div>
            <div class="grupo-formulario flex-1">
              <label>Fecha</label>
              <input type="date" name="fecha" class="control-formulario" value="${fechaActual}" required>
            </div>
          </div>

          <div class="grupo-formulario">
            <label>Observaciones</label>
            <textarea name="observacion" class="control-formulario" rows="2" placeholder="Opcional..."></textarea>
          </div>

          <input type="hidden" name="idUsuario" value="1">

          <!-- 📦 PRODUCTOS A RETIRAR -->
          <h4 class="mt-2 mb">📦 Productos a Retirar</h4>
          <table class="tabla" id="tablaProductos">
            <thead>
              <tr>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <select class="control-formulario" required>
                    <option value="">-- Seleccionar --</option>
                    <c:forEach var="producto" items="${productos}">
                      <option value="${producto}">${producto}</option>
                    </c:forEach>
                  </select>
                </td>
                <td><input type="number" class="control-formulario cantidad" min="1" value="0" required></td>
                <td><button type="button" class="boton boton-peligro" onclick="eliminarFila(this)">Eliminar</button></td>
              </tr>
            </tbody>
          </table>

          <div class="flex justificar-entre items-centrado mt-2">
            <button type="button" class="boton" id="agregarFila">+ Agregar Producto</button>
            <div><strong>Total productos: <span id="totalProductos">1</span> | Total unidades: <span id="totalUnidades">0</span></strong></div>
          </div>

          <div class="flex justificar-fin espacio mt-2">
            <button type="reset" class="boton">Cancelar</button>
            <button type="submit" class="boton boton-exito">Registrar Salida</button>
          </div>
        </form>
      </div>

      <!-- 📋 HISTORIAL DE SALIDAS -->
      <div class="tarjeta">
        <h3 class="mb">📋 Historial de Salidas</h3>
        <table class="tabla">
          <thead>
            <tr>
              <th>ID</th>
              <th>Documento</th>
              <th>Fecha</th>
              <th>Observación</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="salida" items="${salidas}">
              <tr>
                <td>${salida.id}</td>
                <td><strong>${salida.numeroDocumento}</strong></td>
                <td>${salida.fecha}</td>
                <td>${salida.observacion}</td>
                <td>
                  <span class="insignia ${salida.activo ? 'insignia-exito' : 'insignia-peligro'}">
                    ${salida.activo ? 'Activo' : 'Inactivo'}
                  </span>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>

  <!-- ⚙️ JS para totales -->
  <script>
    const tabla = document.querySelector("#tablaProductos tbody");
    const botonAgregar = document.getElementById("agregarFila");
    const totalProductos = document.getElementById("totalProductos");
    const totalUnidades = document.getElementById("totalUnidades");

    function recalcular() {
      let filas = tabla.querySelectorAll("tr").length;
      let unidades = 0;
      tabla.querySelectorAll(".cantidad").forEach(c => unidades += parseInt(c.value) || 0);
      totalProductos.textContent = filas;
      totalUnidades.textContent = unidades;
    }

    tabla.addEventListener("input", recalcular);

    botonAgregar.addEventListener("click", () => {
      const fila = tabla.rows[0].cloneNode(true);
      fila.querySelectorAll("input").forEach(i => i.value = "");
      tabla.appendChild(fila);
      recalcular();
    });

    function eliminarFila(btn) {
      if (tabla.rows.length > 1) {
        btn.closest("tr").remove();
        recalcular();
      }
    }
  </script>
</body>
</html>
