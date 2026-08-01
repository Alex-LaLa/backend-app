package com.univo.backend_app.repositories;

import com.univo.backend_app.models.MensajeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<MensajeDTO, Long> {

}
