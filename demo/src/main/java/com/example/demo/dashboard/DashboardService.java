package com.example.demo.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class DashboardService {

    private final JdbcTemplate jdbc;

    @Autowired
    public DashboardService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Periodos fijos que pide el profe
    public enum Periodo { W7, M1 } // últimas 7 semanas | último mes (30 días)

    // Si usas Java 11 puedes dejar estos POJOs en vez de 'record'
    public static class SeriesDTO {
        public final List<String> labels;
        public final List<Integer> ingresos;
        public final List<Integer> salidas;
        public final List<Double> ventasSoles;
        public final long kpiUnidades;
        public final double kpiVentas;
        public SeriesDTO(List<String> labels, List<Integer> ingresos, List<Integer> salidas,
                         List<Double> ventasSoles, long kpiUnidades, double kpiVentas) {
            this.labels = labels; this.ingresos = ingresos; this.salidas = salidas;
            this.ventasSoles = ventasSoles; this.kpiUnidades = kpiUnidades; this.kpiVentas = kpiVentas;
        }
    }
    public static class TopDTO {
        public final List<String> labels;
        public final List<Integer> valores;
        public TopDTO(List<String> labels, List<Integer> valores) {
            this.labels = labels; this.valores = valores;
        }
    }

    private LocalDate[] rango(Periodo p) {
        LocalDate fin = LocalDate.now();
        LocalDate ini = fin.minusYears(5);     // amplio para verificar
        return new LocalDate[]{ini, fin};
    }



    private static String wkey(LocalDate d) {
        WeekFields wf = WeekFields.ISO;
        return "W" + String.format("%02d", d.get(wf.weekOfWeekBasedYear())) + "-" + d.get(wf.weekBasedYear());
    }

    public SeriesDTO series(Periodo p) {
        LocalDate[] r = rango(p);
        LocalDate ini = r[0], fin = r[1];

        // H2 soporta DATE(fecha); si diera problema, cambia a CAST(doc.fecha AS DATE) AS f
        String qCant = ""
                + "SELECT CAST(doc.fecha AS DATE) AS f, SUM(det.cantidad) AS total "
                + "FROM documentos doc "
                + "JOIN documento_detalles det ON det.id_documento = doc.id "
                + "WHERE doc.activo = TRUE AND det.activo = TRUE "
                + "  AND doc.tipo_movimiento = ? "
                + "  AND doc.fecha BETWEEN ? AND ? "
                + "GROUP BY CAST(doc.fecha AS DATE)";


        Map<String,Integer> ing = new HashMap<>();
        Map<String,Integer> sal = new HashMap<>();
        Map<String,Double>  ven = new HashMap<>();

        // ENTRADAS (unidades)
        jdbc.query(
                qCant,
                new Object[]{"ENTRADA", Date.valueOf(ini), Date.valueOf(fin)},
                (RowCallbackHandler) rs -> {
                    LocalDate d = rs.getDate("f").toLocalDate();
                    ing.merge(wkey(d), rs.getInt("total"), Integer::sum);
                }
        );

        // SALIDAS (unidades)
        jdbc.query(
                qCant,
                new Object[]{"SALIDA", Date.valueOf(ini), Date.valueOf(fin)},
                (RowCallbackHandler) rs -> {
                    LocalDate d = rs.getDate("f").toLocalDate();
                    sal.merge(wkey(d), rs.getInt("total"), Integer::sum);
                }
        );

        // Ventas en S/ (solo salidas)
        String qVen = ""
                + "SELECT CAST(doc.fecha AS DATE) AS f, SUM(det.subtotal) AS total "
                + "FROM documentos doc "
                + "JOIN documento_detalles det ON det.id_documento = doc.id "
                + "WHERE doc.activo = TRUE AND det.activo = TRUE "
                + "  AND doc.tipo_movimiento = 'SALIDA' "
                + "  AND doc.fecha BETWEEN ? AND ? "
                + "GROUP BY CAST(doc.fecha AS DATE)";


        jdbc.query(
                qVen,
                new Object[]{Date.valueOf(ini), Date.valueOf(fin)},
                (RowCallbackHandler) rs -> {
                    LocalDate d = rs.getDate("f").toLocalDate();
                    ven.merge(wkey(d), rs.getDouble("total"), Double::sum);
                }
        );

        // Unir semanas
        TreeSet<String> semanas = new TreeSet<>();
        semanas.addAll(ing.keySet());
        semanas.addAll(sal.keySet());
        semanas.addAll(ven.keySet());

        List<String> labels = new ArrayList<>(semanas);
        List<Integer> lIng = new ArrayList<>();
        List<Integer> lSal = new ArrayList<>();
        List<Double>  lVen = new ArrayList<>();
        long kUnid = 0; double kVen = 0;

        for (String w : labels) {
            int a = ing.getOrDefault(w, 0);
            int b = sal.getOrDefault(w, 0);
            double c = ven.getOrDefault(w, 0.0);
            lIng.add(a); lSal.add(b); lVen.add(c);
            kUnid += b; kVen += c;
        }

        return new SeriesDTO(labels, lIng, lSal, lVen, kUnid, kVen);
    }

    public TopDTO topSalidas(Periodo p) {
        LocalDate[] r = rango(p);
        String sql = ""
                + "SELECT p.nombre AS prod, SUM(det.cantidad) AS total "
                + "FROM documentos doc "
                + "JOIN documento_detalles det ON det.id_documento = doc.id "
                + "JOIN producto_catalogo p ON p.id = det.id_producto_catalogo "
                + "WHERE doc.activo = TRUE AND det.activo = TRUE AND p.activo = TRUE "
                + "  AND doc.tipo_movimiento = 'SALIDA' "
                + "  AND doc.fecha BETWEEN ? AND ? "
                + "GROUP BY p.nombre "
                + "ORDER BY total DESC "
                + "LIMIT 5";


        List<String> labels = new ArrayList<>();
        List<Integer> valores = new ArrayList<>();

        jdbc.query(
                sql,
                ps -> {
                    ps.setDate(1, Date.valueOf(r[0]));
                    ps.setDate(2, Date.valueOf(r[1]));
                },
                (RowCallbackHandler) rs -> {
                    labels.add(rs.getString("prod"));
                    valores.add(rs.getInt("total"));
                }
        );

        return new TopDTO(labels, valores);
    }
}
