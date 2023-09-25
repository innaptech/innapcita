package com.guaire.innapcitas.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Especialidad.
 */
@Entity
@Table(name = "especialidad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "email")
    private String email;

    @Column(name = "administrador")
    private String administrador;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Especialidad especialidad(String especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Especialidad descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public Especialidad email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdministrador() {
        return administrador;
    }

    public Especialidad administrador(String administrador) {
        this.administrador = administrador;
        return this;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
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
        Especialidad especialidad = (Especialidad) o;
        if (especialidad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Especialidad{" +
            "id=" + getId() +
            ", especialidad='" + getEspecialidad() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", email='" + getEmail() + "'" +
            ", administrador='" + getAdministrador() + "'" +
            "}";
    }
}
