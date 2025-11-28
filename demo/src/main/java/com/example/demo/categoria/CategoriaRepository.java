package com.example.demo.categoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepository implements CategoriaDAO {

    private final JdbcTemplate jdbcTemplate;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Categoria> categoriaMapper = new RowMapper<Categoria>() {
        @Override
        public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Categoria(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getBoolean("activo"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
            );
        }
    };

    @Override
    public List<Categoria> listar() {
        String sql = "SELECT * FROM categorias ORDER BY activo DESC, id DESC";
        return jdbcTemplate.query(sql, categoriaMapper);
    }

    @Override
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, categoriaMapper, id);
    }

    @Override
    public int guardar(Categoria c) {
        String sql = "INSERT INTO categorias (nombre, activo, created_at, updated_at) VALUES (?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        return jdbcTemplate.update(sql, c.getNombre());
    }

    @Override
    public int actualizar(Categoria c) {
        String sql = "UPDATE categorias " +
                "SET nombre = ?, activo = ?, updated_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                c.getNombre(),
                c.isActivo(),   // o c.getActivo() si usas Boolean
                c.getId()
        );
    }

    @Override
    public int activar(int id) {
        String sql = "UPDATE categorias SET activo = TRUE, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int softDelete(int id) {
        String sql = "UPDATE categorias SET activo = FALSE, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
