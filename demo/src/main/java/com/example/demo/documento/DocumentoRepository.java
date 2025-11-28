package com.example.demo.documento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.usuario.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentoRepository implements DocumentoDAO {

    private final JdbcTemplate jdbc;

    public DocumentoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<Documento> mapperListado = new RowMapper<Documento>() {
        @Override
        public Documento mapRow(ResultSet rs, int rowNum) throws SQLException {

            Usuario u = new Usuario();
            u.setId(rs.getInt("id_usuario"));
            u.setNombreCompleto(rs.getString("usuario_nombre"));

            Documento d = new Documento();
            d.setId(rs.getInt("id"));
            d.setTipoMovimiento(rs.getString("tipo_movimiento"));
            d.setNumeroDocumento(rs.getString("numero_documento"));
            d.setFecha(rs.getDate("fecha").toLocalDate());
            d.setUsuario(u);
            d.setObservacion(rs.getString("observacion"));
            d.setActivo(rs.getBoolean("activo"));
            d.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            d.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            d.setTotalCantidad(rs.getInt("total_cantidad"));

            return d;
        }
    };

    @Override
    public int guardarCabecera(Documento d) {
        String sql = """
            INSERT INTO documentos
            (tipo_movimiento, numero_documento, fecha, id_usuario, observacion,
             activo, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        """;

        return jdbc.update(sql,
                d.getTipoMovimiento(),
                d.getNumeroDocumento(),
                d.getFecha(),
                d.getUsuario().getId(),
                d.getObservacion()
        );
    }

    @Override
    public int obtenerUltimoId() {
        String sql = "SELECT MAX(id) FROM documentos";
        return jdbc.queryForObject(sql, Integer.class);
    }

    @Override
    public String obtenerUltimoNumeroIngreso() {
        String sql = """
            SELECT numero_documento
            FROM documentos
            WHERE tipo_movimiento = 'INGRESO'
            ORDER BY id DESC LIMIT 1
        """;

        try { return jdbc.queryForObject(sql, String.class); }
        catch (Exception e) { return null; }
    }

    @Override
    public String obtenerUltimoNumeroSalida() {
        String sql = """
            SELECT numero_documento
            FROM documentos
            WHERE tipo_movimiento = 'SALIDA'
            ORDER BY id DESC LIMIT 1
        """;

        try { return jdbc.queryForObject(sql, String.class); }
        catch (Exception e) { return null; }
    }

    @Override
    public List<Documento> listarIngresos() {
        String sql = """
            SELECT d.*,
                   u.nombre_completo AS usuario_nombre,
                   SUM(dd.cantidad) AS total_cantidad
            FROM documentos d
            JOIN usuarios u ON u.id = d.id_usuario
            JOIN documento_detalles dd ON dd.id_documento = d.id
            WHERE d.tipo_movimiento = 'INGRESO'
            GROUP BY d.id, u.nombre_completo
            ORDER BY d.id DESC
        """;

        return jdbc.query(sql, mapperListado);
    }

    @Override
    public List<Documento> listarSalidas() {
        String sql = """
            SELECT d.*,
                   u.nombre_completo AS usuario_nombre,
                   SUM(dd.cantidad) AS total_cantidad
            FROM documentos d
            JOIN usuarios u ON u.id = d.id_usuario
            JOIN documento_detalles dd ON dd.id_documento = d.id
            WHERE d.tipo_movimiento = 'SALIDA'
            GROUP BY d.id, u.nombre_completo
            ORDER BY d.id DESC
        """;

        return jdbc.query(sql, mapperListado);
    }

    @Override
    public Documento buscarPorId(int id) {
        String sql = """
            SELECT d.*,
                   u.nombre_completo AS usuario_nombre
            FROM documentos d
            JOIN usuarios u ON u.id = d.id_usuario
            WHERE d.id = ?
        """;

        return jdbc.queryForObject(sql, (rs, row) -> {

            Usuario u = new Usuario();
            u.setId(rs.getInt("id_usuario"));
            u.setNombreCompleto(rs.getString("usuario_nombre"));

            Documento d = new Documento();
            d.setId(rs.getInt("id"));
            d.setTipoMovimiento(rs.getString("tipo_movimiento"));
            d.setNumeroDocumento(rs.getString("numero_documento"));
            d.setFecha(rs.getDate("fecha").toLocalDate());
            d.setUsuario(u);
            d.setObservacion(rs.getString("observacion"));
            d.setActivo(rs.getBoolean("activo"));
            return d;

        }, id);
    }
}
