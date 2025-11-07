package com.example.demo.productoParque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parque")
public class ProductoParqueController {

    @Autowired
    private ProductoParqueService productoParqueService;

    @GetMapping
    public String listarParque(Model model) {
        model.addAttribute("productos", productoParqueService.listarActivos());
        return "parque/parque"; // Vista opcional si luego la agregas
    }
}
