package com.univo.backend_app.controllers;

import com.univo.backend_app.models.Inventario;
import com.univo.backend_app.repositories.InventarioRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "http://localhost:4200")
public class InventarioController {

    private final InventarioRepository inventarioRepository;
    public InventarioController(InventarioRepository inventarioRepository) {this.inventarioRepository = inventarioRepository;}

    @GetMapping
    public List<Inventario> obtenerInventario() {
        return inventarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Inventario> obtenerInventario(@PathVariable Long id) {
        return inventarioRepository.findById(id);
    }

    @PostMapping
    public Inventario crearInventario(@Valid @RequestBody Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @PutMapping("/{id}")
    public Inventario actualizarInventario(
            @PathVariable Long id,
            @Valid @RequestBody Inventario inventarioActualizado) {

        Inventario inventario = inventarioRepository.findById(id).orElseThrow();

        inventario.setProducto(inventarioActualizado.getProducto());
        inventario.setUnidadesDisponibles(inventarioActualizado.getUnidadesDisponibles());
        inventario.setNivelReorden(inventarioActualizado.getNivelReorden());

        return inventarioRepository.save(inventario);
    }

    @DeleteMapping("/{id}")
    public void eliminarInventario(@PathVariable Long id) {
        inventarioRepository.deleteById(id);
    }
}