package com.example.demo.documento;

import java.util.List;

public interface DocumentoDAO {

    int guardarCabecera(Documento d);

    List<Documento> listarIngresos();
    List<Documento> listarSalidas();

    Documento buscarPorId(int id);

    String obtenerUltimoNumeroIngreso();
    String obtenerUltimoNumeroSalida();

    int obtenerUltimoId(); // ← AÑADIDO
}
