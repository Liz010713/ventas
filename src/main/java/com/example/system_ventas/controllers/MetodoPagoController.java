// Archivo: src/main/java/com/example/system_ventas/controllers/MetodoPagoController.java
package com.example.system_ventas.controllers;

import com.example.system_ventas.models.MetodoPago;
import com.example.system_ventas.repositories.MetodoPagoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {
    private final MetodoPagoRepository repository;

    public MetodoPagoController(MetodoPagoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public MetodoPago create(@RequestBody MetodoPago metodoPago) {
        return repository.save(metodoPago);
    }
}