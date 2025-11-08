package com.example.demo.dashboard.model;

public class TopProductoDTO {
    private String nombreProducto;
    private int totalSalidas;

    public TopProductoDTO(String nombreProducto, int totalSalidas) {
        this.nombreProducto = nombreProducto;
        this.totalSalidas = totalSalidas;
    }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public int getTotalSalidas() { return totalSalidas; }
    public void setTotalSalidas(int totalSalidas) { this.totalSalidas = totalSalidas; }
}