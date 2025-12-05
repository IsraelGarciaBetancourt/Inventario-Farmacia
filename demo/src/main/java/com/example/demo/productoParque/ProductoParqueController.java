package com.example.demo.productoParque;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductoParqueController {

    private final ProductoParqueService service;

    public ProductoParqueController(ProductoParqueService service) {
        this.service = service;
    }

    @GetMapping("/inventario")
    public String listarInventario(Model model) {
        model.addAttribute("items", service.listar());
        model.addAttribute("peligro", service.listarEnPeligro()); // NUEVO
        return "inventario/list";
    }

    @GetMapping("/inventario/desactivar/{id}")
    public String desactivar(@PathVariable int id) {
        service.desactivar(id);
        return "redirect:/inventario";
    }

    @GetMapping("/inventario/activar/{id}")
    public String activar(@PathVariable int id) {
        service.activar(id);
        return "redirect:/inventario";
    }

}
