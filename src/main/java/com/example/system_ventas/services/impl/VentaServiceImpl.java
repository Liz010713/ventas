package com.example.system_ventas.services.impl;

import com.example.system_ventas.exceptions.BadRequestException;
import com.example.system_ventas.exceptions.ResourceNotFoundException;
import com.example.system_ventas.models.Venta;
import com.example.system_ventas.repositories.ProductoRepository;
import com.example.system_ventas.repositories.VentaRepository;
import com.example.system_ventas.services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Venta> listarTodos() {
        List<Venta> ventas = ventaRepository.findAll();
        if (ventas.isEmpty()) {
            throw new ResourceNotFoundException("No hay ventas registradas.");
        }
        return ventas;
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return Optional.ofNullable(ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada")));
    }

    @Override
    public Venta guardar(Venta venta) {
        // 1. Validar Stock
        if (venta.getProducto() == null || venta.getProducto().getStock() == null
                || venta.getProducto().getStock() <= 0) {
            throw new BadRequestException("El producto no tiene stock disponible.");
        }

        // 2. Regla Especial "Brayan"
        boolean esBrayan = venta.getCliente() != null &&
                venta.getCliente().getNombre() != null &&
                venta.getCliente().getNombre().equalsIgnoreCase("Brayan");

        boolean esBogota = venta.getProducto().getProveedor() != null &&
                venta.getProducto().getProveedor().getDireccion() != null &&
                venta.getProducto().getProveedor().getDireccion().toUpperCase().contains("BOGOTÁ");

        if (esBrayan && esBogota) {
            throw new BadRequestException(
                    "Regla de Negocio: El cliente Brayan no puede comprar a proveedores de Bogotá.");
        }

        // Lógica para restar stock
        if (venta.getProducto() != null) {
            venta.getProducto().setStock(venta.getProducto().getStock() - 1);
            productoRepository.save(venta.getProducto()); // Persistencia explícita
            venta.setTotal(venta.getProducto().getPrecio());
        }

        venta.setFecha(LocalDateTime.now());

        return ventaRepository.save(venta);
    }

    @Override
    public com.example.system_ventas.models.Venta actualizar(Long id, com.example.system_ventas.models.Venta venta) {
        com.example.system_ventas.models.Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));

        ventaExistente.setCliente(venta.getCliente());
        ventaExistente.setProducto(venta.getProducto());
        ventaExistente.setVendedor(venta.getVendedor());
        ventaExistente.setMetodoPago(venta.getMetodoPago());
        ventaExistente.setFecha(venta.getFecha());

        if (venta.getProducto() != null) {
            ventaExistente.setTotal(venta.getProducto().getPrecio());
        }

        return ventaRepository.save(ventaExistente);
    }

    @Override
    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }
}