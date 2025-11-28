package com.example.demo.productoParque;

import com.example.demo.productoCatalogo.ProductoCatalogo;

public class ProductoParque {

    private int id;
    private ProductoCatalogo productoCatalogo;
    private int existencias;
    private boolean activo;

    public ProductoParque() {}

    public ProductoParque(int id, ProductoCatalogo productoCatalogo, int existencias, boolean activo) {
        this.id = id;
        this.productoCatalogo = productoCatalogo;
        this.existencias = existencias;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public ProductoCatalogo getProductoCatalogo() { return productoCatalogo; }
    public void setProductoCatalogo(ProductoCatalogo productoCatalogo) { this.productoCatalogo = productoCatalogo; }

    public int getExistencias() { return existencias; }
    public void setExistencias(int existencias) { this.existencias = existencias; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
