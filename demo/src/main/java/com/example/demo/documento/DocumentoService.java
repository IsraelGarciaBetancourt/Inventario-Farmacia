package com.example.demo.documento;

import com.example.demo.productoParque.ProductoParque;
import java.util.List;

public interface DocumentoService {
    List<Documento> listarIngresos();
    List<Documento> listarSalidas(); // ✅ nuevo
    Documento obtenerPorId(int id);
    List<DocumentoDetalle> listarDetalles(int idDocumento);
    String generarNumeroIngreso();
    String generarNumeroSalida();
    int registrarDocumentoConDetalles(Documento doc);
    int registrarSalidaConDetalles(Documento doc);
    List<ProductoParque> listarProductosEnParqueActivos();
}
