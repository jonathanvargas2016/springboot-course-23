package com.js.movies.modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalles")
public class Detalle implements Serializable {
    private static final long serialVersionUID = 7596841230L;

    @Id
    @Column(name = "ID_DETALLES", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PELICULA")
    private Pelicula idPelicula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL_")
    private Perfile idPerfil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Perfile getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfile idPerfil) {
        this.idPerfil = idPerfil;
    }

}