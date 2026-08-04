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


        double total = 0;


        // Primero calculamos y validamos todo
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
                        "No hay suficiente inventario para "
                                + producto.getNombre()
                );

            }


            total += producto.getPrecio() * item.getCantidad();

        }



        // Ahora sí creamos la orden
        Orden orden = new Orden();

        orden.setCliente(cliente);

        orden.setEstado("COMPLETADA");

        orden.setTotal(total);


        orden = ordenRepository.save(orden);



        // Guardamos detalles y actualizamos inventario
        for (VentaItemRequest item : request.getProductos()) {


            Producto producto = productoRepository
                    .findById(item.getProductoId())
                    .orElseThrow();


            Inventario inventario = inventarioRepository
                    .findByProductoId(producto.getId())
                    .orElseThrow();


            inventario.setUnidadesDisponibles(
                    inventario.getUnidadesDisponibles()
                            - item.getCantidad()
            );


            inventarioRepository.save(inventario);



            DetalleOrden detalle = new DetalleOrden();

            detalle.setOrden(orden);

            detalle.setProducto(producto);

            detalle.setCantidad(item.getCantidad());

            detalle.setPrecioUnitario(producto.getPrecio());

            detalle.setSubtotal(
                    producto.getPrecio() * item.getCantidad()
            );


            detalleOrdenRepository.save(detalle);

        }

    }

}