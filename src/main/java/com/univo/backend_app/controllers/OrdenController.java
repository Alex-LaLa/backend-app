package com.univo.backend_app.controllers;

import com.univo.backend_app.models.Orden;
import com.univo.backend_app.repositories.OrdenRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdenController {

    private final OrdenRepository ordenRepository;
    public OrdenController(OrdenRepository ordenRepository) {this.ordenRepository = ordenRepository;}

    @GetMapping
    public List<Orden> obtenerOrdenes() {
        return ordenRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Orden> obtenerOrden(@PathVariable Long id) {
        return ordenRepository.findById(id);
    }

    @PostMapping
    public Orden crearOrden(@Valid @RequestBody Orden orden) {
        return ordenRepository.save(orden);
    }

    @PutMapping("/{id}")
    public Orden actualizarOrden(
            @PathVariable Long id,
            @Valid @RequestBody Orden ordenActualizada) {

        Orden orden = ordenRepository.findById(id).orElseThrow();

        orden.setCliente(ordenActualizada.getCliente());
        orden.setEstado(ordenActualizada.getEstado());
        orden.setMotivoCancelacion(ordenActualizada.getMotivoCancelacion());
        orden.setTotal(ordenActualizada.getTotal());

        return ordenRepository.save(orden);
    }

    @DeleteMapping("/{id}")
    public void eliminarOrden(@PathVariable Long id) {
        ordenRepository.deleteById(id);
    }
}