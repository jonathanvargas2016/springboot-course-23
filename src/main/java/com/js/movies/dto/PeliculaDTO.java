package com.js.movies.dto;

import com.js.movies.modelo.Detalle;
import com.js.movies.modelo.DetalleCatalogo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

public class PeliculaDTO implements Serializable {

    private static final long serialVersionUID = 6352487410L;

    private Integer id;

    private String idGenero;

    private String idIdioma;

    private String titulo;

    private Float duracion;

    private String sinopsis;

    private Boolean estado = false;

    private Instant fechaEstreno;

    private Integer numeroVistas;


    public PeliculaDTO() {
    }

    public PeliculaDTO(Integer id, String idGenero, String idIdioma, String titulo, Float duracion, String sinopsis, Boolean estado, Instant fechaEstreno, Integer numeroVistas) {
        this.id = id;
        this.idGenero = idGenero;
        this.idIdioma = idIdioma;
        this.titulo = titulo;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.estado = estado;
        this.fechaEstreno = fechaEstreno;
        this.numeroVistas = numeroVistas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Instant getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Instant fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public Integer getNumeroVistas() {
        return numeroVistas;
    }

    public void setNumeroVistas(Integer numeroVistas) {
        this.numeroVistas = numeroVistas;
    }

    @Override
    public String toString() {
        return "Pelicula {" +
                "id=" + id +
                ", idGenero='" + idGenero + '\'' +
                ", idIdioma='" + idIdioma + '\'' +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", sinopsis='" + sinopsis + '\'' +
                ", estado=" + estado +
                ", fechaEstreno=" + fechaEstreno +
                ", numeroVistas=" + numeroVistas +
                '}';
    }
}
