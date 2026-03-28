package com.example.system_ventas.controllers;

import com.example.system_ventas.models.Venta;
import com.example.system_ventas.services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * El Controlador es el punto de entrada de las peticiones HTTP.
 * 
 * @RestController indica que esta clase manejará peticiones REST (JSON).
 *                 @RequestMapping("/api/productos") define la ruta base.
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    // Obtener todas las Ventas
    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarTodos();
    }

    // Obtener una Venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscar(@PathVariable Long id) {
        return ventaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva Venta
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venta crear(@RequestBody Venta venta) {
        return ventaService.guardar(venta);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        return ResponseEntity.ok(ventaService.actualizar(id, venta));
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
