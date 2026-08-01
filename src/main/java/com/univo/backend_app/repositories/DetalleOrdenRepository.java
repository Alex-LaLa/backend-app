package com.univo.backend_app.repositories;

import com.univo.backend_app.models.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {

}