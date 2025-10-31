package com.example.demo.categoriaProducto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaService;

    public CategoriaProductoController(CategoriaProductoService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // ✅ Listar todas las categorías
    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "categorias/categorias";
    }

    // ✅ Crear nueva categoría
    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute CategoriaProducto categoria) {
        categoriaService.crearCategoria(categoria);
        return "redirect:/categorias";
    }

    // ✅ Editar categoría (nombre o estado activo/inactivo)
    @PostMapping("/editar")
    public String editarCategoria(@ModelAttribute CategoriaProducto categoria) {
        categoriaService.actualizarCategoria(categoria);
        return "redirect:/categorias";
    }
}
