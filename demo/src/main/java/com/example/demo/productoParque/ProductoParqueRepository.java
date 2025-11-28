package com.example.demo.productoParque;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.productoCatalogo.ProductoCatalogo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoParqueRepository implements ProductoParqueDAO {

    private final JdbcTemplate jdbc;

    public ProductoParqueRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<ProductoParque> mapper = new RowMapper<ProductoParque>() {
        @Override
        public ProductoParque mapRow(ResultSet rs, int rowNum) throws SQLException {

            ProductoCatalogo producto = new ProductoCatalogo(
                    rs.getInt("prod_id"),
                    rs.getString("codigo"),
                    rs.getString("prod_nombre"),
                    null,   // categoría NO se usa aquí
                    rs.getBoolean("prod_activo"),
                    null,
                    null
            );

            return new ProductoParque(
                    rs.getInt("parque_id"),
                    producto,
                    rs.getInt("existencias"),
                    rs.getBoolean("parque_activo")
            );
        }
    };

    @Override
    public List<ProductoParque> listar() {
        String sql = """
            SELECT 
                pp.id AS parque_id,
                pp.existencias,
                pp.activo AS parque_activo,
                pc.id AS prod_id,
                pc.codigo,
                pc.nombre AS prod_nombre,
                pc.activo AS prod_activo
            FROM producto_parque pp
            JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            ORDER BY pc.nombre ASC
        """;

        return jdbc.query(sql, mapper);
    }

    @Override
    public List<ProductoParque> listarActivosConStock() {
        String sql = """
            SELECT 
                pp.id AS parque_id,
                pp.existencias,
                pp.activo AS parque_activo,
                pc.id AS prod_id,
                pc.codigo,
                pc.nombre AS prod_nombre,
                pc.activo AS prod_activo
            FROM producto_parque pp
            JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            WHERE pp.activo = TRUE AND pp.existencias > 0
            ORDER BY pc.nombre ASC
        """;

        return jdbc.query(sql, mapper);
    }

    @Override
    public ProductoParque buscarPorProductoCatalogoId(int idProductoCatalogo) {
        String sql = """
            SELECT 
                pp.id AS parque_id,
                pp.existencias,
                pp.activo AS parque_activo,
                pc.id AS prod_id,
                pc.codigo,
                pc.nombre AS prod_nombre,
                pc.activo AS prod_activo
            FROM producto_parque pp
            JOIN producto_catalogo pc ON pc.id = pp.id_producto_catalogo
            WHERE pp.id_producto_catalogo = ?
        """;

        return jdbc.queryForObject(sql, mapper, idProductoCatalogo);
    }

    @Override
    public int guardar(ProductoParque p) {
        String sql = """
            INSERT INTO producto_parque
            (id_producto_catalogo, existencias, activo, created_at, updated_at)
            VALUES (?, ?, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        """;

        return jdbc.update(sql,
                p.getProductoCatalogo().getId(),
                p.getExistencias()
        );
    }

    @Override
    public int actualizar(ProductoParque p) {
        String sql = """
            UPDATE producto_parque
            SET existencias = ?, activo = ?, updated_at = CURRENT_TIMESTAMP
            WHERE id_producto_catalogo = ?
        """;

        return jdbc.update(sql,
                p.getExistencias(),
                p.isActivo(),
                p.getProductoCatalogo().getId()
        );
    }

    @Override
    public int desactivar(int idProductoCatalogo) {
        String sql = """
        UPDATE producto_parque
        SET activo = FALSE, updated_at = CURRENT_TIMESTAMP
        WHERE id_producto_catalogo = ?
    """;
        return jdbc.update(sql, idProductoCatalogo);
    }

    @Override
    public int activar(int idProductoCatalogo) {
        String sql = """
        UPDATE producto_parque
        SET activo = TRUE, updated_at = CURRENT_TIMESTAMP
        WHERE id_producto_catalogo = ?
    """;
        return jdbc.update(sql, idProductoCatalogo);
    }

}
