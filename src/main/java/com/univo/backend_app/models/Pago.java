package com.univo.backend_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity // Le dice a Spring que esto será una tabla en PostgreSQL
@Table(name = "pagos")
public class Pago {

    // ==========================
    // ATRIBUTOS (Características)
    // ==========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe seleccionar una orden.")
    @OneToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @NotBlank(message = "El estado del pago es obligatorio.")
    private String estadoPago;

    @NotBlank(message = "El método de pago es obligatorio.")
    private String metodoPago;

    @NotNull(message = "El monto es obligatorio.")
    @Positive(message = "El monto debe ser mayor que cero.")
    private Double monto;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaPago;

    // ==========================
    // CONSTRUCTORES
    // ==========================

    // Constructor vacío
    public Pago() {
    }

    // Constructor con parámetros
    public Pago(Orden orden,
                String estadoPago,
                String metodoPago,
                Double monto) {

        this.orden = orden;
        this.estadoPago = estadoPago;
        this.metodoPago = metodoPago;
        this.monto = monto;
    }

    // ==========================
    // GETTERS (Obtener información)
    // ==========================

    public Long getId() {
        return id;
    }

    public Orden getOrden() {
        return orden;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    // ==========================
    // SETTERS (Modificar información)
    // ==========================

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}