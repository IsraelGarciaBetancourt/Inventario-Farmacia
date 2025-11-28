package com.example.demo.categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listar();

    Categoria buscarPorId(int id);

    int guardar(Categoria categoria);

    int activar(int id);

    int actualizar(Categoria categoria);

    int eliminar(int id); // soft delete
}
