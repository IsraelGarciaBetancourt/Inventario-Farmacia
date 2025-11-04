package com.example.demo.ingresoProducto;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngresoProductoServiceImpl implements IngresoProductoService {

    private final IngresoProductoRepository repo;

    public IngresoProductoServiceImpl(IngresoProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<IngresoProducto> listarIngresos() {
        return repo.listarIngresos();
    }

    @Override
    public void crearIngreso(IngresoProducto ingreso) {
        if (ingreso.getObservacion() == null || ingreso.getObservacion().isBlank()) {
            ingreso.setObservacion("Sin observación");
        }
        repo.crearIngreso(ingreso);
    }

    @Override
    public String generarSiguienteNumeroDocumento() {
        String ultimo = repo.obtenerUltimoNumeroDocumento();
        if (ultimo == null || ultimo.isEmpty()) {
            return "ING-001";
        }
        String[] partes = ultimo.split("-");
        int numero = Integer.parseInt(partes[1]);
        numero++;
        return String.format("ING-%03d", numero);
    }

    @Override
    public List<String> listarProductos() {
        return repo.listarProductos();
    }
}
