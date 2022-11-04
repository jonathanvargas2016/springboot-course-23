package com.js.movies.modelo;

import javax.persistence.*;

@Entity
@Table(name = "detalles")
public class Detalle {
    @Id
    @Column(name = "ID_DETALLES")
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