package com.example.demo.usuario;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDao;
    private final UsuarioRepository usuarioRepo;

    public UsuarioServiceImpl(UsuarioDAO usuarioDao, UsuarioRepository usuarioRepo) {
        this.usuarioDao = usuarioDao;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario autenticar(String username, String password) {
        Usuario usuario = usuarioDao.buscarPorUsername(username);
        if (usuario == null) return null;

        // Comparación temporal (mejorar a BCrypt después)
        return usuario.getPasswordHash().equals(password) ? usuario : null;
    }

    // CRUD
    @Override
    public List<Usuario> listar() {
        return usuarioRepo.listar();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepo.buscarPorId(id);
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepo.guardar(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) {
        usuarioRepo.actualizar(usuario);
    }

    @Override
    public void cambiarEstado(Integer id) {
        usuarioRepo.toggleActivo(id);
    }
}
