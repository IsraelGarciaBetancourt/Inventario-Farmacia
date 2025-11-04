package com.example.demo.salidaProducto;

import java.util.List;

public interface SalidaProductoDAO {
    List<SalidaProducto> listarSalidas();
    void crearSalida(SalidaProducto salida);
    void actualizarSalida(SalidaProducto salida);
    List<String> listarProductos();
}
