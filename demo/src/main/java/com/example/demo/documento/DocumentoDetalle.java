package com.example.demo.documento;

import java.time.LocalDateTime;

import com.example.demo.productoCatalogo.ProductoCatalogo;

public class DocumentoDetalle {

    private int id;
    private int idDocumento;
    private ProductoCatalogo productoCatalogo;
    private int cantidad;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DocumentoDetalle() {}

    public DocumentoDetalle(int id, int idDocumento, ProductoCatalogo productoCatalogo,
                            int cantidad, boolean activo, LocalDateTime createdAt,
                            LocalDateTime updatedAt) {
        this.id = id;
        this.idDocumento = idDocumento;
        this.productoCatalogo = productoCatalogo;
        this.cantidad = cantidad;
        this.activo = activo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdDocumento() { return idDocumento; }
    public void setIdDocumento(int idDocumento) { this.idDocumento = idDocumento; }

    public ProductoCatalogo getProductoCatalogo() { return productoCatalogo; }
    public void setProductoCatalogo(ProductoCatalogo productoCatalogo) { this.productoCatalogo = productoCatalogo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
