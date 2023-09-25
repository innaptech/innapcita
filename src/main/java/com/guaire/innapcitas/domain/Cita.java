package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Cita.
 */
@Entity
@Table(name = "cita")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fecha")
    private ZonedDateTime fecha;

    @Column(name = "notas")
    private String notas;

    @Column(name = "paciente_nino")
    private Boolean pacienteNino;

    @Column(name = "consulta_online")
    private Boolean consultaOnline;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("citas")
    private User paciente;

    @ManyToOne
    @JsonIgnoreProperties("citas")
    private EspecialidadMedico especialidadMedico;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("citas")
    private Especialidad especialidad;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("citas")
    private TipoConsulta tipoConsulta;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Set<HistoriaPersonalAdulto> historiaPersonalAdultos = new HashSet<>();

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Set<HistoriaMedicaAdulto> historiaMedicaAdultos = new HashSet<>();

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Set<HistoriaPersonalNino> historiaPersonalNinos = new HashSet<>();

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Set<HistoriaMedicaNino> historiaMedicaNinos = new HashSet<>();

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private EvolucionPaciente evolucionPaciente;

    @ManyToOne
    @JsonIgnoreProperties("citas")
    private EstatusCita estatusCita;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public Cita fecha(ZonedDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNotas() {
        return notas;
    }

    public Cita notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Boolean isPacienteNino() {
        return pacienteNino;
    }

    public Cita pacienteNino(Boolean pacienteNino) {
        this.pacienteNino = pacienteNino;
        return this;
    }

    public void setPacienteNino(Boolean pacienteNino) {
        this.pacienteNino = pacienteNino;
    }

    public User getPaciente() {
        return paciente;
    }

    public Cita paciente(User user) {
        this.paciente = user;
        return this;
    }

    public void setPaciente(User user) {
        this.paciente = user;
    }

    public EspecialidadMedico getEspecialidadMedico() {
        return especialidadMedico;
    }

    public Cita especialidadMedico(EspecialidadMedico especialidadMedico) {
        this.especialidadMedico = especialidadMedico;
        return this;
    }

    public void setEspecialidadMedico(EspecialidadMedico especialidadMedico) {
        this.especialidadMedico = especialidadMedico;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Cita especialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public Cita tipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
        return this;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Set<HistoriaPersonalAdulto> getHistoriaPersonalAdultos() {
        return historiaPersonalAdultos;
    }

    public Cita historiaPersonalAdultos(Set<HistoriaPersonalAdulto> historiaPersonalAdultos) {
        this.historiaPersonalAdultos = historiaPersonalAdultos;
        return this;
    }

    public Cita addHistoriaPersonalAdulto(HistoriaPersonalAdulto historiaPersonalAdulto) {
        this.historiaPersonalAdultos.add(historiaPersonalAdulto);
        return this;
    }

    public Cita removeHistoriaPersonalAdulto(HistoriaPersonalAdulto historiaPersonalAdulto) {
        this.historiaPersonalAdultos.remove(historiaPersonalAdulto);
        return this;
    }

    public void setHistoriaPersonalAdultos(Set<HistoriaPersonalAdulto> historiaPersonalAdultos) {
        this.historiaPersonalAdultos = historiaPersonalAdultos;
    }

    public Set<HistoriaMedicaAdulto> getHistoriaMedicaAdultos() {
        return historiaMedicaAdultos;
    }

    public Cita historiaMedicaAdultos(Set<HistoriaMedicaAdulto> historiaMedicaAdultos) {
        this.historiaMedicaAdultos = historiaMedicaAdultos;
        return this;
    }

    public Cita addHistoriaMedicaAdulto(HistoriaMedicaAdulto historiaMedicaAdulto) {
        this.historiaMedicaAdultos.add(historiaMedicaAdulto);
        return this;
    }

    public Cita removeHistoriaMedicaAdulto(HistoriaMedicaAdulto historiaMedicaAdulto) {
        this.historiaMedicaAdultos.remove(historiaMedicaAdulto);
        return this;
    }

    public void setHistoriaMedicaAdultos(Set<HistoriaMedicaAdulto> historiaMedicaAdultos) {
        this.historiaMedicaAdultos = historiaMedicaAdultos;
    }

    public Set<HistoriaPersonalNino> getHistoriaPersonalNinos() {
        return historiaPersonalNinos;
    }

    public Cita historiaPersonalNinos(Set<HistoriaPersonalNino> historiaPersonalNinos) {
        this.historiaPersonalNinos = historiaPersonalNinos;
        return this;
    }

    public Cita addHistoriaPersonalNino(HistoriaPersonalNino historiaPersonalNino) {
        this.historiaPersonalNinos.add(historiaPersonalNino);
        return this;
    }

    public Cita removeHistoriaPersonalNino(HistoriaPersonalNino historiaPersonalNino) {
        this.historiaPersonalNinos.remove(historiaPersonalNino);
        return this;
    }

    public void setHistoriaPersonalNinos(Set<HistoriaPersonalNino> historiaPersonalNinos) {
        this.historiaPersonalNinos = historiaPersonalNinos;
    }

    public Set<HistoriaMedicaNino> getHistoriaMedicaNinos() {
        return historiaMedicaNinos;
    }

    public Cita historiaMedicaNinos(Set<HistoriaMedicaNino> historiaMedicaNinos) {
        this.historiaMedicaNinos = historiaMedicaNinos;
        return this;
    }

    public Cita addHistoriaMedicaNino(HistoriaMedicaNino historiaMedicaNino) {
        this.historiaMedicaNinos.add(historiaMedicaNino);
        return this;
    }

    public Cita removeHistoriaMedicaNino(HistoriaMedicaNino historiaMedicaNino) {
        this.historiaMedicaNinos.remove(historiaMedicaNino);
        return this;
    }

    public void setHistoriaMedicaNinos(Set<HistoriaMedicaNino> historiaMedicaNinos) {
        this.historiaMedicaNinos = historiaMedicaNinos;
    }

    public EstatusCita getEstatusCita() {
        return estatusCita;
    }

    public Cita estatusCita(EstatusCita estatusCita) {
        this.estatusCita = estatusCita;
        return this;
    }

    public void setEstatusCita(EstatusCita estatusCita) {
        this.estatusCita = estatusCita;
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
        Cita cita = (Cita) o;
        if (cita.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cita.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cita{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", notas='" + getNotas() + "'" +
            ", pacienteNino='" + isPacienteNino() + "'" +
            "}";
    }

    public EvolucionPaciente getEvolucionPaciente() {
        return evolucionPaciente;
    }

    public void setEvolucionPaciente(EvolucionPaciente evolucionPaciente) {
        this.evolucionPaciente = evolucionPaciente;
    }

    public Boolean getConsultaOnline() {
        return consultaOnline;
    }

    public void setConsultaOnline(Boolean consultaOnline) {
        this.consultaOnline = consultaOnline;
    }
}
