<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Nueva Salida</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <style>
        .fila-detalle { display: flex; gap: 10px; margin-bottom: 10px; }
        .fila-detalle select, .fila-detalle input { flex: 1; }
        .stock-label { font-size: 0.9em; color: #8bd0ff; margin-top: -5px; }
    </style>
</head>

<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Registrar Salida</h1>
        </header>

        <div class="tarjeta">

            <c:if test="${not empty error}">
                <div class="alerta alerta-peligro">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/documentos/salidas/guardar" method="post">

                <!-- Número autogenerado -->
                <div class="grupo-formulario">
                    <label>N° Documento</label>
                    <input type="text" class="control-formulario" value="${numero}" disabled>
                    <input type="hidden" name="numeroDocumento" value="${numero}">
                </div>

                <!-- Fecha -->
                <div class="grupo-formulario">
                    <label>Fecha</label>
                    <input type="date" name="fecha"
                           value="${fechaHoy}"
                           class="control-formulario" required>
                </div>

                <!-- Observación -->
                <div class="grupo-formulario">
                    <label>Observación</label>
                    <textarea name="observacion" class="control-formulario"
                              placeholder="Opcional"></textarea>
                </div>

                <hr>

                <h3>Productos:</h3>

                <div id="contenedor-detalles">

                    <!-- FILA BASE -->
                    <div class="fila-detalle">

                        <!-- PRODUCTO -->
                        <div style="flex:1">
                            <select name="idProducto" class="control-formulario producto-select" onchange="mostrarStock(this)" required>
                                <option value="">Seleccione producto</option>

                                <c:forEach var="p" items="${productos}">
                                    <option value="${p.productoCatalogo.id}"
                                            data-stock="${p.existencias}">
                                        ${p.productoCatalogo.codigo} - ${p.productoCatalogo.nombre}
                                    </option>
                                </c:forEach>

                            </select>
                            <div class="stock-label">Existencias: --</div>
                        </div>

                        <!-- CANTIDAD -->
                        <input type="number" name="cantidad" class="control-formulario"
                               min="1" placeholder="Cantidad" required>

                        <!-- ELIMINAR FILA -->
                        <button type="button" class="boton boton-peligro" onclick="eliminarFila(this)">
                            X
                        </button>
                    </div>

                </div>

                <!-- Botón agregar fila -->
                <button type="button" class="boton boton-exito mt" onclick="agregarFila()">
                    + Agregar Producto
                </button>

                <div class="flex justificar-fin espacio mt">
                    <a href="${pageContext.request.contextPath}/documentos/salidas/list" class="boton">Cancelar</a>
                    <button type="submit" class="boton boton-primario">Guardar Salida</button>
                </div>

            </form>

        </div>

    </main>

</div>

<!-- JS -->
<script>

function agregarFila() {
    const cont = document.getElementById("contenedor-detalles");
    const base = cont.children[0];
    const clone = base.cloneNode(true);

    clone.querySelector("select").value = "";
    clone.querySelector(".stock-label").innerText = "Existencias: --";
    clone.querySelector("input[name='cantidad']").value = "";

    cont.appendChild(clone);
}

function eliminarFila(btn) {
    const cont = document.getElementById("contenedor-detalles");
    if (cont.children.length > 1) {
        btn.parentNode.remove();
    }
}

function mostrarStock(select) {
    const stock = select.options[select.selectedIndex].getAttribute("data-stock");
    const label = select.parentNode.querySelector(".stock-label");
    label.innerText = "Existencias: " + stock;
}

</script>

</body>
</html>
