package com.example.demo.productoCatalogo;

import java.util.List;

public interface ProductoCatalogoDAO {

    // Listados
    List<ProductoCatalogo> listarProductosCatalogo();
    List<ProductoCatalogo> listarProductosCatalogoActivos();

    // Obtener uno
    ProductoCatalogo obtenerProductoCatalogoPorId(int id);

    // Persistencia
    int guardarProductoCatalogo(ProductoCatalogo producto);
    int actualizarProductoCatalogo(ProductoCatalogo producto);

    // Desactivar (eliminar lógico)
    int desactivarProductoCatalogo(int id);
    int toggleProductoCatalogo(int id);

}
