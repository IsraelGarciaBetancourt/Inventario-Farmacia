package com.example.demo.productoParque;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoParqueRowMapper implements RowMapper<ProductoParque> {

    @Override
    public ProductoParque mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductoParque p = new ProductoParque();
        p.setId(rs.getInt("id"));
        p.setIdProductoCatalogo(rs.getInt("id_producto_catalogo"));
        p.setExistencias(rs.getInt("existencias"));
        p.setValorStock(rs.getDouble("valor_stock"));
        p.setActivo(rs.getBoolean("activo"));

        // ✅ nombre del producto desde producto_catalogo
        if (hasColumn(rs, "nombre_producto")) {
            p.setNombreProducto(rs.getString("nombre_producto"));
        }
        return p;
    }

    private boolean hasColumn(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
