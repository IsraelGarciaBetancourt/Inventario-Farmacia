package com.example.demo.documento;

import java.util.List;

public interface DocumentoDetalleDAO {

    int guardarDetalle(DocumentoDetalle d);

    List<DocumentoDetalle> listarPorDocumentoId(int idDocumento);
}
