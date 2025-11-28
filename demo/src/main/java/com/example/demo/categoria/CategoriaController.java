package com.example.demo.categoria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/list")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "categoria/list"; // JSP
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/categorias/list";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarPorId(id));
        return "categoria/form";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Categoria categoria) {
        categoriaService.actualizar(categoria);
        return "redirect:/categorias/list";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable int id) {
        categoriaService.activar(id);
        return "redirect:/categorias/list";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        categoriaService.eliminar(id); // soft delete
        return "redirect:/categorias/list";
    }
}
