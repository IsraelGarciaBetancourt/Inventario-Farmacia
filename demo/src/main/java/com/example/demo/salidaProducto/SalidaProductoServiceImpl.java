package com.example.demo.salidaProducto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalidaProductoServiceImpl implements SalidaProductoService {

    private final SalidaProductoRepository repo;

    public SalidaProductoServiceImpl(SalidaProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<SalidaProducto> listarSalidas() {
        return repo.listarSalidas();
    }

    @Override
    public void crearSalida(SalidaProducto salida) {
        if (salida.getObservacion() == null || salida.getObservacion().isBlank()) {
            salida.setObservacion("Sin observación");
        }
        repo.crearSalida(salida);
    }

    @Override
    public String generarSiguienteNumeroDocumento() {
        String ultimo = repo.obtenerUltimoNumeroDocumento();
        if (ultimo == null || ultimo.isEmpty()) {
            return "SAL-001";
        }
        String[] partes = ultimo.split("-");
        int numero = Integer.parseInt(partes[1]);
        numero++;
        return String.format("SAL-%03d", numero);
    }

    @Override
    public List<String> listarProductos() {
        return repo.listarProductos();
    }
}
