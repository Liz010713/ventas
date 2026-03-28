// Archivo: src/main/java/com/example/system_ventas/repositories/PaisRepository.java
package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
}