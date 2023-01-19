package com.js.movies.dto;

import com.js.movies.modelo.Genero;
import com.js.movies.modelo.PagosEvento;

import javax.persistence.*;

public class PeliculaDTO {

    private Integer id;

    private Integer idPagoEvento;


    private String idGenero;

    private String nombre;

    private Float duracion;


    private String resumen;

    private String idioma;

    private Short estado;

    private Integer calificacion;

    public PeliculaDTO() {
    }

    public PeliculaDTO(Integer id, Integer idPagoEvento, String idGenero, String nombre, Float duracion, String resumen, String idioma, Short estado, Integer calificacion) {
        this.id = id;
        this.idPagoEvento = idPagoEvento;
        this.idGenero = idGenero;
        this.nombre = nombre;
        this.duracion = duracion;
        this.resumen = resumen;
        this.idioma = idioma;
        this.estado = estado;
        this.calificacion = calificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPagoEvento() {
        return idPagoEvento;
    }

    public void setIdPagoEvento(Integer idPagoEvento) {
        this.idPagoEvento = idPagoEvento;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
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

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
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

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

}
