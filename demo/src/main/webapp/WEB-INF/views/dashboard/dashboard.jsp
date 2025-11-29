<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="contenedor">

        <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />

        <div class="principal">
            <div class="encabezado">
                <h1>📊 Dashboard</h1>
            </div>

            <div class="tarjeta mb-2">
                <div class="flex items-centrado justificar-entre">
                    <label for="periodo" class="texto-pequeno" style="margin: 0;">Seleccionar Periodo:</label>
                    <select id="periodo" class="control-formulario" style="width: auto; min-width: 200px;" onchange="location.href='/dashboard?periodo='+this.value">
                        <option value="7dias" ${periodo == '7dias' ? 'selected' : ''}>7 Últimos Días</option>
                        <option value="7semanas" ${periodo == '7semanas' ? 'selected' : ''}>7 Últimas Semanas</option>
                        <option value="7meses" ${periodo == '7meses' ? 'selected' : ''}>7 Últimos Meses</option>
                    </select>
                </div>
            </div>

            <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 1.5rem;">
                <div class="tarjeta">
                    <h3 style="margin-bottom: 1.5rem; color: var(--texto-apagado);">Ingresos vs Salidas</h3>
                    <canvas id="lineChart"></canvas>
                </div>

                <div class="tarjeta">
                    <h3 style="margin-bottom: 1.5rem; color: var(--texto-apagado);">Top 5 Productos con Más Salidas</h3>
                    <canvas id="barChart"></canvas>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Gráfico de Líneas
        new Chart(document.getElementById('lineChart'), {
            type: 'line',
            data: {
                labels: [<c:forEach items="${labels}" var="l" varStatus="s">"${l}"${!s.last ? ',' : ''}</c:forEach>],
                datasets: [{
                    label: 'Ingresos',
                    data: [<c:forEach items="${ingresos}" var="i" varStatus="s">${i}${!s.last ? ',' : ''}</c:forEach>],
                    borderColor: '#10b981',
                    backgroundColor: 'rgba(16, 185, 129, 0.1)',
                    borderWidth: 3,
                    tension: 0.3,
                    fill: true
                }, {
                    label: 'Salidas',
                    data: [<c:forEach items="${salidas}" var="s" varStatus="st">${s}${!st.last ? ',' : ''}</c:forEach>],
                    borderColor: '#ef4444',
                    backgroundColor: 'rgba(239, 68, 68, 0.1)',
                    borderWidth: 3,
                    tension: 0.3,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    legend: {
                        position: 'top',
                        labels: { color: '#f8fafc' }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: { color: '#94a3b8' },
                        grid: { color: '#334155' }
                    },
                    x: {
                        ticks: { color: '#94a3b8' },
                        grid: { color: '#334155' }
                    }
                }
            }
        });

        // Gráfico de Barras
        new Chart(document.getElementById('barChart'), {
            type: 'bar',
            data: {
                labels: [<c:forEach items="${productosLabels}" var="p" varStatus="s">"${p}"${!s.last ? ',' : ''}</c:forEach>],
                datasets: [{
                    label: 'Cantidad Vendida',
                    data: [<c:forEach items="${productosCantidades}" var="c" varStatus="s">${c}${!s.last ? ',' : ''}</c:forEach>],
                    backgroundColor: ['#3b82f6','#10b981','#f59e0b','#ef4444','#8b5cf6'],
                    borderWidth: 0
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: { color: '#94a3b8' },
                        grid: { color: '#334155' }
                    },
                    x: {
                        ticks: { color: '#94a3b8' },
                        grid: { display: false }
                    }
                }
            }
        });
    </script>
</body>
</html>