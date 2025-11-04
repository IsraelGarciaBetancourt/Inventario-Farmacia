package com.example.demo.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// DashboardPageController.java
@Controller
public class DashboardPageController {
    @GetMapping({"/", "/dashboard"})
    public String dashboard() {
        return "dashboard/dashboard";
    }
}

