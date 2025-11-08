package com.example.demo.productoParque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inventario")
public class ProductoParqueController {

    @Autowired
    private ProductoParqueService productoParqueService;

    @GetMapping
    public String listarInventario(Model model) {
        model.addAttribute("productos", productoParqueService.listarTodos());
        model.addAttribute("totalUnidades", productoParqueService.obtenerTotalUnidades());
        model.addAttribute("bajoStock", productoParqueService.contarBajoStock());
        return "inventario/inventario";
    }

    @PostMapping("/cambiar-estado")
    public String cambiarEstado(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        try {
            productoParqueService.cambiarEstado(id);
            redirectAttributes.addFlashAttribute("mensaje", "Estado actualizado correctamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar el estado: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/inventario";
    }
}