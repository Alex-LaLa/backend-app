package com.univo.backend_app.services;

import com.univo.backend_app.dto.DashboardResumenDTO;
import com.univo.backend_app.models.Producto;
import com.univo.backend_app.repositories.CategoriaRepository;
import com.univo.backend_app.repositories.InventarioRepository;
import com.univo.backend_app.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final InventarioRepository inventarioRepository;

    public DashboardService(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository,
            InventarioRepository inventarioRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.inventarioRepository = inventarioRepository;
    }

    public DashboardResumenDTO obtenerResumen() {

        DashboardResumenDTO dto = new DashboardResumenDTO();

        dto.setTotalProductos(productoRepository.count());

        dto.setTotalCategorias(categoriaRepository.count());

        dto.setProductosActivos(
                productoRepository.findAll()
                        .stream()
                        .filter(Producto::getActivo)
                        .count()
        );

        dto.setValorInventario(
                inventarioRepository.findAll()
                        .stream()
                        .mapToDouble(i ->
                                i.getProducto().getPrecio() * i.getUnidadesDisponibles())
                        .sum()
        );

        return dto;

    }

}