package com.example.Inventario_Farmacia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashBoardRestController {

    @GetMapping("/dashboard")
    public String helloWorld(){
        return "Soy Dashboard";
    }

}
