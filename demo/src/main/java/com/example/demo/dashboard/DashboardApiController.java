package com.example.demo.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardApiController {
    private final DashboardService svc;
    @Autowired
    public DashboardApiController(DashboardService svc) { this.svc = svc; }

    private DashboardService.Periodo parse(String p) {
        if (p == null) return DashboardService.Periodo.D7;
        switch (p.trim().toUpperCase()) {
            case "D7": return DashboardService.Periodo.D7;
            case "W4": return DashboardService.Periodo.W4;
            case "M4": return DashboardService.Periodo.M4;
            default:   return DashboardService.Periodo.D7;
        }
    }

    @GetMapping("/series")
    public Map<String, Object> series(@RequestParam(defaultValue = "D7") String period) {
        var s = svc.series(parse(period));
        return Map.of(
                "labels", s.labels,
                "ingresos", s.ingresos,
                "salidas", s.salidas,
                "ventasSoles", s.ventasSoles,
                "kpiUnidades", s.kpiUnidades,
                "kpiVentas", s.kpiVentas
        );
    }

    @GetMapping("/top-salidas")
    public Map<String, Object> top(@RequestParam(defaultValue = "D7") String period) {
        var t = svc.topSalidas(parse(period));
        return Map.of("labels", t.labels, "valores", t.valores);
    }
}

