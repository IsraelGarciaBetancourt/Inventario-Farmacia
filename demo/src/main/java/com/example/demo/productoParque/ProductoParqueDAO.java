package com.example.demo.productoParque;

import java.util.List;

public interface ProductoParqueDAO {
    List<ProductoParque> listarActivos();
    ProductoParque obtenerPorIdCatalogo(int idCatalogo);
    int registrar(ProductoParque productoParque);
    int actualizarExistencias(int idCatalogo, int nuevaCantidad, double nuevoValor);
    int actualizarStockPorMovimiento(int idCatalogo, int cantidad, double precioUnitario, boolean esIngreso);
}
