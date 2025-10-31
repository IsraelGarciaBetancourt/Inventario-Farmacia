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

      <div class="flex justificar-entre items-centrado mb-2">
        <h2>Productos</h2>
        <button class="boton boton-primario"><a class="boton boton-primario" href="#modal-crear-producto">+ Nuevo Producto</a></button>
      </div>

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
              <option>Analgésicos</option>
              <option>Antibióticos</option>
              <option>Antiinflamatorios</option>
              <option>Vitaminas</option>
              <option>Antihistamínicos</option>
            </select>
          </div>
          <div class="grupo-formulario flex-1">
            <label>Flag</label>
            <select class="control-formulario">
              <option>-- Todos --</option>
              <option>Activo</option>
              <option>Inactivo</option>
            </select>
          </div>
        </div>
        <button class="boton boton-primario">🔍 Buscar</button>
      </div>

      <div class="tarjeta">
        <table class="tabla">
          <thead>
            <tr>
              <th>Código</th>
              <th>Nombre</th>
              <th>Categoría</th>
              <th>Presentación</th>
              <th>Factor Base</th>
              <th>Flag</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><strong>MED-001</strong></td>
              <td>Paracetamol 500 mg</td>
              <td>Analgésicos</td>
              <td>Caja de 10</td>
              <td><strong>10</strong></td>
              <td><span class="insignia insignia-exito">Activo</span></td>
              <td>
                <button class="boton"><a href="#modal-editar-producto">Editar</a></button>
                <button class="boton boton-peligro">Desactivar</button>
              </td>
            </tr>
            <tr>
              <td><strong>MED-002</strong></td>
              <td>Paracetamol 500 mg</td>
              <td>Analgésicos</td>
              <td>Blíster</td>
              <td><strong>1</strong></td>
              <td><span class="insignia insignia-exito">Activo</span></td>
              <td>
                <button class="boton">Editar</button>
                <button class="boton boton-peligro">Desactivar</button>
              </td>
            </tr>
            <tr>
              <td><strong>MED-003</strong></td>
              <td>Jarabe de Tos 120 ml</td>
              <td>Antitusivos</td>
              <td>Frasco</td>
              <td><strong>1</strong></td>
              <td><span class="insignia insignia-exito">Activo</span></td>
              <td>
                <button class="boton">Editar</button>
                <button class="boton boton-peligro">Desactivar</button>
              </td>
            </tr>
            <tr>
              <td><strong>MED-004</strong></td>
              <td>Jarabe de Tos 120 ml</td>
              <td>Antitusivos</td>
              <td>Caja de 10</td>
              <td><strong>10</strong></td>
              <td><span class="insignia insignia-exito">Activo</span></td>
              <td>
                <button class="boton">Editar</button>
                <button class="boton boton-peligro">Desactivar</button>
              </td>
            </tr>
            <tr>
              <td><strong>MED-005</strong></td>
              <td>Paracetamol 500 mg</td>
              <td>Antitusivos</td>
              <td>Caja de 12</td>
              <td><strong>12</strong></td>
              <td><span class="insignia insignia-exito">Activo</span></td>
              <td>
                <button class="boton">Editar</button>
                <button class="boton boton-peligro">Desactivar</button>
              </td>
            </tr>
            <tr style="opacity: 0.6;">
              <td><strong>MED-010</strong></td>
              <td>Aspirina 500mg</td>
              <td>Analgésicos</td>
              <td>Blíster</td>
              <td><strong>1</strong></td>
              <td><span class="insignia insignia-peligro">Inactivo</span></td>
              <td>
                <button class="boton">Editar</button>
                <button class="boton boton-exito">Activar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </main>
  </div>

  <!-- Modal Crear -->
  <div id="modal-crear-producto" class="modal">
    <a href="#" class="fondo"></a>
    <div class="tarjeta-modal">
      <div class="encabezado-modal">
        <h2>Nuevo producto</h2>
        <a href="#" class="cerrar">✕</a>
      </div>

      <div class="cuerpo-modal">
        <form>
          <div class="grupo-formulario">
            <label for="codigo">Código</label>
            <input id="codigo" type="text" class="control-formulario" placeholder="Ej: MED-001">
          </div>

          <div class="grupo-formulario">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" class="control-formulario" placeholder="Ej: Paracetamol 500mg">
          </div>

          <div class="grupo-formulario">
            <label for="categoria">Categoría</label>
            <select id="categoria" class="control-formulario">
              <option value="">-- Seleccionar --</option>
              <option value="1">Analgésicos</option>
              <option value="2">Antiinflamatorios</option>
              <option value="3">Antibióticos</option>
              <option value="4">Vitaminas</option>
            </select>
          </div>

          <div class="grupo-formulario">
            <label for="unidad">Presentación</label>
            <select id="unidad" class="control-formulario">
              <option value="">-- Seleccionar --</option>
              <option>Blíster</option>
              <option>Caja</option>
              <option>Frasco</option>
            </select>
          </div>

          <div class="grupo-formulario">
            <label for="cantidad">Factor Base</label>
            <input id="cantidad" type="number" class="control-formulario" placeholder="0" min="0">
          </div>

          <div class="grupo-formulario">
            <label>Flag</label>
            <select id="activo" class="control-formulario">
              <option value="true" selected>Activo</option>
              <option value="false">Inactivo</option>
            </select>
          </div>
        </form>
      </div>

      <div class="pie-modal">
        <a href="#" class="boton">Cancelar</a>
        <a href="#" class="boton boton-primario">Guardar</a>
      </div>
    </div>
  </div>

  <!-- Modal Editar -->
  <div id="modal-editar-producto" class="modal">
    <a href="#" class="fondo"></a>
    <div class="tarjeta-modal">
      <div class="encabezado-modal">
        <h2>Editar producto</h2>
        <a href="#" class="cerrar">✕</a>
      </div>

      <div class="cuerpo-modal">
        <form>
          <div class="grupo-formulario">
            <label for="codigo">Código</label>
            <input id="codigo" type="text" class="control-formulario" placeholder="Ej: MED-001" value="MED-001">
          </div>

          <div class="grupo-formulario">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" class="control-formulario" placeholder="Ej: Paracetamol 500mg" value="Paracetamol 500mg">
          </div>

          <div class="grupo-formulario">
            <label for="categoria">Categoría</label>
            <select id="categoria" class="control-formulario">
              <option value="">-- Seleccionar --</option>
              <option value="1" selected>Analgésicos</option>
              <option value="2">Antiinflamatorios</option>
              <option value="3">Antibióticos</option>
              <option value="4">Vitaminas</option>
            </select>
          </div>

          <div class="grupo-formulario">
            <label for="unidad">Presentación</label>
            <input id="cantidad" type="text" class="control-formulario" min="0" value="Caja" disabled>
          </div>

          <div class="grupo-formulario">
            <label for="cantidad">Factor Base</label>
            <input id="cantidad" type="number" class="control-formulario" value="10" disabled>
          </div>

          <div class="grupo-formulario">
            <label>Flag</label>
            <select id="activo" class="control-formulario">
              <option value="true" selected>Activo</option>
              <option value="false">Inactivo</option>
            </select>
          </div>
        </form>
      </div>

      <div class="pie-modal">
        <a href="#" class="boton">Cancelar</a>
        <a href="#" class="boton boton-primario">Guardar</a>
      </div>
    </div>
  </div>

</body>
</html>