package com.example.demo.inventarioProducto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/inventario")
public class InventarioProductoController {

    private final InventarioProductoService inventarioService;

    public InventarioProductoController(InventarioProductoService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // 📦 Muestra la vista con los datos del inventario
    @GetMapping
    public String listarInventario(Model model) {
        List<InventarioProducto> inventario = inventarioService.listarInventario();
        model.addAttribute("inventario", inventario);

        // (opcional) estadísticas de resumen
        long activos = inventario.stream().filter(InventarioProducto::isActivo).count();
        int totalUnidades = inventario.stream().mapToInt(InventarioProducto::getExistencias).sum();

        model.addAttribute("totalActivos", activos);
        model.addAttribute("totalUnidades", totalUnidades);

        return "inventario/inventario"; // Carga /WEB-INF/views/inventario/inventario.jsp
    }
}
