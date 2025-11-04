package com.example.demo.inventarioProducto;

import java.util.List;

public interface InventarioProductoService {
    List<InventarioProducto> listarInventario();
    InventarioProducto obtenerPorCatalogo(Integer idProductoCatalogo);
    void procesarMovimiento(Integer idProductoCatalogo, int cantidad, String tipoMovimiento);
}
