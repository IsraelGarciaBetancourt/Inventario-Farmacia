package com.example.demo.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ===========================
    // 📌 LISTAR USUARIOS (ÚNICA VISTA)
    // ===========================
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuarios/usuarios"; // Tu vista única JSP
    }


    // ===========================
    // 📌 GUARDAR NUEVO USUARIO
    // ===========================
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {

        // si el usuario viene sin id → crear
        usuarioService.guardar(usuario);

        return "redirect:/usuarios";
    }


    // ===========================
    // 📌 ACTUALIZAR USUARIO
    // ===========================
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario) {

        usuarioService.actualizar(usuario);

        return "redirect:/usuarios";
    }


    // ===========================
    // 📌 ACTIVAR / DESACTIVAR USUARIO
    // ===========================
    @GetMapping("/toggle/{id}")
    public String toggleUsuario(@PathVariable Integer id) {

        usuarioService.cambiarEstado(id);

        return "redirect:/usuarios";
    }

}
