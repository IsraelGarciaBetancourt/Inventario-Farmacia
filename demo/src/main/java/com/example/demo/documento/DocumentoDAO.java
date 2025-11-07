package com.example.demo.documento;

import java.util.List;

public interface DocumentoDAO {
    int registrarDocumento(Documento documento);
    int registrarDetalle(DocumentoDetalle detalle);
    List<Documento> listarPorTipo(String tipoMovimiento);
    List<DocumentoDetalle> listarDetallesPorDocumento(int idDocumento);
    Documento buscarPorId(int id);
    String generarNumeroDocumento(String tipoMovimiento);
}
