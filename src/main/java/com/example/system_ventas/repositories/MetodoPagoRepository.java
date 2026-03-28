// Archivo: src/main/java/com/example/system_ventas/repositories/MetodoPagoRepository.java
package com.example.system_ventas.repositories;

import com.example.system_ventas.models.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
}