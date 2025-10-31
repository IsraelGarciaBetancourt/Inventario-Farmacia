package com.example.demo.categoriaProducto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoriaProductoRepository implements CategoriaProductoDAO {

    private final JdbcTemplate jdbcTemplate;

    public CategoriaProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private CategoriaProducto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoriaProducto c = new CategoriaProducto();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        c.setActivo(rs.getBoolean("activo"));
        return c;
    }

    @Override
    public List<CategoriaProducto> listarCategorias() {
        String sql = "SELECT * FROM categorias";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    @Override
    public void crearCategoria(CategoriaProducto categoria) {
        String sql = "INSERT INTO categorias (nombre, activo, created_at, updated_at) VALUES (?, ?, NOW(), NOW())";
        jdbcTemplate.update(sql, categoria.getNombre(), true);
    }

    @Override
    public CategoriaProducto obtenerPorId(Integer id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, id);
    }

    // ✅ Actualiza nombre y flag activo
    @Override
    public void actualizarCategoria(CategoriaProducto categoria) {
        String sql = "UPDATE categorias SET nombre = ?, activo = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(sql, categoria.getNombre(), categoria.getActivo(), categoria.getId());
    }
}
