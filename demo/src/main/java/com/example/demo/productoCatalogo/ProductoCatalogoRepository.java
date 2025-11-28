package com.example.demo.productoCatalogo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.categoria.Categoria;

@Repository
public class ProductoCatalogoRepository implements ProductoCatalogoDAO {

    private final JdbcTemplate jdbc;

    public ProductoCatalogoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<ProductoCatalogo> mapper = new RowMapper<ProductoCatalogo>() {
        @Override
        public ProductoCatalogo mapRow(ResultSet rs, int rowNum) throws SQLException {

            Categoria categoria = new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("categoria_nombre"),
                    rs.getBoolean("categoria_activo"),
                    rs.getTimestamp("categoria_created").toLocalDateTime(),
                    rs.getTimestamp("categoria_updated").toLocalDateTime()
            );

            return new ProductoCatalogo(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    categoria,
                    rs.getBoolean("activo"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
            );
        }
    };

    @Override
    public List<ProductoCatalogo> listar() {
        String sql = """
            SELECT pc.*, 
                   c.nombre AS categoria_nombre,
                   c.activo AS categoria_activo,
                   c.created_at AS categoria_created,
                   c.updated_at AS categoria_updated
            FROM producto_catalogo pc
            JOIN categorias c ON c.id = pc.id_categoria
            ORDER BY pc.activo DESC, pc.id DESC;
        """;

        return jdbc.query(sql, mapper);
    }

    @Override
    public List<ProductoCatalogo> listarActivos() {
        String sql = """
        SELECT pc.*,
               c.id AS id_categoria,
               c.nombre AS categoria_nombre,
               c.activo AS categoria_activo,
               c.created_at AS categoria_created,
               c.updated_at AS categoria_updated
        FROM producto_catalogo pc
        JOIN categorias c ON pc.id_categoria = c.id
        WHERE pc.activo = TRUE
        ORDER BY pc.nombre ASC
    """;

        return jdbc.query(sql, mapper);
    }

    @Override
    public ProductoCatalogo buscarPorId(int id) {
        String sql = """
            SELECT pc.*, 
                   c.nombre AS categoria_nombre,
                   c.activo AS categoria_activo,
                   c.created_at AS categoria_created,
                   c.updated_at AS categoria_updated
            FROM producto_catalogo pc
            JOIN categorias c ON c.id = pc.id_categoria
            WHERE pc.id = ?;
        """;

        return jdbc.queryForObject(sql, mapper, id);
    }

    @Override
    public int guardar(ProductoCatalogo p) {
        String sql = """
            INSERT INTO producto_catalogo 
            (codigo, nombre, id_categoria, activo, created_at, updated_at)
            VALUES (?, ?, ?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
        """;

        return jdbc.update(sql, p.getCodigo(), p.getNombre(), p.getCategoria().getId());
    }

    @Override
    public int actualizar(ProductoCatalogo p) {
        String sql = """
            UPDATE producto_catalogo
            SET codigo = ?, nombre = ?, id_categoria = ?, activo = ?, 
                updated_at = CURRENT_TIMESTAMP
            WHERE id = ?;
        """;

        return jdbc.update(sql,
                p.getCodigo(), p.getNombre(),
                p.getCategoria().getId(), p.isActivo(),
                p.getId()
        );
    }

    @Override
    public int desactivar(int id) {
        return jdbc.update(
                "UPDATE producto_catalogo SET activo = FALSE, updated_at = CURRENT_TIMESTAMP WHERE id = ?",
                id
        );
    }

    @Override
    public int activar(int id) {
        return jdbc.update(
                "UPDATE producto_catalogo SET activo = TRUE, updated_at = CURRENT_TIMESTAMP WHERE id = ?",
                id
        );
    }
}
