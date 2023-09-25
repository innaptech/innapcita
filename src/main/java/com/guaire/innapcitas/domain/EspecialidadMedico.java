package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A EspecialidadMedico.
 */
@Entity
@Table(name = "especialidad_medico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EspecialidadMedico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "monto_hora", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoHora;

    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "monto_hora_online", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoHoraOnline;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("especialidadMedicos")
    private User medico;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("especialidadMedicos")
    private Especialidad especialidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontoHora() {
        return montoHora;
    }

    public EspecialidadMedico montoHora(BigDecimal montoHora) {
        this.montoHora = montoHora;
        return this;
    }

    public void setMontoHora(BigDecimal montoHora) {
        this.montoHora = montoHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EspecialidadMedico descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoHoraOnline() {
        return montoHoraOnline;
    }

    public EspecialidadMedico montoHoraOnline(BigDecimal montoHoraOnline) {
        this.montoHoraOnline = montoHoraOnline;
        return this;
    }

    public void setMontoHoraOnline(BigDecimal montoHoraOnline) {
        this.montoHoraOnline = montoHoraOnline;
    }

    public User getMedico() {
        return medico;
    }

    public EspecialidadMedico medico(User user) {
        this.medico = user;
        return this;
    }

    public void setMedico(User user) {
        this.medico = user;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public EspecialidadMedico especialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
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
        EspecialidadMedico especialidadMedico = (EspecialidadMedico) o;
        if (especialidadMedico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidadMedico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EspecialidadMedico{" +
            "id=" + getId() +
            ", montoHora=" + getMontoHora() +
            ", descripcion='" + getDescripcion() + "'" +
            ", montoHoraOnline=" + getMontoHoraOnline() +
            "}";
    }
}
