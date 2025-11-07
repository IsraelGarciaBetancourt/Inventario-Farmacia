<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>📤 Registro de Salidas</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal-basico.css">

  <!-- Fix de legibilidad del select en tema oscuro -->
  <style>
    select.control-formulario,
    select.control-formulario option{
      color:#fff;
      background:#0f172a;
    }
  </style>
</head>
<body>
<div class="contenedor">

  <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

  <main class="principal">
    <header class="encabezado">
      <h1>Registro de Salidas</h1>
      <div class="insignia-usuario">
        <span>${sessionScope.nombreUsuario}</span>
        <div class="avatar"></div>
      </div>
    </header>

    <!-- ================= NUEVA SALIDA ================= -->
    <div class="tarjeta">
      <h3 class="mb">📝 Nueva Salida</h3>

      <form id="formSalida">
        <input type="hidden" name="tipoMovimiento" value="SALIDA"/>

        <div class="flex espacio flex-envolver">
          <div class="grupo-formulario flex-1">
            <label>Documento</label>
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

        <h4 class="mt-2 mb">📦 Productos a Retirar</h4>
        <table class="tabla" id="tablaProductos">
          <thead>
          <tr>
            <th>Producto</th>
            <th>Existencias</th>
            <th>Cantidad</th>
            <th>Precio Unitario (S/)</th>
            <th>Subtotal</th>
            <th>Acciones</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>
              <!-- productosParque debe venir con: id_producto_catalogo, existencias, nombre_producto -->
              <select class="control-formulario producto" required onchange="actualizarExistencias(this)">
                <option value="">-- Seleccionar --</option>
                <c:forEach var="p" items="${productosParque}">
                  <option value="${p.idProductoCatalogo}" data-existencias="${p.existencias}">
                    ${p.nombreProducto}
                  </option>
                </c:forEach>
              </select>
            </td>
            <td class="texto-centro existencias"><strong>0</strong></td>
            <td><input type="number" class="control-formulario cantidad" min="1" value="0" placeholder="0"></td>
            <td><input type="number" class="control-formulario precio" min="0" step="0.01" value="0.00" placeholder="0.00"></td>
            <td class="texto-derecha subtotal"><strong>S/ 0.00</strong></td>
            <td>
              <button type="button" class="boton boton-peligro" onclick="eliminarFila(this)">Eliminar</button>
            </td>
          </tr>
          </tbody>
        </table>

        <div class="flex justificar-entre items-centrado mt-2">
          <button type="button" class="boton" id="agregarFila">+ Agregar Producto</button>
          <div><strong>Total: S/ <span id="total">0.00</span></strong></div>
        </div>

        <div class="flex justificar-fin espacio mt-2">
          <button type="reset" class="boton">Cancelar</button>
          <button type="submit" class="boton boton-exito">Registrar Salida</button>
        </div>
      </form>
    </div>

    <!-- ================= HISTORIAL ================= -->
    <div class="tarjeta mt-2">
      <h3 class="mb">📋 Historial de Salidas</h3>
      <table class="tabla">
        <thead>
        <tr>
          <th>ID</th>
          <th>Documento</th>
          <th>Fecha</th>
          <th>Observación</th>
          <th>Total Productos</th>
          <th>Total Unidades</th>
          <th>Valor Total</th>
          <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sal" items="${salidas}">
          <tr>
            <td>${sal.id}</td>
            <td><strong>${sal.numeroDocumento}</strong></td>
            <td><fmt:formatDate value="${sal.fecha}" pattern="dd/MM/yyyy"/></td>
            <td>${sal.observacion}</td>
            <td>${sal.totalProductos}</td>
            <td>${sal.totalUnidades}</td>
            <td>S/ <fmt:formatNumber value="${sal.totalValor}" type="number" minFractionDigits="2"/></td>
            <td>
              <button type="button" class="boton boton-primario ver-btn" data-id="${sal.id}">Ver</button>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

  </main>
