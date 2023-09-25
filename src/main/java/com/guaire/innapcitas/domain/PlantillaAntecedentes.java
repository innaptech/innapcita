package com.guaire.innapcitas.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PlantillaAntecedentes.
 */
@Entity
@Table(name = "plantilla_antecedentes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlantillaAntecedentes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    
    @Lob
    @Column(name = "antecedentes", nullable = false)
    private String antecedentes;

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

    public PlantillaAntecedentes nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public PlantillaAntecedentes antecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
        return this;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
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
        PlantillaAntecedentes plantillaAntecedentes = (PlantillaAntecedentes) o;
        if (plantillaAntecedentes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plantillaAntecedentes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlantillaAntecedentes{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", antecedentes='" + getAntecedentes() + "'" +
            "}";
    }
}
