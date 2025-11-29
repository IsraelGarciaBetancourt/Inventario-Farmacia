package com.example.demo.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public String mostrarDashboard(@RequestParam(defaultValue = "7dias") String periodo, Model model) {

        DashboardData data = dashboardService.obtenerDatosDashboard(periodo);

        model.addAttribute("labels", data.getLabels());
        model.addAttribute("ingresos", data.getIngresos());
        model.addAttribute("salidas", data.getSalidas());
        model.addAttribute("productosLabels", data.getProductosLabels());
        model.addAttribute("productosCantidades", data.getProductosCantidades());
        model.addAttribute("periodo", periodo);

        return "dashboard/dashboard";
    }
}