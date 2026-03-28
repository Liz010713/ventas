package com.example.system_ventas.services.impl;

import com.example.system_ventas.exceptions.DuplicateResourceException;
import com.example.system_ventas.exceptions.ResourceNotFoundException;
import com.example.system_ventas.models.Vendedor;
import com.example.system_ventas.repositories.VendedorRepository;
import com.example.system_ventas.services.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorServiceImpl implements IVendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public List<Vendedor> listarTodos() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        if (vendedores.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron vendedores en el sistema.");
        }
        return vendedores;
    }

    @Override
    public Optional<Vendedor> buscarPorId(Long id) {
        return Optional.ofNullable(vendedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con ID: " + id)));
    }

    @Override
    public Vendedor guardar(Vendedor vendedor) {
        if (vendedorRepository.findByDocumento(vendedor.getDocumento()).isPresent()) {
            throw new DuplicateResourceException("El documento " + vendedor.getDocumento() + " ya está registrado.");
        }
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor actualizar(Long id, Vendedor vendedor) {
        vendedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Vendedor no encontrado con ID: " + id));

        // Validar si el nuevo documento ya pertenece a otro vendedor
        Optional<Vendedor> vendedorConDocumento = vendedorRepository.findByDocumento(vendedor.getDocumento());
        if (vendedorConDocumento.isPresent() && !vendedorConDocumento.get().getId().equals(id)) {
            throw new DuplicateResourceException("El documento " + vendedor.getDocumento() + " ya pertenece a otro vendedor.");
        }

        vendedor.setId(id);
        return vendedorRepository.save(vendedor);
    }

    @Override
    public void eliminar(Long id) {
        if (!vendedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Vendedor no encontrado con ID: " + id);
        }
        vendedorRepository.deleteById(id);
    }
}

