package com.example.demo.productoCatalogo;

import java.util.List;
import com.example.demo.categoriaProducto.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalogo")
public class ProductoCatalogoController {

    @Autowired
    private ProductoCatalogoService productoCatalogoService;

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    public String listarCatalogo(Model model) {
        List<ProductoCatalogo> productos = productoCatalogoService.listarProductosCatalogo();
        model.addAttribute("productos", productos);

        // Cargar categorías activas para los modales
        model.addAttribute("categorias", categoriaProductoService.listarCategoriasActivas());
        return "catalogo/catalogo";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoCatalogo producto) {
        productoCatalogoService.guardarProductoCatalogo(producto);
        return "redirect:/catalogo";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute ProductoCatalogo producto) {
        productoCatalogoService.actualizarProductoCatalogo(producto);
        return "redirect:/catalogo";
    }

    @GetMapping("/toggle/{id}")
    public String toggle(@PathVariable int id) {
        productoCatalogoService.toggleProductoCatalogo(id);
        return "redirect:/catalogo";
    }

    @GetMapping("/eliminar/{id}")
    public String desactivarProducto(@PathVariable int id) {
        productoCatalogoService.desactivarProductoCatalogo(id);
        return "redirect:/catalogo";
    }
}
