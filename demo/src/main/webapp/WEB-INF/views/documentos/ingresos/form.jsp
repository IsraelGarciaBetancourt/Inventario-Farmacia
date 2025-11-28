<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Nuevo Ingreso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

    <style>
        .fila-detalle { display: flex; gap: 10px; margin-bottom: 10px; }
        .fila-detalle select, .fila-detalle input { flex: 1; }
    </style>

</head>

<body>

<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Registrar Ingreso</h1>
        </header>

        <div class="tarjeta">

            <form action="${pageContext.request.contextPath}/documentos/ingresos/guardar" method="post">

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
                        <select name="idProducto" class="control-formulario" required>
                            <option value="">Seleccione producto</option>

                            <c:forEach var="p" items="${productos}">
                                <option value="${p.id}">
                                    ${p.codigo} - ${p.nombre}
                                </option>
                            </c:forEach>

                        </select>

                        <!-- CANTIDAD -->
                        <input type="number" name="cantidad" class="control-formulario"
                               min="1" placeholder="Cantidad" required>

                        <!-- ELIMINAR -->
                        <button type="button" class="boton boton-peligro" onclick="eliminarFila(this)">
                            X
                        </button>
                    </div>

                </div>

                <!-- Botón agregar -->
                <button type="button" class="boton boton-exito mt" onclick="agregarFila()">
                    + Agregar Producto
                </button>

                <div class="flex justificar-fin espacio mt">
                    <a href="${pageContext.request.contextPath}/documentos/ingresos/list" class="boton">
                        Cancelar
                    </a>

                    <button type="submit" class="boton boton-primario">
                        Guardar Ingreso
                    </button>
                </div>

            </form>

        </div>

    </main>

</div>

<!-- JS simple -->
<script>
function agregarFila() {
    const cont = document.getElementById("contenedor-detalles");
    const fila = cont.children[0].cloneNode(true);

    // limpiar valores
    fila.querySelector("select").value = "";
    fila.querySelector("input[name='cantidad']").value = "";

    cont.appendChild(fila);
}

function eliminarFila(btn) {
    const cont = document.getElementById("contenedor-detalles");
    if (cont.children.length > 1) {
        btn.parentNode.remove();
    }
}
</script>

</body>
</html>
