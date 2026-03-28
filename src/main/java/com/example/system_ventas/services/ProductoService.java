package com.example.system_ventas.services;

import com.example.system_ventas.exceptions.*;
import com.example.system_ventas.models.Producto;
import com.example.system_ventas.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> findAll() {
        List<Producto> list = repository.findAll();
        if (list.isEmpty())
            throw new NoRecordsFoundException("No hay productos registrados.");
        return list;
    }

    public Producto findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));
    }

    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    public Producto update(Long id, Producto productoDetails) {
        Producto producto = findById(id);
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        return repository.save(producto);
    }

    public void delete(Long id) {
        Producto producto = findById(id);
        repository.delete(producto);
    }
}