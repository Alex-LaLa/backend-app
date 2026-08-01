package com.univo.backend_app.repositories;

import com.univo.backend_app.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}