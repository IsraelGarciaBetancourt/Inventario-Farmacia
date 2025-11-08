package com.example.demo.dashboard;

import com.example.demo.dashboard.model.DashboardMetrics;
import com.example.demo.dashboard.model.PeriodoEstadistico;
import com.example.demo.dashboard.model.TopProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // KPIs generales (stock, valor, riesgo)
    public DashboardMetrics obtenerMetricasGenerales() {
        String sqlTotalStock = "SELECT COALESCE(SUM(existencias), 0) FROM producto_parque";
        String sqlValorTotal  = "SELECT COALESCE(SUM(valor_stock), 0) FROM producto_parque";
        String sqlRiesgo      = "SELECT COUNT(*) FROM producto_parque WHERE existencias < 50";

        int totalStock    = jdbcTemplate.queryForObject(sqlTotalStock, Integer.class);
        double valorTotal = jdbcTemplate.queryForObject(sqlValorTotal, Double.class);
        int productosEnRiesgo = jdbcTemplate.queryForObject(sqlRiesgo, Integer.class);

        return new DashboardMetrics(totalStock, valorTotal, productosEnRiesgo);
    }

    // Serie de tiempo por período -> siempre devuelve 7 / 4 / 4 puntos
    public List<PeriodoEstadistico> obtenerEstadisticasPorPeriodo(int dias) {
        // Etiqueta según período
        final String labelExpr;
        if (dias == 7) {
            labelExpr = "FORMATDATETIME(f.dia, 'yyyy-MM-dd')";           // 7 días: por día
        } else if (dias == 28) {
            // 4 semanas: Año ISO + semana (ww)
            labelExpr = "FORMATDATETIME(f.dia, 'YYYY') || '-W' || FORMATDATETIME(f.dia, 'ww')";
        } else {
            // 4 meses: Año-Mes
            labelExpr = "FORMATDATETIME(f.dia, 'yyyy-MM')";
        }

        String sql = String.format("""
            WITH fechas AS (
                 -- Genera N días consecutivos: X es la columna de SYSTEM_RANGE en H2 2.x
                 SELECT DATEADD('DAY', -S.X, CURRENT_DATE) AS dia
                 FROM SYSTEM_RANGE(0, ? - 1) S
            )
            SELECT 
                 %1$s AS periodo,
                 COALESCE(SUM(CASE WHEN d.tipo_movimiento = 'INGRESO' THEN d.total_unidades END), 0) AS ingresos,
                 COALESCE(SUM(CASE WHEN d.tipo_movimiento = 'SALIDA'  THEN d.total_unidades END), 0) AS salidas,
                 MIN(f.dia) AS orden
            FROM fechas f
            LEFT JOIN documentos d 
              ON d.fecha = f.dia
            GROUP BY %1$s
            ORDER BY orden ASC;
        """, labelExpr);

        return jdbcTemplate.query(sql, (rs, rowNum) -> new PeriodoEstadistico(
                rs.getString("periodo"),
                rs.getInt("ingresos"),
                rs.getInt("salidas")
        ), dias);
    }

    // KPIs del período (ventas reales S/ y unidades) usando la tabla documentos
    public Map<String, Object> obtenerVentasYUnidadesPeriodo(int dias) {
        String sqlVentas = """
            SELECT COALESCE(SUM(total_valor), 0)
            FROM documentos
            WHERE tipo_movimiento = 'SALIDA'
              AND fecha >= DATEADD('DAY', -?, CURRENT_DATE)
        """;
        String sqlUnidades = """
            SELECT COALESCE(SUM(total_unidades), 0)
            FROM documentos
            WHERE tipo_movimiento = 'SALIDA'
              AND fecha >= DATEADD('DAY', -?, CURRENT_DATE)
        """;

        double ventas  = jdbcTemplate.queryForObject(sqlVentas,  Double.class, dias);
        int unidades   = jdbcTemplate.queryForObject(sqlUnidades, Integer.class, dias);

        Map<String, Object> out = new HashMap<>();
        out.put("ventas", ventas);
        out.put("unidades", unidades);
        return out;
    }

    // Top 5 productos por salidas
    public List<TopProductoDTO> obtenerTopProductosVendidos(int dias) {
        String sql = """
            SELECT pc.nombre AS nombreProducto, SUM(dd.cantidad) AS totalSalidas
            FROM documento_detalles dd
            JOIN documentos d        ON d.id  = dd.id_documento
            JOIN producto_catalogo pc ON pc.id = dd.id_producto_catalogo
            WHERE d.tipo_movimiento = 'SALIDA'
              AND d.fecha >= DATEADD('DAY', -?, CURRENT_DATE)
            GROUP BY pc.nombre
            ORDER BY totalSalidas DESC
            LIMIT 5
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new TopProductoDTO(
                rs.getString("nombreProducto"),
                rs.getInt("totalSalidas")
        ), dias);
    }
}
