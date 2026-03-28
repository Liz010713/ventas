package com.example.system_ventas.controllers;

import com.example.system_ventas.models.Vendedor;
import com.example.system_ventas.services.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * El Controlador es el punto de entrada de las peticiones HTTP.
 * 
 * @RestController indica que esta clase manejará peticiones REST (JSON).
 *                 @RequestMapping("/api/productos") define la ruta base.
 */
@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private IVendedorService vendedorService;

    // Obtener todos los vendedores
    @GetMapping
    public List<Vendedor> listar() {
        return vendedorService.listarTodos();
    }

    // Obtener un vendedor por su ID
    @GetMapping("/{id}")
    public Vendedor buscar(@PathVariable Long id) {
        return vendedorService.buscarPorId(id).get();
    }

    // Crear un nuevo vendedor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor crear(@RequestBody Vendedor vendedor) {
        return vendedorService.guardar(vendedor);
    }

    // Actualizar un vendedor
    @PutMapping("/{id}")
    public Vendedor actualizar(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        return vendedorService.actualizar(id, vendedor);
    }

    // Eliminar un vendedor
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        vendedorService.eliminar(id);
    }
}
