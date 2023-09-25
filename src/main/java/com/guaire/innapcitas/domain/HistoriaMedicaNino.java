package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A HistoriaMedicaNino.
 */
@Entity
@Table(name = "historia_medica_nino")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistoriaMedicaNino implements Serializable {

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

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "email")
    private String email;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "abuelos_viven")
    private Boolean abuelosViven;

    @Column(name = "edad_abuelos")
    private String edadAbuelos;

    @Column(name = "numero_abuelos")
    private String numeroAbuelos;

    @Column(name = "enfermedad_abuelos")
    private String enfermedadAbuelos;

    @Column(name = "tios_viven")
    private Boolean tiosViven;

    @Column(name = "edad_tios")
    private String edadTios;

    @Column(name = "numero_tios")
    private String numeroTios;

    @Column(name = "enfermedad_tios")
    private String enfermedadTios;

    @Column(name = "padre_vive")
    private Boolean padreVive;

    @Column(name = "edad_padre")
    private String edadPadre;

    @Column(name = "numero_padre")
    private String numeroPadre;

    @Column(name = "enfermedad_padre")
    private String enfermedadPadre;

    @Column(name = "madre_vive")
    private Boolean madreVive;

    @Column(name = "edad_madre")
    private String edadMadre;

    @Column(name = "numero_madre")
    private String numeroMadre;

    @Column(name = "enfermedad_madre")
    private String enfermedadMadre;

    @Column(name = "hermanos_viven")
    private Boolean hermanosViven;

    @Column(name = "edad_hermanos")
    private String edadHermanos;

    @Column(name = "numero_hermanos")
    private String numeroHermanos;

    @Column(name = "enfermedad_hermanos")
    private String enfermedadHermanos;

    @Column(name = "esposo_vive")
    private Boolean esposoVive;

    @Column(name = "edad_esposo")
    private String edadEsposo;

    @Column(name = "numero_esposo")
    private String numeroEsposo;

    @Column(name = "enfermedad_esposo")
    private String enfermedadEsposo;

    @Column(name = "sarampion")
    private Boolean sarampion;

    @Column(name = "renales")
    private Boolean renales;

    @Column(name = "vertigos")
    private Boolean vertigos;

    @Column(name = "cardiacos")
    private Boolean cardiacos;

    @Column(name = "dolor_de_cabeza")
    private Boolean dolorDeCabeza;

    @Column(name = "lechina")
    private Boolean lechina;

    @Column(name = "alergias")
    private Boolean alergias;

    @Column(name = "diarreas")
    private Boolean diarreas;

    @Column(name = "varices")
    private Boolean varices;

    @Column(name = "hipertension_arterial")
    private Boolean hipertensionArterial;

    @Column(name = "rubeola")
    private Boolean rubeola;

    @Column(name = "neurologicos")
    private Boolean neurologicos;

    @Column(name = "otorrinolaringologos")
    private Boolean otorrinolaringologos;

    @Column(name = "catarros")
    private Boolean catarros;

    @Column(name = "enfermedad_mental")
    private Boolean enfermedadMental;

    @Column(name = "parotiditis")
    private Boolean parotiditis;

    @Column(name = "sobrepeso")
    private Boolean sobrepeso;

    @Column(name = "reumaticos")
    private Boolean reumaticos;

    @Column(name = "infecciones")
    private Boolean infecciones;

    @Column(name = "intervencion_quirurgica")
    private Boolean intervencionQuirurgica;

    @Column(name = "parasitosis")
    private Boolean parasitosis;

    @Column(name = "epilepsia")
    private Boolean epilepsia;

    @Column(name = "dermatologicos")
    private Boolean dermatologicos;

    @Column(name = "pulmonares")
    private Boolean pulmonares;

    @Column(name = "trastornos_del_aprendizaje")
    private Boolean trastornosDelAprendizaje;

    @Column(name = "tiroides")
    private Boolean tiroides;

    @Column(name = "asma")
    private Boolean asma;

    @Column(name = "dolor_de_columna")
    private Boolean dolorDeColumna;

    @Column(name = "tuberculosis")
    private Boolean tuberculosis;

    @Column(name = "traumatismos_craneoencefalicos")
    private Boolean traumatismosCraneoencefalicos;

    @Column(name = "mareos")
    private Boolean mareos;

    @Column(name = "desmayos")
    private Boolean desmayos;

    @Column(name = "muscular_o_fractura")
    private Boolean muscularOFractura;

    @Column(name = "intestinales")
    private Boolean intestinales;

    @Column(name = "enfermedad_de_transmision_sexual")
    private Boolean enfermedadDeTransmisionSexual;

    @Column(name = "estomacales")
    private Boolean estomacales;

    @Column(name = "higado")
    private Boolean higado;

    @Column(name = "colesterol_trigliceridos")
    private Boolean colesterolTrigliceridos;

    @Column(name = "enfermedad_y_ano_del_diagnostico_1")
    private String enfermedadYAnoDelDiagnostico1;

    @Column(name = "tratamiento_indicado_o_intervencion_quirurgica_1")
    private String tratamientoIndicadoOIntervencionQuirurgica1;

    @Column(name = "enfermedad_y_ano_del_diagnostico_2")
    private String enfermedadYAnoDelDiagnostico2;

    @Column(name = "tratamiento_indicado_o_intervencion_quirurgica_2")
    private String tratamientoIndicadoOIntervencionQuirurgica2;

    @Column(name = "enfermedad_y_ano_del_diagnostico_3")
    private String enfermedadYAnoDelDiagnostico3;

    @Column(name = "tratamiento_indicado_o_intervencion_quirurgica_3")
    private String tratamientoIndicadoOIntervencionQuirurgica3;

    @Column(name = "medicamento_1")
    private String medicamento1;

    @Column(name = "dosis_diaria_1")
    private String dosisDiaria1;

    @Column(name = "medicamento_2")
    private String medicamento2;

    @Column(name = "dosis_diaria_2")
    private String dosisDiaria2;

    @Column(name = "medicamento_3")
    private String medicamento3;

    @Column(name = "dosis_diaria_3")
    private String dosisDiaria3;

    @Column(name = "notas")
    private String notas;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("historiaMedicaNinos")
    private User paciente;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Long evolucionId;

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

    public HistoriaMedicaNino primerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
        return this;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public HistoriaMedicaNino primerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
        return this;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getEmail() {
        return email;
    }

    public HistoriaMedicaNino email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public HistoriaMedicaNino cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Boolean isAbuelosViven() {
        return abuelosViven;
    }

    public HistoriaMedicaNino abuelosViven(Boolean abuelosViven) {
        this.abuelosViven = abuelosViven;
        return this;
    }

    public void setAbuelosViven(Boolean abuelosViven) {
        this.abuelosViven = abuelosViven;
    }

    public String getEdadAbuelos() {
        return edadAbuelos;
    }

    public HistoriaMedicaNino edadAbuelos(String edadAbuelos) {
        this.edadAbuelos = edadAbuelos;
        return this;
    }

    public void setEdadAbuelos(String edadAbuelos) {
        this.edadAbuelos = edadAbuelos;
    }

    public String getNumeroAbuelos() {
        return numeroAbuelos;
    }

    public HistoriaMedicaNino numeroAbuelos(String numeroAbuelos) {
        this.numeroAbuelos = numeroAbuelos;
        return this;
    }

    public void setNumeroAbuelos(String numeroAbuelos) {
        this.numeroAbuelos = numeroAbuelos;
    }

    public String getEnfermedadAbuelos() {
        return enfermedadAbuelos;
    }

    public HistoriaMedicaNino enfermedadAbuelos(String enfermedadAbuelos) {
        this.enfermedadAbuelos = enfermedadAbuelos;
        return this;
    }

    public void setEnfermedadAbuelos(String enfermedadAbuelos) {
        this.enfermedadAbuelos = enfermedadAbuelos;
    }

    public Boolean isTiosViven() {
        return tiosViven;
    }

    public HistoriaMedicaNino tiosViven(Boolean tiosViven) {
        this.tiosViven = tiosViven;
        return this;
    }

    public void setTiosViven(Boolean tiosViven) {
        this.tiosViven = tiosViven;
    }

    public String getEdadTios() {
        return edadTios;
    }

    public HistoriaMedicaNino edadTios(String edadTios) {
        this.edadTios = edadTios;
        return this;
    }

    public void setEdadTios(String edadTios) {
        this.edadTios = edadTios;
    }

    public String getNumeroTios() {
        return numeroTios;
    }

    public HistoriaMedicaNino numeroTios(String numeroTios) {
        this.numeroTios = numeroTios;
        return this;
    }

    public void setNumeroTios(String numeroTios) {
        this.numeroTios = numeroTios;
    }

    public String getEnfermedadTios() {
        return enfermedadTios;
    }

    public HistoriaMedicaNino enfermedadTios(String enfermedadTios) {
        this.enfermedadTios = enfermedadTios;
        return this;
    }

    public void setEnfermedadTios(String enfermedadTios) {
        this.enfermedadTios = enfermedadTios;
    }

    public Boolean isPadreVive() {
        return padreVive;
    }

    public HistoriaMedicaNino padreVive(Boolean padreVive) {
        this.padreVive = padreVive;
        return this;
    }

    public void setPadreVive(Boolean padreVive) {
        this.padreVive = padreVive;
    }

    public String getEdadPadre() {
        return edadPadre;
    }

    public HistoriaMedicaNino edadPadre(String edadPadre) {
        this.edadPadre = edadPadre;
        return this;
    }

    public void setEdadPadre(String edadPadre) {
        this.edadPadre = edadPadre;
    }

    public String getNumeroPadre() {
        return numeroPadre;
    }

    public HistoriaMedicaNino numeroPadre(String numeroPadre) {
        this.numeroPadre = numeroPadre;
        return this;
    }

    public void setNumeroPadre(String numeroPadre) {
        this.numeroPadre = numeroPadre;
    }

    public String getEnfermedadPadre() {
        return enfermedadPadre;
    }

    public HistoriaMedicaNino enfermedadPadre(String enfermedadPadre) {
        this.enfermedadPadre = enfermedadPadre;
        return this;
    }

    public void setEnfermedadPadre(String enfermedadPadre) {
        this.enfermedadPadre = enfermedadPadre;
    }

    public Boolean isMadreVive() {
        return madreVive;
    }

    public HistoriaMedicaNino madreVive(Boolean madreVive) {
        this.madreVive = madreVive;
        return this;
    }

    public void setMadreVive(Boolean madreVive) {
        this.madreVive = madreVive;
    }

    public String getEdadMadre() {
        return edadMadre;
    }

    public HistoriaMedicaNino edadMadre(String edadMadre) {
        this.edadMadre = edadMadre;
        return this;
    }

    public void setEdadMadre(String edadMadre) {
        this.edadMadre = edadMadre;
    }

    public String getNumeroMadre() {
        return numeroMadre;
    }

    public HistoriaMedicaNino numeroMadre(String numeroMadre) {
        this.numeroMadre = numeroMadre;
        return this;
    }

    public void setNumeroMadre(String numeroMadre) {
        this.numeroMadre = numeroMadre;
    }

    public String getEnfermedadMadre() {
        return enfermedadMadre;
    }

    public HistoriaMedicaNino enfermedadMadre(String enfermedadMadre) {
        this.enfermedadMadre = enfermedadMadre;
        return this;
    }

    public void setEnfermedadMadre(String enfermedadMadre) {
        this.enfermedadMadre = enfermedadMadre;
    }

    public Boolean isHermanosViven() {
        return hermanosViven;
    }

    public HistoriaMedicaNino hermanosViven(Boolean hermanosViven) {
        this.hermanosViven = hermanosViven;
        return this;
    }

    public void setHermanosViven(Boolean hermanosViven) {
        this.hermanosViven = hermanosViven;
    }

    public String getEdadHermanos() {
        return edadHermanos;
    }

    public HistoriaMedicaNino edadHermanos(String edadHermanos) {
        this.edadHermanos = edadHermanos;
        return this;
    }

    public void setEdadHermanos(String edadHermanos) {
        this.edadHermanos = edadHermanos;
    }

    public String getNumeroHermanos() {
        return numeroHermanos;
    }

    public HistoriaMedicaNino numeroHermanos(String numeroHermanos) {
        this.numeroHermanos = numeroHermanos;
        return this;
    }

    public void setNumeroHermanos(String numeroHermanos) {
        this.numeroHermanos = numeroHermanos;
    }

    public String getEnfermedadHermanos() {
        return enfermedadHermanos;
    }

    public HistoriaMedicaNino enfermedadHermanos(String enfermedadHermanos) {
        this.enfermedadHermanos = enfermedadHermanos;
        return this;
    }

    public void setEnfermedadHermanos(String enfermedadHermanos) {
        this.enfermedadHermanos = enfermedadHermanos;
    }

    public Boolean isEsposoVive() {
        return esposoVive;
    }

    public HistoriaMedicaNino esposoVive(Boolean esposoVive) {
        this.esposoVive = esposoVive;
        return this;
    }

    public void setEsposoVive(Boolean esposoVive) {
        this.esposoVive = esposoVive;
    }

    public String getEdadEsposo() {
        return edadEsposo;
    }

    public HistoriaMedicaNino edadEsposo(String edadEsposo) {
        this.edadEsposo = edadEsposo;
        return this;
    }

    public void setEdadEsposo(String edadEsposo) {
        this.edadEsposo = edadEsposo;
    }

    public String getNumeroEsposo() {
        return numeroEsposo;
    }

    public HistoriaMedicaNino numeroEsposo(String numeroEsposo) {
        this.numeroEsposo = numeroEsposo;
        return this;
    }

    public void setNumeroEsposo(String numeroEsposo) {
        this.numeroEsposo = numeroEsposo;
    }

    public String getEnfermedadEsposo() {
        return enfermedadEsposo;
    }

    public HistoriaMedicaNino enfermedadEsposo(String enfermedadEsposo) {
        this.enfermedadEsposo = enfermedadEsposo;
        return this;
    }

    public void setEnfermedadEsposo(String enfermedadEsposo) {
        this.enfermedadEsposo = enfermedadEsposo;
    }

    public Boolean isSarampion() {
        return sarampion;
    }

    public HistoriaMedicaNino sarampion(Boolean sarampion) {
        this.sarampion = sarampion;
        return this;
    }

    public void setSarampion(Boolean sarampion) {
        this.sarampion = sarampion;
    }

    public Boolean isRenales() {
        return renales;
    }

    public HistoriaMedicaNino renales(Boolean renales) {
        this.renales = renales;
        return this;
    }

    public void setRenales(Boolean renales) {
        this.renales = renales;
    }

    public Boolean isVertigos() {
        return vertigos;
    }

    public HistoriaMedicaNino vertigos(Boolean vertigos) {
        this.vertigos = vertigos;
        return this;
    }

    public void setVertigos(Boolean vertigos) {
        this.vertigos = vertigos;
    }

    public Boolean isCardiacos() {
        return cardiacos;
    }

    public HistoriaMedicaNino cardiacos(Boolean cardiacos) {
        this.cardiacos = cardiacos;
        return this;
    }

    public void setCardiacos(Boolean cardiacos) {
        this.cardiacos = cardiacos;
    }

    public Boolean isDolorDeCabeza() {
        return dolorDeCabeza;
    }

    public HistoriaMedicaNino dolorDeCabeza(Boolean dolorDeCabeza) {
        this.dolorDeCabeza = dolorDeCabeza;
        return this;
    }

    public void setDolorDeCabeza(Boolean dolorDeCabeza) {
        this.dolorDeCabeza = dolorDeCabeza;
    }

    public Boolean isLechina() {
        return lechina;
    }

    public HistoriaMedicaNino lechina(Boolean lechina) {
        this.lechina = lechina;
        return this;
    }

    public void setLechina(Boolean lechina) {
        this.lechina = lechina;
    }

    public Boolean isAlergias() {
        return alergias;
    }

    public HistoriaMedicaNino alergias(Boolean alergias) {
        this.alergias = alergias;
        return this;
    }

    public void setAlergias(Boolean alergias) {
        this.alergias = alergias;
    }

    public Boolean isDiarreas() {
        return diarreas;
    }

    public HistoriaMedicaNino diarreas(Boolean diarreas) {
        this.diarreas = diarreas;
        return this;
    }

    public void setDiarreas(Boolean diarreas) {
        this.diarreas = diarreas;
    }

    public Boolean isVarices() {
        return varices;
    }

    public HistoriaMedicaNino varices(Boolean varices) {
        this.varices = varices;
        return this;
    }

    public void setVarices(Boolean varices) {
        this.varices = varices;
    }

    public Boolean isHipertensionArterial() {
        return hipertensionArterial;
    }

    public HistoriaMedicaNino hipertensionArterial(Boolean hipertensionArterial) {
        this.hipertensionArterial = hipertensionArterial;
        return this;
    }

    public void setHipertensionArterial(Boolean hipertensionArterial) {
        this.hipertensionArterial = hipertensionArterial;
    }

    public Boolean isRubeola() {
        return rubeola;
    }

    public HistoriaMedicaNino rubeola(Boolean rubeola) {
        this.rubeola = rubeola;
        return this;
    }

    public void setRubeola(Boolean rubeola) {
        this.rubeola = rubeola;
    }

    public Boolean isNeurologicos() {
        return neurologicos;
    }

    public HistoriaMedicaNino neurologicos(Boolean neurologicos) {
        this.neurologicos = neurologicos;
        return this;
    }

    public void setNeurologicos(Boolean neurologicos) {
        this.neurologicos = neurologicos;
    }

    public Boolean isOtorrinolaringologos() {
        return otorrinolaringologos;
    }

    public HistoriaMedicaNino otorrinolaringologos(Boolean otorrinolaringologos) {
        this.otorrinolaringologos = otorrinolaringologos;
        return this;
    }

    public void setOtorrinolaringologos(Boolean otorrinolaringologos) {
        this.otorrinolaringologos = otorrinolaringologos;
    }

    public Boolean isCatarros() {
        return catarros;
    }

    public HistoriaMedicaNino catarros(Boolean catarros) {
        this.catarros = catarros;
        return this;
    }

    public void setCatarros(Boolean catarros) {
        this.catarros = catarros;
    }

    public Boolean isEnfermedadMental() {
        return enfermedadMental;
    }

    public HistoriaMedicaNino enfermedadMental(Boolean enfermedadMental) {
        this.enfermedadMental = enfermedadMental;
        return this;
    }

    public void setEnfermedadMental(Boolean enfermedadMental) {
        this.enfermedadMental = enfermedadMental;
    }

    public Boolean isParotiditis() {
        return parotiditis;
    }

    public HistoriaMedicaNino parotiditis(Boolean parotiditis) {
        this.parotiditis = parotiditis;
        return this;
    }

    public void setParotiditis(Boolean parotiditis) {
        this.parotiditis = parotiditis;
    }

    public Boolean isSobrepeso() {
        return sobrepeso;
    }

    public HistoriaMedicaNino sobrepeso(Boolean sobrepeso) {
        this.sobrepeso = sobrepeso;
        return this;
    }

    public void setSobrepeso(Boolean sobrepeso) {
        this.sobrepeso = sobrepeso;
    }

    public Boolean isReumaticos() {
        return reumaticos;
    }

    public HistoriaMedicaNino reumaticos(Boolean reumaticos) {
        this.reumaticos = reumaticos;
        return this;
    }

    public void setReumaticos(Boolean reumaticos) {
        this.reumaticos = reumaticos;
    }

    public Boolean isInfecciones() {
        return infecciones;
    }

    public HistoriaMedicaNino infecciones(Boolean infecciones) {
        this.infecciones = infecciones;
        return this;
    }

    public void setInfecciones(Boolean infecciones) {
        this.infecciones = infecciones;
    }

    public Boolean isIntervencionQuirurgica() {
        return intervencionQuirurgica;
    }

    public HistoriaMedicaNino intervencionQuirurgica(Boolean intervencionQuirurgica) {
        this.intervencionQuirurgica = intervencionQuirurgica;
        return this;
    }

    public void setIntervencionQuirurgica(Boolean intervencionQuirurgica) {
        this.intervencionQuirurgica = intervencionQuirurgica;
    }

    public Boolean isParasitosis() {
        return parasitosis;
    }

    public HistoriaMedicaNino parasitosis(Boolean parasitosis) {
        this.parasitosis = parasitosis;
        return this;
    }

    public void setParasitosis(Boolean parasitosis) {
        this.parasitosis = parasitosis;
    }

    public Boolean isEpilepsia() {
        return epilepsia;
    }

    public HistoriaMedicaNino epilepsia(Boolean epilepsia) {
        this.epilepsia = epilepsia;
        return this;
    }

    public void setEpilepsia(Boolean epilepsia) {
        this.epilepsia = epilepsia;
    }

    public Boolean isDermatologicos() {
        return dermatologicos;
    }

    public HistoriaMedicaNino dermatologicos(Boolean dermatologicos) {
        this.dermatologicos = dermatologicos;
        return this;
    }

    public void setDermatologicos(Boolean dermatologicos) {
        this.dermatologicos = dermatologicos;
    }

    public Boolean isPulmonares() {
        return pulmonares;
    }

    public HistoriaMedicaNino pulmonares(Boolean pulmonares) {
        this.pulmonares = pulmonares;
        return this;
    }

    public void setPulmonares(Boolean pulmonares) {
        this.pulmonares = pulmonares;
    }

    public Boolean isTrastornosDelAprendizaje() {
        return trastornosDelAprendizaje;
    }

    public HistoriaMedicaNino trastornosDelAprendizaje(Boolean trastornosDelAprendizaje) {
        this.trastornosDelAprendizaje = trastornosDelAprendizaje;
        return this;
    }

    public void setTrastornosDelAprendizaje(Boolean trastornosDelAprendizaje) {
        this.trastornosDelAprendizaje = trastornosDelAprendizaje;
    }

    public Boolean isTiroides() {
        return tiroides;
    }

    public HistoriaMedicaNino tiroides(Boolean tiroides) {
        this.tiroides = tiroides;
        return this;
    }

    public void setTiroides(Boolean tiroides) {
        this.tiroides = tiroides;
    }

    public Boolean isAsma() {
        return asma;
    }

    public HistoriaMedicaNino asma(Boolean asma) {
        this.asma = asma;
        return this;
    }

    public void setAsma(Boolean asma) {
        this.asma = asma;
    }

    public Boolean isDolorDeColumna() {
        return dolorDeColumna;
    }

    public HistoriaMedicaNino dolorDeColumna(Boolean dolorDeColumna) {
        this.dolorDeColumna = dolorDeColumna;
        return this;
    }

    public void setDolorDeColumna(Boolean dolorDeColumna) {
        this.dolorDeColumna = dolorDeColumna;
    }

    public Boolean isTuberculosis() {
        return tuberculosis;
    }

    public HistoriaMedicaNino tuberculosis(Boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
        return this;
    }

    public void setTuberculosis(Boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public Boolean isTraumatismosCraneoencefalicos() {
        return traumatismosCraneoencefalicos;
    }

    public HistoriaMedicaNino traumatismosCraneoencefalicos(Boolean traumatismosCraneoencefalicos) {
        this.traumatismosCraneoencefalicos = traumatismosCraneoencefalicos;
        return this;
    }

    public void setTraumatismosCraneoencefalicos(Boolean traumatismosCraneoencefalicos) {
        this.traumatismosCraneoencefalicos = traumatismosCraneoencefalicos;
    }

    public Boolean isMareos() {
        return mareos;
    }

    public HistoriaMedicaNino mareos(Boolean mareos) {
        this.mareos = mareos;
        return this;
    }

    public void setMareos(Boolean mareos) {
        this.mareos = mareos;
    }

    public Boolean isDesmayos() {
        return desmayos;
    }

    public HistoriaMedicaNino desmayos(Boolean desmayos) {
        this.desmayos = desmayos;
        return this;
    }

    public void setDesmayos(Boolean desmayos) {
        this.desmayos = desmayos;
    }

    public Boolean isMuscularOFractura() {
        return muscularOFractura;
    }

    public HistoriaMedicaNino muscularOFractura(Boolean muscularOFractura) {
        this.muscularOFractura = muscularOFractura;
        return this;
    }

    public void setMuscularOFractura(Boolean muscularOFractura) {
        this.muscularOFractura = muscularOFractura;
    }

    public Boolean isIntestinales() {
        return intestinales;
    }

    public HistoriaMedicaNino intestinales(Boolean intestinales) {
        this.intestinales = intestinales;
        return this;
    }

    public void setIntestinales(Boolean intestinales) {
        this.intestinales = intestinales;
    }

    public Boolean isEnfermedadDeTransmisionSexual() {
        return enfermedadDeTransmisionSexual;
    }

    public HistoriaMedicaNino enfermedadDeTransmisionSexual(Boolean enfermedadDeTransmisionSexual) {
        this.enfermedadDeTransmisionSexual = enfermedadDeTransmisionSexual;
        return this;
    }

    public void setEnfermedadDeTransmisionSexual(Boolean enfermedadDeTransmisionSexual) {
        this.enfermedadDeTransmisionSexual = enfermedadDeTransmisionSexual;
    }

    public Boolean isEstomacales() {
        return estomacales;
    }

    public HistoriaMedicaNino estomacales(Boolean estomacales) {
        this.estomacales = estomacales;
        return this;
    }

    public void setEstomacales(Boolean estomacales) {
        this.estomacales = estomacales;
    }

    public Boolean isHigado() {
        return higado;
    }

    public HistoriaMedicaNino higado(Boolean higado) {
        this.higado = higado;
        return this;
    }

    public void setHigado(Boolean higado) {
        this.higado = higado;
    }

    public Boolean isColesterolTrigliceridos() {
        return colesterolTrigliceridos;
    }

    public HistoriaMedicaNino colesterolTrigliceridos(Boolean colesterolTrigliceridos) {
        this.colesterolTrigliceridos = colesterolTrigliceridos;
        return this;
    }

    public void setColesterolTrigliceridos(Boolean colesterolTrigliceridos) {
        this.colesterolTrigliceridos = colesterolTrigliceridos;
    }

    public String getEnfermedadYAnoDelDiagnostico1() {
        return enfermedadYAnoDelDiagnostico1;
    }

    public HistoriaMedicaNino enfermedadYAnoDelDiagnostico1(String enfermedadYAnoDelDiagnostico1) {
        this.enfermedadYAnoDelDiagnostico1 = enfermedadYAnoDelDiagnostico1;
        return this;
    }

    public void setEnfermedadYAnoDelDiagnostico1(String enfermedadYAnoDelDiagnostico1) {
        this.enfermedadYAnoDelDiagnostico1 = enfermedadYAnoDelDiagnostico1;
    }

    public String getTratamientoIndicadoOIntervencionQuirurgica1() {
        return tratamientoIndicadoOIntervencionQuirurgica1;
    }

    public HistoriaMedicaNino tratamientoIndicadoOIntervencionQuirurgica1(String tratamientoIndicadoOIntervencionQuirurgica1) {
        this.tratamientoIndicadoOIntervencionQuirurgica1 = tratamientoIndicadoOIntervencionQuirurgica1;
        return this;
    }

    public void setTratamientoIndicadoOIntervencionQuirurgica1(String tratamientoIndicadoOIntervencionQuirurgica1) {
        this.tratamientoIndicadoOIntervencionQuirurgica1 = tratamientoIndicadoOIntervencionQuirurgica1;
    }

    public String getEnfermedadYAnoDelDiagnostico2() {
        return enfermedadYAnoDelDiagnostico2;
    }

    public HistoriaMedicaNino enfermedadYAnoDelDiagnostico2(String enfermedadYAnoDelDiagnostico2) {
        this.enfermedadYAnoDelDiagnostico2 = enfermedadYAnoDelDiagnostico2;
        return this;
    }

    public void setEnfermedadYAnoDelDiagnostico2(String enfermedadYAnoDelDiagnostico2) {
        this.enfermedadYAnoDelDiagnostico2 = enfermedadYAnoDelDiagnostico2;
    }

    public String getTratamientoIndicadoOIntervencionQuirurgica2() {
        return tratamientoIndicadoOIntervencionQuirurgica2;
    }

    public HistoriaMedicaNino tratamientoIndicadoOIntervencionQuirurgica2(String tratamientoIndicadoOIntervencionQuirurgica2) {
        this.tratamientoIndicadoOIntervencionQuirurgica2 = tratamientoIndicadoOIntervencionQuirurgica2;
        return this;
    }

    public void setTratamientoIndicadoOIntervencionQuirurgica2(String tratamientoIndicadoOIntervencionQuirurgica2) {
        this.tratamientoIndicadoOIntervencionQuirurgica2 = tratamientoIndicadoOIntervencionQuirurgica2;
    }

    public String getEnfermedadYAnoDelDiagnostico3() {
        return enfermedadYAnoDelDiagnostico3;
    }

    public HistoriaMedicaNino enfermedadYAnoDelDiagnostico3(String enfermedadYAnoDelDiagnostico3) {
        this.enfermedadYAnoDelDiagnostico3 = enfermedadYAnoDelDiagnostico3;
        return this;
    }

    public void setEnfermedadYAnoDelDiagnostico3(String enfermedadYAnoDelDiagnostico3) {
        this.enfermedadYAnoDelDiagnostico3 = enfermedadYAnoDelDiagnostico3;
    }

    public String getTratamientoIndicadoOIntervencionQuirurgica3() {
        return tratamientoIndicadoOIntervencionQuirurgica3;
    }

    public HistoriaMedicaNino tratamientoIndicadoOIntervencionQuirurgica3(String tratamientoIndicadoOIntervencionQuirurgica3) {
        this.tratamientoIndicadoOIntervencionQuirurgica3 = tratamientoIndicadoOIntervencionQuirurgica3;
        return this;
    }

    public void setTratamientoIndicadoOIntervencionQuirurgica3(String tratamientoIndicadoOIntervencionQuirurgica3) {
        this.tratamientoIndicadoOIntervencionQuirurgica3 = tratamientoIndicadoOIntervencionQuirurgica3;
    }

    public String getMedicamento1() {
        return medicamento1;
    }

    public HistoriaMedicaNino medicamento1(String medicamento1) {
        this.medicamento1 = medicamento1;
        return this;
    }

    public void setMedicamento1(String medicamento1) {
        this.medicamento1 = medicamento1;
    }

    public String getDosisDiaria1() {
        return dosisDiaria1;
    }

    public HistoriaMedicaNino dosisDiaria1(String dosisDiaria1) {
        this.dosisDiaria1 = dosisDiaria1;
        return this;
    }

    public void setDosisDiaria1(String dosisDiaria1) {
        this.dosisDiaria1 = dosisDiaria1;
    }

    public String getMedicamento2() {
        return medicamento2;
    }

    public HistoriaMedicaNino medicamento2(String medicamento2) {
        this.medicamento2 = medicamento2;
        return this;
    }

    public void setMedicamento2(String medicamento2) {
        this.medicamento2 = medicamento2;
    }

    public String getDosisDiaria2() {
        return dosisDiaria2;
    }

    public HistoriaMedicaNino dosisDiaria2(String dosisDiaria2) {
        this.dosisDiaria2 = dosisDiaria2;
        return this;
    }

    public void setDosisDiaria2(String dosisDiaria2) {
        this.dosisDiaria2 = dosisDiaria2;
    }

    public String getMedicamento3() {
        return medicamento3;
    }

    public HistoriaMedicaNino medicamento3(String medicamento3) {
        this.medicamento3 = medicamento3;
        return this;
    }

    public void setMedicamento3(String medicamento3) {
        this.medicamento3 = medicamento3;
    }

    public String getDosisDiaria3() {
        return dosisDiaria3;
    }

    public HistoriaMedicaNino dosisDiaria3(String dosisDiaria3) {
        this.dosisDiaria3 = dosisDiaria3;
        return this;
    }

    public void setDosisDiaria3(String dosisDiaria3) {
        this.dosisDiaria3 = dosisDiaria3;
    }

    public String getNotas() {
        return notas;
    }

    public HistoriaMedicaNino notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public User getPaciente() {
        return paciente;
    }

    public HistoriaMedicaNino paciente(User user) {
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
        HistoriaMedicaNino historiaMedicaNino = (HistoriaMedicaNino) o;
        if (historiaMedicaNino.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historiaMedicaNino.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoriaMedicaNino{" +
            "id=" + getId() +
            ", primerNombre='" + getPrimerNombre() + "'" +
            ", primerApellido='" + getPrimerApellido() + "'" +
            ", email='" + getEmail() + "'" +
            ", cedula='" + getCedula() + "'" +
            ", abuelosViven='" + isAbuelosViven() + "'" +
            ", edadAbuelos='" + getEdadAbuelos() + "'" +
            ", numeroAbuelos='" + getNumeroAbuelos() + "'" +
            ", enfermedadAbuelos='" + getEnfermedadAbuelos() + "'" +
            ", tiosViven='" + isTiosViven() + "'" +
            ", edadTios='" + getEdadTios() + "'" +
            ", numeroTios='" + getNumeroTios() + "'" +
            ", enfermedadTios='" + getEnfermedadTios() + "'" +
            ", padreVive='" + isPadreVive() + "'" +
            ", edadPadre='" + getEdadPadre() + "'" +
            ", numeroPadre='" + getNumeroPadre() + "'" +
            ", enfermedadPadre='" + getEnfermedadPadre() + "'" +
            ", madreVive='" + isMadreVive() + "'" +
            ", edadMadre='" + getEdadMadre() + "'" +
            ", numeroMadre='" + getNumeroMadre() + "'" +
            ", enfermedadMadre='" + getEnfermedadMadre() + "'" +
            ", hermanosViven='" + isHermanosViven() + "'" +
            ", edadHermanos='" + getEdadHermanos() + "'" +
            ", numeroHermanos='" + getNumeroHermanos() + "'" +
            ", enfermedadHermanos='" + getEnfermedadHermanos() + "'" +
            ", esposoVive='" + isEsposoVive() + "'" +
            ", edadEsposo='" + getEdadEsposo() + "'" +
            ", numeroEsposo='" + getNumeroEsposo() + "'" +
            ", enfermedadEsposo='" + getEnfermedadEsposo() + "'" +
            ", sarampion='" + isSarampion() + "'" +
            ", renales='" + isRenales() + "'" +
            ", vertigos='" + isVertigos() + "'" +
            ", cardiacos='" + isCardiacos() + "'" +
            ", dolorDeCabeza='" + isDolorDeCabeza() + "'" +
            ", lechina='" + isLechina() + "'" +
            ", alergias='" + isAlergias() + "'" +
            ", diarreas='" + isDiarreas() + "'" +
            ", varices='" + isVarices() + "'" +
            ", hipertensionArterial='" + isHipertensionArterial() + "'" +
            ", rubeola='" + isRubeola() + "'" +
            ", neurologicos='" + isNeurologicos() + "'" +
            ", otorrinolaringologos='" + isOtorrinolaringologos() + "'" +
            ", catarros='" + isCatarros() + "'" +
            ", enfermedadMental='" + isEnfermedadMental() + "'" +
            ", parotiditis='" + isParotiditis() + "'" +
            ", sobrepeso='" + isSobrepeso() + "'" +
            ", reumaticos='" + isReumaticos() + "'" +
            ", infecciones='" + isInfecciones() + "'" +
            ", intervencionQuirurgica='" + isIntervencionQuirurgica() + "'" +
            ", parasitosis='" + isParasitosis() + "'" +
            ", epilepsia='" + isEpilepsia() + "'" +
            ", dermatologicos='" + isDermatologicos() + "'" +
            ", pulmonares='" + isPulmonares() + "'" +
            ", trastornosDelAprendizaje='" + isTrastornosDelAprendizaje() + "'" +
            ", tiroides='" + isTiroides() + "'" +
            ", asma='" + isAsma() + "'" +
            ", dolorDeColumna='" + isDolorDeColumna() + "'" +
            ", tuberculosis='" + isTuberculosis() + "'" +
            ", traumatismosCraneoencefalicos='" + isTraumatismosCraneoencefalicos() + "'" +
            ", mareos='" + isMareos() + "'" +
            ", desmayos='" + isDesmayos() + "'" +
            ", muscularOFractura='" + isMuscularOFractura() + "'" +
            ", intestinales='" + isIntestinales() + "'" +
            ", enfermedadDeTransmisionSexual='" + isEnfermedadDeTransmisionSexual() + "'" +
            ", estomacales='" + isEstomacales() + "'" +
            ", higado='" + isHigado() + "'" +
            ", colesterolTrigliceridos='" + isColesterolTrigliceridos() + "'" +
            ", enfermedadYAnoDelDiagnostico1='" + getEnfermedadYAnoDelDiagnostico1() + "'" +
            ", tratamientoIndicadoOIntervencionQuirurgica1='" + getTratamientoIndicadoOIntervencionQuirurgica1() + "'" +
            ", enfermedadYAnoDelDiagnostico2='" + getEnfermedadYAnoDelDiagnostico2() + "'" +
            ", tratamientoIndicadoOIntervencionQuirurgica2='" + getTratamientoIndicadoOIntervencionQuirurgica2() + "'" +
            ", enfermedadYAnoDelDiagnostico3='" + getEnfermedadYAnoDelDiagnostico3() + "'" +
            ", tratamientoIndicadoOIntervencionQuirurgica3='" + getTratamientoIndicadoOIntervencionQuirurgica3() + "'" +
            ", medicamento1='" + getMedicamento1() + "'" +
            ", dosisDiaria1='" + getDosisDiaria1() + "'" +
            ", medicamento2='" + getMedicamento2() + "'" +
            ", dosisDiaria2='" + getDosisDiaria2() + "'" +
            ", medicamento3='" + getMedicamento3() + "'" +
            ", dosisDiaria3='" + getDosisDiaria3() + "'" +
            ", notas='" + getNotas() + "'" +
            "}";
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Long getEvolucionId() {
        return evolucionId;
    }

    public void setEvolucionId(Long evolucionId) {
        this.evolucionId = evolucionId;
    }
}
