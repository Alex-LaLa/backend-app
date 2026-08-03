package com.univo.backend_app.dto;

import java.util.List;

public class VentaRequest {

    private Long clienteId;

    private List<VentaItemRequest> productos;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<VentaItemRequest> getProductos() {
        return productos;
    }

    public void setProductos(List<VentaItemRequest> productos) {
        this.productos = productos;
    }

}