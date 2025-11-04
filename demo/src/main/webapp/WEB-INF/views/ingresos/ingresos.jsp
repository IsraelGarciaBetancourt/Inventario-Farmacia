<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registro de Ingresos</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-basico.css">
</head>
<body>
  <div class="contenedor">
    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">
      <header class="encabezado">
        <h1>Registro de Ingresos</h1>
        <div class="insignia-usuario">
          <span>Almacenero1</span>
          <div class="avatar"></div>
        </div>
      </header>

      <div class="tarjeta">
        <h3 class="mb">📝 Nuevo Ingreso</h3>
        <form action="${pageContext.request.contextPath}/ingresos/crear" method="post">
          <div class="flex espacio flex-envolver">
            <div class="grupo-formulario flex-1">
              <label>Documento</label>
              <input type="text" name="numeroDocumento" class="control-formulario"
                     value="${nuevoNumero}" readonly>
            </div>
            <div class="grupo-formulario flex-1">
              <label>Fecha</label>
              <input type="date" name="fecha" class="control-formulario"
                     value="${fechaActual}" required>
            </div>
          </div>

          <div class="grupo-formulario">
            <label>Observaciones</label>
            <textarea name="observacion" class="control-formulario" rows="2"
                      placeholder="Opcional..."></textarea>
          </div>

          <input type="hidden" name="idUsuario" value="1">

          <h4 class="mt-2 mb">📦 Productos a Ingresar</h4>
          <table class="tabla" id="tablaProductos">
            <thead>
              <tr>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Precio Unitario (S/)</th>
                <th>Subtotal</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <select class="control-formulario producto" required>
                    <option value="">-- Seleccionar --</option>
                    <c:forEach var="producto" items="${productos}">
                      <option value="${producto}">${producto}</option>
                    </c:forEach>
                  </select>
                </td>
                <td><input type="number" class="control-formulario cantidad" min="1" value="0"></td>
                <td><input type="number" class="control-formulario precio" min="0" step="0.01" value="0.00"></td>
                <td class="texto-derecha subtotal"><strong>S/ 0.00</strong></td>
                <td><button type="button" class="boton boton-peligro" onclick="eliminarFila(this)">Eliminar</button></td>
              </tr>
            </tbody>
          </table>

          <div class="flex justificar-entre items-centrado mt-2">
            <button type="button" class="boton" id="agregarFila">+ Agregar Producto</button>
            <div><strong>Total: S/ <span id="total">0.00</span></strong></div>
          </div>

          <div class="flex justificar-fin espacio mt-2">
            <button type="reset" class="boton">Cancelar</button>
            <button type="submit" class="boton boton-exito">Registrar Ingreso</button>
          </div>
        </form>
      </div>

      <div class="tarjeta">
        <h3 class="mb">📋 Historial de Ingresos</h3>
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
            <c:forEach var="ingreso" items="${ingresos}">
              <tr>
                <td>${ingreso.id}</td>
                <td><strong>${ingreso.numeroDocumento}</strong></td>
                <td>${ingreso.fecha}</td>
                <td>${ingreso.observacion}</td>
                <td>
                  <span class="insignia ${ingreso.activo ? 'insignia-exito' : 'insignia-peligro'}">
                    ${ingreso.activo ? 'Activo' : 'Inactivo'}
                  </span>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
  </div>

  <script>
    const tabla = document.querySelector("#tablaProductos tbody");
    const botonAgregar = document.getElementById("agregarFila");
    const totalSpan = document.getElementById("total");

    function calcularTotales() {
      let total = 0;
      tabla.querySelectorAll("tr").forEach(fila => {
        const cantidad = parseFloat(fila.querySelector(".cantidad").value) || 0;
        const precio = parseFloat(fila.querySelector(".precio").value) || 0;
        const subtotal = cantidad * precio;
        fila.querySelector(".subtotal strong").textContent = "S/ " + subtotal.toFixed(2);
        total += subtotal;
      });
      totalSpan.textContent = total.toFixed(2);
    }

    tabla.addEventListener("input", calcularTotales);

    botonAgregar.addEventListener("click", () => {
      const fila = tabla.rows[0].cloneNode(true);
      fila.querySelectorAll("input").forEach(i => i.value = "");
      fila.querySelector(".subtotal strong").textContent = "S/ 0.00";
      tabla.appendChild(fila);
    });

    function eliminarFila(btn) {
      if (tabla.rows.length > 1) {
        btn.closest("tr").remove();
        calcularTotales();
      }
    }
  </script>
</body>
</html>
