package com.example.demo.ingresoProducto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IngresoProductoRepository {

    private final JdbcTemplate jdbcTemplate;

    public IngresoProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<IngresoProducto> listarIngresos() {
        String sql = """
            SELECT d.id, d.numero_documento AS numeroDocumento, d.fecha,
                   d.id_usuario, d.observacion, d.activo
            FROM documentos d
            WHERE d.tipo_movimiento = 'INGRESO'
            ORDER BY d.id DESC
        """;

        return jdbcTemplate.query(sql, new RowMapper<IngresoProducto>() {
            @Override
            public IngresoProducto mapRow(ResultSet rs, int rowNum) throws SQLException {
                IngresoProducto ingreso = new IngresoProducto();
                ingreso.setId(rs.getInt("id"));
                ingreso.setNumeroDocumento(rs.getString("numeroDocumento"));
                ingreso.setFecha(rs.getDate("fecha").toLocalDate());
                ingreso.setIdUsuario(rs.getInt("id_usuario"));
                ingreso.setObservacion(rs.getString("observacion"));
                ingreso.setActivo(rs.getBoolean("activo"));
                return ingreso;
            }
        });
    }

    public void crearIngreso(IngresoProducto ingreso) {
        String sql = """
            INSERT INTO documentos 
            (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
            VALUES ('INGRESO', ?, ?, ?, ?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        """;
        jdbcTemplate.update(sql,
                ingreso.getNumeroDocumento(),
                ingreso.getFecha(),
                ingreso.getIdUsuario(),
                ingreso.getObservacion()
        );
    }

    public String obtenerUltimoNumeroDocumento() {
        String sql = "SELECT numero_documento FROM documentos WHERE tipo_movimiento = 'INGRESO' ORDER BY id DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> listarProductos() {
        String sql = "SELECT nombre FROM producto_catalogo WHERE activo = TRUE ORDER BY nombre ASC";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
