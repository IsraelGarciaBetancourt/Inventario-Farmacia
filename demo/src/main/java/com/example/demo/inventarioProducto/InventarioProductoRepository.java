package com.example.demo.inventarioProducto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventarioProductoRepository {

    private final JdbcTemplate jdbcTemplate;

    public InventarioProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🧩 Mapear columnas del JOIN
    private InventarioProducto mapRow(ResultSet rs, int rowNum) throws SQLException {
        InventarioProducto p = new InventarioProducto();
        p.setId(rs.getInt("id_parque"));
        p.setId_producto_catalogo(rs.getInt("id_producto_catalogo"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombre(rs.getString("nombre_producto"));
        p.setCategoriaNombre(rs.getString("categoria"));
        p.setExistencias(rs.getInt("existencias"));
        p.setActivo(rs.getBoolean("activo_parque"));
        if (rs.getTimestamp("created_at_parque") != null)
            p.setCreatedAt(rs.getTimestamp("created_at_parque").toLocalDateTime());
        if (rs.getTimestamp("updated_at_parque") != null)
            p.setUpdatedAt(rs.getTimestamp("updated_at_parque").toLocalDateTime());
        return p;
    }

    // 🔍 Listar inventario con JOIN
    public List<InventarioProducto> listarInventario() {
        String sql = """
            SELECT 
                pp.id AS id_parque,
                pp.id_producto_catalogo,
                pc.codigo,
                pc.nombre AS nombre_producto,
                c.nombre AS categoria,
                pp.existencias,
                pp.activo AS activo_parque,
                pp.created_at AS created_at_parque,
                pp.updated_at AS updated_at_parque
            FROM producto_parque pp
            JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            JOIN categorias c ON c.id = pc.id_categoria
            ORDER BY pc.nombre ASC
        """;
        return jdbcTemplate.query(sql, this::mapRow);
    }

    // 🔍 Buscar producto específico
    public InventarioProducto buscarPorCatalogo(Integer idProductoCatalogo) {
        String sql = """
            SELECT 
                pp.id AS id_parque,
                pp.id_producto_catalogo,
                pc.codigo,
                pc.nombre AS nombre_producto,
                c.nombre AS categoria,
                pp.existencias,
                pp.activo AS activo_parque,
                pp.created_at AS created_at_parque,
                pp.updated_at AS updated_at_parque
            FROM producto_parque pp
            JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            JOIN categorias c ON c.id = pc.id_categoria
            WHERE pp.id_producto_catalogo = ?
        """;
        List<InventarioProducto> lista = jdbcTemplate.query(sql, this::mapRow, idProductoCatalogo);
        return lista.isEmpty() ? null : lista.get(0);
    }

    // 🔄 Actualizaciones de stock (igual que antes)
    public void aplicarEntrada(Integer idProductoCatalogo, int cantidad) {
        String sql = """
            UPDATE producto_parque
            SET existencias = existencias + ?, updated_at = CURRENT_TIMESTAMP
            WHERE id_producto_catalogo = ? AND activo = TRUE
        """;
        jdbcTemplate.update(sql, cantidad, idProductoCatalogo);
    }

    public void aplicarSalida(Integer idProductoCatalogo, int cantidad) {
        String sql = """
            UPDATE producto_parque
            SET existencias = GREATEST(existencias - ?, 0), updated_at = CURRENT_TIMESTAMP
            WHERE id_producto_catalogo = ? AND activo = TRUE
        """;
        jdbcTemplate.update(sql, cantidad, idProductoCatalogo);
    }
}
