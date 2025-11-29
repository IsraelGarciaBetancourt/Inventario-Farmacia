package com.example.demo.dashboard;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardDAO dashboardDAO;

    public DashboardServiceImpl(DashboardDAO dashboardDAO) {
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public DashboardData obtenerDatosDashboard(String periodo) {

        LocalDate hoy = LocalDate.now();

        List<String> labels = new ArrayList<>();
        List<Integer> ingresos = new ArrayList<>();
        List<Integer> salidas = new ArrayList<>();

        // Generar 7 periodos
        for (int i = 6; i >= 0; i--) {
            LocalDate inicio, fin;
            String label;

            switch (periodo) {
                case "7semanas":
                    fin = hoy.minusWeeks(i);
                    inicio = fin.minusDays(6);
                    label = inicio.format(DateTimeFormatter.ofPattern("dd/MM")) + " - " +
                            fin.format(DateTimeFormatter.ofPattern("dd/MM"));
                    break;

                case "7meses":
                    inicio = hoy.minusMonths(i).withDayOfMonth(1);
                    fin = inicio.withDayOfMonth(inicio.lengthOfMonth());
                    label = inicio.format(DateTimeFormatter.ofPattern("MMM yyyy", new Locale("es", "ES")));
                    break;

                default: // 7dias
                    inicio = hoy.minusDays(i);
                    fin = inicio;
                    label = inicio.format(DateTimeFormatter.ofPattern("dd/MM"));
            }

            labels.add(label);

            // Obtener cantidades de ingresos y salidas
            int cantIngresos = dashboardDAO.obtenerCantidadPorTipo("INGRESO", inicio, fin);
            int cantSalidas = dashboardDAO.obtenerCantidadPorTipo("SALIDA", inicio, fin);

            ingresos.add(cantIngresos);
            salidas.add(cantSalidas);
        }

        // Obtener Top 5 productos
        LocalDate fechaInicio = calcularFechaInicio(hoy, periodo);
        List<Map<String, Object>> top5 = dashboardDAO.obtenerTop5ProductosSalidas(fechaInicio, hoy);

        List<String> productosLabels = new ArrayList<>();
        List<Integer> productosCantidades = new ArrayList<>();

        for (Map<String, Object> row : top5) {
            productosLabels.add((String) row.get("nombre"));
            productosCantidades.add(((Number) row.get("total")).intValue());
        }

        return new DashboardData(labels, ingresos, salidas, productosLabels, productosCantidades);
    }

    private LocalDate calcularFechaInicio(LocalDate hoy, String periodo) {
        switch (periodo) {
            case "7semanas":
                return hoy.minusWeeks(7);
            case "7meses":
                return hoy.minusMonths(7);
            default:
                return hoy.minusDays(6);
        }
    }
}