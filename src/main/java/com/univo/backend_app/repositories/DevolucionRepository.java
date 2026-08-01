package com.univo.backend_app.repositories;

import com.univo.backend_app.models.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {

}