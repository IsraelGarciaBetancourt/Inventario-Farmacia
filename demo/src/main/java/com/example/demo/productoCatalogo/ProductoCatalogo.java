package com.example.demo.productoCatalogo;

import java.time.LocalDateTime;
import com.example.demo.categoria.Categoria;

public class ProductoCatalogo {

    private int id;
    private String codigo;
    private String nombre;
    private Categoria categoria;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductoCatalogo() {}

    public ProductoCatalogo(int id, String codigo, String nombre, Categoria categoria,
                            boolean activo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.activo = activo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
