package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * El Repositorio es una interfaz que Spring implementará automáticamente.
 * Al extender JpaRepository, obtenemos métodos como save(), findAll(), findById(), etc.
 */
import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByDocumento(String documento);
    // Aquí podrías añadir métodos de búsqueda personalizados si los necesitaras
    // Ejemplo: List<Vendedor> findByNombres(String nombres);
}