</div>

<!-- =============== MODAL DETALLE (mismo look & feel que INGRESOS) =============== -->
<div id="modal-detalle" class="modal">
  <a href="#" class="fondo"></a>
  <div class="tarjeta-modal" style="max-width: 980px;">
    <div class="encabezado-modal flex justificar-entre items-centrado">
      <h2>Detalle del Documento</h2>
      <a href="#" class="cerrar">✕</a>
    </div>

    <div class="cuerpo-modal">
      <!-- CABECERA -->
      <div class="flex espacio flex-envolver mb-2">
        <div class="grupo-formulario flex-1">
          <label>Tipo Movimiento</label>
          <p id="md-tipo" class="control-formulario texto-negrita">Salida</p>
        </div>
        <div class="grupo-formulario flex-1">
          <label>Número Documento</label>
          <p id="md-numero" class="control-formulario"></p>
        </div>
      </div>

      <div class="flex espacio flex-envolver mb-2">
        <div class="grupo-formulario flex-1">
          <label>Fecha</label>
          <p id="md-fecha" class="control-formulario"></p>
        </div>
        <div class="grupo-formulario flex-1">
          <label>Usuario</label>
          <p id="md-usuario" class="control-formulario"></p>
        </div>
      </div>

      <!-- OBSERVACIÓN -->
      <div class="grupo-formulario mb-3">
        <label>Observación</label>
        <textarea id="md-observacion" class="control-formulario" rows="2" readonly></textarea>
      </div>

      <!-- DETALLES -->
      <h4 class="mt-2 mb">📦 Productos del Documento</h4>
      <table class="tabla" id="md-tabla">
        <thead>
        <tr>
          <th>Producto</th>
          <th>Cantidad</th>
          <th>Precio Unitario (S/)</th>
          <th>Subtotal (S/)</th>
        </tr>
        </thead>
        <tbody></tbody>
      </table>

      <!-- TOTALES -->
      <div class="flex justificar-fin mt-2">
        <div>
          <strong>
            Total productos: <span id="md-total-prod">0</span> &nbsp;|&nbsp;
            Total unidades: <span id="md-total-uni">0</span> &nbsp;|&nbsp;
            Valor total: S/ <span id="md-total-val">0.00</span>
          </strong>
        </div>
      </div>
    </div>

    <div class="pie-modal flex justificar-fin mt-2">
      <a href="#" class="boton">Cerrar</a>
    </div>
  </div>
</div>

