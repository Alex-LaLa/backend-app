package com.univo.backend_app.controllers;

import com.univo.backend_app.dto.DashboardResumenDTO;
import com.univo.backend_app.services.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "${FRONTEND_URL}")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardResumenDTO obtenerResumen() {
        return dashboardService.obtenerResumen();
    }

}