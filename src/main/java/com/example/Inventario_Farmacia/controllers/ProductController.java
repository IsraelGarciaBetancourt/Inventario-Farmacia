package com.example.Inventario_Farmacia.controllers;

import com.example.Inventario_Farmacia.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {


    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(123, "PARA-300", "Paracetamol 300mg", 321, 100, true),
            new Product(124, "IBU-300", "Ibuprofeno 300mg", 321, 130, true),
            new Product(125, "JATOS-500", "Jarabe de Tos 500ml", 322, 90, true),
            new Product(126, "CETI-100", "Cetirizina 100mg", 323, 102, true)
    ));

    @GetMapping("/productos")
    public List<Product> getProducts(){
        return products;
    }

}
