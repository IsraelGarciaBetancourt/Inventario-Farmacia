package com.example.demo.dashboard;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class DashboardRepository implements DashboardDAO {

    private final JdbcTemplate jdbc;

    public DashboardRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int obtenerCantidadPorTipo(String tipoMovimiento, LocalDate inicio, LocalDate fin) {
        String sql = """
            SELECT COALESCE(SUM(dd.cantidad), 0)
            FROM documento_detalles dd
            INNER JOIN documentos d ON dd.id_documento = d.id
            WHERE d.tipo_movimiento = ?
              AND d.activo = TRUE
              AND dd.activo = TRUE
              AND d.fecha BETWEEN ? AND ?
        """;

        Integer resultado = jdbc.queryForObject(sql, Integer.class, tipoMovimiento, inicio, fin);
        return resultado != null ? resultado : 0;
    }

    @Override
    public List<Map<String, Object>> obtenerTop5ProductosSalidas(LocalDate inicio, LocalDate fin) {
        String sql = """
            SELECT pc.nombre, SUM(dd.cantidad) as total
            FROM documento_detalles dd
            INNER JOIN documentos d ON dd.id_documento = d.id
            INNER JOIN producto_catalogo pc ON dd.id_producto_catalogo = pc.id
            WHERE d.tipo_movimiento = 'SALIDA'
              AND d.activo = TRUE
              AND dd.activo = TRUE
              AND pc.activo = TRUE
              AND d.fecha BETWEEN ? AND ?
            GROUP BY pc.id, pc.nombre
            ORDER BY total DESC
            LIMIT 5
        """;

        return jdbc.queryForList(sql, inicio, fin);
    }
}