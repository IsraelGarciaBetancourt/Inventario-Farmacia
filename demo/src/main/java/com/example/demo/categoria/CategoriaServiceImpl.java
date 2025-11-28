package com.example.demo.categoria;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }

    @Override
    public Categoria buscarPorId(int id) {
        return categoriaDAO.buscarPorId(id);
    }

    @Override
    public int guardar(Categoria categoria) {
        return categoriaDAO.guardar(categoria);
    }

    @Override
    public int actualizar(Categoria categoria) {
        return categoriaDAO.actualizar(categoria);
    }

    @Override
    public int activar(int id) {
        return categoriaDAO.activar(id);
    }

    @Override
    public int eliminar(int id) {
        return categoriaDAO.softDelete(id);
    }
}
