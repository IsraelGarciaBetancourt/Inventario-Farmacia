package com.example.demo.productoParque;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoParqueRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductoParqueRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ✅ Lista de productos activos con nombre del catálogo (para salidas)
    public List<ProductoParque> listarActivos() {
        String sql = """
            SELECT 
                pp.id,
                pp.id_producto_catalogo,
                pp.existencias,
                pp.valor_stock,
                pp.activo,
                pc.nombre AS nombre_producto
            FROM producto_parque pp
            INNER JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            WHERE pp.activo = TRUE AND pc.activo = TRUE
            ORDER BY pc.nombre ASC
        """;
        return jdbcTemplate.query(sql, (rs, i) -> {
            ProductoParque p = new ProductoParque();
            p.setId(rs.getInt("id"));
            p.setIdProductoCatalogo(rs.getInt("id_producto_catalogo"));
            p.setExistencias(rs.getInt("existencias"));
            p.setValorStock(rs.getDouble("valor_stock"));
            p.setActivo(rs.getBoolean("activo"));
            p.setNombreProducto(rs.getString("nombre_producto"));
            return p;
        });
    }

    // ✅ Obtener un producto del parque por su id_catalogo
    public ProductoParque obtenerPorIdCatalogo(int idCatalogo) {
        String sql = """
            SELECT 
                pp.id,
                pp.id_producto_catalogo,
                pp.existencias,
                pp.valor_stock,
                pp.activo,
                pc.nombre AS nombre_producto
            FROM producto_parque pp
            INNER JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            WHERE pp.id_producto_catalogo = ?
        """;
        return jdbcTemplate.queryForObject(sql, (rs, i) -> {
            ProductoParque p = new ProductoParque();
            p.setId(rs.getInt("id"));
            p.setIdProductoCatalogo(rs.getInt("id_producto_catalogo"));
            p.setExistencias(rs.getInt("existencias"));
            p.setValorStock(rs.getDouble("valor_stock"));
            p.setActivo(rs.getBoolean("activo"));
            p.setNombreProducto(rs.getString("nombre_producto"));
            return p;
        }, idCatalogo);
    }

    // ✅ Actualiza el stock de un producto dependiendo del tipo de movimiento
    public void actualizarStockPorMovimiento(int idCatalogo, int cantidad, double precioUnitario, boolean esIngreso) {
        String sql;
        if (esIngreso) {
            sql = """
                UPDATE producto_parque
                SET existencias = existencias + ?,
                    valor_stock = valor_stock + (? * ?),
                    updated_at = CURRENT_TIMESTAMP
                WHERE id_producto_catalogo = ? AND activo = TRUE
            """;
        } else {
            sql = """
                UPDATE producto_parque
                SET existencias = existencias - ?,
                    valor_stock = valor_stock - (? * ?),
                    updated_at = CURRENT_TIMESTAMP
                WHERE id_producto_catalogo = ? AND activo = TRUE
            """;
        }
        jdbcTemplate.update(sql, cantidad, cantidad, precioUnitario, idCatalogo);
    }
}
