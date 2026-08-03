package com.univo.backend_app.dto;

import java.util.List;

public class DashboardResumenDTO {

    private Long totalProductos;

    private Long totalCategorias;

    private Long productosActivos;

    private Double valorInventario;

    private List<String> categorias;

    private List<Long> cantidades;

    public DashboardResumenDTO() {
    }

    public Long getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Long totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Long getTotalCategorias() {
        return totalCategorias;
    }

    public void setTotalCategorias(Long totalCategorias) {
        this.totalCategorias = totalCategorias;
    }

    public Long getProductosActivos() {
        return productosActivos;
    }

    public void setProductosActivos(Long productosActivos) {
        this.productosActivos = productosActivos;
    }

    public Double getValorInventario() {
        return valorInventario;
    }

    public void setValorInventario(Double valorInventario) {
        this.valorInventario = valorInventario;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<Long> getCantidades() {
        return cantidades;
    }

    public void setCantidades(List<Long> cantidades) {
        this.cantidades = cantidades;
    }

}