package com.example.demo.dashboard;

public interface DashboardService {

    /**
     * Obtiene todos los datos del dashboard según el periodo
     * @param periodo "7dias", "7semanas" o "7meses"
     * @return DashboardData con todos los datos para las gráficas
     */
    DashboardData obtenerDatosDashboard(String periodo);
}