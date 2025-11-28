<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String currentPath = request.getRequestURI();
    pageContext.setAttribute("currentPath", currentPath);
%>

<aside class="barra-lateral">
  <h2 class="mb-2">💊 Farmacia Juley</h2>
  <nav>
    <a href="${pageContext.request.contextPath}/dashboard"
       class="enlace-nav ${fn:contains(currentPath, '/dashboard') ? 'activo' : ''}">📊 Dashboard</a>

    <a href="${pageContext.request.contextPath}/documentos/ingresos/list"
       class="enlace-nav ${fn:contains(currentPath, '/documentos/ingresos/list') ? 'activo' : ''}">📥 Ingresos</a>

    <a href="${pageContext.request.contextPath}/documentos/salidas/list"
       class="enlace-nav ${fn:contains(currentPath, '/documentos/salidas/list') ? 'activo' : ''}">📤 Salidas</a>

    <a href="${pageContext.request.contextPath}/productoCatalogo/list"
       class="enlace-nav ${fn:contains(currentPath, '/productoCatalogo/list') ? 'activo' : ''}">📋 Catálogo</a>

    <a href="${pageContext.request.contextPath}/inventario"
       class="enlace-nav ${fn:contains(currentPath, '/inventario') ? 'activo' : ''}">📦 Inventario</a>

    <a href="${pageContext.request.contextPath}/categorias/list"
       class="enlace-nav ${fn:contains(currentPath, '/categorias/list') ? 'activo' : ''}">📂 Categorías</a>

    <c:if test="${sessionScope.rol == 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/usuarios/list"
           class="enlace-nav ${fn:contains(currentPath, '/usuarios/list') ? 'activo' : ''}">
            👤 Usuarios
        </a>
    </c:if>

    <a href="${pageContext.request.contextPath}/logout"
       class="enlace-nav"
       onclick="return confirm('¿Seguro que deseas cerrar sesión?');">🚪 Cerrar Sesión</a>
  </nav>
</aside>
