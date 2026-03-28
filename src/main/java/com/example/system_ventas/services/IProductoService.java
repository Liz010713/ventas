package com.example.system_ventas.services;

import com.example.system_ventas.models.Producto;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de negocio para Producto.
 */
public interface IProductoService {
    List<Producto> listarTodos();

    Optional<Producto> buscarPorId(Long id);

    Producto guardar(Producto producto);

    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);
}
