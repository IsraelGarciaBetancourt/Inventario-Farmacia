package com.example.demo.documento;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DocumentoRepository {

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert insertDocumento;

    public DocumentoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.insertDocumento = new SimpleJdbcInsert(jdbc)
                .withTableName("documentos")
                .usingGeneratedKeyColumns("id");
    }

    /** 🔹 Inserta la cabecera del documento y devuelve el ID generado */
    public int insertarCabecera(Documento d) {
        Map<String, Object> params = new HashMap<>();
        params.put("tipo_movimiento", d.getTipoMovimiento());
        params.put("numero_documento", d.getNumeroDocumento());
        params.put("fecha", new java.sql.Date(d.getFecha().getTime()));
        params.put("id_usuario", d.getIdUsuario());
        params.put("observacion", d.getObservacion());
        params.put("total_productos", d.getTotalProductos());
        params.put("total_unidades", d.getTotalUnidades());
        params.put("total_valor", d.getTotalValor());
        params.put("activo", true);

        Number key = insertDocumento.executeAndReturnKey(params);
        return key.intValue();
    }

    /** 🔹 Inserta un detalle del documento */
    public int insertarDetalle(DocumentoDetalle det) {
        String sql = """
            INSERT INTO documento_detalles
              (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        double subtotal = det.getCantidad() * det.getPrecioUnitario();
        return jdbc.update(sql,
                det.getIdDocumento(),
                det.getIdProductoCatalogo(),
                det.getCantidad(),
                det.getPrecioUnitario(),
                subtotal,
                true
        );
    }

    /** 🔹 Lista todos los ingresos */
    public List<Documento> listarIngresos() {
        String sql = """
            SELECT id, tipo_movimiento, numero_documento, fecha, id_usuario,
                   observacion, total_productos, total_unidades, total_valor, activo
            FROM documentos
            WHERE tipo_movimiento = 'INGRESO'
            ORDER BY id DESC
        """;
        return jdbc.query(sql, new DocumentoRowMapper());
    }

    /** 🔹 Lista todas las salidas */
    public List<Documento> listarSalidas() {
        String sql = """
        SELECT d.*, u.nombre_completo AS usuario_nombre
        FROM documentos d
        JOIN usuarios u ON d.id_usuario = u.id
        WHERE d.tipo_movimiento = 'SALIDA' AND d.activo = TRUE
        ORDER BY d.fecha DESC, d.id DESC
    """;
        return jdbc.query(sql, new DocumentoRowMapperConUsuario());
    }

    /** 🔹 Obtiene un documento por su ID (para el modal de detalle) */
    public Documento obtenerPorId(int id) {
        String sql = """
            SELECT d.*, u.nombre_completo AS usuario_nombre
            FROM documentos d
            JOIN usuarios u ON d.id_usuario = u.id
            WHERE d.id = ?
        """;
        return jdbc.queryForObject(sql, new DocumentoRowMapperConUsuario(), id);
    }

    /** 🔹 Lista los detalles de un documento con el nombre del producto */
    public List<DocumentoDetalle> listarDetallesPorDocumento(int idDocumento) {
        System.out.println("📥 [DEBUG] Entrando a listarDetallesPorDocumento con idDocumento = " + idDocumento);

        String sql = """
        SELECT d.id,
               d.id_documento,
               d.id_producto_catalogo,
               d.cantidad,
               d.precio_unitario,
               (d.cantidad * d.precio_unitario) AS subtotal,
               d.activo,
               p.nombre AS producto_nombre
        FROM documento_detalles d
        JOIN producto_catalogo p ON p.id = d.id_producto_catalogo
        WHERE d.id_documento = ? AND d.activo = TRUE
        ORDER BY d.id ASC
        """;

        try {
            List<DocumentoDetalle> detalles = jdbc.query(sql, new DocumentoDetalleRowMapper(), idDocumento);
            System.out.println("✅ [DEBUG] Detalles obtenidos: " + detalles.size());
            for (DocumentoDetalle det : detalles) {
                System.out.printf("   → ID:%d  Prod:%s  Cant:%d  Precio:%.2f  Sub:%.2f%n",
                        det.getId(), det.getProductoNombre(), det.getCantidad(),
                        det.getPrecioUnitario(), det.getSubtotal());
            }
            return detalles;
        } catch (Exception e) {
            System.err.println("❌ [ERROR] listarDetallesPorDocumento falló: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /** 🔹 Genera el próximo número correlativo tipo "ING-001" (compatible con H2 y MySQL) */
    public String siguienteNumeroIngreso() {
        String sql = """
            SELECT COALESCE(MAX(CAST(SUBSTRING(numero_documento, 5) AS INT)), 0)
            FROM documentos
            WHERE tipo_movimiento = 'INGRESO' AND numero_documento LIKE 'ING-%'
        """;
        Integer max = jdbc.queryForObject(sql, Integer.class);
        int next = (max == null ? 0 : max) + 1;
        return String.format("ING-%03d", next);
    }

    /** 🔹 Genera el próximo número correlativo tipo "SAL-001" (compatible con H2 y MySQL) */
    public String siguienteNumeroSalida() {
        String sql = """
        SELECT COALESCE(MAX(CAST(SUBSTRING(numero_documento, 5) AS INT)), 0)
        FROM documentos
        WHERE tipo_movimiento = 'SALIDA' AND numero_documento LIKE 'SAL-%'
    """;
        Integer max = jdbc.queryForObject(sql, Integer.class);
        int next = (max == null ? 0 : max) + 1;
        return String.format("SAL-%03d", next);
    }

    /** 🔹 Actualiza el stock en producto_parque tras ingreso o salida */
    public void actualizarStockYValor(int idProductoCatalogo, int cantidad, double precioUnitario, boolean esIngreso) {
        String operacion = esIngreso ? "+" : "-";
        String sql = String.format("""
            UPDATE producto_parque
            SET existencias = existencias %s ?,
                valor_stock = valor_stock %s (? * ?)
            WHERE id_producto_catalogo = ?
        """, operacion, operacion);

        jdbc.update(sql, cantidad, cantidad, precioUnitario, idProductoCatalogo);
    }

    /** 🔹 Devuelve la lista de productos con sus existencias actuales */
    public List<Map<String, Object>> listarProductosEnParque() {
        String sql = """
            SELECT p.id_producto_catalogo, pc.nombre AS producto_nombre,
                   p.existencias, p.valor_stock
            FROM producto_parque p
            JOIN producto_catalogo pc ON p.id_producto_catalogo = pc.id
            WHERE p.activo = TRUE
            ORDER BY pc.nombre
        """;
        return jdbc.queryForList(sql);
    }
}
