package com.guaire.innapcitas.domain;

public class Tratamiento {

    private String nombre;

    private String indicacion;

    private Boolean esPsicotropico;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEsPsicotropico() {
        return esPsicotropico;
    }

    public void setEsPsicotropico(Boolean esPsicotropico) {
        this.esPsicotropico = esPsicotropico;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }
}
