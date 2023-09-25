package com.guaire.innapcitas.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PlantillaHistoriaPersonalNino.
 */
@Entity
@Table(name = "plantilla_historia_personal_nino")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlantillaHistoriaPersonalNino implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "activo")
    private Boolean activo;

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

    public PlantillaHistoriaPersonalNino nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public PlantillaHistoriaPersonalNino contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Boolean isActivo() {
        return activo;
    }

    public PlantillaHistoriaPersonalNino activo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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
        PlantillaHistoriaPersonalNino plantillaHistoriaPersonalNino = (PlantillaHistoriaPersonalNino) o;
        if (plantillaHistoriaPersonalNino.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plantillaHistoriaPersonalNino.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlantillaHistoriaPersonalNino{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", contenido='" + getContenido() + "'" +
            ", activo='" + isActivo() + "'" +
            "}";
    }
}
