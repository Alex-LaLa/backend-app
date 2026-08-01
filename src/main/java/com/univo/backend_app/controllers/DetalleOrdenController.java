package com.univo.backend_app.controllers;

import com.univo.backend_app.models.DetalleOrden;
import com.univo.backend_app.repositories.DetalleOrdenRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalle-orden")
@CrossOrigin(origins = "http://localhost:4200")
public class DetalleOrdenController {

    private final DetalleOrdenRepository detalleOrdenRepository;
    public DetalleOrdenController(DetalleOrdenRepository detalleOrdenRepository) {this.detalleOrdenRepository = detalleOrdenRepository;}

    @GetMapping
    public List<DetalleOrden> obtenerDetalles() {
        return detalleOrdenRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DetalleOrden> obtenerDetalle(@PathVariable Long id) {
        return detalleOrdenRepository.findById(id);
    }

    @PostMapping
    public DetalleOrden crearDetalle(@Valid @RequestBody DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }

    @PutMapping("/{id}")
    public DetalleOrden actualizarDetalle(
            @PathVariable Long id,
            @Valid @RequestBody DetalleOrden detalleActualizado) {

        DetalleOrden detalle = detalleOrdenRepository.findById(id).orElseThrow();

        detalle.setOrden(detalleActualizado.getOrden());
        detalle.setProducto(detalleActualizado.getProducto());
        detalle.setCantidad(detalleActualizado.getCantidad());
        detalle.setPrecioUnitario(detalleActualizado.getPrecioUnitario());

        return detalleOrdenRepository.save(detalle);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalle(@PathVariable Long id) {
        detalleOrdenRepository.deleteById(id);
    }
}