package com.js.movies.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @Column(name = "ID_PELICULA", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL_")
    private Perfile idPerfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GENERO")
    private Genero idGenero;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "AUTOR", nullable = false, length = 150)
    private String autor;

    @Column(name = "DURACION", nullable = false)
    private Float duracion;

    @Lob
    @Column(name = "SINOPSIS", nullable = false)
    private String sinopsis;

    @Column(name = "IDIOMA", nullable = false, length = 20)
    private String idioma;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado = false;

    @Column(name = "FECHA_ESTRENO", nullable = false)
    private LocalDate fechaEstreno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfile getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfile idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

}