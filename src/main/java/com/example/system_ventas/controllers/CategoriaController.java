// Archivo: src/main/java/com/example/system_ventas/controllers/CategoriaController.java
package com.example.system_ventas.controllers;

import com.example.system_ventas.models.Categoria;
import com.example.system_ventas.repositories.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Categoria create(@RequestBody Categoria categoria) {
        return repository.save(categoria);
    }
}