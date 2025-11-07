package com.example.demo.categoriaProducto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaProductoRepository implements CategoriaProductoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<CategoriaProducto> mapRowToCategoria = new RowMapper<CategoriaProducto>() {
        @Override
        public CategoriaProducto mapRow(ResultSet rs, int rowNum) throws SQLException {
            CategoriaProducto c = new CategoriaProducto();
            c.setId(rs.getInt("id"));
            c.setNombre(rs.getString("nombre"));
            c.setActivo(rs.getBoolean("activo"));
            return c;
        }
    };

    @Override
    public List<CategoriaProducto> listarCategorias() {
        String sql = "SELECT id, nombre, activo FROM categorias ORDER BY id DESC";
        return jdbcTemplate.query(sql, mapRowToCategoria);
    }

    @Override
    public List<CategoriaProducto> listarCategoriasActivas() {
        String sql = "SELECT id, nombre, activo FROM categorias WHERE activo = TRUE ORDER BY nombre";
        return jdbcTemplate.query(sql, mapRowToCategoria);
    }

    @Override
    public CategoriaProducto obtenerCategoriaPorId(int id) {
        String sql = "SELECT id, nombre, activo FROM categorias WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, mapRowToCategoria, id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public int guardarCategoria(CategoriaProducto categoria) {
        String sql = "INSERT INTO categorias (nombre, activo) VALUES (?, TRUE)";
        return jdbcTemplate.update(sql, categoria.getNombre());
    }

    @Override
    public int actualizarCategoria(CategoriaProducto categoria) {
        String sql = "UPDATE categorias SET nombre = ?, activo = ? WHERE id = ?";
        return jdbcTemplate.update(sql, categoria.getNombre(), categoria.getActivo(), categoria.getId());
    }

    @Override
    public int eliminarCategoria(int id) {
        String sql = "UPDATE categorias SET activo = FALSE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
