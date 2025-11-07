package com.example.demo.productoCatalogo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoCatalogoRepository implements ProductoCatalogoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<ProductoCatalogo> mapRowToProducto = new RowMapper<ProductoCatalogo>() {
        @Override
        public ProductoCatalogo mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductoCatalogo p = new ProductoCatalogo();
            p.setId(rs.getInt("id"));
            p.setCodigo(rs.getString("codigo"));
            p.setNombre(rs.getString("nombre"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setActivo(rs.getBoolean("activo"));
            try {
                // Columna auxiliar del JOIN
                String nomCat = rs.getString("nombre_categoria");
                p.setNombreCategoria(nomCat);
            } catch (SQLException ignore) {
                // Si no viene del SELECT, no rompe
            }
            return p;
        }
    };

    @Override
    public List<ProductoCatalogo> listarProductosCatalogo() {
        String sql = """
            SELECT p.id, p.codigo, p.nombre, p.id_categoria, p.activo,
                   c.nombre AS nombre_categoria
            FROM producto_catalogo p
            INNER JOIN categorias c ON p.id_categoria = c.id
            ORDER BY p.id DESC
        """;
        return jdbcTemplate.query(sql, mapRowToProducto);
    }

    @Override
    public List<ProductoCatalogo> listarProductosCatalogoActivos() {
        String sql = """
            SELECT p.id, p.codigo, p.nombre, p.id_categoria, p.activo,
                   c.nombre AS nombre_categoria
            FROM producto_catalogo p
            INNER JOIN categorias c ON p.id_categoria = c.id
            WHERE p.activo = TRUE
            ORDER BY p.nombre
        """;
        return jdbcTemplate.query(sql, mapRowToProducto);
    }

    @Override
    public ProductoCatalogo obtenerProductoCatalogoPorId(int id) {
        String sql = """
            SELECT p.id, p.codigo, p.nombre, p.id_categoria, p.activo,
                   c.nombre AS nombre_categoria
            FROM producto_catalogo p
            INNER JOIN categorias c ON p.id_categoria = c.id
            WHERE p.id = ?
        """;
        try {
            return jdbcTemplate.queryForObject(sql, mapRowToProducto, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int guardarProductoCatalogo(ProductoCatalogo producto) {
        String sql = "INSERT INTO producto_catalogo (codigo, nombre, id_categoria, activo) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                producto.getCodigo(),
                producto.getNombre(),
                producto.getIdCategoria(),
                producto.getActivo()); // <- ahora respeta el select del formulario
    }

    @Override
    public int actualizarProductoCatalogo(ProductoCatalogo producto) {
        String sql = "UPDATE producto_catalogo SET codigo = ?, nombre = ?, id_categoria = ?, activo = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                producto.getCodigo(),
                producto.getNombre(),
                producto.getIdCategoria(),
                producto.getActivo(),
                producto.getId());
    }

    @Override
    public int toggleProductoCatalogo(int id) {
        // MySQL: NOT invierte el boolean (tinyint 0/1)
        String sql = "UPDATE producto_catalogo SET activo = NOT activo WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int desactivarProductoCatalogo(int id) {
        String sql = "UPDATE producto_catalogo SET activo = FALSE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
