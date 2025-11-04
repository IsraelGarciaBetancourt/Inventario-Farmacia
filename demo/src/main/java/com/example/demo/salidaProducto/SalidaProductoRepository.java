package com.example.demo.salidaProducto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SalidaProductoRepository {

    private final JdbcTemplate jdbcTemplate;

    public SalidaProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SalidaProducto> listarSalidas() {
        String sql = """
            SELECT d.id, d.numero_documento AS numeroDocumento, d.fecha,
                   d.id_usuario, d.observacion, d.activo
            FROM documentos d
            WHERE d.tipo_movimiento = 'SALIDA'
            ORDER BY d.id DESC
        """;

        return jdbcTemplate.query(sql, new RowMapper<SalidaProducto>() {
            @Override
            public SalidaProducto mapRow(ResultSet rs, int rowNum) throws SQLException {
                SalidaProducto salida = new SalidaProducto();
                salida.setId(rs.getInt("id"));
                salida.setNumeroDocumento(rs.getString("numeroDocumento"));
                salida.setFecha(rs.getDate("fecha").toLocalDate());
                salida.setIdUsuario(rs.getInt("id_usuario"));
                salida.setObservacion(rs.getString("observacion"));
                salida.setActivo(rs.getBoolean("activo"));
                return salida;
            }
        });
    }

    public void crearSalida(SalidaProducto salida) {
        String sql = """
            INSERT INTO documentos 
            (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
            VALUES ('SALIDA', ?, ?, ?, ?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        """;
        jdbcTemplate.update(sql,
                salida.getNumeroDocumento(),
                salida.getFecha(),
                salida.getIdUsuario(),
                salida.getObservacion()
        );
    }

    public String obtenerUltimoNumeroDocumento() {
        String sql = "SELECT numero_documento FROM documentos WHERE tipo_movimiento = 'SALIDA' ORDER BY id DESC LIMIT 1";
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
