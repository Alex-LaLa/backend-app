package com.univo.backend_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity // Le dice a Spring que esto será una tabla en PostgreSQL
@Table(name = "inventario")
public class Inventario {

    // ==========================
    // ATRIBUTOS (Características)
    // ==========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe seleccionar un producto.")
    @OneToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @NotNull(message = "Las unidades disponibles son obligatorias.")
    @PositiveOrZero(message = "Las unidades disponibles no pueden ser negativas.")
    private Integer unidadesDisponibles;

    @NotNull(message = "El nivel de reorden es obligatorio.")
    @PositiveOrZero(message = "El nivel de reorden no puede ser negativo.")
    private Integer nivelReorden;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime ultimaActualizacion;

    // ==========================
    // CONSTRUCTORES
    // ==========================

    // Constructor vacío
    public Inventario() {
    }

    // Constructor con parámetros
    public Inventario(Producto producto,
                      Integer unidadesDisponibles,
                      Integer nivelReorden) {

        this.producto = producto;
        this.unidadesDisponibles = unidadesDisponibles;
        this.nivelReorden = nivelReorden;
    }

    // ==========================
    // GETTERS (Obtener información)
    // ==========================

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public Integer getNivelReorden() {
        return nivelReorden;
    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    // ==========================
    // SETTERS (Modificar información)
    // ==========================

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public void setNivelReorden(Integer nivelReorden) {
        this.nivelReorden = nivelReorden;
    }

}