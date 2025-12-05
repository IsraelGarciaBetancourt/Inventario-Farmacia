package com.example.demo.productoParque;

import java.util.List;

public interface ProductoParqueService {

    List<ProductoParque> listar();
    List<ProductoParque> listarActivosConStock();

    List<ProductoParque> listarEnPeligro();

    ProductoParque buscarPorProductoCatalogoId(int idProductoCatalogo);

    void desactivar(int idProductoCatalogo);
    void activar(int idProductoCatalogo);

    void registrarIngreso(int idProductoCatalogo, int cantidad);
    boolean registrarSalida(int idProductoCatalogo, int cantidad);
}
