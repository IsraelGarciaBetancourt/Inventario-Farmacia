<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Editar Categoría</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Editar Categoría</h1>
        </header>

        <div class="tarjeta">

            <form action="${pageContext.request.contextPath}/categorias/actualizar" method="post">

                <input type="hidden" name="id" value="${categoria.id}">

                <div class="grupo-formulario">
                    <label for="nombre">Nombre</label>
                    <input id="nombre" name="nombre" type="text"
                           class="control-formulario"
                           value="${categoria.nombre}" required>
                </div>

                <div class="grupo-formulario">
                    <label>Estado</label>
                    <select name="activo" class="control-formulario">
                        <option value="true"  ${categoria.activo ? 'selected' : ''}>Activo</option>
                        <option value="false" ${!categoria.activo ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>

                <div class="flex justificar-fin espacio mt">
                    <a href="${pageContext.request.contextPath}/categorias/list" class="boton">
                        Cancelar
                    </a>
                    <button type="submit" class="boton boton-primario">
                        Guardar Cambios
                    </button>
                </div>

            </form>

        </div>

    </main>

</div>
</body>

</html>
