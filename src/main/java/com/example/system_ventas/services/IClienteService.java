package com.example.system_ventas.services;

import com.example.system_ventas.models.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> listarTodos();

    Optional<Cliente> buscarPorId(Long id);

    Cliente guardar(Cliente cliente);

    Cliente actualizar(Long id, Cliente cliente);

    void eliminar(Long id);
}