<!-- ================= SCRIPTS ================= -->
<script>
document.addEventListener("DOMContentLoaded", () => {
  const tabla = document.querySelector("#tablaProductos tbody");
  const form  = document.getElementById("formSalida");
  const baseURL = window.location.origin;

  // Agregar fila
  document.getElementById("agregarFila").addEventListener("click", () => {
    const clone = tabla.querySelector("tr").cloneNode(true);
    clone.querySelectorAll("input").forEach(i => i.value = (i.type === "number" ? 0 : ""));
    clone.querySelector(".producto").selectedIndex = 0;
    clone.querySelector(".existencias strong").textContent = "0";
    clone.querySelector(".subtotal strong").textContent = "S/ 0.00";
    tabla.appendChild(clone);
  });

  // Eliminar fila
  window.eliminarFila = (btn) => {
    const filas = tabla.querySelectorAll("tr");
    if (filas.length > 1) btn.closest("tr").remove();
    recalcularTotal();
  };

  // Al cambiar producto: mostrar existencias
  window.actualizarExistencias = (select) => {
    const tr = select.closest("tr");
    const exist = parseInt(select.options[select.selectedIndex].dataset.existencias || "0", 10);
    tr.querySelector(".existencias strong").textContent = String(exist);
    // opcional: reset cantidad y subtotal al cambiar producto
    tr.querySelector(".cantidad").value = 0;
    tr.querySelector(".precio").value = 0.00;
    tr.querySelector(".subtotal strong").textContent = "S/ 0.00";
    recalcularTotal();
  };

  // Subtotales + validar que la cantidad no supere existencias
  tabla.addEventListener("input", (e) => {
    if (!e.target.classList.contains("cantidad") && !e.target.classList.contains("precio")) return;

    const fila = e.target.closest("tr");
    const exist = parseInt(fila.querySelector(".existencias strong").textContent || "0", 10);
    let cantidad = parseFloat(fila.querySelector(".cantidad").value) || 0;
    const precio  = parseFloat(fila.querySelector(".precio").value) || 0;

    if (cantidad > exist) {
      cantidad = exist;
      fila.querySelector(".cantidad").value = exist;
    }

    fila.querySelector(".subtotal strong").textContent = "S/ " + (cantidad * precio).toFixed(2);
    recalcularTotal();
  });

  function recalcularTotal(){
    let total = 0;
    tabla.querySelectorAll("tr").forEach(tr => {
      const c = parseFloat(tr.querySelector(".cantidad").value) || 0;
      const p = parseFloat(tr.querySelector(".precio").value) || 0;
      total += c * p;
    });
    document.getElementById("total").textContent = total.toFixed(2);
  }

  // Guardar salida
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const documento = {
      tipoMovimiento: "SALIDA",
      numeroDocumento: form.numeroDocumento.value,
      fecha: form.fecha.value,
      observacion: form.observacion.value,
      detalles: []
    };

    // construir detalles
    tabla.querySelectorAll("tr").forEach(tr => {
      const idP = parseInt(tr.querySelector(".producto").value);
      const exist = parseInt(tr.querySelector(".existencias strong").textContent || "0", 10);
      const cant = Math.min(parseFloat(tr.querySelector(".cantidad").value) || 0, exist);
      const prec = parseFloat(tr.querySelector(".precio").value) || 0;

      if (idP && cant > 0 && prec >= 0) {
        documento.detalles.push({
          idProductoCatalogo: idP,
          cantidad: cant,
          precioUnitario: prec
        });
      }
    });

    if (documento.detalles.length === 0) return alert("Debe ingresar al menos un producto válido.");

    const res = await fetch(baseURL + "/documentos/guardarSalida", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(documento)
    });

    const text = await res.text();
    alert(text);
    if (res.ok) location.reload();
  });

  // Ver detalle
  document.querySelectorAll(".ver-btn").forEach(btn => {
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      const id = btn.dataset.id;
      console.log("🔍 Data-id capturado:", id, typeof id);
      if (!id) {
        alert("❌ No se pudo identificar el documento.");
        return;
      }
      verDetalle(Number(id));
    });
  });
});

