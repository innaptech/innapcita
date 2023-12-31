package com.guaire.innapcitas.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PieDePaginaInforme.
 */
@Entity
@Table(name = "pie_de_pagina_informe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PieDePaginaInforme implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "pie_de_pagina", nullable = false)
    private String pieDePagina;

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

    public PieDePaginaInforme nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPieDePagina() {
        return pieDePagina;
    }

    public PieDePaginaInforme pieDePagina(String pieDePagina) {
        this.pieDePagina = pieDePagina;
        return this;
    }

    public void setPieDePagina(String pieDePagina) {
        this.pieDePagina = pieDePagina;
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
        PieDePaginaInforme pieDePaginaInforme = (PieDePaginaInforme) o;
        if (pieDePaginaInforme.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pieDePaginaInforme.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PieDePaginaInforme{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", pieDePagina='" + getPieDePagina() + "'" +
            "}";
    }
}
