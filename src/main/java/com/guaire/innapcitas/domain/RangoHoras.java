package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RangoHoras.
 */
@Entity
@Table(name = "rango_horas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RangoHoras implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private Integer horaInicio;

    @NotNull
    @Column(name = "minuto_inicio", nullable = false)
    private Integer minutoInicio;

    @NotNull
    @Column(name = "hora_fin", nullable = false)
    private Integer horaFin;

    @NotNull
    @Column(name = "minuto_fin", nullable = false)
    private Integer minutoFin;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("rangoHoras")
    private HorarioDisponibilidad horarioDisponibilidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHoraInicio() {
        return horaInicio;
    }

    public RangoHoras horaInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getMinutoInicio() {
        return minutoInicio;
    }

    public RangoHoras minutoInicio(Integer minutoInicio) {
        this.minutoInicio = minutoInicio;
        return this;
    }

    public void setMinutoInicio(Integer minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    public RangoHoras horaFin(Integer horaFin) {
        this.horaFin = horaFin;
        return this;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getMinutoFin() {
        return minutoFin;
    }

    public RangoHoras minutoFin(Integer minutoFin) {
        this.minutoFin = minutoFin;
        return this;
    }

    public void setMinutoFin(Integer minutoFin) {
        this.minutoFin = minutoFin;
    }

    public HorarioDisponibilidad getHorarioDisponibilidad() {
        return horarioDisponibilidad;
    }

    public RangoHoras horarioDisponibilidad(HorarioDisponibilidad horarioDisponibilidad) {
        this.horarioDisponibilidad = horarioDisponibilidad;
        return this;
    }

    public void setHorarioDisponibilidad(HorarioDisponibilidad horarioDisponibilidad) {
        this.horarioDisponibilidad = horarioDisponibilidad;
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
        RangoHoras rangoHoras = (RangoHoras) o;
        if (rangoHoras.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rangoHoras.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RangoHoras{" +
            "id=" + getId() +
            ", horaInicio=" + getHoraInicio() +
            ", minutoInicio=" + getMinutoInicio() +
            ", horaFin=" + getHoraFin() +
            ", minutoFin=" + getMinutoFin() +
            "}";
    }
}
