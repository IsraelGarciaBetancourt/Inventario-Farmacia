package com.example.demo.ingresoProducto;

import java.util.List;

public interface IngresoProductoService {
    List<IngresoProducto> listarIngresos();
    void crearIngreso(IngresoProducto ingreso);
    String generarSiguienteNumeroDocumento();
    List<String> listarProductos();
}
