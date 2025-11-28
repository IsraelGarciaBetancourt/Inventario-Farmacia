package com.example.demo.documento;

import java.util.List;

public interface DocumentoService {

    List<Documento> listarIngresos();
    List<Documento> listarSalidas();

    Documento buscarPorId(int id);

    String generarNumeroIngreso();
    String generarNumeroSalida();

    String guardarIngreso(Documento documento, List<DocumentoDetalle> detalles);

    String guardarSalida(Documento documento, List<DocumentoDetalle> detalles);

    List<DocumentoDetalle> listarDetalles(int idDocumento);
}
