package com.example.demo.documento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.productoCatalogo.ProductoCatalogo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentoDetalleRepository implements DocumentoDetalleDAO {

    private final JdbcTemplate jdbc;

    public DocumentoDetalleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<DocumentoDetalle> mapper = (ResultSet rs, int rowNum) -> {

        ProductoCatalogo p = new ProductoCatalogo();
        p.setId(rs.getInt("id_producto_catalogo"));
        p.setNombre(rs.getString("nombre_producto"));

        return new DocumentoDetalle(
                rs.getInt("id"),
                rs.getInt("id_documento"),
                p,
                rs.getInt("cantidad"),
                rs.getBoolean("activo"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    };

    @Override
    public int guardarDetalle(DocumentoDetalle d) {
        String sql = """
            INSERT INTO documento_detalles
            (id_documento, id_producto_catalogo, cantidad, activo, created_at, updated_at)
            VALUES (?, ?, ?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        """;
        return jdbc.update(sql,
                d.getIdDocumento(),
                d.getProductoCatalogo().getId(),
                d.getCantidad()
        );
    }

    @Override
    public List<DocumentoDetalle> listarPorDocumentoId(int idDocumento) {
        String sql = """
            SELECT dd.*, pc.nombre AS nombre_producto
            FROM documento_detalles dd
            JOIN producto_catalogo pc ON pc.id = dd.id_producto_catalogo
            WHERE dd.id_documento = ?
        """;
        return jdbc.query(sql, mapper, idDocumento);
    }
}
