package com.example.demo.dashboard;

import java.util.List;

public class DashboardData {

    // Para el gráfico de líneas
    private List<String> labels;
    private List<Integer> ingresos;
    private List<Integer> salidas;

    // Para el gráfico de barras
    private List<String> productosLabels;
    private List<Integer> productosCantidades;

    public DashboardData() {}

    public DashboardData(List<String> labels, List<Integer> ingresos, List<Integer> salidas,
                         List<String> productosLabels, List<Integer> productosCantidades) {
        this.labels = labels;
        this.ingresos = ingresos;
        this.salidas = salidas;
        this.productosLabels = productosLabels;
        this.productosCantidades = productosCantidades;
    }

    public List<String> getLabels() { return labels; }
    public void setLabels(List<String> labels) { this.labels = labels; }

    public List<Integer> getIngresos() { return ingresos; }
    public void setIngresos(List<Integer> ingresos) { this.ingresos = ingresos; }

    public List<Integer> getSalidas() { return salidas; }
    public void setSalidas(List<Integer> salidas) { this.salidas = salidas; }

    public List<String> getProductosLabels() { return productosLabels; }
    public void setProductosLabels(List<String> productosLabels) { this.productosLabels = productosLabels; }

    public List<Integer> getProductosCantidades() { return productosCantidades; }
    public void setProductosCantidades(List<Integer> productosCantidades) { this.productosCantidades = productosCantidades; }
}