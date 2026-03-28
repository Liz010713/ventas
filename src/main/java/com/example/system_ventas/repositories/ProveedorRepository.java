package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    boolean existsByNombreEmpresa(String nombreEmpresa);
}