package com.univo.backend_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría es obligatorio.")
    private String nombre;

    private Boolean activo;


    // Constructor vacío
    public Categoria() {
    }


    // Constructor con parámetros
    public Categoria(String nombre, Boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }


    // Getters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getActivo() {
        return activo;
    }


    // Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}