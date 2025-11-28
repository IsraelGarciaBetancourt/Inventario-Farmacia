package com.example.demo.usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario buscarPorId(int id);

    Usuario buscarPorUsername(String username);

    int guardar(Usuario u);

    int actualizar(Usuario u);

    int desactivar(int id);

    int activar(int id);
}
