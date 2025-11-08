package com.example.demo.dashboard;

import com.example.demo.dashboard.model.DashboardMetrics;
import com.example.demo.dashboard.model.PeriodoEstadistico;
import com.example.demo.dashboard.model.TopProductoDTO;
import java.util.List;

public interface DashboardService {
    DashboardMetrics obtenerMetricasGenerales();
    List<PeriodoEstadistico> obtenerEstadisticasPorPeriodo(int dias);
    List<TopProductoDTO> obtenerTopProductosVendidos(int dias);
}
