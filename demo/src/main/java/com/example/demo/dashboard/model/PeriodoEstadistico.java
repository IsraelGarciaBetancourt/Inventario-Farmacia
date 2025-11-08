package com.example.demo.dashboard.model;

public class PeriodoEstadistico {
    private String periodo;   // Ejemplo: "2025-W44" o "2025-10-31"
    private int ingresos;     // Total de unidades ingresadas en el periodo
    private int salidas;      // Total de unidades salidas en el periodo

    public PeriodoEstadistico() {}

    public PeriodoEstadistico(String periodo, int ingresos, int salidas) {
        this.periodo = periodo;
        this.ingresos = ingresos;
        this.salidas = salidas;
    }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public int getIngresos() { return ingresos; }
    public void setIngresos(int ingresos) { this.ingresos = ingresos; }

    public int getSalidas() { return salidas; }
    public void setSalidas(int salidas) { this.salidas = salidas; }
}