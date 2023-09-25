package com.guaire.innapcitas.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CabeceraInforme.
 */
@Entity
@Table(name = "cabecera_informe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CabeceraInforme implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "cabecera", nullable = false)
    private String cabecera;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public CabeceraInforme nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCabecera() {
        return cabecera;
    }

    public CabeceraInforme cabecera(String cabecera) {
        this.cabecera = cabecera;
        return this;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
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
        CabeceraInforme cabeceraInforme = (CabeceraInforme) o;
        if (cabeceraInforme.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cabeceraInforme.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CabeceraInforme{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", cabecera='" + getCabecera() + "'" +
            "}";
    }
}
