package com.example.demo.productoCatalogo;

import java.util.List;

public interface ProductoCatalogoService {

    List<ProductoCatalogo> listarProductosCatalogo();
    List<ProductoCatalogo> listarProductosCatalogoActivos();

    ProductoCatalogo obtenerProductoCatalogoPorId(int id);
    boolean guardarProductoCatalogo(ProductoCatalogo producto);
    boolean actualizarProductoCatalogo(ProductoCatalogo producto);

    boolean toggleProductoCatalogo(int id);
    boolean desactivarProductoCatalogo(int id);
}
