// Archivo: src/main/java/com/example/system_ventas/controllers/PaisController.java
package com.example.system_ventas.controllers;

import com.example.system_ventas.models.Pais;
import com.example.system_ventas.repositories.PaisRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paises")
public class PaisController {
    private final PaisRepository repository;

    public PaisController(PaisRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Pais create(@RequestBody Pais pais) {
        return repository.save(pais);
    }
}