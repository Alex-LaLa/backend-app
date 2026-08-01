package com.univo.backend_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity // Le dice a Spring que esto será una tabla en PostgreSQL
@Table(name = "clientes")
public class Cliente {

    // ==========================
    // ATRIBUTOS (Características)
    // ==========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio.")
    private String nombreCompleto;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "Debe ingresar un correo válido.")
    private String email;

    @NotBlank(message = "La ciudad es obligatoria.")
    private String ciudad;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    // ==========================
    // CONSTRUCTORES
    // ==========================

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String nombreCompleto,
                   String email,
                   String ciudad) {

        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.ciudad = ciudad;
    }

    // ==========================
    // GETTERS (Obtener información)
    // ==========================

    public Long getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    // ==========================
    // SETTERS (Modificar información)
    // ==========================

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}