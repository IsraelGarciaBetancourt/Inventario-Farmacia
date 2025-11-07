package com.example.demo.documento;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentoRowMapperConUsuario implements RowMapper<Documento> {
    @Override
    public Documento mapRow(ResultSet rs, int rowNum) throws SQLException {
        Documento d = new Documento();
        d.setId(rs.getInt("id"));
        d.setTipoMovimiento(rs.getString("tipo_movimiento"));
        d.setNumeroDocumento(rs.getString("numero_documento"));
        d.setFecha(rs.getDate("fecha"));
        d.setIdUsuario(rs.getInt("id_usuario"));
        d.setObservacion(rs.getString("observacion"));
        d.setTotalProductos(rs.getInt("total_productos"));
        d.setTotalUnidades(rs.getInt("total_unidades"));
        d.setTotalValor(rs.getDouble("total_valor"));
        d.setActivo(rs.getBoolean("activo"));

        // 🔹 Campo adicional del JOIN
        d.setUsuarioNombre(rs.getString("usuario_nombre"));

        return d;
    }
}
