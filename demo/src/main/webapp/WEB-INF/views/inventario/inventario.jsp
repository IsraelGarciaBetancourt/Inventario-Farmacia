<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inventario - Farmacia</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
  <div class="contenedor">
    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">
      <header class="encabezado">
        <h1>Inventario</h1>
        <div class="insignia-usuario">
          <span>${sessionScope.nombreUsuario}</span>
          <div class="avatar"></div>
        </div>
      </header>

      <!-- Mensajes de éxito o error -->
      <c:if test="${not empty mensaje}">
        <div class="alerta ${tipoMensaje == 'success' ? 'alerta-exito' : 'alerta-peligro'}" id="alertaMensaje">
          ${mensaje}
        </div>
      </c:if>

      <!-- Barra de búsqueda-->
      <div class="tarjeta">
        <h3 class="mb">🔍 Buscar en Inventario</h3>
        <div class="flex espacio items-centrado flex-envolver">
          <div class="grupo-formulario flex-1">
            <input type="text" id="buscarInventario" class="control-formulario" placeholder="Código, nombre o presentación">
          </div>
          <div style="margin-top: 1.5rem;">
            <button class="boton boton-primario" onclick="filtrarTabla()">Buscar</button>
          </div>
        </div>
      </div>

      <!-- Tabla de inventario -->
      <div class="tarjeta">
        <table class="tabla" id="tablaInventario">
          <thead>
            <tr>
              <th>Código</th>
              <th>Nombre</th>
              <th>Categoría</th>
              <th class="texto-derecha">Stock Actual</th>
              <th>Valor del Stock</th>
              <th>Estado</th>
              <th class="texto-centro">Acción</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="producto" items="${productos}">
              <tr>
                <td><strong>${producto.codigoProducto}</strong></td>
                <td>${producto.nombreProducto}</td>
                <td>${producto.categoriaNombre != null ? producto.categoriaNombre : 'Sin categoría'}</td>
                <td class="texto-derecha">
                  <strong
                    <c:if test="${producto.existencias < 20}">style="color: var(--peligro);"</c:if>
                    <c:if test="${producto.existencias >= 20 && producto.existencias < 50}">style="color: var(--advertencia);"</c:if>
                  >
                    ${producto.existencias}
                  </strong>
                </td>
                <td>
                  S/. <fmt:formatNumber value="${producto.valorStock}" minFractionDigits="2" maxFractionDigits="2"/>
                </td>
                <td>
                  <span class="insignia ${producto.activo ? 'insignia-exito' : 'insignia-peligro'}">
                    ${producto.activo ? 'Activo' : 'Inactivo'}
                  </span>
                </td>
                <td class="texto-centro">
                  <form action="${pageContext.request.contextPath}/inventario/cambiar-estado" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="${producto.id}">
                    <button type="submit"
                            class="boton boton-pequeno ${producto.activo ? 'boton-peligro' : 'boton-exito'}"
                            onclick="return confirm('¿Está seguro de ${producto.activo ? 'desactivar' : 'activar'} este producto?')">
                      ${producto.activo ? '🚫 Desactivar' : '✅ Activar'}
                    </button>
                  </form>
                </td>
              </tr>
            </c:forEach>
            <c:if test="${empty productos}">
              <tr>
                <td colspan="7" class="texto-centro" style="padding: 2rem; color: var(--texto-apagado);">
                  No hay productos en el inventario
                </td>
              </tr>
            </c:if>
          </tbody>
        </table>
      </div>

      <!-- Resumen opcional (solo lectura) -->
      <div class="estadisticas">
        <div class="estadistica">
          <div class="etiqueta-estadistica">Unidades totales</div>
          <div class="valor-estadistica">${totalUnidades}</div>
          <p class="texto-pequeno">sumadas en su unidad física</p>
        </div>
        <div class="estadistica">
          <div class="etiqueta-estadistica">Bajo stock</div>
          <div class="valor-estadistica">${bajoStock}</div>
          <p class="texto-pequeno">por debajo del mínimo (50)</p>
        </div>
      </div>
    </main>
  </div>

  <script>
    // Función para filtrar la tabla
    function filtrarTabla() {
      const input = document.getElementById('buscarInventario');
      const filter = input.value.toUpperCase();
      const table = document.getElementById('tablaInventario');
      const tr = table.getElementsByTagName('tr');

      for (let i = 1; i < tr.length; i++) {
        const tdCodigo = tr[i].getElementsByTagName('td')[0];
        const tdNombre = tr[i].getElementsByTagName('td')[1];
        const tdCategoria = tr[i].getElementsByTagName('td')[2];

        if (tdCodigo && tdNombre && tdCategoria) {
          const textoCodigo = tdCodigo.textContent || tdCodigo.innerText;
          const textoNombre = tdNombre.textContent || tdNombre.innerText;
          const textoCategoria = tdCategoria.textContent || tdCategoria.innerText;

          if (textoCodigo.toUpperCase().indexOf(filter) > -1 ||
              textoNombre.toUpperCase().indexOf(filter) > -1 ||
              textoCategoria.toUpperCase().indexOf(filter) > -1) {
            tr[i].style.display = '';
          } else {
            tr[i].style.display = 'none';
          }
        }
      }
    }

    // Filtrar mientras se escribe
    document.getElementById('buscarInventario').addEventListener('keyup', filtrarTabla);

    // Auto-ocultar mensajes después de 5 segundos
    window.addEventListener('DOMContentLoaded', function() {
      const alerta = document.getElementById('alertaMensaje');
      if (alerta) {
        setTimeout(function() {
          alerta.style.transition = 'opacity 0.5s';
          alerta.style.opacity = '0';
          setTimeout(function() {
            alerta.remove();
          }, 500);
        }, 5000);
      }
    });
  </script>
</body>
</html>