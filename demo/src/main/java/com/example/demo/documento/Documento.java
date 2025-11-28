package com.example.demo.documento;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.usuario.Usuario;

public class Documento {

    private int id;
    private String tipoMovimiento;   // INGRESO / SALIDA
    private String numeroDocumento;
    private LocalDate fecha;
    private Usuario usuario;
    private String observacion;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // EXTRA para la vista de listar (SUM(dd.cantidad))
    private int totalCantidad;

    public Documento() {}

    public Documento(int id, String tipoMovimiento, String numeroDocumento,
                     LocalDate fecha, Usuario usuario, String observacion,
                     boolean activo, LocalDateTime createdAt, LocalDateTime updatedAt,
                     int totalCantidad) {
        this.id = id;
        this.tipoMovimiento = tipoMovimiento;
        this.numeroDocumento = numeroDocumento;
        this.fecha = fecha;
        this.usuario = usuario;
        this.observacion = observacion;
        this.activo = activo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalCantidad = totalCantidad;
    }

    // Getters y setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public int getTotalCantidad() { return totalCantidad; }
    public void setTotalCantidad(int totalCantidad) { this.totalCantidad = totalCantidad; }
}
