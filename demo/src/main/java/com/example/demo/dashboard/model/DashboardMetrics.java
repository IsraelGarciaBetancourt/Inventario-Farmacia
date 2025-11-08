package com.example.demo.dashboard.model;

public class DashboardMetrics {

    private int totalStock;
    private double valorTotal;
    private int productosEnRiesgo;

    public DashboardMetrics() {}

    public DashboardMetrics(int totalStock, double valorTotal, int productosEnRiesgo) {
        this.totalStock = totalStock;
        this.valorTotal = valorTotal;
        this.productosEnRiesgo = productosEnRiesgo;
    }

    public int getTotalStock() { return totalStock; }
    public void setTotalStock(int totalStock) { this.totalStock = totalStock; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public int getProductosEnRiesgo() { return productosEnRiesgo; }
    public void setProductosEnRiesgo(int productosEnRiesgo) { this.productosEnRiesgo = productosEnRiesgo; }

}
