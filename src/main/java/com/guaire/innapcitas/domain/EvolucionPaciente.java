package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A EvolucionPaciente.
 */
@Entity
@Table(name = "evolucion_paciente")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvolucionPaciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fecha")
    private ZonedDateTime fecha;

    @Column(name = "peso")
    private String peso;

    @Column(name = "t_a_acostada")
    private String tAAcostada;

    @Column(name = "t_a_sentada")
    private String tASentada;

    @Column(name = "t_a_parada")
    private String tAParada;

    @Column(name = "talla")
    private String talla;

    @Column(name = "imc")
    private String imc;

    @Column(name = "firma")
    private String firma;

    @Column(name = "nuevos_sintomas")
    private String nuevosSintomas;

    @Column(name = "lateralidad_mano")
    private String lateralidadMano;

    @Column(name = "lateralidad_pierna")
    private String lateralidadPierna;

    @Column(name = "lateralidad_ojo")
    private String lateralidadOjo;

    @Column(name = "lateralidad_oido")
    private String lateralidadOido;

    @Column(name = "examen_piel")
    private String examenPiel;

    @Column(name = "examen_cabeza")
    private String examenCabeza;

    @Column(name = "examen_ojos")
    private String examenOjos;

    @Column(name = "examen_orl")
    private String examenOrl;

    @Column(name = "examen_cuello")
    private String examenCuello;

    @Column(name = "examen_cardiovascular")
    private String examenCardiovascular;

    @Column(name = "examen_pulmonar")
    private String examenPulmonar;

    @Column(name = "examen_abdomen")
    private String examenAbdomen;

    @Column(name = "examen_genitourinario")
    private String examenGenitourinario;

    @Column(name = "examen_neurologico")
    private String examenNeurologico;

    @Column(name = "examen_articular")
    private String examenArticular;

    @Column(name = "examen_neuromuscular")
    private String examenNeuromuscular;

    @Column(name = "impresiones_diagnosticas")
    private String impresionesDiagnosticas;

    @Column(name = "tratamiento")
    private String tratamiento;

    @Column(name = "recomendaciones")
    private String recomendaciones;

    @Column(name = "proxima_consulta")
    private LocalDate proximaConsulta;

    @Column(name = "frecuencia_cardiaca")
    private String frecuenciaCardiaca;

    @Column(name = "frecuencia_respiratoria")
    private String frecuenciaRespiratoria;

    @Column(name = "examen_mental")
    private String examenMental;

    @Column(name = "informacion_relevante")
    private String informacionRelevante;

    @Column(name = "recomendaciones_generales")
    private String recomendacionesGenerales;

    @Column(name = "recipe")
    private String recipe;

    @Column(name = "indicaciones")
    private String indicaciones;

    @Column(name = "examenes_indicados")
    private String examenesIndicados;

    @ManyToOne(optional = false)
    @NotNull
    private Cita cita;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private List<ExamenComplementario> examenesComplementarios;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String informeArchivo;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String recipeArchivo;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String indicacionesArchivo;

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

    public EvolucionPaciente fecha(ZonedDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public String getPeso() {
        return peso;
    }

    public EvolucionPaciente peso(String peso) {
        this.peso = peso;
        return this;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String gettAAcostada() {
        return tAAcostada;
    }

    public EvolucionPaciente tAAcostada(String tAAcostada) {
        this.tAAcostada = tAAcostada;
        return this;
    }

    public void settAAcostada(String tAAcostada) {
        this.tAAcostada = tAAcostada;
    }

    public String gettASentada() {
        return tASentada;
    }

    public EvolucionPaciente tASentada(String tASentada) {
        this.tASentada = tASentada;
        return this;
    }

    public void settASentada(String tASentada) {
        this.tASentada = tASentada;
    }

    public String gettAParada() {
        return tAParada;
    }

    public EvolucionPaciente tAParada(String tAParada) {
        this.tAParada = tAParada;
        return this;
    }

    public void settAParada(String tAParada) {
        this.tAParada = tAParada;
    }

    public String getTalla() {
        return talla;
    }

    public EvolucionPaciente talla(String talla) {
        this.talla = talla;
        return this;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getImc() {
        return imc;
    }

    public EvolucionPaciente imc(String imc) {
        this.imc = imc;
        return this;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getFirma() {
        return firma;
    }

    public EvolucionPaciente firma(String firma) {
        this.firma = firma;
        return this;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getNuevosSintomas() {
        return nuevosSintomas;
    }

    public EvolucionPaciente nuevosSintomas(String nuevosSintomas) {
        this.nuevosSintomas = nuevosSintomas;
        return this;
    }

    public void setNuevosSintomas(String nuevosSintomas) {
        this.nuevosSintomas = nuevosSintomas;
    }

    public String getLateralidadMano() {
        return lateralidadMano;
    }

    public EvolucionPaciente lateralidadMano(String lateralidadMano) {
        this.lateralidadMano = lateralidadMano;
        return this;
    }

    public void setLateralidadMano(String lateralidadMano) {
        this.lateralidadMano = lateralidadMano;
    }

    public String getLateralidadPierna() {
        return lateralidadPierna;
    }

    public EvolucionPaciente lateralidadPierna(String lateralidadPierna) {
        this.lateralidadPierna = lateralidadPierna;
        return this;
    }

    public void setLateralidadPierna(String lateralidadPierna) {
        this.lateralidadPierna = lateralidadPierna;
    }

    public String getLateralidadOjo() {
        return lateralidadOjo;
    }

    public EvolucionPaciente lateralidadOjo(String lateralidadOjo) {
        this.lateralidadOjo = lateralidadOjo;
        return this;
    }

    public void setLateralidadOjo(String lateralidadOjo) {
        this.lateralidadOjo = lateralidadOjo;
    }

    public String getLateralidadOido() {
        return lateralidadOido;
    }

    public EvolucionPaciente lateralidadOido(String lateralidadOido) {
        this.lateralidadOido = lateralidadOido;
        return this;
    }

    public void setLateralidadOido(String lateralidadOido) {
        this.lateralidadOido = lateralidadOido;
    }

    public String getExamenPiel() {
        return examenPiel;
    }

    public EvolucionPaciente examenPiel(String examenPiel) {
        this.examenPiel = examenPiel;
        return this;
    }

    public void setExamenPiel(String examenPiel) {
        this.examenPiel = examenPiel;
    }

    public String getExamenCabeza() {
        return examenCabeza;
    }

    public EvolucionPaciente examenCabeza(String examenCabeza) {
        this.examenCabeza = examenCabeza;
        return this;
    }

    public void setExamenCabeza(String examenCabeza) {
        this.examenCabeza = examenCabeza;
    }

    public String getExamenOjos() {
        return examenOjos;
    }

    public EvolucionPaciente examenOjos(String examenOjos) {
        this.examenOjos = examenOjos;
        return this;
    }

    public void setExamenOjos(String examenOjos) {
        this.examenOjos = examenOjos;
    }

    public String getExamenOrl() {
        return examenOrl;
    }

    public EvolucionPaciente examenOrl(String examenOrl) {
        this.examenOrl = examenOrl;
        return this;
    }

    public void setExamenOrl(String examenOrl) {
        this.examenOrl = examenOrl;
    }

    public String getExamenCuello() {
        return examenCuello;
    }

    public EvolucionPaciente examenCuello(String examenCuello) {
        this.examenCuello = examenCuello;
        return this;
    }

    public void setExamenCuello(String examenCuello) {
        this.examenCuello = examenCuello;
    }

    public String getExamenCardiovascular() {
        return examenCardiovascular;
    }

    public EvolucionPaciente examenCardiovascular(String examenCardiovascular) {
        this.examenCardiovascular = examenCardiovascular;
        return this;
    }

    public void setExamenCardiovascular(String examenCardiovascular) {
        this.examenCardiovascular = examenCardiovascular;
    }

    public String getExamenPulmonar() {
        return examenPulmonar;
    }

    public EvolucionPaciente examenPulmonar(String examenPulmonar) {
        this.examenPulmonar = examenPulmonar;
        return this;
    }

    public void setExamenPulmonar(String examenPulmonar) {
        this.examenPulmonar = examenPulmonar;
    }

    public String getExamenAbdomen() {
        return examenAbdomen;
    }

    public EvolucionPaciente examenAbdomen(String examenAbdomen) {
        this.examenAbdomen = examenAbdomen;
        return this;
    }

    public void setExamenAbdomen(String examenAbdomen) {
        this.examenAbdomen = examenAbdomen;
    }

    public String getExamenGenitourinario() {
        return examenGenitourinario;
    }

    public EvolucionPaciente examenGenitourinario(String examenGenitourinario) {
        this.examenGenitourinario = examenGenitourinario;
        return this;
    }

    public void setExamenGenitourinario(String examenGenitourinario) {
        this.examenGenitourinario = examenGenitourinario;
    }

    public String getExamenNeurologico() {
        return examenNeurologico;
    }

    public EvolucionPaciente examenNeurologico(String examenNeurologico) {
        this.examenNeurologico = examenNeurologico;
        return this;
    }

    public void setExamenNeurologico(String examenNeurologico) {
        this.examenNeurologico = examenNeurologico;
    }

    public String getExamenArticular() {
        return examenArticular;
    }

    public EvolucionPaciente examenArticular(String examenArticular) {
        this.examenArticular = examenArticular;
        return this;
    }

    public void setExamenArticular(String examenArticular) {
        this.examenArticular = examenArticular;
    }

    public String getExamenNeuromuscular() {
        return examenNeuromuscular;
    }

    public EvolucionPaciente examenNeuromuscular(String examenNeuromuscular) {
        this.examenNeuromuscular = examenNeuromuscular;
        return this;
    }

    public void setExamenNeuromuscular(String examenNeuromuscular) {
        this.examenNeuromuscular = examenNeuromuscular;
    }

    public String getImpresionesDiagnosticas() {
        return impresionesDiagnosticas;
    }

    public EvolucionPaciente impresionesDiagnosticas(String impresionesDiagnosticas) {
        this.impresionesDiagnosticas = impresionesDiagnosticas;
        return this;
    }

    public void setImpresionesDiagnosticas(String impresionesDiagnosticas) {
        this.impresionesDiagnosticas = impresionesDiagnosticas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public EvolucionPaciente tratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
        return this;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public EvolucionPaciente recomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
        return this;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public LocalDate getProximaConsulta() {
        return proximaConsulta;
    }

    public EvolucionPaciente proximaConsulta(LocalDate proximaConsulta) {
        this.proximaConsulta = proximaConsulta;
        return this;
    }

    public void setProximaConsulta(LocalDate proximaConsulta) {
        this.proximaConsulta = proximaConsulta;
    }

    public String getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public EvolucionPaciente frecuenciaCardiaca(String frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        return this;
    }

    public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public String getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public EvolucionPaciente frecuenciaRespiratoria(String frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        return this;
    }

    public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public String getExamenMental() {
        return examenMental;
    }

    public EvolucionPaciente examenMental(String examenMental) {
        this.examenMental = examenMental;
        return this;
    }

    public void setExamenMental(String examenMental) {
        this.examenMental = examenMental;
    }

    public String getInformacionRelevante() {
        return informacionRelevante;
    }

    public EvolucionPaciente informacionRelevante(String informacionRelevante) {
        this.informacionRelevante = informacionRelevante;
        return this;
    }

    public void setInformacionRelevante(String informacionRelevante) {
        this.informacionRelevante = informacionRelevante;
    }

    public String getRecomendacionesGenerales() {
        return recomendacionesGenerales;
    }

    public EvolucionPaciente recomendacionesGenerales(String recomendacionesGenerales) {
        this.recomendacionesGenerales = recomendacionesGenerales;
        return this;
    }

    public void setRecomendacionesGenerales(String recomendacionesGenerales) {
        this.recomendacionesGenerales = recomendacionesGenerales;
    }

    public String getRecipe() {
        return recipe;
    }

    public EvolucionPaciente recipe(String recipe) {
        this.recipe = recipe;
        return this;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public EvolucionPaciente indicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
        return this;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public Cita getCita() {
        return cita;
    }

    public EvolucionPaciente cita(Cita cita) {
        this.cita = cita;
        return this;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
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
        EvolucionPaciente evolucionPaciente = (EvolucionPaciente) o;
        if (evolucionPaciente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evolucionPaciente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvolucionPaciente{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", peso='" + getPeso() + "'" +
            ", tAAcostada='" + gettAAcostada() + "'" +
            ", tASentada='" + gettASentada() + "'" +
            ", tAParada='" + gettAParada() + "'" +
            ", talla='" + getTalla() + "'" +
            ", imc='" + getImc() + "'" +
            ", firma='" + getFirma() + "'" +
            ", nuevosSintomas='" + getNuevosSintomas() + "'" +
            ", lateralidadMano='" + getLateralidadMano() + "'" +
            ", lateralidadPierna='" + getLateralidadPierna() + "'" +
            ", lateralidadOjo='" + getLateralidadOjo() + "'" +
            ", lateralidadOido='" + getLateralidadOido() + "'" +
            ", examenPiel='" + getExamenPiel() + "'" +
            ", examenCabeza='" + getExamenCabeza() + "'" +
            ", examenOjos='" + getExamenOjos() + "'" +
            ", examenOrl='" + getExamenOrl() + "'" +
            ", examenCuello='" + getExamenCuello() + "'" +
            ", examenCardiovascular='" + getExamenCardiovascular() + "'" +
            ", examenPulmonar='" + getExamenPulmonar() + "'" +
            ", examenAbdomen='" + getExamenAbdomen() + "'" +
            ", examenGenitourinario='" + getExamenGenitourinario() + "'" +
            ", examenNeurologico='" + getExamenNeurologico() + "'" +
            ", examenArticular='" + getExamenArticular() + "'" +
            ", examenNeuromuscular='" + getExamenNeuromuscular() + "'" +
            ", impresionesDiagnosticas='" + getImpresionesDiagnosticas() + "'" +
            ", tratamiento='" + getTratamiento() + "'" +
            ", recomendaciones='" + getRecomendaciones() + "'" +
            ", proximaConsulta='" + getProximaConsulta() + "'" +
            ", frecuenciaCardiaca='" + getFrecuenciaCardiaca() + "'" +
            ", frecuenciaRespiratoria='" + getFrecuenciaRespiratoria() + "'" +
            ", examenMental='" + getExamenMental() + "'" +
            ", informacionRelevante='" + getInformacionRelevante() + "'" +
            ", recomendacionesGenerales='" + getRecomendacionesGenerales() + "'" +
            ", recipe='" + getRecipe() + "'" +
            ", indicaciones='" + getIndicaciones() + "'" +
            "}";
    }

    public List<ExamenComplementario> getExamenesComplementarios() {
        return examenesComplementarios;
    }

    public void setExamenesComplementarios(List<ExamenComplementario> examenesComplementarios) {
        this.examenesComplementarios = examenesComplementarios;
    }

    public String getInformeArchivo() {
        return informeArchivo;
    }

    public void setInformeArchivo(String informeArchivo) {
        this.informeArchivo = informeArchivo;
    }

    public String getRecipeArchivo() {
        return recipeArchivo;
    }

    public void setRecipeArchivo(String recipeArchivo) {
        this.recipeArchivo = recipeArchivo;
    }

    public String getIndicacionesArchivo() {
        return indicacionesArchivo;
    }

    public void setIndicacionesArchivo(String indicacionesArchivo) {
        this.indicacionesArchivo = indicacionesArchivo;
    }

    public String getExamenesIndicados() {
        return examenesIndicados;
    }

    public void setExamenesIndicados(String examenesIndicados) {
        this.examenesIndicados = examenesIndicados;
    }
}
