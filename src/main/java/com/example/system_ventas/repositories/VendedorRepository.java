package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    boolean existsByDocumento(String documento);
}