package com.example.demo.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    private boolean verificarAdmin(HttpSession session) {
        return session.getAttribute("rol") != null &&
                session.getAttribute("rol").equals("ADMIN");
    }

    @GetMapping("/list")
    public String listar(Model model, HttpSession session) {
        if (!verificarAdmin(session)) return "redirect:/login";

        model.addAttribute("usuarios", servicio.listar());
        return "usuarios/list";
    }

    @GetMapping("/crear")
    public String crear(Model model, HttpSession session) {
        if (!verificarAdmin(session)) return "redirect:/login";

        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model, HttpSession session) {
        if (!verificarAdmin(session)) return "redirect:/login";

        model.addAttribute("usuario", servicio.buscarPorId(id));
        return "usuarios/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario u, HttpSession session) {
        if (!verificarAdmin(session)) return "redirect:/login";

        servicio.guardar(u);
        return "redirect:/usuarios/list";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Usuario u, HttpSession session) {
        if (!verificarAdmin(session)) return "redirect:/login";

        servicio.actualizar(u);
        return "redirect:/usuarios/list";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable int id, HttpSession session) {
        if (!verificarAdmin(session)) return "redirect:/login";

        servicio.desactivar(id);
        return "redirect:/usuarios/list";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable int id, HttpSession session) {
        if (!verificarAdmin(session)) return "redirect:/login";

        servicio.activar(id);
        return "redirect:/usuarios/list";
    }
}
