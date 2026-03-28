package com.example.system_ventas.services;

import com.example.system_ventas.exceptions.*;
import com.example.system_ventas.models.*;
import com.example.system_ventas.repositories.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository,
            ClienteRepository clienteRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Venta> findAll() {
        List<Venta> list = ventaRepository.findAll();
        if (list.isEmpty())
            throw new NoRecordsFoundException("No hay ventas registradas.");
        return list;
    }

    public Venta findById(Long id) {
        return ventaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada."));
    }

    public Venta save(Venta venta) {
        Producto producto = productoRepository.findById(venta.getProducto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado."));

        Cliente cliente = clienteRepository.findById(venta.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado."));

        // Validación 1: Stock igual o menor a 0
        if (producto.getStock() <= 0) {
            throw new BusinessRuleException(
                    "No se puede realizar la venta. El producto tiene stock igual o menor a 0.");
        }

        // Validación 2: Regla especial de "Brayan" y proveedor de "Bogotá"
        boolean isBrayan = cliente.getNombre().trim().equalsIgnoreCase("Brayan");
        boolean isProveedorBogota = producto.getProveedor() != null &&
                producto.getProveedor().getDireccion() != null &&
                producto.getProveedor().getDireccion().toLowerCase().contains("bogotá");

        if (isBrayan && isProveedorBogota) {
            throw new BusinessRuleException(
                    "Regla de negocio: El cliente Brayan no puede comprar productos de proveedores de Bogotá.");
        }

        // Se descuenta el stock
        producto.setStock(producto.getStock() - 1); // Asumiendo venta unitaria. Ajustar si la venta tiene cantidad.
        productoRepository.save(producto);

        venta.setFecha(LocalDateTime.now());
        return ventaRepository.save(venta);
    }

    public void delete(Long id) {
        Venta venta = findById(id);
        ventaRepository.delete(venta);
    }
}