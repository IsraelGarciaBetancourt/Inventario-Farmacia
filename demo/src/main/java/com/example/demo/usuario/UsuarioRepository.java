package com.example.demo.usuario;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioRepository implements UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // MAPPER (agrega created_at y updated_at si existen)
    private Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setNombreCompleto(rs.getString("nombre_completo"));
        u.setRol(rs.getString("rol"));
        u.setActivo(rs.getBoolean("activo"));
        return u;
    }

    // BUSCAR POR USERNAME PARA LOGIN
    @Override
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ? LIMIT 1";
        List<Usuario> lista = jdbcTemplate.query(sql, this::mapRow, username);
        return lista.isEmpty() ? null : lista.get(0);
    }

    // LISTAR
    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuarios ORDER BY id DESC";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, id);
    }

    // GUARDAR
    public void guardar(Usuario u) {
        String sql = """
            INSERT INTO usuarios (username, password_hash, nombre_completo, rol, activo)
            VALUES (?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                u.getUsername(),
                u.getPasswordHash(),
                u.getNombreCompleto(),
                u.getRol(),
                u.isActivo());
    }

    // ACTUALIZAR
    public void actualizar(Usuario u) {
        String sql = """
            UPDATE usuarios SET username=?, password_hash=?, nombre_completo=?, rol=?, activo=?
            WHERE id=?
        """;
        jdbcTemplate.update(sql,
                u.getUsername(),
                u.getPasswordHash(),
                u.getNombreCompleto(),
                u.getRol(),
                u.isActivo(),
                u.getId());
    }

    // TOGGLE ACTIVO
    public void toggleActivo(Integer id) {
        String sql = """
            UPDATE usuarios 
            SET activo = NOT activo 
            WHERE id = ?
        """;
        jdbcTemplate.update(sql, id);
    }
}
