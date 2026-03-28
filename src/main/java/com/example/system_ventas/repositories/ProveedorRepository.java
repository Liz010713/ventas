package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    boolean existsByNombreEmpresa(String nombreEmpresa);
}
