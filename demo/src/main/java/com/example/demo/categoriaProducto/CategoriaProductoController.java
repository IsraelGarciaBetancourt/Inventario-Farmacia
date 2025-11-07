package com.example.demo.categoriaProducto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    public String listarCategorias(Model model) {
        List<CategoriaProducto> categorias = categoriaProductoService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "categorias/categorias"; // JSP: /WEB-INF/views/categorias/categorias.jsp
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute CategoriaProducto categoria) {
        categoriaProductoService.guardarCategoria(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/actualizar")
    public String actualizarCategoria(@ModelAttribute CategoriaProducto categoria) {
        categoriaProductoService.actualizarCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable int id) {
        categoriaProductoService.eliminarCategoria(id);
        return "redirect:/categorias";
    }
}
