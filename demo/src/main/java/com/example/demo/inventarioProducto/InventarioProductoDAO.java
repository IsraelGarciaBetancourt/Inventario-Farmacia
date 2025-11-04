package com.example.demo.inventarioProducto;

import java.util.List;

public interface InventarioProductoDAO {
    List<InventarioProducto> listarInventario();
    void crearInventario(InventarioProducto inventario);
    InventarioProducto obtenerPorId(Integer id);
    void actualizarInventario(InventarioProducto inventario);
}
