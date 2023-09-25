package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A Pago.
 */
@Entity
@Table(name = "pago")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @NotNull
    @Column(name = "fecha_emision", nullable = false)
    private Instant fechaEmision;

    @Column(name = "fecha_estatus")
    private Instant fechaEstatus;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("pagos")
    private Cita cita;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("pagos")
    private EstatusPago estatusPago;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("pagos")
    private TipoPago tipoPago;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public Pago monto(BigDecimal monto) {
        this.monto = monto;
        return this;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Instant getFechaEmision() {
        return fechaEmision;
    }

    public Pago fechaEmision(Instant fechaEmision) {
        this.fechaEmision = fechaEmision;
        return this;
    }

    public void setFechaEmision(Instant fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Instant getFechaEstatus() {
        return fechaEstatus;
    }

    public Pago fechaEstatus(Instant fechaEstatus) {
        this.fechaEstatus = fechaEstatus;
        return this;
    }

    public void setFechaEstatus(Instant fechaEstatus) {
        this.fechaEstatus = fechaEstatus;
    }

    public Cita getCita() {
        return cita;
    }

    public Pago cita(Cita cita) {
        this.cita = cita;
        return this;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public EstatusPago getEstatusPago() {
        return estatusPago;
    }

    public Pago estatusPago(EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
        return this;
    }

    public void setEstatusPago(EstatusPago estatusPago) {
        this.estatusPago = estatusPago;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public Pago tipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
        return this;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pago pago = (Pago) o;
        if (pago.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pago.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pago{" +
            "id=" + getId() +
            ", monto=" + getMonto() +
            ", fechaEmision='" + getFechaEmision() + "'" +
            ", fechaEstatus='" + getFechaEstatus() + "'" +
            "}";
    }
}
