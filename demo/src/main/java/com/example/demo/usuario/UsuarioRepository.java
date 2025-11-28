package com.example.demo.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository implements UsuarioDAO {

    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<Usuario> mapper = new RowMapper<Usuario>() {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Usuario(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nombre_completo"),
                    rs.getString("rol"),
                    rs.getBoolean("activo"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
            );
        }
    };

    @Override
    public List<Usuario> listar() {
        return jdbc.query(
                "SELECT * FROM usuarios ORDER BY activo DESC, id DESC",
                mapper
        );
    }

    @Override
    public Usuario buscarPorId(int id) {
        return jdbc.queryForObject(
                "SELECT * FROM usuarios WHERE id = ?",
                mapper, id
        );
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return jdbc.queryForObject(
                "SELECT * FROM usuarios WHERE username = ?",
                mapper, username
        );
    }

    @Override
    public int guardar(Usuario u) {
        String sql = """
            INSERT INTO usuarios (username, password, nombre_completo, rol, activo, created_at, updated_at)
            VALUES (?, ?, ?, ?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        """;
        return jdbc.update(sql,
                u.getUsername(),
                u.getPassword(),
                u.getNombreCompleto(),
                u.getRol()
        );
    }

    @Override
    public int actualizar(Usuario u) {
        String sql = """
            UPDATE usuarios SET username = ?, password = ?, nombre_completo = ?, rol = ?, 
                                activo = ?, updated_at = CURRENT_TIMESTAMP
            WHERE id = ?
        """;
        return jdbc.update(sql,
                u.getUsername(),
                u.getPassword(),
                u.getNombreCompleto(),
                u.getRol(),
                u.isActivo(),
                u.getId()
        );
    }

    @Override
    public int desactivar(int id) {
        return jdbc.update("UPDATE usuarios SET activo = FALSE, updated_at=CURRENT_TIMESTAMP WHERE id=?", id);
    }

    @Override
    public int activar(int id) {
        return jdbc.update("UPDATE usuarios SET activo = TRUE, updated_at=CURRENT_TIMESTAMP WHERE id=?", id);
    }
}
