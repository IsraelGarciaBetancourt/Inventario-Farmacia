package com.example.demo.inventarioProducto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventarioProductoServiceImpl implements InventarioProductoService {

    private final InventarioProductoRepository repo;

    public InventarioProductoServiceImpl(InventarioProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<InventarioProducto> listarInventario() {
        return repo.listarInventario();
    }

    @Override
    public InventarioProducto obtenerPorCatalogo(Integer idProductoCatalogo) {
        return repo.buscarPorCatalogo(idProductoCatalogo);
    }

    @Override
    public void procesarMovimiento(Integer idProductoCatalogo, int cantidad, String tipoMovimiento) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }

        switch (tipoMovimiento.toUpperCase()) {
            case "ENTRADA":
                repo.aplicarEntrada(idProductoCatalogo, cantidad);
                break;

            case "SALIDA":
                repo.aplicarSalida(idProductoCatalogo, cantidad);
                break;

            default:
                throw new IllegalArgumentException("Tipo de movimiento no válido: " + tipoMovimiento);
        }
    }
}
