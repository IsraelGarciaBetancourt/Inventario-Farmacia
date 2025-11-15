package com.example.demo.usuario;

import java.util.List;

public interface UsuarioService {

    Usuario autenticar(String username, String password);

    List<Usuario> listar();
    Usuario buscarPorId(Integer id);
    void guardar(Usuario usuario);
    void actualizar(Usuario usuario);
    void cambiarEstado(Integer id);
}
