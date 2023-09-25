package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A HistoriaPersonalAdulto.
 */
@Entity
@Table(name = "historia_personal_adulto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistoriaPersonalAdulto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String archivo;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "profesion")
    private String profesion;

    @Column(name = "ocupacion_actual")
    private String ocupacionActual;

    @Column(name = "religion")
    private String religion;

    @Column(name = "direccion_habitacion")
    private String direccionHabitacion;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @Column(name = "telefono_fijo")
    private String telefonoFijo;

    @Column(name = "telefono_celular")
    private String telefonoCelular;

    @Column(name = "email")
    private String email;

    @Column(name = "direccion_trabajo")
    private String direccionTrabajo;

    @Column(name = "telefono_trabajo")
    private String telefonoTrabajo;

    @Column(name = "fax_trabajo")
    private String faxTrabajo;

    @Column(name = "familiar_medico")
    private Boolean familiarMedico;

    @Column(name = "carnet_familiar_medico")
    private String carnetFamiliarMedico;

    @Column(name = "especialidad_familiar_medico")
    private String especialidadFamiliarMedico;

    @Column(name = "lugar_trabajo_familiar_medico")
    private String lugarTrabajoFamiliarMedico;

    @Column(name = "notas")
    private String notas;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalAdultos")
    private Sexo sexo;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalAdultos")
    private ReferenciaMedica referidoPor;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalAdultos")
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalAdultos")
    private TipoConsulta tipoConsulta;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalAdultos")
    private Especialidad especialidad;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("historiaPersonalAdultos")
    private User paciente;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Long historiaMedicaId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public HistoriaPersonalAdulto primerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
        return this;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public HistoriaPersonalAdulto segundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
        return this;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public HistoriaPersonalAdulto primerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
        return this;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public HistoriaPersonalAdulto segundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
        return this;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public HistoriaPersonalAdulto edad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public HistoriaPersonalAdulto lugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
        return this;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public HistoriaPersonalAdulto fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCedula() {
        return cedula;
    }

    public HistoriaPersonalAdulto cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public HistoriaPersonalAdulto nacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProfesion() {
        return profesion;
    }

    public HistoriaPersonalAdulto profesion(String profesion) {
        this.profesion = profesion;
        return this;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getOcupacionActual() {
        return ocupacionActual;
    }

    public HistoriaPersonalAdulto ocupacionActual(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
        return this;
    }

    public void setOcupacionActual(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    public String getReligion() {
        return religion;
    }

    public HistoriaPersonalAdulto religion(String religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getDireccionHabitacion() {
        return direccionHabitacion;
    }

    public HistoriaPersonalAdulto direccionHabitacion(String direccionHabitacion) {
        this.direccionHabitacion = direccionHabitacion;
        return this;
    }

    public void setDireccionHabitacion(String direccionHabitacion) {
        this.direccionHabitacion = direccionHabitacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public HistoriaPersonalAdulto ciudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public HistoriaPersonalAdulto estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public HistoriaPersonalAdulto pais(String pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public HistoriaPersonalAdulto telefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
        return this;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public HistoriaPersonalAdulto telefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
        return this;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getEmail() {
        return email;
    }

    public HistoriaPersonalAdulto email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    public HistoriaPersonalAdulto direccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
        return this;
    }

    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }

    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public HistoriaPersonalAdulto telefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
        return this;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public String getFaxTrabajo() {
        return faxTrabajo;
    }

    public HistoriaPersonalAdulto faxTrabajo(String faxTrabajo) {
        this.faxTrabajo = faxTrabajo;
        return this;
    }

    public void setFaxTrabajo(String faxTrabajo) {
        this.faxTrabajo = faxTrabajo;
    }

    public Boolean isFamiliarMedico() {
        return familiarMedico;
    }

    public HistoriaPersonalAdulto familiarMedico(Boolean familiarMedico) {
        this.familiarMedico = familiarMedico;
        return this;
    }

    public void setFamiliarMedico(Boolean familiarMedico) {
        this.familiarMedico = familiarMedico;
    }

    public String getCarnetFamiliarMedico() {
        return carnetFamiliarMedico;
    }

    public HistoriaPersonalAdulto carnetFamiliarMedico(String carnetFamiliarMedico) {
        this.carnetFamiliarMedico = carnetFamiliarMedico;
        return this;
    }

    public void setCarnetFamiliarMedico(String carnetFamiliarMedico) {
        this.carnetFamiliarMedico = carnetFamiliarMedico;
    }

    public String getEspecialidadFamiliarMedico() {
        return especialidadFamiliarMedico;
    }

    public HistoriaPersonalAdulto especialidadFamiliarMedico(String especialidadFamiliarMedico) {
        this.especialidadFamiliarMedico = especialidadFamiliarMedico;
        return this;
    }

    public void setEspecialidadFamiliarMedico(String especialidadFamiliarMedico) {
        this.especialidadFamiliarMedico = especialidadFamiliarMedico;
    }

    public String getLugarTrabajoFamiliarMedico() {
        return lugarTrabajoFamiliarMedico;
    }

    public HistoriaPersonalAdulto lugarTrabajoFamiliarMedico(String lugarTrabajoFamiliarMedico) {
        this.lugarTrabajoFamiliarMedico = lugarTrabajoFamiliarMedico;
        return this;
    }

    public void setLugarTrabajoFamiliarMedico(String lugarTrabajoFamiliarMedico) {
        this.lugarTrabajoFamiliarMedico = lugarTrabajoFamiliarMedico;
    }

    public String getNotas() {
        return notas;
    }

    public HistoriaPersonalAdulto notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public HistoriaPersonalAdulto sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public ReferenciaMedica getReferidoPor() {
        return referidoPor;
    }

    public HistoriaPersonalAdulto referidoPor(ReferenciaMedica referenciaMedica) {
        this.referidoPor = referenciaMedica;
        return this;
    }

    public void setReferidoPor(ReferenciaMedica referenciaMedica) {
        this.referidoPor = referenciaMedica;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public HistoriaPersonalAdulto estadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public HistoriaPersonalAdulto tipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
        return this;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public HistoriaPersonalAdulto especialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public User getPaciente() {
        return paciente;
    }

    public HistoriaPersonalAdulto paciente(User user) {
        this.paciente = user;
        return this;
    }

    public void setPaciente(User user) {
        this.paciente = user;
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
        HistoriaPersonalAdulto historiaPersonalAdulto = (HistoriaPersonalAdulto) o;
        if (historiaPersonalAdulto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historiaPersonalAdulto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoriaPersonalAdulto{" +
            "id=" + getId() +
            ", primerNombre='" + getPrimerNombre() + "'" +
            ", segundoNombre='" + getSegundoNombre() + "'" +
            ", primerApellido='" + getPrimerApellido() + "'" +
            ", segundoApellido='" + getSegundoApellido() + "'" +
            ", edad=" + getEdad() +
            ", lugarNacimiento='" + getLugarNacimiento() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", cedula='" + getCedula() + "'" +
            ", nacionalidad='" + getNacionalidad() + "'" +
            ", profesion='" + getProfesion() + "'" +
            ", ocupacionActual='" + getOcupacionActual() + "'" +
            ", religion='" + getReligion() + "'" +
            ", direccionHabitacion='" + getDireccionHabitacion() + "'" +
            ", ciudad='" + getCiudad() + "'" +
            ", estado='" + getEstado() + "'" +
            ", pais='" + getPais() + "'" +
            ", telefonoFijo='" + getTelefonoFijo() + "'" +
            ", telefonoCelular='" + getTelefonoCelular() + "'" +
            ", email='" + getEmail() + "'" +
            ", direccionTrabajo='" + getDireccionTrabajo() + "'" +
            ", telefonoTrabajo='" + getTelefonoTrabajo() + "'" +
            ", faxTrabajo='" + getFaxTrabajo() + "'" +
            ", familiarMedico='" + isFamiliarMedico() + "'" +
            ", carnetFamiliarMedico='" + getCarnetFamiliarMedico() + "'" +
            ", especialidadFamiliarMedico='" + getEspecialidadFamiliarMedico() + "'" +
            ", lugarTrabajoFamiliarMedico='" + getLugarTrabajoFamiliarMedico() + "'" +
            ", notas='" + getNotas() + "'" +
            "}";
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Long getHistoriaMedicaId() {
        return historiaMedicaId;
    }

    public void setHistoriaMedicaId(Long historiaMedicaId) {
        this.historiaMedicaId = historiaMedicaId;
    }
}
