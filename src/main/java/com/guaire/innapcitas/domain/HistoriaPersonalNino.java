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
import java.util.Objects;

/**
 * A HistoriaPersonalNino.
 */
@Entity
@Table(name = "historia_personal_nino")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistoriaPersonalNino implements Serializable {

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

    @Column(name = "familiar_medico")
    private Boolean familiarMedico;

    @Column(name = "carnet_familiar_medico")
    private String carnetFamiliarMedico;

    @Column(name = "especialidad_familiar_medico")
    private String especialidadFamiliarMedico;

    @Column(name = "lugar_trabajo_familiar_medico")
    private String lugarTrabajoFamiliarMedico;

    @Column(name = "appellidos_nombres_representante")
    private String appellidosNombresRepresentante;

    @Column(name = "lugar_fecha_nacimiento_representante")
    private String lugarFechaNacimientoRepresentante;

    @Column(name = "cedula_representante")
    private String cedulaRepresentante;

    @Column(name = "edad_representante")
    private Integer edadRepresentante;

    @Column(name = "telefono_celular_representante")
    private String telefonoCelularRepresentante;

    @Column(name = "telefono_habitacion_representante")
    private String telefonoHabitacionRepresentante;

    @Column(name = "quien_trae_nino_consulta")
    private String quienTraeNinoConsulta;

    @Column(name = "cantidad_hermanos")
    private Integer cantidadHermanos;

    @Column(name = "posicion_hermano")
    private Integer posicionHermano;

    @Column(name = "relacion_madre")
    private String relacionMadre;

    @Column(name = "relacion_padre")
    private String relacionPadre;

    @Column(name = "relacion_hermanos")
    private String relacionHermanos;

    @Column(name = "relacion_otros")
    private String relacionOtros;

    @Column(name = "grado_afectividad_nino")
    private String gradoAfectividadNino;

    @Column(name = "caracter_nino")
    private String caracterNino;

    @Column(name = "quien_cuida_nino_padres_trabajan")
    private String quienCuidaNinoPadresTrabajan;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private Sexo sexo;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private ReferenciaMedica referidoPor;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private TipoConsulta tipoConsulta;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private Especialidad especialidad;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private GradoInstruccion gradoInstruccionMadre;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private GradoInstruccion gradoInstruccionPadre;

    @ManyToOne
    @JsonIgnoreProperties("historiaPersonalNinos")
    private GradoInstruccion gradoInstruccionOtro;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("historiaPersonalNinos")
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

    public HistoriaPersonalNino primerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
        return this;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public HistoriaPersonalNino segundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
        return this;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public HistoriaPersonalNino primerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
        return this;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public HistoriaPersonalNino segundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
        return this;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public HistoriaPersonalNino edad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public HistoriaPersonalNino lugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
        return this;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public HistoriaPersonalNino fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCedula() {
        return cedula;
    }

    public HistoriaPersonalNino cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public HistoriaPersonalNino nacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProfesion() {
        return profesion;
    }

    public HistoriaPersonalNino profesion(String profesion) {
        this.profesion = profesion;
        return this;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getOcupacionActual() {
        return ocupacionActual;
    }

    public HistoriaPersonalNino ocupacionActual(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
        return this;
    }

    public void setOcupacionActual(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    public String getReligion() {
        return religion;
    }

    public HistoriaPersonalNino religion(String religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getDireccionHabitacion() {
        return direccionHabitacion;
    }

    public HistoriaPersonalNino direccionHabitacion(String direccionHabitacion) {
        this.direccionHabitacion = direccionHabitacion;
        return this;
    }

    public void setDireccionHabitacion(String direccionHabitacion) {
        this.direccionHabitacion = direccionHabitacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public HistoriaPersonalNino ciudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public HistoriaPersonalNino estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public HistoriaPersonalNino pais(String pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public HistoriaPersonalNino telefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
        return this;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public HistoriaPersonalNino telefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
        return this;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getEmail() {
        return email;
    }

    public HistoriaPersonalNino email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isFamiliarMedico() {
        return familiarMedico;
    }

    public HistoriaPersonalNino familiarMedico(Boolean familiarMedico) {
        this.familiarMedico = familiarMedico;
        return this;
    }

    public void setFamiliarMedico(Boolean familiarMedico) {
        this.familiarMedico = familiarMedico;
    }

    public String getCarnetFamiliarMedico() {
        return carnetFamiliarMedico;
    }

    public HistoriaPersonalNino carnetFamiliarMedico(String carnetFamiliarMedico) {
        this.carnetFamiliarMedico = carnetFamiliarMedico;
        return this;
    }

    public void setCarnetFamiliarMedico(String carnetFamiliarMedico) {
        this.carnetFamiliarMedico = carnetFamiliarMedico;
    }

    public String getEspecialidadFamiliarMedico() {
        return especialidadFamiliarMedico;
    }

    public HistoriaPersonalNino especialidadFamiliarMedico(String especialidadFamiliarMedico) {
        this.especialidadFamiliarMedico = especialidadFamiliarMedico;
        return this;
    }

    public void setEspecialidadFamiliarMedico(String especialidadFamiliarMedico) {
        this.especialidadFamiliarMedico = especialidadFamiliarMedico;
    }

    public String getLugarTrabajoFamiliarMedico() {
        return lugarTrabajoFamiliarMedico;
    }

    public HistoriaPersonalNino lugarTrabajoFamiliarMedico(String lugarTrabajoFamiliarMedico) {
        this.lugarTrabajoFamiliarMedico = lugarTrabajoFamiliarMedico;
        return this;
    }

    public void setLugarTrabajoFamiliarMedico(String lugarTrabajoFamiliarMedico) {
        this.lugarTrabajoFamiliarMedico = lugarTrabajoFamiliarMedico;
    }

    public String getAppellidosNombresRepresentante() {
        return appellidosNombresRepresentante;
    }

    public HistoriaPersonalNino appellidosNombresRepresentante(String appellidosNombresRepresentante) {
        this.appellidosNombresRepresentante = appellidosNombresRepresentante;
        return this;
    }

    public void setAppellidosNombresRepresentante(String appellidosNombresRepresentante) {
        this.appellidosNombresRepresentante = appellidosNombresRepresentante;
    }

    public String getLugarFechaNacimientoRepresentante() {
        return lugarFechaNacimientoRepresentante;
    }

    public HistoriaPersonalNino lugarFechaNacimientoRepresentante(String lugarFechaNacimientoRepresentante) {
        this.lugarFechaNacimientoRepresentante = lugarFechaNacimientoRepresentante;
        return this;
    }

    public void setLugarFechaNacimientoRepresentante(String lugarFechaNacimientoRepresentante) {
        this.lugarFechaNacimientoRepresentante = lugarFechaNacimientoRepresentante;
    }

    public String getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public HistoriaPersonalNino cedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
        return this;
    }

    public void setCedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }

    public Integer getEdadRepresentante() {
        return edadRepresentante;
    }

    public HistoriaPersonalNino edadRepresentante(Integer edadRepresentante) {
        this.edadRepresentante = edadRepresentante;
        return this;
    }

    public void setEdadRepresentante(Integer edadRepresentante) {
        this.edadRepresentante = edadRepresentante;
    }

    public String getTelefonoCelularRepresentante() {
        return telefonoCelularRepresentante;
    }

    public HistoriaPersonalNino telefonoCelularRepresentante(String telefonoCelularRepresentante) {
        this.telefonoCelularRepresentante = telefonoCelularRepresentante;
        return this;
    }

    public void setTelefonoCelularRepresentante(String telefonoCelularRepresentante) {
        this.telefonoCelularRepresentante = telefonoCelularRepresentante;
    }

    public String getTelefonoHabitacionRepresentante() {
        return telefonoHabitacionRepresentante;
    }

    public HistoriaPersonalNino telefonoHabitacionRepresentante(String telefonoHabitacionRepresentante) {
        this.telefonoHabitacionRepresentante = telefonoHabitacionRepresentante;
        return this;
    }

    public void setTelefonoHabitacionRepresentante(String telefonoHabitacionRepresentante) {
        this.telefonoHabitacionRepresentante = telefonoHabitacionRepresentante;
    }

    public String getQuienTraeNinoConsulta() {
        return quienTraeNinoConsulta;
    }

    public HistoriaPersonalNino quienTraeNinoConsulta(String quienTraeNinoConsulta) {
        this.quienTraeNinoConsulta = quienTraeNinoConsulta;
        return this;
    }

    public void setQuienTraeNinoConsulta(String quienTraeNinoConsulta) {
        this.quienTraeNinoConsulta = quienTraeNinoConsulta;
    }

    public Integer getCantidadHermanos() {
        return cantidadHermanos;
    }

    public HistoriaPersonalNino cantidadHermanos(Integer cantidadHermanos) {
        this.cantidadHermanos = cantidadHermanos;
        return this;
    }

    public void setCantidadHermanos(Integer cantidadHermanos) {
        this.cantidadHermanos = cantidadHermanos;
    }

    public Integer getPosicionHermano() {
        return posicionHermano;
    }

    public HistoriaPersonalNino posicionHermano(Integer posicionHermano) {
        this.posicionHermano = posicionHermano;
        return this;
    }

    public void setPosicionHermano(Integer posicionHermano) {
        this.posicionHermano = posicionHermano;
    }

    public String getRelacionMadre() {
        return relacionMadre;
    }

    public HistoriaPersonalNino relacionMadre(String relacionMadre) {
        this.relacionMadre = relacionMadre;
        return this;
    }

    public void setRelacionMadre(String relacionMadre) {
        this.relacionMadre = relacionMadre;
    }

    public String getRelacionPadre() {
        return relacionPadre;
    }

    public HistoriaPersonalNino relacionPadre(String relacionPadre) {
        this.relacionPadre = relacionPadre;
        return this;
    }

    public void setRelacionPadre(String relacionPadre) {
        this.relacionPadre = relacionPadre;
    }

    public String getRelacionHermanos() {
        return relacionHermanos;
    }

    public HistoriaPersonalNino relacionHermanos(String relacionHermanos) {
        this.relacionHermanos = relacionHermanos;
        return this;
    }

    public void setRelacionHermanos(String relacionHermanos) {
        this.relacionHermanos = relacionHermanos;
    }

    public String getRelacionOtros() {
        return relacionOtros;
    }

    public HistoriaPersonalNino relacionOtros(String relacionOtros) {
        this.relacionOtros = relacionOtros;
        return this;
    }

    public void setRelacionOtros(String relacionOtros) {
        this.relacionOtros = relacionOtros;
    }

    public String getGradoAfectividadNino() {
        return gradoAfectividadNino;
    }

    public HistoriaPersonalNino gradoAfectividadNino(String gradoAfectividadNino) {
        this.gradoAfectividadNino = gradoAfectividadNino;
        return this;
    }

    public void setGradoAfectividadNino(String gradoAfectividadNino) {
        this.gradoAfectividadNino = gradoAfectividadNino;
    }

    public String getCaracterNino() {
        return caracterNino;
    }

    public HistoriaPersonalNino caracterNino(String caracterNino) {
        this.caracterNino = caracterNino;
        return this;
    }

    public void setCaracterNino(String caracterNino) {
        this.caracterNino = caracterNino;
    }

    public String getQuienCuidaNinoPadresTrabajan() {
        return quienCuidaNinoPadresTrabajan;
    }

    public HistoriaPersonalNino quienCuidaNinoPadresTrabajan(String quienCuidaNinoPadresTrabajan) {
        this.quienCuidaNinoPadresTrabajan = quienCuidaNinoPadresTrabajan;
        return this;
    }

    public void setQuienCuidaNinoPadresTrabajan(String quienCuidaNinoPadresTrabajan) {
        this.quienCuidaNinoPadresTrabajan = quienCuidaNinoPadresTrabajan;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public HistoriaPersonalNino sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public ReferenciaMedica getReferidoPor() {
        return referidoPor;
    }

    public HistoriaPersonalNino referidoPor(ReferenciaMedica referenciaMedica) {
        this.referidoPor = referenciaMedica;
        return this;
    }

    public void setReferidoPor(ReferenciaMedica referenciaMedica) {
        this.referidoPor = referenciaMedica;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public HistoriaPersonalNino estadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public HistoriaPersonalNino tipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
        return this;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public HistoriaPersonalNino especialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public GradoInstruccion getGradoInstruccionMadre() {
        return gradoInstruccionMadre;
    }

    public HistoriaPersonalNino gradoInstruccionMadre(GradoInstruccion gradoInstruccion) {
        this.gradoInstruccionMadre = gradoInstruccion;
        return this;
    }

    public void setGradoInstruccionMadre(GradoInstruccion gradoInstruccion) {
        this.gradoInstruccionMadre = gradoInstruccion;
    }

    public GradoInstruccion getGradoInstruccionPadre() {
        return gradoInstruccionPadre;
    }

    public HistoriaPersonalNino gradoInstruccionPadre(GradoInstruccion gradoInstruccion) {
        this.gradoInstruccionPadre = gradoInstruccion;
        return this;
    }

    public void setGradoInstruccionPadre(GradoInstruccion gradoInstruccion) {
        this.gradoInstruccionPadre = gradoInstruccion;
    }

    public GradoInstruccion getGradoInstruccionOtro() {
        return gradoInstruccionOtro;
    }

    public HistoriaPersonalNino gradoInstruccionOtro(GradoInstruccion gradoInstruccion) {
        this.gradoInstruccionOtro = gradoInstruccion;
        return this;
    }

    public void setGradoInstruccionOtro(GradoInstruccion gradoInstruccion) {
        this.gradoInstruccionOtro = gradoInstruccion;
    }

    public User getPaciente() {
        return paciente;
    }

    public HistoriaPersonalNino paciente(User user) {
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
        HistoriaPersonalNino historiaPersonalNino = (HistoriaPersonalNino) o;
        if (historiaPersonalNino.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historiaPersonalNino.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoriaPersonalNino{" +
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
            ", familiarMedico='" + isFamiliarMedico() + "'" +
            ", carnetFamiliarMedico='" + getCarnetFamiliarMedico() + "'" +
            ", especialidadFamiliarMedico='" + getEspecialidadFamiliarMedico() + "'" +
            ", lugarTrabajoFamiliarMedico='" + getLugarTrabajoFamiliarMedico() + "'" +
            ", appellidosNombresRepresentante='" + getAppellidosNombresRepresentante() + "'" +
            ", lugarFechaNacimientoRepresentante='" + getLugarFechaNacimientoRepresentante() + "'" +
            ", cedulaRepresentante='" + getCedulaRepresentante() + "'" +
            ", edadRepresentante=" + getEdadRepresentante() +
            ", telefonoCelularRepresentante='" + getTelefonoCelularRepresentante() + "'" +
            ", telefonoHabitacionRepresentante='" + getTelefonoHabitacionRepresentante() + "'" +
            ", quienTraeNinoConsulta='" + getQuienTraeNinoConsulta() + "'" +
            ", cantidadHermanos=" + getCantidadHermanos() +
            ", posicionHermano=" + getPosicionHermano() +
            ", relacionMadre='" + getRelacionMadre() + "'" +
            ", relacionPadre='" + getRelacionPadre() + "'" +
            ", relacionHermanos='" + getRelacionHermanos() + "'" +
            ", relacionOtros='" + getRelacionOtros() + "'" +
            ", gradoAfectividadNino='" + getGradoAfectividadNino() + "'" +
            ", caracterNino='" + getCaracterNino() + "'" +
            ", quienCuidaNinoPadresTrabajan='" + getQuienCuidaNinoPadresTrabajan() + "'" +
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
