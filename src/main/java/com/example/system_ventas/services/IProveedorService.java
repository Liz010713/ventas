package com.example.system_ventas.services;

import com.example.system_ventas.models.Proveedor;
import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    List<Proveedor> listarTodos();
    Optional<Proveedor> buscarPorId(Long id);
    Proveedor guardar(Proveedor proveedor);
    Proveedor actualizar(Long id, Proveedor proveedor);
    void eliminar(Long id);
}
