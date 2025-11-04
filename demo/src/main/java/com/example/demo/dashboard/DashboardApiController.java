package com.example.demo.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardApiController {

    private final DashboardService svc;

    @Autowired
    public DashboardApiController(DashboardService svc) {
        this.svc = svc;
    }

    private DashboardService.Periodo parse(String p) {
        return "M1".equalsIgnoreCase(p) ? DashboardService.Periodo.M1 : DashboardService.Periodo.W7;
    }

    @GetMapping("/series")
    public Map<String, Object> series(@RequestParam(defaultValue = "W7") String period) {
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
    public Map<String, Object> top(@RequestParam(defaultValue = "W7") String period) {
        var t = svc.topSalidas(parse(period));
        return Map.of("labels", t.labels, "valores", t.valores);
    }
}
