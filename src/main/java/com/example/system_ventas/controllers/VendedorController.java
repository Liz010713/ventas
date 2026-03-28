// Archivo: src/main/java/com/example/system_ventas/controllers/VendedorController.java
package com.example.system_ventas.controllers;

import com.example.system_ventas.models.Vendedor;
import com.example.system_ventas.services.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    private final VendedorService service;

    public VendedorController(VendedorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Vendedor>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Vendedor> create(@RequestBody Vendedor vendedor) {
        return new ResponseEntity<>(service.save(vendedor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> update(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        return ResponseEntity.ok(service.update(id, vendedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}