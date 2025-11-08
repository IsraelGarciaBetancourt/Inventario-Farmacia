package com.example.demo.productoParque;

import java.util.List;

public interface ProductoParqueService {
    List<ProductoParque> listarTodos();
    List<ProductoParque> listarActivos();
    ProductoParque obtenerPorIdCatalogo(int idCatalogo);
    void actualizarStockPorMovimiento(int idCatalogo, int cantidad, double precioUnitario, boolean esIngreso);
    void cambiarEstado(int id);
    int contarBajoStock();
    int obtenerTotalUnidades();
}