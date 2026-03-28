// Archivo: src/main/java/com/example/system_ventas/repositories/CategoriaRepository.java
package com.example.system_ventas.repositories;

import com.example.system_ventas.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}