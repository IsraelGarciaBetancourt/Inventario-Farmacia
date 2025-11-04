package com.example.demo.salidaProducto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/salidas")
public class SalidaProductoController {

    private final SalidaProductoService salidaService;

    public SalidaProductoController(SalidaProductoService salidaService) {
        this.salidaService = salidaService;
    }

    @GetMapping
    public String listarSalidas(Model model) {
        model.addAttribute("salidas", salidaService.listarSalidas());
        model.addAttribute("nuevoNumero", salidaService.generarSiguienteNumeroDocumento());
        model.addAttribute("productos", salidaService.listarProductos());
        model.addAttribute("fechaActual", LocalDate.now());
        return "salidas/salidas";
    }

    @PostMapping("/crear")
    public String crearSalida(@ModelAttribute SalidaProducto salida) {
        if (salida.getObservacion() == null || salida.getObservacion().isBlank()) {
            salida.setObservacion("Sin observación");
        }
        salidaService.crearSalida(salida);
        return "redirect:/salidas";
    }
}
