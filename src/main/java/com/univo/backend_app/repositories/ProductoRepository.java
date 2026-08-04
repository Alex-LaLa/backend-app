package com.univo.backend_app.repositories;

import com.univo.backend_app.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {


    @Query("""
SELECT i.producto
FROM Inventario i
WHERE i.unidadesDisponibles > 0
AND i.producto.activo = true
""")
    List<Producto> findProductosDisponibles();

    List<Producto> findByActivoTrue();

}