package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A HorarioDisponibilidad.
 */
@Entity
@Table(name = "horario_disponibilidad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HorarioDisponibilidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarioDisponibilidads")
    private DiaSemana diaSemana;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarioDisponibilidads")
    private EspecialidadMedico especialidadMedico;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private List<RangoHoras> horas;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public HorarioDisponibilidad diaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
        return this;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public EspecialidadMedico getEspecialidadMedico() {
        return especialidadMedico;
    }

    public HorarioDisponibilidad especialidadMedico(EspecialidadMedico especialidadMedico) {
        this.especialidadMedico = especialidadMedico;
        return this;
    }

    public void setEspecialidadMedico(EspecialidadMedico especialidadMedico) {
        this.especialidadMedico = especialidadMedico;
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
        HorarioDisponibilidad horarioDisponibilidad = (HorarioDisponibilidad) o;
        if (horarioDisponibilidad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), horarioDisponibilidad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HorarioDisponibilidad{" +
            "id=" + getId() +
            "}";
    }

    public List<RangoHoras> getHoras() {
        return horas;
    }

    public void setHoras(List<RangoHoras> horas) {
        this.horas = horas;
    }
}
