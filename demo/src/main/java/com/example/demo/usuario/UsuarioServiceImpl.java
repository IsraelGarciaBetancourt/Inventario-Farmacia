package com.example.demo.usuario;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO dao;

    public UsuarioServiceImpl(UsuarioDAO dao) {
        this.dao = dao;
    }

    public List<Usuario> listar() { return dao.listar(); }
    public Usuario buscarPorId(int id) { return dao.buscarPorId(id); }
    public Usuario buscarPorUsername(String username) { return dao.buscarPorUsername(username); }
    public int guardar(Usuario u) { return dao.guardar(u); }
    public int actualizar(Usuario u) { return dao.actualizar(u); }
    public int desactivar(int id) { return dao.desactivar(id); }
    public int activar(int id) { return dao.activar(id); }
}
