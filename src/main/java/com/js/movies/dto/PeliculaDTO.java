package com.js.movies.dto;

import com.js.movies.modelo.Genero;
import java.io.Serializable;

public class PeliculaDTO {
    private Integer id;

    private String detGenero;
    private String nombre;

    private Float duracion;

    private String sinopsis;

    private String idioma;

    private Short estado;

    private Float raiting;

    public PeliculaDTO(){

    }

    public PeliculaDTO(Integer id, String detGenero, String nombre, Float duracion, String sinopsis, String idioma, Short estado, Float raiting) {
        this.id = id;
        this.detGenero = detGenero;
        this.nombre = nombre;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.idioma = idioma;
        this.estado = estado;
        this.raiting = raiting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdGenero() {
        return detGenero;
    }

    public void setIdGenero(String detGenero) {
        this.detGenero = detGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Float getRaiting() {
        return raiting;
    }

    public void setRaiting(Float raiting) {
        this.raiting = raiting;
    }

}
