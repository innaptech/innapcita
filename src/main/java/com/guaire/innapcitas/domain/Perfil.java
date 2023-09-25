package com.guaire.innapcitas.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Perfil.
 */
@Entity
@Table(name = "perfil")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "sello")
    private byte[] sello;

    @Column(name = "sello_content_type")
    private String selloContentType;

    @Column(name = "profesion")
    private String profesion;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "experiencia")
    private String experiencia;

    @Column(name = "reconocimientos")
    private String reconocimientos;

    @Column(name = "actividad_profesional_actual")
    private String actividadProfesionalActual;

    @Column(name = "colegiaturas_asociaciones")
    private String colegiaturasAsociaciones;

    @Column(name = "areas_de_interes")
    private String areasDeInteres;

    @Column(name = "rif")
    private String rif;

    @Column(name = "institucion")
    private String institucion;

    @Lob
    @Column(name = "logo_institucion")
    private byte[] logoInstitucion;

    @Column(name = "logo_institucion_content_type")
    private String logoInstitucionContentType;

    @Column(name = "otras_instituciones")
    private String otrasInstituciones;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "titulo_abreviado")
    private String titulo_abreviado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @ManyToOne
    @JsonIgnoreProperties("perfils")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public Perfil foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public Perfil fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Perfil descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getSello() {
        return sello;
    }

    public Perfil sello(byte[] sello) {
        this.sello = sello;
        return this;
    }

    public void setSello(byte[] sello) {
        this.sello = sello;
    }

    public String getSelloContentType() {
        return selloContentType;
    }

    public Perfil selloContentType(String selloContentType) {
        this.selloContentType = selloContentType;
        return this;
    }

    public void setSelloContentType(String selloContentType) {
        this.selloContentType = selloContentType;
    }

    public String getProfesion() {
        return profesion;
    }

    public Perfil profesion(String profesion) {
        this.profesion = profesion;
        return this;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEmail() {
        return email;
    }

    public Perfil email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Perfil telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Perfil direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public Perfil experiencia(String experiencia) {
        this.experiencia = experiencia;
        return this;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getReconocimientos() {
        return reconocimientos;
    }

    public Perfil reconocimientos(String reconocimientos) {
        this.reconocimientos = reconocimientos;
        return this;
    }

    public void setReconocimientos(String reconocimientos) {
        this.reconocimientos = reconocimientos;
    }

    public String getActividadProfesionalActual() {
        return actividadProfesionalActual;
    }

    public Perfil actividadProfesionalActual(String actividadProfesionalActual) {
        this.actividadProfesionalActual = actividadProfesionalActual;
        return this;
    }

    public void setActividadProfesionalActual(String actividadProfesionalActual) {
        this.actividadProfesionalActual = actividadProfesionalActual;
    }

    public String getColegiaturasAsociaciones() {
        return colegiaturasAsociaciones;
    }

    public Perfil colegiaturasAsociaciones(String colegiaturasAsociaciones) {
        this.colegiaturasAsociaciones = colegiaturasAsociaciones;
        return this;
    }

    public void setColegiaturasAsociaciones(String colegiaturasAsociaciones) {
        this.colegiaturasAsociaciones = colegiaturasAsociaciones;
    }

    public String getAreasDeInteres() {
        return areasDeInteres;
    }

    public Perfil areasDeInteres(String areasDeInteres) {
        this.areasDeInteres = areasDeInteres;
        return this;
    }

    public void setAreasDeInteres(String areasDeInteres) {
        this.areasDeInteres = areasDeInteres;
    }

    public String getRif() {
        return rif;
    }

    public Perfil rif(String rif) {
        this.rif = rif;
        return this;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getInstitucion() {
        return institucion;
    }

    public Perfil institucion(String institucion) {
        this.institucion = institucion;
        return this;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public byte[] getLogoInstitucion() {
        return logoInstitucion;
    }

    public Perfil logoInstitucion(byte[] logoInstitucion) {
        this.logoInstitucion = logoInstitucion;
        return this;
    }

    public void setLogoInstitucion(byte[] logoInstitucion) {
        this.logoInstitucion = logoInstitucion;
    }

    public String getLogoInstitucionContentType() {
        return logoInstitucionContentType;
    }

    public Perfil logoInstitucionContentType(String logoInstitucionContentType) {
        this.logoInstitucionContentType = logoInstitucionContentType;
        return this;
    }

    public void setLogoInstitucionContentType(String logoInstitucionContentType) {
        this.logoInstitucionContentType = logoInstitucionContentType;
    }

    public String getOtrasInstituciones() {
        return otrasInstituciones;
    }

    public Perfil otrasInstituciones(String otrasInstituciones) {
        this.otrasInstituciones = otrasInstituciones;
        return this;
    }

    public void setOtrasInstituciones(String otrasInstituciones) {
        this.otrasInstituciones = otrasInstituciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public Perfil titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public User getUser() {
        return user;
    }

    public Perfil user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        Perfil perfil = (Perfil) o;
        if (perfil.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), perfil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Perfil{" +
            "id=" + getId() +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", sello='" + getSello() + "'" +
            ", selloContentType='" + getSelloContentType() + "'" +
            ", profesion='" + getProfesion() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", experiencia='" + getExperiencia() + "'" +
            ", reconocimientos='" + getReconocimientos() + "'" +
            ", actividadProfesionalActual='" + getActividadProfesionalActual() + "'" +
            ", colegiaturasAsociaciones='" + getColegiaturasAsociaciones() + "'" +
            ", areasDeInteres='" + getAreasDeInteres() + "'" +
            ", rif='" + getRif() + "'" +
            ", institucion='" + getInstitucion() + "'" +
            ", logoInstitucion='" + getLogoInstitucion() + "'" +
            ", logoInstitucionContentType='" + getLogoInstitucionContentType() + "'" +
            ", otrasInstituciones='" + getOtrasInstituciones() + "'" +
            ", titulo='" + getTitulo() + "'" +
            "}";
    }

    public String getTitulo_abreviado() {
        return titulo_abreviado;
    }

    public void setTitulo_abreviado(String titulo_abreviado) {
        this.titulo_abreviado = titulo_abreviado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
