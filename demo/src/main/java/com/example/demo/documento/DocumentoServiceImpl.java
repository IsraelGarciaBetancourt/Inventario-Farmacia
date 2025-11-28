package com.example.demo.documento;

import java.util.List;

import com.example.demo.productoParque.ProductoParqueService;
import org.springframework.stereotype.Service;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoDAO documentoDAO;
    private final DocumentoDetalleDAO detalleDAO;
    private final ProductoParqueService parqueService;

    public DocumentoServiceImpl(DocumentoDAO documentoDAO,
                                DocumentoDetalleDAO detalleDAO,
                                ProductoParqueService parqueService) {
        this.documentoDAO = documentoDAO;
        this.detalleDAO = detalleDAO;
        this.parqueService = parqueService;
    }

    @Override
    public List<Documento> listarIngresos() {
        return documentoDAO.listarIngresos();
    }

    @Override
    public List<Documento> listarSalidas() {
        return documentoDAO.listarSalidas();
    }

    @Override
    public Documento buscarPorId(int id) {
        return documentoDAO.buscarPorId(id);
    }

    @Override
    public List<DocumentoDetalle> listarDetalles(int idDocumento) {
        return detalleDAO.listarPorDocumentoId(idDocumento);
    }

    @Override
    public String generarNumeroIngreso() {
        String ultimo = documentoDAO.obtenerUltimoNumeroIngreso();
        return generarCorrelativo(ultimo, "ING-");
    }

    @Override
    public String generarNumeroSalida() {
        String ultimo = documentoDAO.obtenerUltimoNumeroSalida();
        return generarCorrelativo(ultimo, "SAL-");
    }

    private String generarCorrelativo(String ultimo, String prefijo) {
        if (ultimo == null) {
            return prefijo + "0001";
        }
        int numero = Integer.parseInt(ultimo.substring(4));
        numero++;
        return String.format(prefijo + "%04d", numero);
    }

    @Override
    public String guardarIngreso(Documento documento, List<DocumentoDetalle> detalles) {

        documentoDAO.guardarCabecera(documento);

        int idDoc = documentoDAO.obtenerUltimoId(); // ← AHORA SI OBTIENE EL ID REAL

        for (DocumentoDetalle det : detalles) {
            det.setIdDocumento(idDoc);
            detalleDAO.guardarDetalle(det);

            parqueService.registrarIngreso(
                    det.getProductoCatalogo().getId(),
                    det.getCantidad()
            );
        }

        return "OK";
    }

    @Override
    public String guardarSalida(Documento documento, List<DocumentoDetalle> detalles) {

        // Validar stock antes de guardar
        for (DocumentoDetalle det : detalles) {
            if (!parqueService.registrarSalida(det.getProductoCatalogo().getId(), det.getCantidad())) {
                return "NO_STOCK";
            }
        }

        documentoDAO.guardarCabecera(documento);

        int idDoc = documentoDAO.obtenerUltimoId(); // ← AHORA SI OBTIENE EL ID REAL

        for (DocumentoDetalle det : detalles) {
            det.setIdDocumento(idDoc);
            detalleDAO.guardarDetalle(det);
        }

        return "OK";
    }
}
