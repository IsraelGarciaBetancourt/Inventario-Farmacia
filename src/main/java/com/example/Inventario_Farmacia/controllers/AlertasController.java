package com.example.Inventario_Farmacia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertasController {

    @GetMapping("/alertas/{alerta}")
    public String alertas(@PathVariable String alerta){
        return "Alerta: " + alerta;
    }

}
