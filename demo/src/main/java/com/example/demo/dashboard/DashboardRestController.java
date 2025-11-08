package com.example.demo.dashboard;

import com.example.demo.dashboard.model.PeriodoEstadistico;
import com.example.demo.dashboard.model.TopProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardRestController {

    @Autowired private DashboardService dashboardService;
    @Autowired private DashboardRepository dashboardRepository;

    @GetMapping("/series")
    public Map<String, Object> obtenerSeries(@RequestParam(defaultValue = "D7") String period) {
        int dias = switch (period.toUpperCase()) {
            case "D7" -> 7;
            case "W4" -> 28;
            case "M4" -> 120;
            default -> 7;
        };

        List<PeriodoEstadistico> est = dashboardService.obtenerEstadisticasPorPeriodo(dias);
        Map<String, Object> kpis    = dashboardRepository.obtenerVentasYUnidadesPeriodo(dias);

        Map<String, Object> data = new HashMap<>();
        data.put("labels",   est.stream().map(PeriodoEstadistico::getPeriodo).collect(Collectors.toList()));
        data.put("ingresos", est.stream().map(PeriodoEstadistico::getIngresos).collect(Collectors.toList()));
        data.put("salidas",  est.stream().map(PeriodoEstadistico::getSalidas).collect(Collectors.toList()));
        data.put("kpiVentas",   kpis.get("ventas"));
        data.put("kpiUnidades", kpis.get("unidades"));
        return data;
    }

    @GetMapping("/top-salidas")
    public Map<String, Object> obtenerTopSalidas(@RequestParam(defaultValue = "D7") String period) {
        int dias = switch (period.toUpperCase()) {
            case "D7" -> 7;
            case "W4" -> 28;
            case "M4" -> 120;
            default -> 7;
        };
        var top = dashboardService.obtenerTopProductosVendidos(dias);

        Map<String, Object> data = new HashMap<>();
        data.put("labels",  top.stream().map(TopProductoDTO::getNombreProducto).collect(Collectors.toList()));
        data.put("valores", top.stream().map(TopProductoDTO::getTotalSalidas).collect(Collectors.toList()));
        return data;
    }
}
