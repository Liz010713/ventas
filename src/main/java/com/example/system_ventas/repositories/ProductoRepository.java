package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * El Repositorio es una interfaz que Spring implementará automáticamente.
 * Al extender JpaRepository, obtenemos métodos como save(), findAll(), findById(), etc.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Aquí podrías añadir métodos de búsqueda personalizados si los necesitaras
    // Ejemplo: List<Producto> findByNombre(String nombre);
}

