package com.example.demo.ingresoProducto;

import java.time.LocalDate;

public class IngresoProducto {

    private Integer id;
    private String numeroDocumento;
    private LocalDate fecha;
    private Integer idUsuario;
    private String observacion;
    private Boolean activo;

    public IngresoProducto() {}

    public IngresoProducto(Integer id, String numeroDocumento, LocalDate fecha, Integer idUsuario, String observacion, Boolean activo) {
        this.id = id;
        this.numeroDocumento = numeroDocumento;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.observacion = observacion;
        this.activo = activo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
