package com.example.demo.productoCatalogo;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoCatalogoServiceImpl implements ProductoCatalogoService {

    private final ProductoCatalogoDAO dao;

    public ProductoCatalogoServiceImpl(ProductoCatalogoDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<ProductoCatalogo> listar() { return dao.listar(); }

    @Override
    public List<ProductoCatalogo> listarActivos() {
        return dao.listarActivos();
    }

    @Override
    public ProductoCatalogo buscarPorId(int id) { return dao.buscarPorId(id); }

    @Override
    public int guardar(ProductoCatalogo p) { return dao.guardar(p); }

    @Override
    public int actualizar(ProductoCatalogo p) { return dao.actualizar(p); }

    @Override
    public int desactivar(int id) { return dao.desactivar(id); }

    @Override
    public int activar(int id) { return dao.activar(id); }
}
