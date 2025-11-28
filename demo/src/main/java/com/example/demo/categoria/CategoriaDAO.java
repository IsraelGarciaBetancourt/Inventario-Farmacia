package com.example.demo.categoria;

import java.util.List;

public interface CategoriaDAO {

    List<Categoria> listar();

    Categoria buscarPorId(int id);

    int guardar(Categoria categoria);

    int actualizar(Categoria categoria);

    int activar(int id);

    int softDelete(int id);
}
