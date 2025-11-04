package com.example.demo.ingresoProducto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/ingresos")
public class IngresoProductoController {

    private final IngresoProductoService ingresoService;

    public IngresoProductoController(IngresoProductoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @GetMapping
    public String listarIngresos(Model model) {
        model.addAttribute("ingresos", ingresoService.listarIngresos());
        model.addAttribute("nuevoNumero", ingresoService.generarSiguienteNumeroDocumento());
        model.addAttribute("productos", ingresoService.listarProductos());
        model.addAttribute("fechaActual", LocalDate.now());
        return "ingresos/ingresos";
    }

    @PostMapping("/crear")
    public String crearIngreso(@ModelAttribute IngresoProducto ingreso) {
        if (ingreso.getObservacion() == null || ingreso.getObservacion().isBlank()) {
            ingreso.setObservacion("Sin observación");
        }
        ingresoService.crearIngreso(ingreso);
        return "redirect:/ingresos";
    }
}
