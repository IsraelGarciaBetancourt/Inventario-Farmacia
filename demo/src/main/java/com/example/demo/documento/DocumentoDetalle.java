package com.example.demo.documento;

public class DocumentoDetalle {

    private int id;
    private int idDocumento;
    private int idProductoCatalogo;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private boolean activo;
    private String productoNombre;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdDocumento() { return idDocumento; }
    public void setIdDocumento(int idDocumento) { this.idDocumento = idDocumento; }

    public int getIdProductoCatalogo() { return idProductoCatalogo; }
    public void setIdProductoCatalogo(int idProductoCatalogo) { this.idProductoCatalogo = idProductoCatalogo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
}
