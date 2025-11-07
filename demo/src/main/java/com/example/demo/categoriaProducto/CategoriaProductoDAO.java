package com.example.demo.categoriaProducto;

import java.util.List;

public interface CategoriaProductoDAO {

    // Listados
    List<CategoriaProducto> listarCategorias();
    List<CategoriaProducto> listarCategoriasActivas();

    // Obtener uno
    CategoriaProducto obtenerCategoriaPorId(int id);

    // Persistencia
    int guardarCategoria(CategoriaProducto categoria);
    int actualizarCategoria(CategoriaProducto categoria);

    // Desactivar (eliminado lógico)
    int eliminarCategoria(int id);
}
