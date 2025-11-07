package com.example.demo.documento;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentoDetalleRowMapper implements RowMapper<DocumentoDetalle> {
    @Override
    public DocumentoDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentoDetalle d = new DocumentoDetalle();
        d.setId(rs.getInt("id"));
        d.setIdDocumento(rs.getInt("id_documento"));
        d.setIdProductoCatalogo(rs.getInt("id_producto_catalogo"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setPrecioUnitario(rs.getDouble("precio_unitario"));
        d.setSubtotal(rs.getDouble("subtotal"));
        d.setActivo(rs.getBoolean("activo"));
        try {
            d.setProductoNombre(rs.getString("producto_nombre"));
        } catch (SQLException e) {
            d.setProductoNombre(null);
        }
        return d;
    }
}
