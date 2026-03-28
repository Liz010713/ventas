package com.example.system_ventas.services;

import com.example.system_ventas.models.Vendedor;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones de negocio para Vendedor.
 */
public interface IVendedorService {
    List<Vendedor> listarTodos();
    Optional<Vendedor> buscarPorId(Long id);
    Vendedor guardar(Vendedor vendedor);
    Vendedor actualizar(Long id, Vendedor vendedor);
    void eliminar(Long id);
}

