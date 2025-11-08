package com.example.demo.dashboard;

import com.example.demo.dashboard.model.DashboardMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        DashboardMetrics metricas = dashboardService.obtenerMetricasGenerales();
        model.addAttribute("metricas", metricas);
        return "dashboard/dashboard";
    }
}
