package com.example.demo.categoriaProducto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    private final CategoriaProductoDAO categoriaDAO;

    public CategoriaProductoServiceImpl(CategoriaProductoDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<CategoriaProducto> listarCategorias() {
        return categoriaDAO.listarCategorias();
    }

    @Override
    public void crearCategoria(CategoriaProducto categoria) {
        categoriaDAO.crearCategoria(categoria);
    }

    @Override
    public CategoriaProducto obtenerPorId(Integer id) {
        return categoriaDAO.obtenerPorId(id);
    }

    @Override
    public void actualizarCategoria(CategoriaProducto categoria) {
        categoriaDAO.actualizarCategoria(categoria);
    }
}
