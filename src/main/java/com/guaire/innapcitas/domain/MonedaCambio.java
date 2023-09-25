package com.guaire.innapcitas.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A MonedaCambio.
 */
@Entity
@Table(name = "moneda_cambio")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MonedaCambio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nombre_moneda", nullable = false)
    private String nombreMoneda;

    @NotNull
    @Column(name = "simbolo", nullable = false)
    private String simbolo;

    @NotNull
    @Column(name = "monto_cambio", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoCambio;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public MonedaCambio nombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
        return this;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public MonedaCambio simbolo(String simbolo) {
        this.simbolo = simbolo;
        return this;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public BigDecimal getMontoCambio() {
        return montoCambio;
    }

    public MonedaCambio montoCambio(BigDecimal montoCambio) {
        this.montoCambio = montoCambio;
        return this;
    }

    public void setMontoCambio(BigDecimal montoCambio) {
        this.montoCambio = montoCambio;
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
        MonedaCambio monedaCambio = (MonedaCambio) o;
        if (monedaCambio.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monedaCambio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonedaCambio{" +
            "id=" + getId() +
            ", nombreMoneda='" + getNombreMoneda() + "'" +
            ", simbolo='" + getSimbolo() + "'" +
            ", montoCambio=" + getMontoCambio() +
            "}";
    }
}
