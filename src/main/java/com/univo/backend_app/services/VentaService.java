package com.univo.backend_app.services;

import com.univo.backend_app.dto.VentaItemRequest;
import com.univo.backend_app.dto.VentaRequest;
import com.univo.backend_app.models.*;
import com.univo.backend_app.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaService {

    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final OrdenRepository ordenRepository;
    private final DetalleOrdenRepository detalleOrdenRepository;
    private final InventarioRepository inventarioRepository;

    public VentaService(
            ClienteRepository clienteRepository,
            ProductoRepository productoRepository,
            OrdenRepository ordenRepository,
            DetalleOrdenRepository detalleOrdenRepository,
            InventarioRepository inventarioRepository) {

        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.ordenRepository = ordenRepository;
        this.detalleOrdenRepository = detalleOrdenRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Transactional
    public void registrarVenta(VentaRequest request) {

        Cliente cliente = clienteRepository
                .findById(request.getClienteId())
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado"));

        Orden orden = new Orden();

        orden.setCliente(cliente);

        orden.setEstado("COMPLETADA");

        orden.setTotal(0.0);

        orden = ordenRepository.save(orden);

        double total = 0;
        for (VentaItemRequest item : request.getProductos()) {
            Producto producto = productoRepository
                    .findById(item.getProductoId())
                    .orElseThrow(() ->
                            new RuntimeException("Producto no encontrado"));

            Inventario inventario = inventarioRepository
                    .findByProductoId(producto.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Inventario no encontrado"));
            if (inventario.getUnidadesDisponibles() < item.getCantidad()) {
                throw new RuntimeException(
                        "No hay suficiente inventario para " + producto.getNombre()
                );
            }

            inventario.setUnidadesDisponibles(
                    inventario.getUnidadesDisponibles() - item.getCantidad()
            );

            inventarioRepository.save(inventario);
            double subtotal = producto.getPrecio() * item.getCantidad();

            DetalleOrden detalle = new DetalleOrden();

            detalle.setOrden(orden);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(subtotal);

            detalleOrdenRepository.save(detalle);

            total += subtotal;
        }
        orden.setTotal(total);
        ordenRepository.save(orden);
    }

}