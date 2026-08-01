package com.univo.backend_app.controllers;

import com.univo.backend_app.models.Pago;
import com.univo.backend_app.repositories.PagoRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "http://localhost:4200")
public class PagoController {

    private final PagoRepository pagoRepository;
    public PagoController(PagoRepository pagoRepository) {this.pagoRepository = pagoRepository;}

    @GetMapping
    public List<Pago> obtenerPagos() {
        return pagoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pago> obtenerPago(@PathVariable Long id) {
        return pagoRepository.findById(id);
    }

    @PostMapping
    public Pago crearPago(@Valid @RequestBody Pago pago) {
        return pagoRepository.save(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizarPago(
            @PathVariable Long id,
            @Valid @RequestBody Pago pagoActualizado) {

        Pago pago = pagoRepository.findById(id).orElseThrow();

        pago.setOrden(pagoActualizado.getOrden());
        pago.setEstadoPago(pagoActualizado.getEstadoPago());
        pago.setMetodoPago(pagoActualizado.getMetodoPago());
        pago.setMonto(pagoActualizado.getMonto());

        return pagoRepository.save(pago);
    }

    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Long id) {
        pagoRepository.deleteById(id);
    }
}