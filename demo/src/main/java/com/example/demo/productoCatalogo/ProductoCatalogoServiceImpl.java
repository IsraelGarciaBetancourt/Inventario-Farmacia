package com.example.demo.productoCatalogo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoCatalogoServiceImpl implements ProductoCatalogoService {

    @Autowired
    private ProductoCatalogoRepository productoCatalogoRepository;

    @Override
    public List<ProductoCatalogo> listarProductosCatalogo() {
        return productoCatalogoRepository.listarProductosCatalogo();
    }

    @Override
    public List<ProductoCatalogo> listarProductosCatalogoActivos() {
        return productoCatalogoRepository.listarProductosCatalogoActivos();
    }

    @Override
    public ProductoCatalogo obtenerProductoCatalogoPorId(int id) {
        return productoCatalogoRepository.obtenerProductoCatalogoPorId(id);
    }

    @Override
    public boolean guardarProductoCatalogo(ProductoCatalogo producto) {
        return productoCatalogoRepository.guardarProductoCatalogo(producto) > 0;
    }

    @Override
    public boolean actualizarProductoCatalogo(ProductoCatalogo producto) {
        return productoCatalogoRepository.actualizarProductoCatalogo(producto) > 0;
    }

    @Override
    public boolean toggleProductoCatalogo(int id) {
        return productoCatalogoRepository.toggleProductoCatalogo(id) > 0;
    }

    @Override
    public boolean desactivarProductoCatalogo(int id) {
        return productoCatalogoRepository.desactivarProductoCatalogo(id) > 0;
    }
}
