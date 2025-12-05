package com.example.demo.productoParque;

import java.util.List;

public interface ProductoParqueDAO {

    List<ProductoParque> listar();               // todos (activados e inactivos)
    List<ProductoParque> listarActivosConStock(); // para salidas (solo activos y existencias > 0)
    List<ProductoParque> listarEnPeligro(); // existencias < 50
    ProductoParque buscarPorProductoCatalogoId(int idProductoCatalogo);

    int guardar(ProductoParque p);
    int actualizar(ProductoParque p);

    int desactivar(int idProductoCatalogo);
    int activar(int idProductoCatalogo);

}
