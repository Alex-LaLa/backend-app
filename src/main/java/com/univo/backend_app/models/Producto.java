package com.univo.backend_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "productos")
public class Producto {

    // ==========================
    // ATRIBUTOS
    // ==========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotNull(message = "La categoría es obligatoria.")
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull(message = "El precio es obligatorio.")
    @Positive(message = "El precio debe ser mayor que cero.")
    private Double precio;

    @NotNull(message = "Debe indicar si el producto está activo.")
    private Boolean activo;

    // ==========================
    // CONSTRUCTORES
    // ==========================

    public Producto() {
    }

    public Producto(String nombre,
                    Categoria categoria,
                    Double precio,
                    Boolean activo) {

        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.activo = activo;
    }

    // ==========================
    // GETTERS
    // ==========================

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public Boolean getActivo() {
        return activo;
    }

    // ==========================
    // SETTERS
    // ==========================

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}