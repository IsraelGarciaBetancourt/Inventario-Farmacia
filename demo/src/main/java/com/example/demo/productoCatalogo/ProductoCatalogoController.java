package com.example.demo.productoCatalogo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.categoria.CategoriaService;

@Controller
@RequestMapping("/productoCatalogo")
public class ProductoCatalogoController {

    private final ProductoCatalogoService service;
    private final CategoriaService categoriaService;

    public ProductoCatalogoController(ProductoCatalogoService service, CategoriaService categoriaService) {
        this.service = service;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/list")
    public String listar(Model model) {
        model.addAttribute("productos", service.listar());
        return "productoCatalogo/list";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("producto", new ProductoCatalogo());
        model.addAttribute("categorias", categoriaService.listar());
        return "productoCatalogo/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("producto", service.buscarPorId(id));
        model.addAttribute("categorias", categoriaService.listar());
        return "productoCatalogo/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ProductoCatalogo p) {
        service.guardar(p);
        return "redirect:/productoCatalogo/list";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute ProductoCatalogo p) {
        service.actualizar(p);
        return "redirect:/productoCatalogo/list";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable int id) {
        service.desactivar(id);
        return "redirect:/productoCatalogo/list";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable int id) {
        service.activar(id);
        return "redirect:/productoCatalogo/list";
    }
}
