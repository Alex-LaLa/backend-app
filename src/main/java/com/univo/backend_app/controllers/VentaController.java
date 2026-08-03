package com.univo.backend_app.controllers;

import com.univo.backend_app.dto.VentaRequest;
import com.univo.backend_app.services.VentaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "${FRONTEND_URL}")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public String registrarVenta(@RequestBody VentaRequest request) {

        ventaService.registrarVenta(request);

        return "Venta registrada correctamente";

    }

}