package com.example.system_ventas.services;

import com.example.system_ventas.exceptions.*;
import com.example.system_ventas.models.Vendedor;
import com.example.system_ventas.repositories.VendedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendedorService {
    private final VendedorRepository repository;

    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

    public List<Vendedor> findAll() {
        List<Vendedor> list = repository.findAll();
        if (list.isEmpty())
            throw new NoRecordsFoundException("No hay vendedores registrados.");
        return list;
    }

    public Vendedor findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado."));
    }

    public Vendedor save(Vendedor vendedor) {
        if (repository.existsByDocumento(vendedor.getDocumento())) {
            throw new BusinessRuleException("Ya existe un vendedor con la cédula: " + vendedor.getDocumento());
        }
        return repository.save(vendedor);
    }

    public Vendedor update(Long id, Vendedor vendedorDetails) {
        Vendedor vendedor = findById(id);
        vendedor.setNombres(vendedorDetails.getNombres());
        // Actualizar demás campos...
        return repository.save(vendedor);
    }

    public void delete(Long id) {
        Vendedor vendedor = findById(id);
        repository.delete(vendedor);
    }
}