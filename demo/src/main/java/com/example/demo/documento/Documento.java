package com.example.demo.documento;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

public class Documento {

    private int id;
    private String tipoMovimiento;
    private String numeroDocumento;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha;

    private int idUsuario;
    private String usuarioNombre;
    private String observacion;

    private int totalProductos;
    private int totalUnidades;
    private double totalValor;
    private boolean activo;

    private List<DocumentoDetalle> detalles;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public int getTotalProductos() { return totalProductos; }
    public void setTotalProductos(int totalProductos) { this.totalProductos = totalProductos; }

    public int getTotalUnidades() { return totalUnidades; }
    public void setTotalUnidades(int totalUnidades) { this.totalUnidades = totalUnidades; }

    public double getTotalValor() { return totalValor; }
    public void setTotalValor(double totalValor) { this.totalValor = totalValor; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public List<DocumentoDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<DocumentoDetalle> detalles) { this.detalles = detalles; }
}
