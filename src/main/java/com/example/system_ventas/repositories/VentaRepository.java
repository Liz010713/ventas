package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}