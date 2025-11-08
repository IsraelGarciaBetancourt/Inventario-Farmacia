package com.example.demo.productoParque;

public class ProductoParque {
    private int id;
    private int idProductoCatalogo;
    private int existencias;
    private double valorStock;
    private boolean activo;
    private String nombreProducto;
    private String codigoProducto;
    private String categoriaNombre;

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

    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }

    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }
}