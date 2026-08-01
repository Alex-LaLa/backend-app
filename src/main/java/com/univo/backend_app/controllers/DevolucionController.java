package com.univo.backend_app.controllers;

import com.univo.backend_app.models.Devolucion;
import com.univo.backend_app.repositories.DevolucionRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin(origins = "http://localhost:4200")
public class DevolucionController {

    private final DevolucionRepository devolucionRepository;
    public DevolucionController(DevolucionRepository devolucionRepository) {this.devolucionRepository = devolucionRepository;}

    @GetMapping
    public List<Devolucion> obtenerDevoluciones() {
        return devolucionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Devolucion> obtenerDevolucion(@PathVariable Long id) {
        return devolucionRepository.findById(id);
    }

    @PostMapping
    public Devolucion crearDevolucion(@Valid @RequestBody Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    @PutMapping("/{id}")
    public Devolucion actualizarDevolucion(
            @PathVariable Long id,
            @Valid @RequestBody Devolucion devolucionActualizada) {

        Devolucion devolucion = devolucionRepository.findById(id).orElseThrow();

        devolucion.setDetalleOrden(devolucionActualizada.getDetalleOrden());
        devolucion.setCantidadDevuelta(devolucionActualizada.getCantidadDevuelta());
        devolucion.setMontoReembolsado(devolucionActualizada.getMontoReembolsado());
        devolucion.setMotivo(devolucionActualizada.getMotivo());
        devolucion.setEstado(devolucionActualizada.getEstado());

        return devolucionRepository.save(devolucion);
    }

    @DeleteMapping("/{id}")
    public void eliminarDevolucion(@PathVariable Long id) {
        devolucionRepository.deleteById(id);
    }
}