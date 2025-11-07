package com.example.demo.documento;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentoRowMapper implements RowMapper<Documento> {

    @Override
    public Documento mapRow(ResultSet rs, int rowNum) throws SQLException {
        Documento documento = new Documento();

        documento.setId(rs.getInt("id"));
        documento.setTipoMovimiento(rs.getString("tipo_movimiento"));
        documento.setNumeroDocumento(rs.getString("numero_documento"));
        documento.setFecha(rs.getDate("fecha"));
        documento.setIdUsuario(rs.getInt("id_usuario"));
        documento.setObservacion(rs.getString("observacion"));
        documento.setTotalProductos(rs.getInt("total_productos"));
        documento.setTotalUnidades(rs.getInt("total_unidades"));
        documento.setTotalValor(rs.getDouble("total_valor"));
        documento.setActivo(rs.getBoolean("activo"));

        return documento;
    }
}
