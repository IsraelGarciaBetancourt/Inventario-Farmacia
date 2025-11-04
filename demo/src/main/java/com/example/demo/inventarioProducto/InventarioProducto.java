package com.example.demo.inventarioProducto;

import java.time.LocalDateTime;

public class InventarioProducto {
    private Integer id;
    private Integer id_producto_catalogo;
    private String codigo;           // de PRODUCTO_CATALOGO
    private String nombre;           // de PRODUCTO_CATALOGO
    private String categoriaNombre;  // de CATEGORIAS
    private Integer existencias;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getId_producto_catalogo() { return id_producto_catalogo; }
    public void setId_producto_catalogo(Integer id_producto_catalogo) { this.id_producto_catalogo = id_producto_catalogo; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }

    public Integer getExistencias() { return existencias; }
    public void setExistencias(Integer existencias) { this.existencias = existencias; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
