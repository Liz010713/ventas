package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}