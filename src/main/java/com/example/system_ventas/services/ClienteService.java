package com.example.system_ventas.services;

import com.example.system_ventas.exceptions.*;
import com.example.system_ventas.models.Cliente;
import com.example.system_ventas.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        List<Cliente> list = repository.findAll();
        if (list.isEmpty())
            throw new NoRecordsFoundException("No hay clientes registrados en el sistema.");
        return list;
    }

    public Cliente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + id + " no encontrado."));
    }

    public Cliente save(Cliente cliente) {
        if (repository.existsByDocumento(cliente.getDocumento())) {
            throw new BusinessRuleException(
                    "Ya existe un cliente registrado con la cédula/documento: " + cliente.getDocumento());
        }
        return repository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteDetails) {
        Cliente cliente = findById(id);
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setApellido(clienteDetails.getApellido());
        // Actualizar demás campos...
        return repository.save(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = findById(id);
        repository.delete(cliente);
    }
}