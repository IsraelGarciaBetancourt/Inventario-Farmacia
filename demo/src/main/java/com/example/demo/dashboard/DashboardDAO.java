package com.example.demo.dashboard;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DashboardDAO {

    /**
     * Obtiene la suma de cantidades de ingresos o salidas en un rango de fechas
     */
    int obtenerCantidadPorTipo(String tipoMovimiento, LocalDate inicio, LocalDate fin);

    /**
     * Obtiene los top 5 productos con más salidas
     */
    List<Map<String, Object>> obtenerTop5ProductosSalidas(LocalDate inicio, LocalDate fin);
}