package com.example.demo.documento;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.productoCatalogo.ProductoCatalogo;

public class DocumentoMapper {

    public static List<DocumentoDetalle> mapDetalles(List<Integer> idProducto, List<Integer> cantidad) {

        List<DocumentoDetalle> lista = new ArrayList<>();

        for (int i = 0; i < idProducto.size(); i++) {

            // Saltar filas vacías (JS generará inputs vacíos a veces)
            if (idProducto.get(i) == null || cantidad.get(i) == null)
                continue;

            DocumentoDetalle det = new DocumentoDetalle();
            det.setProductoCatalogo(new ProductoCatalogo(idProducto.get(i), null, null, null, true, null, null));
            det.setCantidad(cantidad.get(i));
            lista.add(det);
        }

        return lista;
    }
}
