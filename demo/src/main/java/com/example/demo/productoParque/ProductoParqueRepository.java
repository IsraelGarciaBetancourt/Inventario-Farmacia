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

    // ✅ Lista TODOS los productos (activos e inactivos)
    public List<ProductoParque> listarTodos() {
        String sql = """
            SELECT 
                pp.id,
                pp.id_producto_catalogo,
                pp.existencias,
                pp.valor_stock,
                pp.activo,
                pc.nombre AS nombre_producto,
                pc.codigo AS codigo_producto,
                c.nombre AS categoria_nombre
            FROM producto_parque pp
            INNER JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            LEFT JOIN categorias c ON c.id = pc.id_categoria
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
            p.setCodigoProducto(rs.getString("codigo_producto"));
            p.setCategoriaNombre(rs.getString("categoria_nombre"));
            return p;
        });
    }

    // ✅ Lista de productos activos (para salidas)
    public List<ProductoParque> listarActivos() {
        String sql = """
            SELECT 
                pp.id,
                pp.id_producto_catalogo,
                pp.existencias,
                pp.valor_stock,
                pp.activo,
                pc.nombre AS nombre_producto,
                pc.codigo AS codigo_producto,
                c.nombre AS categoria_nombre
            FROM producto_parque pp
            INNER JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            LEFT JOIN categorias c ON c.id = pc.id_categoria
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
            p.setCodigoProducto(rs.getString("codigo_producto"));
            p.setCategoriaNombre(rs.getString("categoria_nombre"));
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
                pc.nombre AS nombre_producto,
                pc.codigo AS codigo_producto,
                c.nombre AS categoria_nombre
            FROM producto_parque pp
            INNER JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            LEFT JOIN categorias c ON c.id = pc.id_categoria
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
            p.setCodigoProducto(rs.getString("codigo_producto"));
            p.setCategoriaNombre(rs.getString("categoria_nombre"));
            return p;
        }, idCatalogo);
    }

    // ✅ Verifica si existe un producto en el parque
    private boolean existeEnParque(int idCatalogo) {
        String sql = "SELECT COUNT(*) FROM producto_parque WHERE id_producto_catalogo = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idCatalogo);
        return count != null && count > 0;
    }

    // ✅ Crea un nuevo registro en producto_parque si no existe
    private void crearEnParqueSiNoExiste(int idCatalogo) {
        if (!existeEnParque(idCatalogo)) {
            String sql = """
                INSERT INTO producto_parque (id_producto_catalogo, existencias, valor_stock, activo, created_at, updated_at)
                VALUES (?, 0, 0, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            """;
            jdbcTemplate.update(sql, idCatalogo);
            System.out.println("✅ Producto " + idCatalogo + " creado automáticamente en producto_parque");
        }
    }

    // ✅ Actualiza el stock de un producto dependiendo del tipo de movimiento
    public void actualizarStockPorMovimiento(int idCatalogo, int cantidad, double precioUnitario, boolean esIngreso) {
        // 🔥 PRIMERO: Asegurar que el producto existe en producto_parque
        crearEnParqueSiNoExiste(idCatalogo);

        // SEGUNDO: Actualizar el stock
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

    // ✅ Cambiar estado activo/inactivo de un producto en el parque
    public int cambiarEstado(int id) {
        String sql = """
            UPDATE producto_parque
            SET activo = NOT activo,
                updated_at = CURRENT_TIMESTAMP
            WHERE id = ?
        """;
        return jdbcTemplate.update(sql, id);
    }

    // ✅ Contar productos con bajo stock (menos de 50 unidades)
    public int contarBajoStock() {
        String sql = "SELECT COUNT(*) FROM producto_parque WHERE existencias < 50 AND activo = TRUE";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    // ✅ Obtener total de unidades en inventario
    public int obtenerTotalUnidades() {
        String sql = "SELECT COALESCE(SUM(existencias), 0) FROM producto_parque WHERE activo = TRUE";
        Integer total = jdbcTemplate.queryForObject(sql, Integer.class);
        return total != null ? total : 0;
    }
}