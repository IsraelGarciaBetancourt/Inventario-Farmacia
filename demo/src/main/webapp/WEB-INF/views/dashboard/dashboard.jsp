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

    <main class="principal">
        <header class="encabezado">
            <h1>Dashboard</h1>
            <div class="insignia-usuario">
                <span>${sessionScope.nombreUsuario}</span>
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

        <!-- SELECTOR DE PERÍODO -->
        <div class="tarjeta">
            <h3 class="mb">Seleccionar período</h3>
            <div class="flex espacio items-centrado">
                <div class="grupo-formulario flex-1">
                    <label for="periodo">Período</label>
                    <select id="periodo" class="control-formulario">
                        <option value="D7" selected>Últimos 7 días</option>
                        <option value="W4">Últimas 4 semanas</option>
                        <option value="M4">Últimos 4 meses</option>
                    </select>
                </div>
                <button id="btnGenerar" class="boton boton-primario">Generar</button>
            </div>
            <p id="ayuda-periodo" class="texto-pequeno mt">
                Mostrando últimos 7 días (agrupado por día).
            </p>
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

<!-- Chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
<script>
const $   = id => document.getElementById(id);
const sol = n  => 'S/ ' + Number(n || 0).toLocaleString('es-PE');
const intf= n  => Number(n || 0).toLocaleString('es-PE');

let lineChart, barChart;

// URL absoluta (evita rutas relativas rotas)
const base = window.location.origin + '${pageContext.request.contextPath}';

function setAyuda(periodKey, puntos) {
  const map = {
    D7: 'Mostrando últimos 7 días (agrupado por día).',
    W4: 'Mostrando últimas 4 semanas (agrupado por semana ISO).',
    M4: 'Mostrando últimos 4 meses (agrupado por mes).'
  };
  const texto = map[periodKey] || '';
  $('ayuda-periodo').textContent = `${texto} Se muestran ${puntos} puntos de datos.`;
}

async function render(periodKey) {
  console.log("🟡 render() recibió:", periodKey);
  try {
    $('btnGenerar').disabled = true;

    // Enviamos ambos parámetros (por si tu controlador espera "p" en lugar de "period")
    const qs = "period=" + encodeURIComponent(periodKey) + "&p=" + encodeURIComponent(periodKey);
    const urlS = base + "/api/dashboard/series?" + qs;
    const urlT = base + "/api/dashboard/top-salidas?" + qs;
    console.log("Fetch URLs:", urlS, urlT);

    const [rs, rt] = await Promise.all([
      fetch(urlS, { cache: 'no-store' }),
      fetch(urlT, { cache: 'no-store' })
    ]);

    if (!rs.ok || !rt.ok) throw new Error('HTTP ' + rs.status + ' / ' + rt.status);

    const s = await rs.json();
    const t = await rt.json();

    // KPIs reales
    $('kpi-ventas').textContent   = sol(s.kpiVentas);
    $('kpi-unidades').textContent = intf(s.kpiUnidades);

    // Línea ingresos vs salidas
    if (lineChart) lineChart.destroy();
    lineChart = new Chart($('chartLine'), {
      type: 'line',
      data: {
        labels: s.labels,
        datasets: [
          { label: 'Ingresos (unid.)', data: s.ingresos, borderColor: '#10b981', tension: .3, pointRadius: 3, fill: false },
          { label: 'Salidas (unid.)',  data: s.salidas,  borderColor: '#ef4444', tension: .3, pointRadius: 3, fill: false }
        ]
      },
      options: {
        plugins: { legend: { position: 'bottom' } },
        scales: { x: { grid: { display:false } }, y: { beginAtZero:true } },
        responsive: true, maintainAspectRatio: false
      }
    });

    // Barras top productos
    if (barChart) barChart.destroy();
    barChart = new Chart($('chartTop'), {
      type: 'bar',
      data: { labels: t.labels, datasets: [{ label: 'Unidades', data: t.valores, backgroundColor: '#3b82f6' }] },
      options: {
        plugins: { legend: { display:false } },
        scales: { x: { grid: { display:false } }, y: { beginAtZero:true } },
        responsive: true, maintainAspectRatio: false
      }
    });

    setAyuda(periodKey, s.labels.length);
  } catch (e) {
    console.error('Error dashboard:', e);
    $('kpi-ventas').textContent   = '—';
    $('kpi-unidades').textContent = '—';
    $('ayuda-periodo').textContent = 'No se pudieron cargar datos.';
  } finally {
    $('btnGenerar').disabled = false;
  }
}

// Botón Generar
$('btnGenerar').addEventListener('click', () => {
  const periodKey = $('periodo').value;
  render(periodKey);
});

// SOLO UNA carga inicial (no dupliques esto)
window.addEventListener('DOMContentLoaded', () => render('D7'));
</script>


</body>
</html>
