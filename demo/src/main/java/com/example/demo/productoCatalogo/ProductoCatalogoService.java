package com.example.demo.productoCatalogo;

import java.util.List;

    public interface ProductoCatalogoService {

    List<ProductoCatalogo> listar();

    List<ProductoCatalogo> listarActivos();

    ProductoCatalogo buscarPorId(int id);

    int guardar(ProductoCatalogo p);

    int actualizar(ProductoCatalogo p);

    int desactivar(int id);

    int activar(int id);
}
