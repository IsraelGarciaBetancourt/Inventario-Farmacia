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

    <a href="${pageContext.request.contextPath}/documentos/ingresos"
       class="enlace-nav ${fn:contains(currentPath, '/documentos/ingresos') ? 'activo' : ''}">📥 Ingresos</a>

    <a href="${pageContext.request.contextPath}/documentos/salidas"
       class="enlace-nav ${fn:contains(currentPath, '/documentos/salidas') ? 'activo' : ''}">📤 Salidas</a>

    <a href="${pageContext.request.contextPath}/catalogo"
       class="enlace-nav ${fn:contains(currentPath, '/catalogo') ? 'activo' : ''}">📋 Catálogo</a>

    <a href="${pageContext.request.contextPath}/inventario"
       class="enlace-nav ${fn:contains(currentPath, '/inventario') ? 'activo' : ''}">📦 Inventario</a>

    <a href="${pageContext.request.contextPath}/categorias"
       class="enlace-nav ${fn:contains(currentPath, '/categorias') ? 'activo' : ''}">📂 Categorías</a>

    <a href="${pageContext.request.contextPath}/reportes"
       class="enlace-nav ${fn:contains(currentPath, '/reportes') ? 'activo' : ''}">📈 Reportes</a>

    <a href="${pageContext.request.contextPath}/logout"
       class="enlace-nav"
       onclick="return confirm('¿Seguro que deseas cerrar sesión?');">🚪 Cerrar Sesión</a>
  </nav>
</aside>
