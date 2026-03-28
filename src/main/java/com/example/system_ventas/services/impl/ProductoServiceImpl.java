package com.example.system_ventas.services.impl;

import com.example.system_ventas.exceptions.BadRequestException;
import com.example.system_ventas.models.Producto;
import com.example.system_ventas.repositories.ProductoRepository;
import com.example.system_ventas.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz de servicio.
 * Marcada con @Service para que Spring la detecte y administre.
 */
@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        validarProducto(producto);
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    private void validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new BadRequestException("El nombre del producto no puede estar vacío o ser nulo.");
        }
        if (producto.getPrecio() == null) {
            throw new BadRequestException("El precio del producto no puede ser nulo.");
        }
        if (producto.getStock() == null) {
            throw new BadRequestException("El stock del producto no puede ser nulo.");
        }
    }
}
