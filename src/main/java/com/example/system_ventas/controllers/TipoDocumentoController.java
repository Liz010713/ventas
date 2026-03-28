// Archivo: src/main/java/com/example/system_ventas/controllers/TipoDocumentoController.java
package com.example.system_ventas.controllers;

import com.example.system_ventas.models.TipoDocumento;
import com.example.system_ventas.repositories.TipoDocumentoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipos-documento")
public class TipoDocumentoController {
    private final TipoDocumentoRepository repository;

    public TipoDocumentoController(TipoDocumentoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public TipoDocumento create(@RequestBody TipoDocumento tipoDocumento) {
        return repository.save(tipoDocumento);
    }
}