// ========= Modal Detalle (MÉTODO ROBUSTO como ingresos) =========
async function verDetalle(idDocumento) {
  const id = parseInt(idDocumento, 10);

  if (!id || isNaN(id)) {
    alert("❌ ID de documento inválido");
    console.error("ID inválido recibido:", idDocumento);
    return;
  }

  const baseURL = window.location.origin;

  console.log("📄 ID del documento (procesado):", id, typeof id);
  console.log("🌐 Base URL:", baseURL);

  const modal = document.getElementById("modal-detalle");
  const tbody = modal.querySelector("#md-tabla tbody");
  const tipo = modal.querySelector("#md-tipo");
  const numero = modal.querySelector("#md-numero");
  const fecha = modal.querySelector("#md-fecha");
  const usuario = modal.querySelector("#md-usuario");
  const obs = modal.querySelector("#md-observacion");
  const totalProd = modal.querySelector("#md-total-prod");
  const totalUni = modal.querySelector("#md-total-uni");
  const totalVal = modal.querySelector("#md-total-val");

  // Limpiar antes de cargar
  tbody.innerHTML = "";
  tipo.textContent = "SALIDA";
  numero.textContent = "";
  fecha.textContent = "";
  usuario.textContent = "";
  obs.value = "";
  totalProd.textContent = "0";
  totalUni.textContent = "0";
  totalVal.textContent = "0.00";

  try {
    // ---- FETCH DETALLE ----
    const detalleUrl = baseURL + "/documentos/detalle/" + id;
    console.log("📡 Fetch detalle:", detalleUrl);

    const detalleRes = await fetch(detalleUrl);
    if (!detalleRes.ok) throw new Error("No se pudo obtener el detalle");
    const detalles = await detalleRes.json();
    console.log("📦 Detalles recibidos:", detalles);

    if (!detalles || detalles.length === 0) {
      alert("⚠️ No hay detalles registrados para este documento.");
      return;
    }

    // Llenar tabla con método robusto
    let totalProductos = 0, totalUnidades = 0, totalValor = 0;

    console.log("🔄 Limpiando tabla antes de llenar...");
    tbody.innerHTML = ""; // Limpiar de nuevo por si acaso

    detalles.forEach(det => {
      console.log("📦 Procesando detalle:", det);

      const cantidad = parseInt(det.cantidad, 10) || 0;
      const precioUnitario = parseFloat(det.precioUnitario) || 0;
      const sub = cantidad * precioUnitario;
      const nombreProducto = String(det.productoNombre || "(sin nombre)");

      console.log("➕ Agregando fila: " + nombreProducto + ", Cant: " + cantidad + ", Precio: " + precioUnitario + ", Sub: " + sub);

      const fila = document.createElement("tr");

      // Crear celdas individualmente (MÉTODO ROBUSTO)
      const tdProducto = document.createElement("td");
      tdProducto.textContent = nombreProducto;

      const tdCantidad = document.createElement("td");
      tdCantidad.className = "texto-centro";
      tdCantidad.textContent = String(cantidad);

      const tdPrecio = document.createElement("td");
      tdPrecio.className = "texto-derecha";
      tdPrecio.textContent = "S/ " + precioUnitario.toFixed(2);

      const tdSubtotal = document.createElement("td");
      tdSubtotal.className = "texto-derecha";
      tdSubtotal.textContent = "S/ " + sub.toFixed(2);

      fila.appendChild(tdProducto);
      fila.appendChild(tdCantidad);
      fila.appendChild(tdPrecio);
      fila.appendChild(tdSubtotal);

      tbody.appendChild(fila);

      totalProductos++;
      totalUnidades += cantidad;
      totalValor += sub;
    });

    console.log("✅ Tabla llenada: " + totalProductos + " productos, " + totalUnidades + " unidades, S/ " + totalValor.toFixed(2));

    totalProd.textContent = totalProductos;
    totalUni.textContent = totalUnidades;
    totalVal.textContent = totalValor.toFixed(2);

    // ---- FETCH CABECERA ----
    const cabeceraUrl = baseURL + "/documentos/" + id;
    console.log("📡 Fetch cabecera:", cabeceraUrl);
    const cabRes = await fetch(cabeceraUrl);
    if (!cabRes.ok) throw new Error("No se pudo obtener cabecera");
    const cab = await cabRes.json();

    numero.textContent = cab.numeroDocumento || "";
    fecha.textContent = cab.fecha ? new Date(cab.fecha).toLocaleDateString("es-PE") : "";
    usuario.textContent = cab.usuarioNombre || "Usuario " + (cab.idUsuario || "");
    obs.value = cab.observacion || "";

    // Mostrar modal
    modal.classList.add("visible");

  } catch (err) {
    console.error("❌ Error al cargar detalle:", err);
    alert("No se pudo mostrar el detalle del documento.");
  }
}

// Cerrar modal
document.querySelectorAll("#modal-detalle .fondo, #modal-detalle .cerrar, #modal-detalle .pie-modal a")
  .forEach(btn => btn.addEventListener("click", e => {
    e.preventDefault();
    document.getElementById("modal-detalle").classList.remove("visible");
  }));
</script>
</body>
</html>
