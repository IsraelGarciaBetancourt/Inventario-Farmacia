package com.example.demo.categoriaProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    public List<CategoriaProducto> listarCategorias() {
        return categoriaProductoRepository.listarCategorias();
    }

    @Override
    public List<CategoriaProducto> listarCategoriasActivas() {
        return categoriaProductoRepository.listarCategoriasActivas();
    }

    @Override
    public CategoriaProducto obtenerCategoriaPorId(int id) {
        return categoriaProductoRepository.obtenerCategoriaPorId(id);
    }

    @Override
    public boolean guardarCategoria(CategoriaProducto categoria) {
        return categoriaProductoRepository.guardarCategoria(categoria) > 0;
    }

    @Override
    public boolean actualizarCategoria(CategoriaProducto categoria) {
        return categoriaProductoRepository.actualizarCategoria(categoria) > 0;
    }

    @Override
    public boolean eliminarCategoria(int id) {
        return categoriaProductoRepository.eliminarCategoria(id) > 0;
    }
}
