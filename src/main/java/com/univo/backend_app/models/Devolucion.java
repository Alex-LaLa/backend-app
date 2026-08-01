package com.univo.backend_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity // Le dice a Spring que esto será una tabla en PostgreSQL
@Table(name = "devoluciones")
public class Devolucion {

    // ==========================
    // ATRIBUTOS (Características)
    // ==========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe seleccionar un detalle de orden.")
    @ManyToOne
    @JoinColumn(name = "detalle_orden_id")
    private DetalleOrden detalleOrden;

    @NotNull(message = "La cantidad devuelta es obligatoria.")
    @Positive(message = "La cantidad devuelta debe ser mayor que cero.")
    private Integer cantidadDevuelta;

    @NotNull(message = "El monto reembolsado es obligatorio.")
    @PositiveOrZero(message = "El monto reembolsado no puede ser negativo.")
    private Double montoReembolsado;

    @NotBlank(message = "El motivo de la devolución es obligatorio.")
    private String motivo;

    @NotBlank(message = "El estado de la devolución es obligatorio.")
    private String estado;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    // ==========================
    // CONSTRUCTORES
    // ==========================

    // Constructor vacío
    public Devolucion() {
    }

    // Constructor con parámetros
    public Devolucion(DetalleOrden detalleOrden,
                      Integer cantidadDevuelta,
                      Double montoReembolsado,
                      String motivo,
                      String estado) {

        this.detalleOrden = detalleOrden;
        this.cantidadDevuelta = cantidadDevuelta;
        this.montoReembolsado = montoReembolsado;
        this.motivo = motivo;
        this.estado = estado;
    }

    // ==========================
    // GETTERS (Obtener información)
    // ==========================

    public Long getId() {
        return id;
    }

    public DetalleOrden getDetalleOrden() {
        return detalleOrden;
    }

    public Integer getCantidadDevuelta() {
        return cantidadDevuelta;
    }

    public Double getMontoReembolsado() {
        return montoReembolsado;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    // ==========================
    // SETTERS (Modificar información)
    // ==========================

    public void setDetalleOrden(DetalleOrden detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public void setCantidadDevuelta(Integer cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }

    public void setMontoReembolsado(Double montoReembolsado) {
        this.montoReembolsado = montoReembolsado;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}