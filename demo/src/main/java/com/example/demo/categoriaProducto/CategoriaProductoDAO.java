package com.example.demo.categoriaProducto;

import java.util.List;

public interface CategoriaProductoDAO {
    List<CategoriaProducto> listarCategorias();
    void crearCategoria(CategoriaProducto categoria);
    CategoriaProducto obtenerPorId(Integer id);
    void actualizarCategoria(CategoriaProducto categoria);
}
