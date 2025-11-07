package com.example.demo.documento;

import com.example.demo.productoParque.ProductoParque;
import com.example.demo.productoParque.ProductoParqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository repo;
    private final ProductoParqueService productoParqueService;

    public DocumentoServiceImpl(DocumentoRepository repo, ProductoParqueService productoParqueService) {
        this.repo = repo;
        this.productoParqueService = productoParqueService;
    }

    @Override
    public List<Documento> listarIngresos() {
        return repo.listarIngresos();
    }

    @Override
    public List<Documento> listarSalidas() {
        return repo.listarSalidas();
    }

    @Override
    public Documento obtenerPorId(int id) {
        return repo.obtenerPorId(id);
    }

    @Override
    public List<DocumentoDetalle> listarDetalles(int idDocumento) {
        return repo.listarDetallesPorDocumento(idDocumento);
    }

    @Override
    public String generarNumeroIngreso() {
        return repo.siguienteNumeroIngreso();
    }

    @Override
    public String generarNumeroSalida() {
        return repo.siguienteNumeroSalida();
    }

    @Override
    public List<ProductoParque> listarProductosEnParqueActivos() {
        return productoParqueService.listarActivos();
    }

    // ========================= INGRESOS =========================
    @Override
    @Transactional
    public int registrarDocumentoConDetalles(Documento doc) {
        int totalProductos = 0;
        int totalUnidades = 0;
        double totalValor = 0.0;

        if (doc.getDetalles() != null) {
            for (DocumentoDetalle det : doc.getDetalles()) {
                if (det == null) continue;
                int cantidad = Objects.requireNonNullElse(det.getCantidad(), 0);
                double precio = Objects.requireNonNullElse(det.getPrecioUnitario(), 0.0);
                if (cantidad > 0) {
                    totalProductos++;
                    totalUnidades += cantidad;
                    totalValor += cantidad * precio;
                }
            }
        }

        doc.setTotalProductos(totalProductos);
        doc.setTotalUnidades(totalUnidades);
        doc.setTotalValor(totalValor);

        int idDocumento = repo.insertarCabecera(doc);

        if (doc.getDetalles() != null) {
            for (DocumentoDetalle det : doc.getDetalles()) {
                if (det == null) continue;
                det.setIdDocumento(idDocumento);
                repo.insertarDetalle(det);

                // ✅ Actualiza existencias (suma para ingreso)
                productoParqueService.actualizarStockPorMovimiento(
                        det.getIdProductoCatalogo(),
                        det.getCantidad(),
                        det.getPrecioUnitario(),
                        true
                );
            }
        }

        return idDocumento;
    }

    // ========================= SALIDAS =========================
    @Override
    @Transactional
    public int registrarSalidaConDetalles(Documento doc) {
        int totalProductos = 0;
        int totalUnidades = 0;
        double totalValor = 0.0;

        if (doc.getDetalles() != null) {
            for (DocumentoDetalle det : doc.getDetalles()) {
                if (det == null) continue;
                int cantidad = Objects.requireNonNullElse(det.getCantidad(), 0);
                double precio = Objects.requireNonNullElse(det.getPrecioUnitario(), 0.0);
                if (cantidad > 0) {
                    totalProductos++;
                    totalUnidades += cantidad;
                    totalValor += cantidad * precio;
                }
            }
        }

        doc.setTotalProductos(totalProductos);
        doc.setTotalUnidades(totalUnidades);
        doc.setTotalValor(totalValor);

        int idDocumento = repo.insertarCabecera(doc);

        if (doc.getDetalles() != null) {
            for (DocumentoDetalle det : doc.getDetalles()) {
                if (det == null) continue;
                det.setIdDocumento(idDocumento);
                repo.insertarDetalle(det);

                // ✅ Actualiza existencias (resta para salida)
                productoParqueService.actualizarStockPorMovimiento(
                        det.getIdProductoCatalogo(),
                        det.getCantidad(),
                        det.getPrecioUnitario(),
                        false
                );
            }
        }

        return idDocumento;
    }
}
