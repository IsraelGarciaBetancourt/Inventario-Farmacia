package com.example.Inventario_Farmacia.model;

import java.time.LocalDate;

public class Document {

    // Atributos
    private int ID;
    private String tipo_movimiento;
    private String numero_documento;
    private LocalDate fecha;
    private int id_usuario;
    private String observacion;

    // Constructor
    public Document(int ID, String tipo_movimiento, String numero_documento, LocalDate fecha, int id_usuario, String observacion) {
        this.ID = ID;
        this.tipo_movimiento = tipo_movimiento;
        this.numero_documento = numero_documento;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.observacion = observacion;
    }

    // Getters y Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
