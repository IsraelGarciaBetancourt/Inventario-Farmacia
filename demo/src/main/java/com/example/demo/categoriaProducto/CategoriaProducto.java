package com.example.demo.categoriaProducto;

import java.time.LocalDateTime;

public class CategoriaProducto {

    private Integer id;
    private String nombre;
    private Boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor vacío
    public CategoriaProducto() {
    }

    // Constructor con parámetros
    public CategoriaProducto(Integer id, String nombre, Boolean activo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
