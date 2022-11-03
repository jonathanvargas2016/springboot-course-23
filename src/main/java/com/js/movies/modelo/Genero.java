package com.js.movies.modelo;

import javax.persistence.*;

@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GENERO", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE_GENERO", nullable = false, length = 50)
    private String nombreGenero;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}