package com.example.demo.dashboard;

import com.example.demo.dashboard.model.DashboardMetrics;
import com.example.demo.dashboard.model.PeriodoEstadistico;
import com.example.demo.dashboard.model.TopProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Override
    public DashboardMetrics obtenerMetricasGenerales() {
        return dashboardRepository.obtenerMetricasGenerales();
    }

    @Override
    public List<PeriodoEstadistico> obtenerEstadisticasPorPeriodo(int dias) {
        return dashboardRepository.obtenerEstadisticasPorPeriodo(dias);
    }

    @Override
    public List<TopProductoDTO> obtenerTopProductosVendidos(int dias) {
        return dashboardRepository.obtenerTopProductosVendidos(dias);
    }
}
