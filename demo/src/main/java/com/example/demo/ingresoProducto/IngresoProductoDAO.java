package com.example.demo.ingresoProducto;

import java.util.List;

public interface IngresoProductoDAO {
    List<IngresoProducto> listarIngresos();
    void crearIngreso(IngresoProducto ingreso);
    void actualizarIngreso(IngresoProducto ingreso);

    List<String> listarProductos();
}
