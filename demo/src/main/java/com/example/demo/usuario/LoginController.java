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

    // ✅ Muestra la vista de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login/login"; // 👈 Ajusta el path si tu JSP está en /WEB-INF/views/login/login.jsp
    }

    // ✅ Procesa el envío del formulario
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {
        Usuario usuario = usuarioService.autenticar(username, password);

        if (usuario == null || !usuario.isActivo()) {
            model.addAttribute("error", "Usuario o contraseña incorrectos o usuario inactivo");
            return "login/login"; // 👈 Redirige de nuevo al formulario
        }

        // Guardar usuario completo en sesión
        session.setAttribute("usuarioLogueado", usuario);
        session.setAttribute("nombreUsuario", usuario.getNombreCompleto());
        session.setAttribute("rolUsuario", usuario.getRol());

        // Redirige al dashboard o inventario
        return "redirect:/inventario";
    }

    // ✅ Cierre de sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
