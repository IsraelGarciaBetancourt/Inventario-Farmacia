package com.example.demo.productoParque;

public class ProductoParque {
    private int id;
    private int idProductoCatalogo;
    private int existencias;
    private double valorStock;
    private boolean activo;
    private String nombreProducto; // ✅ agregado para mostrar en los selects

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdProductoCatalogo() { return idProductoCatalogo; }
    public void setIdProductoCatalogo(int idProductoCatalogo) { this.idProductoCatalogo = idProductoCatalogo; }

    public int getExistencias() { return existencias; }
    public void setExistencias(int existencias) { this.existencias = existencias; }

    public double getValorStock() { return valorStock; }
    public void setValorStock(double valorStock) { this.valorStock = valorStock; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
}
