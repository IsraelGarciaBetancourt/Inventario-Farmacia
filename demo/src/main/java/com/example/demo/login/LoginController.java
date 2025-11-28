package com.example.demo.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.example.demo.usuario.Usuario;
import com.example.demo.usuario.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar formulario
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Procesar formulario
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        try {
            Usuario u = usuarioService.buscarPorUsername(username);

            if (!u.getPassword().equals(password)) {
                model.addAttribute("error", "Contraseña incorrecta");
                return "login";
            }

            if (!u.isActivo()) {
                model.addAttribute("error", "El usuario está desactivado");
                return "login";
            }

            // GUARDAR DATOS EN LA SESIÓN
            session.setAttribute("usuarioId", u.getId());
            session.setAttribute("username", u.getUsername());
            session.setAttribute("rol", u.getRol());
            session.setAttribute("nombreUsuario", u.getNombreCompleto());

            return "redirect:/productoCatalogo/list";

        } catch (Exception e) {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }
    }

    // CERRAR SESIÓN
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
