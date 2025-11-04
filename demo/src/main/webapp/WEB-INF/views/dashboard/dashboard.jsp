<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dashboard - Farmacia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="contenedor">
    <jsp:include page="/WEB-INF/views/barra-lateral.jsp" />


    <!-- CONTENIDO -->
    <main class="principal">
        <header class="encabezado">
            <h1>Dashboard</h1>
            <div class="insignia-usuario">
                <span>Admin</span>
                <div class="avatar"></div>
            </div>
        </header>

        <!-- INDICADORES (KPI) -->
        <section class="estadisticas" aria-label="Indicadores">
            <div class="estadistica">
                <div class="etiqueta-estadistica">Ventas del período</div>
                <div id="kpi-ventas" class="valor-estadistica">—</div>
                <div class="texto-pequeno">Total en S/</div>
            </div>
            <div class="estadistica">
                <div class="etiqueta-estadistica">Unidades vendidas</div>
                <div id="kpi-unidades" class="valor-estadistica">—</div>
                <div class="texto-pequeno">Total de salidas (unid.)</div>
            </div>
        </section>

        <!-- SELECTOR DE PERÍODO (simple) -->
        <div class="tarjeta">
            <h3 class="mb">Seleccionar período</h3>
            <div class="flex espacio items-centrado">
                <div class="grupo-formulario">
                    <label>Período</label>
                    <select id="periodo" class="control-formulario">
                        <option value="W7" selected>Últimas 7 semanas</option>
                        <option value="M1">Último mes (30 días)</option>
                    </select>
                </div>
                <button id="btnGenerar" class="boton boton-primario">Generar</button>
            </div>
            <p class="texto-pequeno mt">Los datos se agrupan por semana según el período.</p>
        </div>

        <!-- FILA DE GRÁFICOS -->
        <section class="estadisticas">
            <!-- LÍNEA: INGRESOS VS SALIDAS -->
            <div class="tarjeta">
                <h3 class="mb">Comparación de ingresos y salidas (período)</h3>
                <div style="height:320px"><canvas id="chartLine"></canvas></div>
            </div>

            <!-- BARRAS: TOP 5 SALIDAS -->
            <div class="tarjeta">
                <h3 class="mb">Top 5 productos con más salidas (período)</h3>
                <div style="height:320px"><canvas id="chartTop"></canvas></div>
            </div>
        </section>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
<script>
    const $   = id => document.getElementById(id);
    const sol = n => 'S/ ' + Number(n || 0).toLocaleString('es-PE');
    const intf= n => Number(n || 0).toLocaleString('es-PE');
    let lineChart, barChart;

    async function render(periodKey){
        const base = '${pageContext.request.contextPath}';
        const s = await fetch(`${base}/api/dashboard/series?period=${periodKey}`).then(r=>r.json());
        const t = await fetch(`${base}/api/dashboard/top-salidas?period=${periodKey}`).then(r=>r.json());

        // KPIs
        $('kpi-ventas').textContent   = sol(s.kpiVentas);
        $('kpi-unidades').textContent = intf(s.kpiUnidades);

        // Línea
        if (lineChart) lineChart.destroy();
        lineChart = new Chart($('chartLine'), {
            type:'line',
            data:{ labels:s.labels,
                datasets:[
                    { label:'Ingresos (unid.)', data:s.ingresos, tension:.3, pointRadius:3, fill:false },
                    { label:'Salidas (unid.)',  data:s.salidas,  tension:.3, pointRadius:3, fill:false }
                ]},
            options:{ plugins:{legend:{position:'bottom'}},
                scales:{x:{grid:{display:false}}, y:{beginAtZero:true}},
                responsive:true, maintainAspectRatio:false }
        });

        // Barras
        if (barChart) barChart.destroy();
        barChart = new Chart($('chartTop'), {
            type:'bar',
            data:{ labels:t.labels, datasets:[{ label:'Unidades', data:t.valores }] },
            options:{ plugins:{legend:{display:false}},
                scales:{x:{grid:{display:false}}, y:{beginAtZero:true}},
                responsive:true, maintainAspectRatio:false }
        });
    }

    $('btnGenerar').addEventListener('click', ()=>render($('periodo').value));
    render('W7');
</script>
</body>
</html>