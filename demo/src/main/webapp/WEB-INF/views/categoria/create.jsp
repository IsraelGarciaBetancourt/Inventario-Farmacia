<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Nueva Categoría</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
<div class="contenedor">

    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

    <main class="principal">

        <header class="encabezado">
            <h1>Nueva Categoría</h1>
        </header>

        <div class="tarjeta">

            <form action="${pageContext.request.contextPath}/categorias/guardar" method="post">

                <div class="grupo-formulario">
                    <label for="nombre">Nombre</label>
                    <input id="nombre" name="nombre" type="text" class="control-formulario"
                           placeholder="Ej: Analgésicos" required>
                </div>

                <div class="flex justificar-fin espacio mt">
                    <a href="${pageContext.request.contextPath}/categorias/list" class="boton">
                        Cancelar
                    </a>
                    <button type="submit" class="boton boton-primario">
                        Guardar
                    </button>
                </div>

            </form>

        </div>

    </main>
</div>
</body>

</html>
