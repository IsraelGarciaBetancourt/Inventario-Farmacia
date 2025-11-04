package com.example.demo.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario autenticar(String username, String password) {
        Usuario usuario = usuarioRepo.buscarPorUsername(username);
        if (usuario == null) return null;

        // ⚠️ Como tus contraseñas están en texto plano (admin123, almacenero123)
        // solo comparamos directamente
        if (usuario.getPasswordHash().equals(password)) {
            return usuario;
        }

        return null;
    }
}
