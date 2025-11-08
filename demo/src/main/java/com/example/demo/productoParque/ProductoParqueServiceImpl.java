package com.example.demo.productoParque;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoParqueServiceImpl implements ProductoParqueService {

    private final ProductoParqueRepository productoParqueRepository;

    public ProductoParqueServiceImpl(ProductoParqueRepository productoParqueRepository) {
        this.productoParqueRepository = productoParqueRepository;
    }

    @Override
    public List<ProductoParque> listarTodos() {
        return productoParqueRepository.listarTodos();
    }

    @Override
    public List<ProductoParque> listarActivos() {
        return productoParqueRepository.listarActivos();
    }

    @Override
    public ProductoParque obtenerPorIdCatalogo(int idCatalogo) {
        return productoParqueRepository.obtenerPorIdCatalogo(idCatalogo);
    }

    @Override
    public void actualizarStockPorMovimiento(int idCatalogo, int cantidad, double precioUnitario, boolean esIngreso) {
        productoParqueRepository.actualizarStockPorMovimiento(idCatalogo, cantidad, precioUnitario, esIngreso);
    }

    @Override
    public void cambiarEstado(int id) {
        productoParqueRepository.cambiarEstado(id);
    }

    @Override
    public int contarBajoStock() {
        return productoParqueRepository.contarBajoStock();
    }

    @Override
    public int obtenerTotalUnidades() {
        return productoParqueRepository.obtenerTotalUnidades();
    }
}