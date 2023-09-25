package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PlantillaRecipe.
 */
@Entity
@Table(name = "plantilla_recipe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlantillaRecipe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "texto", nullable = false)
    private String texto;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("plantillaRecipes")
    private User medico;

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

    public PlantillaRecipe nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public PlantillaRecipe texto(String texto) {
        this.texto = texto;
        return this;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public User getMedico() {
        return medico;
    }

    public PlantillaRecipe medico(User user) {
        this.medico = user;
        return this;
    }

    public void setMedico(User user) {
        this.medico = user;
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
        PlantillaRecipe plantillaRecipe = (PlantillaRecipe) o;
        if (plantillaRecipe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plantillaRecipe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlantillaRecipe{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", texto='" + getTexto() + "'" +
            "}";
    }
}
