package com.example.demo.usuario;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        if (session.getAttribute("usuarioLogeado") != null) {
            return "redirect:/inventario"; // ya logeado
        }
        return "login/login"; // JSP
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {
        Usuario usuario = usuarioService.autenticar(username, password);

        if (usuario == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login/login";
        }

        session.setAttribute("usuarioLogeado", usuario);
        session.setAttribute("nombreUsuario", usuario.getNombreCompleto());
        session.setAttribute("rolUsuario", usuario.getRol());

        return "redirect:/inventario";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
