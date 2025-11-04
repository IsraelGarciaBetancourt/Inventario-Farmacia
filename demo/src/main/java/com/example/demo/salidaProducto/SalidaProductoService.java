package com.example.demo.salidaProducto;

import java.util.List;

public interface SalidaProductoService {
    List<SalidaProducto> listarSalidas();
    void crearSalida(SalidaProducto salida);
    String generarSiguienteNumeroDocumento();
    List<String> listarProductos();
}
