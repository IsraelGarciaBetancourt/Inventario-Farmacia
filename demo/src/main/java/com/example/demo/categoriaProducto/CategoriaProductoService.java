package com.example.demo.categoriaProducto;

import java.util.List;

public interface CategoriaProductoService {

    List<CategoriaProducto> listarCategorias();

    CategoriaProducto obtenerCategoriaPorId(int id);

    boolean guardarCategoria(CategoriaProducto categoria);

    boolean actualizarCategoria(CategoriaProducto categoria);

    boolean eliminarCategoria(int id);

    // 🔥 Nuevo método para productoCatalogo.jsp
    List<CategoriaProducto> listarCategoriasActivas();
}
