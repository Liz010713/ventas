package com.example.system_ventas.services;

import com.example.system_ventas.exceptions.*;
import com.example.system_ventas.models.Proveedor;
import com.example.system_ventas.repositories.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {
    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public List<Proveedor> findAll() {
        List<Proveedor> list = repository.findAll();
        if (list.isEmpty())
            throw new NoRecordsFoundException("No hay proveedores registrados.");
        return list;
    }

    public Proveedor findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado."));
    }

    public Proveedor save(Proveedor proveedor) {
        if (repository.existsByNombreEmpresa(proveedor.getNombreEmpresa())) {
            throw new BusinessRuleException("Ya existe un proveedor con el nombre: " + proveedor.getNombreEmpresa());
        }
        return repository.save(proveedor);
    }

    public Proveedor update(Long id, Proveedor proveedorDetails) {
        Proveedor proveedor = findById(id);
        proveedor.setNombreEmpresa(proveedorDetails.getNombreEmpresa());
        // Actualizar demás campos...
        return repository.save(proveedor);
    }

    public void delete(Long id) {
        Proveedor proveedor = findById(id);
        repository.delete(proveedor);
    }
}