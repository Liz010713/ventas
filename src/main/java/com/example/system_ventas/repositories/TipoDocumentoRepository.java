// Archivo: src/main/java/com/example/system_ventas/repositories/TipoDocumentoRepository.java
package com.example.system_ventas.repositories;

import com.example.system_ventas.models.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
}