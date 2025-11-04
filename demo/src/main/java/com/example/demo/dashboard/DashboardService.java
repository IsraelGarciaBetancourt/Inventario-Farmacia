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
    public enum Periodo { D7, W4, M4 }


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
        LocalDate hoy = LocalDate.now();

        switch (p) {
            case D7 -> {
                LocalDate fin = hoy;
                LocalDate ini = fin.minusDays(6);
                return new LocalDate[]{ini, fin};
            }
            case W4 -> {
                // 4 semanas completas: lunes de hace 3 semanas ... domingo de la semana actual
                LocalDate start = startOfIsoWeek(hoy).minusWeeks(3);
                LocalDate end   = start.plusWeeks(4).minusDays(1);
                return new LocalDate[]{start, end};
            }


            case M4 -> {
                // Últimos 4 meses (incluye el mes actual)
                var actual = java.time.YearMonth.from(hoy);
                var masAntiguo = actual.minusMonths(3); // total 4
                LocalDate ini = masAntiguo.atDay(1);
                LocalDate fin = actual.atEndOfMonth();
                return new LocalDate[]{ini, fin};
            }
            default -> throw new IllegalArgumentException("Periodo inválido: " + p);
        }
    }




    private static String wkey(LocalDate d) {
        WeekFields wf = WeekFields.ISO;
        return "W" + String.format("%02d", d.get(wf.weekOfWeekBasedYear())) + "-" + d.get(wf.weekBasedYear());
    }

    private static final Locale LOCALE_ES = new Locale("es", "PE");

    private LocalDate startOfIsoWeek(LocalDate d) {
        return d.with(java.time.DayOfWeek.MONDAY);
    }

    private String weekKey(LocalDate d) {
        var wf = java.time.temporal.WeekFields.ISO;
        return "W" + String.format("%02d", d.get(wf.weekOfWeekBasedYear())) + "-" + d.get(wf.weekBasedYear());
    }

    private String monthKey(java.time.YearMonth ym) {
        return ym.format(java.time.format.DateTimeFormatter.ofPattern("MMM yyyy", LOCALE_ES));
    }


    public SeriesDTO series(Periodo p) {
        LocalDate[] r = rango(p);
        LocalDate ini = r[0], fin = r[1];

        // SQL base (por día). OJO: booleanos con TRUE/FALSE y CAST a DATE
        String qCant = ""
                + "SELECT CAST(doc.fecha AS DATE) AS f, SUM(det.cantidad) AS total "
                + "FROM documentos doc "
                + "JOIN documento_detalles det ON det.id_documento = doc.id "
                + "WHERE doc.activo = TRUE AND det.activo = TRUE "
                + "  AND doc.tipo_movimiento = ? "
                + "  AND doc.fecha BETWEEN ? AND ? "
                + "GROUP BY CAST(doc.fecha AS DATE)";

        String qVen = ""
                + "SELECT CAST(doc.fecha AS DATE) AS f, SUM(det.subtotal) AS total "
                + "FROM documentos doc "
                + "JOIN documento_detalles det ON det.id_documento = doc.id "
                + "WHERE doc.activo = TRUE AND det.activo = TRUE "
                + "  AND doc.tipo_movimiento = 'SALIDA' "
                + "  AND doc.fecha BETWEEN ? AND ? "
                + "GROUP BY CAST(doc.fecha AS DATE)";

        Map<LocalDate,Integer> ingDia = new HashMap<>();
        Map<LocalDate,Integer> salDia = new HashMap<>();
        Map<LocalDate,Double>  venDia = new HashMap<>();

        // Entradas
        jdbc.query(qCant, ps -> {
            ps.setString(1, "ENTRADA");
            ps.setDate(2, Date.valueOf(ini));
            ps.setDate(3, Date.valueOf(fin));
        }, rs -> {
            LocalDate d = rs.getDate("f").toLocalDate();
            ingDia.merge(d, rs.getInt("total"), Integer::sum);
        });

        // Salidas
        jdbc.query(qCant, ps -> {
            ps.setString(1, "SALIDA");
            ps.setDate(2, Date.valueOf(ini));
            ps.setDate(3, Date.valueOf(fin));
        }, rs -> {
            LocalDate d = rs.getDate("f").toLocalDate();
            salDia.merge(d, rs.getInt("total"), Integer::sum);
        });

        // Ventas S/
        jdbc.query(qVen, ps -> {
            ps.setDate(1, Date.valueOf(ini));
            ps.setDate(2, Date.valueOf(fin));
        }, rs -> {
            LocalDate d = rs.getDate("f").toLocalDate();
            venDia.merge(d, rs.getDouble("total"), Double::sum);
        });

        // Construcción de labels y agregación por período
        List<String> labels = new ArrayList<>();
        List<Integer> lIng = new ArrayList<>();
        List<Integer> lSal = new ArrayList<>();
        List<Double>  lVen = new ArrayList<>();

        long kUnid = 0;
        double kVen = 0;

        switch (p) {
            case D7 -> {
                // Día a día
                var fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM", LOCALE_ES);
                for (LocalDate d = ini; !d.isAfter(fin); d = d.plusDays(1)) {
                    labels.add(d.format(fmt));
                    int a = ingDia.getOrDefault(d, 0);
                    int b = salDia.getOrDefault(d, 0);
                    double c = venDia.getOrDefault(d, 0.0);
                    lIng.add(a); lSal.add(b); lVen.add(c);
                    kUnid += b; kVen += c;
                }
            }
            case W4 -> {
                // Semana a semana (4 semanas)
                for (LocalDate wIni = ini; !wIni.isAfter(fin); wIni = wIni.plusWeeks(1)) {
                    LocalDate wFin = wIni.plusDays(6);
                    String label = weekKey(wIni);
                    int a = 0, b = 0; double c = 0;
                    for (LocalDate d = wIni; !d.isAfter(wFin); d = d.plusDays(1)) {
                        a += ingDia.getOrDefault(d, 0);
                        b += salDia.getOrDefault(d, 0);
                        c += venDia.getOrDefault(d, 0.0);
                    }
                    labels.add(label);
                    lIng.add(a); lSal.add(b); lVen.add(c);
                    kUnid += b; kVen += c;
                }
            }
            case M4 -> {
                // Mes a mes (4 meses)
                var ymIni = java.time.YearMonth.from(ini);
                var ymFin = java.time.YearMonth.from(fin);
                for (var ym = ymIni; !ym.isAfter(ymFin); ym = ym.plusMonths(1)) {
                    String label = monthKey(ym); // "oct. 2025", etc.
                    int a = 0, b = 0; double c = 0;
                    LocalDate mIni = ym.atDay(1);
                    LocalDate mFin = ym.atEndOfMonth();
                    for (LocalDate d = mIni; !d.isAfter(mFin); d = d.plusDays(1)) {
                        a += ingDia.getOrDefault(d, 0);
                        b += salDia.getOrDefault(d, 0);
                        c += venDia.getOrDefault(d, 0.0);
                    }
                    labels.add(label);
                    lIng.add(a); lSal.add(b); lVen.add(c);
                    kUnid += b; kVen += c;
                }
            }
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
                + "  AND doc.tipo_movimiento='SALIDA' "
                + "  AND doc.fecha BETWEEN ? AND ? "
                + "GROUP BY p.nombre "
                + "ORDER BY total DESC "
                + "LIMIT 5";

        List<String> labels = new ArrayList<>();
        List<Integer> valores = new ArrayList<>();

        jdbc.query(sql, ps -> {
            ps.setDate(1, Date.valueOf(r[0]));
            ps.setDate(2, Date.valueOf(r[1]));
        }, rs -> {
            labels.add(rs.getString("prod"));
            valores.add(rs.getInt("total"));
        });

        return new TopDTO(labels, valores);
    }

}
