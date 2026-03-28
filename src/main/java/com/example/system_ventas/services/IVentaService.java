package com.example.system_ventas.services;

import com.example.system_ventas.models.Venta;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de negocio para Venta.
 */
public interface IVentaService {
    List<Venta> listarTodos();

    Optional<Venta> buscarPorId(Long id);

    Venta guardar(Venta venta);

    Venta actualizar(Long id, Venta venta);

    void eliminar(Long id);
}
